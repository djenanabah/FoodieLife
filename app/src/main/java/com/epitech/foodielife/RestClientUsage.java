package com.epitech.foodielife;

/**
 * Created by Tsy-jon on 30/04/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import org.json.*;
import com.loopj.android.http.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cz.msebera.android.httpclient.Header;

/**
 * Created by LAU on 27/04/2017.
 */

public class RestClientUsage {
    private final Activity activity;
    public static boolean endRequestAll;

    public RestClientUsage(Activity activity)
    {
        this.endRequestAll = false;
        this.activity = activity;
    }
/*
    public void  register(RequestParams params) throws JSONException {
        RestClient.get("adduser", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject)
            {

            }
        });
    }*/

    public void login(final String name, final String password) throws JSONException {
        RequestParams params = new RequestParams();

        RestClient.get("login", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject)
            {

            }
        });
    }

}
