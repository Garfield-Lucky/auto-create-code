package com.wzw.exception;

import com.wzw.enums.ResultEnum;

public class AccException extends RuntimeException {

    private Integer code;

    public AccException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public AccException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
