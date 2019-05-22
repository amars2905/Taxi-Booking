package com.texibook.retrofit_provider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class WebServiceResponse {

    public static void handleResponse(Response<?> response, WebResponse webResponse) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                try {
                    webResponse.onResponseSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                webResponse.onResponseFailed(response.message());
            }
        } else {
            try {
                if (response.errorBody() != null) {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    webResponse.onResponseFailed(jObjError.getString("error"));
                } else {
                    webResponse.onResponseFailed(response.message());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}