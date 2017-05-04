package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Mark;
import com.epitech.foodielife.beans.UserClientInfo;

/**
 * Created by djena on 04/05/2017.
 */

public class AddMarkActivity extends AppCompatActivity{

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Context t= this;
    private RestClientUsage mClient;
    private UserClientInfo mUserInfo;
    private Dish mCurrentDish;
    private Mark mMark;

    private Button mAddPictureBtn;
    private ImageView mDishImageView;
    private RatingBar mMarkBar;
    private EditText mMarkdecription;
    private Button mSubmitBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClient = new RestClientUsage(this);
        mUserInfo = (UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        mCurrentDish = (Dish) getIntent().getSerializableExtra("Dish");
        mMark = new Mark();

        mDishImageView = (ImageView) findViewById(R.id.dishImageView);
        mAddPictureBtn = (Button) findViewById(R.id.addPictureBtn);
        mMarkBar = (RatingBar) findViewById(R.id.mark_bar);
        mMarkdecription = (EditText) findViewById(R.id.mark_description);
        mAddPictureBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        mSubmitBtn = (Button)findViewById(R.id.submit_btn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMark();
            }
        });
    }

    private void sendMark(){
        mMark.setPhoto("toto");
        mMark.setIdDish(mCurrentDish.getIdDish());
        mMark.setUser(mUserInfo.getName());
        mMark.setStars(mMarkBar.getNumStars());
        mMark.setCommentaire(mMarkdecription.getText().toString());
        mClient.add_mark(t, mUserInfo, mMark);
    }

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

    public void addMarkOnSuccess(){
        finishActivity(RESULT_OK);
    }

    public void addMarkOnFailure(){
        Toast.makeText(this, R.string.add_mark_on_failed, Toast.LENGTH_SHORT);
    }
}
