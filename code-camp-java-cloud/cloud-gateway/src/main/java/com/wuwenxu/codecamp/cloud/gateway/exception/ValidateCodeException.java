package com.wuwenxu.codecamp.cloud.gateway.exception;

/**
 * @author zhangquan
 * @date 2019/5/21
 */
public class ValidateCodeException extends Exception {
  private static final long serialVersionUID = -7285211528095468156L;

  public ValidateCodeException() {
  }

  public ValidateCodeException(String msg) {
    super(msg);
  }
}
