package com.wzw.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),
    ERROR(-1, "error"),
    DRIVER_NOT_EXIST(101, "数据库驱动不存在"),
    SQL_NOT_EXIST(102, "数据库查询语句不存在"),
    TABLE_NOT_EXIST(103, "查询表结构结果为空"),
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
