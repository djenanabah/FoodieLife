package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Mark;
import com.epitech.foodielife.beans.Restaurant;
import com.epitech.foodielife.beans.UserClientInfo;

import java.util.List;

/**
 * Created by djena on 02/05/2017.
 */

public class DishPostActivity extends AppCompatActivity{

    private RestClientUsage mClient;
    private UserClientInfo mUserInfo;
    private Dish mDish;


    private EditText mRestaurantName;
    private EditText mDishName;
    private EditText mDishDescritption;
    private Button mSubmitBtn;
    private Context t = this;
    private List<Restaurant> mRestaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishpost);

        mClient = new RestClientUsage(this);
        mUserInfo = (UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        mRestaurantList = (List<Restaurant>) getIntent().getSerializableExtra("restaurantList");

        mDish = new Dish();


        mRestaurantName = (EditText)findViewById(R.id.restaurant_name);
        mDishName = (EditText) findViewById(R.id.dish_name);
        mDishDescritption = (EditText) findViewById(R.id.dish_description);

        mSubmitBtn = (Button)findViewById(R.id.submit_btn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAddDish();
            }
        });
    }

    private boolean editTextIsSet(EditText zone, String errorMsg){
        String value = zone.getText().toString();
        if (value.matches("")) {
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void submitAddDish(){
        int restaurantId;
        if (!editTextIsSet(mDishName, "\"You did not enter a dish name\"") &&
                !editTextIsSet(mRestaurantName, "\"You did not enter a  restaurant name\"")){
            return;
        }
        if ((restaurantId = getRestaurantIdByName(mRestaurantName.getText().toString())) == 0){
            Toast.makeText(this, "This Restaurant doesn't exist, please create the restaurant.", Toast.LENGTH_SHORT);
            return;

        }
        mDish.setIdRestaurant(restaurantId);
        sendDish();
    }

    private int getRestaurantIdByName(String name){
        for(Restaurant rest: mRestaurantList){
            if (rest.getName().equals(name)){
                return (rest.getIdRestaurant());
            }
        }
        return (0);
    }



    private void sendDish(){
        mDish.setName(mDishName.getText().toString());
        mDish.setDescription(mDishDescritption.getText().toString());
        mClient.add_dish(t, mUserInfo, mDish);
    }

    public  void addDishFailure(){
        Toast.makeText(this, R.string.add_dish_post_on_failed, Toast.LENGTH_SHORT);
    }

    public  void addDishSuccess(){
        Toast.makeText(this, R.string.add_dish_post_on_success, Toast.LENGTH_SHORT);
        finishActivity(RESULT_OK);
    }

}