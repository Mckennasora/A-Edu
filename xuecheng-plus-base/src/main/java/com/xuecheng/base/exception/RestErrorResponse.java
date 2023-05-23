package com.xuecheng.base.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestErrorResponse implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage = errMessage;
    }

}
