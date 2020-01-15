package com.wuwenxu.codecamp.cloud.gateway.handler;

import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lengleng
 * @date 2019/2/1
 * 验证码生成逻辑处理类
 */
@Slf4j
@Component
@AllArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {
  private final Producer producer;

  @Override
  public Mono<ServerResponse> handle(ServerRequest serverRequest) {
    //生成验证码
    String        text  = producer.createText();
    BufferedImage image = producer.createImage(text);

    //保存验证码信息
    String randomStr = serverRequest.queryParam("randomStr").get();
    System.out.println("可以存库可以存redis--->" + randomStr);
    // 转换流信息写出
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpeg", os);
    } catch (IOException e) {
      log.error("ImageIO write err", e);
      return Mono.error(e);
    }

    return ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.IMAGE_JPEG)
            .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
  }
}
