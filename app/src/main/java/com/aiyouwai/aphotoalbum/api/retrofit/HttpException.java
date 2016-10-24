package com.aiyouwai.aphotoalbum.api.retrofit;

public class HttpException extends RuntimeException {

    private HttpResult result;

    public HttpException(HttpResult result) {
        this.result = result;
    }

    @Override
    public String getMessage() {
        return "接口错误:" + result.resultCode + " -- " + result.resultMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }
}
