package com.healthpay.common.exception;

public class BusException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String            msg;                  //错误信息
    private String            errCode;              // 错误代码

    public static void throwException(String value) {
        throw new BusException(value);
    }
    
    public BusException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusException(String errCode, String msg) {
        super(msg);
        this.msg = msg;
        this.errCode = errCode;
    }

    public String getMessage() {
        return msg;
    }

    public String getErrCode() {
        return errCode;
    }
}
