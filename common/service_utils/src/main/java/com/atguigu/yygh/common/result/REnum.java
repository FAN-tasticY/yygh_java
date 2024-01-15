package com.atguigu.yygh.common.result;

public enum REnum {
    SUCCESS(20000,true,"成功"),
    ERROR(20001,false,"失败")
    ;

    private Integer code;
    private Boolean flag;
    private String message;

    REnum(Integer code, Boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
