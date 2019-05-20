package com.texibook.retrofit_provider;

import org.json.JSONException;

import java.io.IOException;

import retrofit2.Response;


public interface WebResponse {

    void onResponseSuccess(Response<?> result) throws JSONException, IOException;

    void onResponseFailed(String error);
}