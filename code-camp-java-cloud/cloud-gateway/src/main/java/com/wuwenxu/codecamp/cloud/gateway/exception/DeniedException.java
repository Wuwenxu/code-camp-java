package com.wuwenxu.codecamp.cloud.gateway.exception;

import lombok.NoArgsConstructor;

/**
 * @author zhangquan
 * @date 2019/5/21
 * 403 授权拒绝
 */
@NoArgsConstructor
public class DeniedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DeniedException(String message) {
    super(message);
  }

  public DeniedException(Throwable cause) {
    super(cause);
  }

  public DeniedException(String message, Throwable cause) {
    super(message, cause);
  }

  public DeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
