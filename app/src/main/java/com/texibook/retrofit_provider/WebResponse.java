package com.texibook.retrofit_provider;

import org.json.JSONException;

import retrofit2.Response;


public interface WebResponse {

    void onResponseSuccess(Response<?> result) throws JSONException;

    void onResponseFailed(String error);
}