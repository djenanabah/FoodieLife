package com.epitech.foodielife;

import android.util.Log;
import com.loopj.android.http.*;

/**
 * Created by LAU on 27/04/2017.
 */

public class RestClient {
    private static final String BASE_URL = "109.17.221.105:8080";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        //Log.i("url", getAbsoluteUrl(url));
        //Log.i("params", params.toString());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
