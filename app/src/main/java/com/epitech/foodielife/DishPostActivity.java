package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
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
    private Mark mMark;

    private Button mAddPictureBtn;
    private ImageView mDishImageView;
    private EditText mRestaurantName;
    private EditText mDishName;
    private EditText mDishDescritption;
    private RatingBar mMarkBar;
    private EditText mMarkdecription;
    private Button mSubmitBtn;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Context t = this;
    private List<Restaurant> mRestaurantList;
    private List<Dish> mDishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishpost);

        mClient = new RestClientUsage(this);
        mUserInfo = (UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        mRestaurantList = (List<Restaurant>) getIntent().getSerializableExtra("restaurantList");

        mDish = new Dish();
        mMark = new Mark();

        mDishImageView = (ImageView)findViewById(R.id.dishImageView);
        mAddPictureBtn = (Button)findViewById(R.id.addPictureBtn);
        mRestaurantName = (EditText)findViewById(R.id.restaurant_name);
        mDishName = (EditText) findViewById(R.id.dish_name);
        mDishDescritption = (EditText) findViewById(R.id.dish_description);
        //add Mark
        mMarkBar = (RatingBar) findViewById(R.id.mark_bar);
        mMarkdecription = (EditText) findViewById(R.id.mark_description);
        mSubmitBtn = (Button)findViewById(R.id.submit_btn);

        mAddPictureBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                    captureImage();
            }
        });
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

    private void sendMark(){
        mMark.setIdDish(mDish.getIdDish());
        mMark.setUser(mUserInfo.getName());
        mMark.setStars(mMarkBar.getNumStars());
        mMark.setCommentaire(mMarkdecription.getText().toString());
        mClient.add_mark(t, mUserInfo, mMark);
    }

    private void sendDish(){
        mDish.setPhoto("toto");
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


    // Take picture
    private  void captureImage(){
        // Create an implicit intent, for image capture.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Start camera and wait for the results.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Take picture failure", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE){
            if (resultCode == RESULT_OK){
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                mDishImageView.setImageBitmap(bp);
            }
            else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}