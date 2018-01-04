package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.epitech.foodielife.beans.Restaurant;
import com.epitech.foodielife.beans.UserClientInfo;

/**
 * Created by Tsy-jon on 01/05/2017.
 */

public class RestaurantFormActivity extends AppCompatActivity {

    private RestClientUsage client;
    private Restaurant restaurant;
    private UserClientInfo user;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantform);
        final Context t = this;

        client = new RestClientUsage(this);
        restaurant = new Restaurant();
        user = (UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        latitude = getIntent().getDoubleExtra("latitude", 0.00);
        longitude = getIntent().getDoubleExtra("longitude", 0.00);


        Button submit = (Button) findViewById(R.id.add_restaurant);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.restaurant_name);
                EditText address = (EditText) findViewById(R.id.restaurant_address);
                EditText description = (EditText) findViewById(R.id.restaurant_description);

                restaurant.setName(name.getText().toString());
                restaurant.setAdresse(address.getText().toString());
                restaurant.setDescription(description.getText().toString());
                restaurant.setLatitude(latitude);
                restaurant.setLongitude(longitude);

                client.add_restaurant(t, user, restaurant);
            }
        });
    }

    public void addRestaurantSuccess() {
        Intent intent = new Intent(RestaurantFormActivity.this, MapsActivity.class);
        intent.putExtra("UserClientInfo", getIntent().getSerializableExtra("UserClientInfo"));
        startActivity(intent);
    }

    public void addRestaurantFailure()
    {
        Toast.makeText(this, R.string.add_restaurant_failed, Toast.LENGTH_SHORT).show();
    }
}
