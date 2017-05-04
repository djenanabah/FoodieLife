package com.epitech.foodielife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epitech.foodielife.beans.Restaurant;

/**
 * Created by Tsy-jon on 01/05/2017.
 */

public class RestaurantFormActivity extends AppCompatActivity {

    private RestClientUsage client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantform);

        client = new RestClientUsage(this);

        Button submit = (Button) findViewById(R.id.add_restaurant);
        if (submit == null)
            Log.i("TEST", "mqkdlmkdmlqsdkqsldmkqsld");
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.restaurant_name);
                EditText address = (EditText) findViewById(R.id.restaurant_address);
                EditText description = (EditText) findViewById(R.id.restaurant_description);
                double latitude = getIntent().getDoubleExtra("latitude", 0.00);
                double longitude = getIntent().getDoubleExtra("longitude", 0.00);

                Restaurant restaurant = new Restaurant();
                restaurant.setName(name.getText().toString());
                restaurant.setAdresse(address.getText().toString());
                restaurant.setDescription(description.getText().toString());
                restaurant.setLatitude(latitude);
                restaurant.setLongitude(longitude);

                //client.add_restaurant(null, restaurant);
            }
        });
    }
}
