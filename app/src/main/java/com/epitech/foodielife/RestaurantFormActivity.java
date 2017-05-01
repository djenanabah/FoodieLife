package com.epitech.foodielife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

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

                client.add_restaurant(user, restaurant);
            }
        });
    }

    public void addRestaurantSuccess(JSONObject response)
    {
        Intent intent = new Intent(RestaurantFormActivity.this, MapsActivity.class);
        intent.putExtra("UserClientInfo", getIntent().getSerializableExtra("UserClientInfo"));
        startActivity(intent); // modifier MapsActivity pour qu'il mette le marqueur avant de changer d'activité
    }
}
