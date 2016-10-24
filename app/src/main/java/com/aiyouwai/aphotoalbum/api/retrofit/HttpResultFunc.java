package com.aiyouwai.aphotoalbum.api.retrofit;

import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.resultCode == 0) {
            throw new HttpException(httpResult);
        }
        return httpResult.t;
    }
}
