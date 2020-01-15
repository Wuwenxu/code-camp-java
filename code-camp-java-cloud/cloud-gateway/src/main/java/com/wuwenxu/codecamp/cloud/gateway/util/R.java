package com.wuwenxu.codecamp.cloud.gateway.util;

import com.wuwenxu.codecamp.cloud.gateway.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  @Getter
  @Setter
  private int code = CommonConstants.SUCCESS;

  @Getter
  @Setter
  private String msg = "success";


  @Getter
  @Setter
  private T data;

  public R() {
    super();
  }

  public R(T data) {
    super();
    this.data = data;
  }

  public R(T data, String msg) {
    super();
    this.data = data;
    this.msg = msg;
  }

  public R(Throwable e) {
    super();
    this.msg = e.getMessage();
    this.code = CommonConstants.FAIL;
  }
}
