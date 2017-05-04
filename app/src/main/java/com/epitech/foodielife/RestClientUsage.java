package com.epitech.foodielife;

/**
 * Created by Tsy-jon on 30/04/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import org.json.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Mark;
import com.epitech.foodielife.beans.Params;
import com.epitech.foodielife.beans.ResponseDish;
import com.epitech.foodielife.beans.ResponseMark;
import com.epitech.foodielife.beans.ResponseRestaurant;
import com.epitech.foodielife.beans.Restaurant;
import com.epitech.foodielife.beans.UserClientInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.*;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.data;


/**
 * Created by LAU on 27/04/2017.
 */

public class RestClientUsage {
    private final Activity activity;
    public static boolean endRequestAll;
    String REST_API_URL = "http://10.0.2.2:8080/";

    public RestClientUsage(Activity activity)
    {
        this.endRequestAll = false;
        this.activity = activity;
    }

    public void login(final String value) throws JSONException {
        RequestParams params = new RequestParams();
        params.put("token", value);

        RestClient.post("login", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("RestClientUsage", "OnSuccess");
                ((LoginActivity)activity).updateUserInfo(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject)
            {
                //Log.i("RCU - OnFailure", jsonObject.toString());
            }
        });
    }

/*    public void addDishPost(UserClientInfo user, Dish dish, Mark mark){
        RequestParams params = new RequestParams();
        params.put("user", user);
        params.put("value", dish);
        RestClient.post("", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //TODO : message on success
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //TODO : message on failure
            }
        });
*/
    public void add_restaurant(Context t, UserClientInfo user, Restaurant restaurant)
    {
        Params<Restaurant> params = new Params<>();
        params.setUser(user);
        params.setValue(restaurant);

        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;

        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "restaurant/add",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ResponseRestaurant resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseRestaurant.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || (resp.getMessage() != "200")) {
                            ((RestaurantFormActivity)activity).addRestaurantFailure();
                        }
                        else {
                            ((RestaurantFormActivity)activity).addRestaurantSuccess();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void get_restaurants(Context t, UserClientInfo user)
    {
        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;
        Log.d("TITI:", finalData);
        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "restaurant",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("reponse:", response);
                        ResponseRestaurant resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseRestaurant.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || !(resp.getMessage().equals("200"))) {
                            ((MapsActivity)activity).getRestaurantsFailure();
                        }
                        else {
                            ((MapsActivity)activity).getRestaurantsSuccess(resp.getList());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void add_dish(Context t, UserClientInfo user, Dish dish)
    {
        Params<Dish> params = new Params<>();
        params.setUser(user);
        params.setValue(dish);

        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;

        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "dish/add",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ResponseDish resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseDish.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || (resp.getMessage() != "200")) {
                            ((DishPostActivity)activity).addDishFailure();
                            // MESSAGE FAILURE
                        }
                        else {
                            ((DishPostActivity)activity).addDishSuccess();
                            // MESSAGE SUCCESS
                            // to do
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void get_dish(Context t, UserClientInfo user, Dish dish)
    {
        Params<Dish> params = new Params<>();
        params.setUser(user);
        params.setValue(dish);

        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;
        Log.d("TITI:", finalData);
        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "dish",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("reponse:", response);
                        ResponseDish resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseDish.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || !(resp.getMessage().equals("200"))) {
                            ((RestaurantInfoActivity)activity).retrieveDishesFailure();
                        }
                        else {
                            ((RestaurantInfoActivity)activity).retrieveDishesSuccess(resp.getList());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void add_mark(Context t, UserClientInfo user, Mark mark)
    {
        Params<Mark> params = new Params<>();
        params.setUser(user);
        params.setValue(mark);

        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;

        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "mark/add",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ResponseMark resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseMark.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || (resp.getMessage() != "200")) {
                            //((DishPostActivity)activity).addMarkFailure();
                        }
                        else {
                            //((DishPostActivity)activity).addMarkSuccess();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void get_mark(Context t, UserClientInfo user, Mark mark)
    {
        Params<Mark> params = new Params<>();
        params.setUser(user);
        params.setValue(mark);

        final ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(t);
        final String finalData = data;
        Log.d("TITI:", finalData);
        StringRequest myReq = new StringRequest(Request.Method.POST,
                REST_API_URL + "mark",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("reponse:", response);
                        ResponseMark resp = null;
                        try {
                            resp = mapper.readValue(response, ResponseMark.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if ((resp == null) || !(resp.getMessage().equals("200"))) {
                            ((DishViewActivity)activity).getMarkOnFailure();
                            // MESSAGE FAILURE
                        }
                        else {
                            ((DishViewActivity)activity).getMarkListOnSuccess(resp.getList());
                            // MESSAGE SUCCESS
                            // to do resp.getList(); ==> List<Restaurant>
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return finalData.getBytes();
            }

        };
        queue.add(myReq);
    }
}
