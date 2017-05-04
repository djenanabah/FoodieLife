package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Mark;
import com.epitech.foodielife.beans.UserClientInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by djena on 04/05/2017.
 */

public class DishViewActivity extends AppCompatActivity {

    private Context t= this;
    private RestClientUsage mClient;
    private UserClientInfo mUserInfo;
    private Dish mCurrentDish;

    private TextView mDishName;
    private TextView mDishDecription;
    private ScrollView mMarkScrollView;
    private Button mAddMark;
    private LinearLayout mMarkElemLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishview);

        mClient = new RestClientUsage(this);
        mUserInfo =(UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        mCurrentDish = (Dish) getIntent().getSerializableExtra("Dish");

        mDishName = (TextView)findViewById(R.id.dish_name);
        mDishName.setText(mCurrentDish.getName());

        mDishDecription = (TextView)findViewById(R.id.dish_description);
        mDishDecription.setText(mCurrentDish.getDescription());
        mMarkScrollView = (ScrollView)findViewById(R.id.marks_scroll_view);
        mMarkElemLayout = (LinearLayout)findViewById(R.id.mark_layout);
        mAddMark = (Button)findViewById(R.id.add_mark);
        mAddMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Call AddMarkActivity
                Intent addMarkIntent = new Intent(DishViewActivity.this, AddMarkActivity.class);
                addMarkIntent.putExtra("UserClientInfo", mUserInfo);
                addMarkIntent.putExtra("Dish",mCurrentDish);
                startActivity(addMarkIntent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Mark mark = new Mark();
        mark.setIdDish(mCurrentDish.getIdDish());
        mClient.get_mark(t, mUserInfo, mark);
    }

    private void createMarkScrollElem(Mark mark){
        TextView userName = new TextView(this);
        userName.setText(mark.getUser());
        mMarkElemLayout.addView(userName);

        RatingBar markRatingBar = new RatingBar(this);
        markRatingBar.setNumStars(5);
        markRatingBar.setStepSize(1.0f);
        markRatingBar.setRating(mark.getStars());
        mMarkElemLayout.addView(markRatingBar);

        TextView markComment = new TextView(this);
        markComment.setText(mark.getCommentaire());
        mMarkElemLayout.addView(markComment);
    }

    public void getMarkListOnSuccess( List<Mark> marks){
        mMarkElemLayout.removeAllViews();
        for (Mark mark: marks) {
            createMarkScrollElem(mark);
        }
        finishActivity(RESULT_OK);
    }

    public void getMarkOnFailure(){
    }
}