package com.epitech.foodielife;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Mark;
import com.epitech.foodielife.beans.UserClientInfo;

import java.util.List;

/**
 * Created by djena on 04/05/2017.
 */

public class DishViewActivity extends AppCompatActivity {

    private Context t= this;
    private RestClientUsage mClient;
    private UserClientInfo mUserInfo;
    private Dish mCurrentDish;
    private List<Mark> mMarksList;

    private TextView mDishName;
    private TextView mDishDecription;
    private ScrollView mMarkScrollView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClient = new RestClientUsage(this);
        mUserInfo =(UserClientInfo) getIntent().getSerializableExtra("UserClientInfo");
        mCurrentDish = (Dish) getIntent().getSerializableExtra("Dish");

        mDishName = (TextView)findViewById(R.id.dish_name);
        mDishName.setText(mCurrentDish.getName());

        mDishDecription = (TextView)findViewById(R.id.dish_description);
        mDishDecription.setText(mCurrentDish.getDescription());
        mMarkScrollView = (ScrollView)findViewById(R.id.marks_scroll_view);

        Mark mark = new Mark();
        mark.setIdDish(mCurrentDish.getIdDish());
        mClient.get_mark(t, mUserInfo, mark);
    }

    private void createMarkScrollElem(Mark mark){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView userName = new TextView(this);
        userName.setText(mark.getUser());
        linearLayout.addView(userName);

        RatingBar markRatingBar = new RatingBar(this);
        markRatingBar.setNumStars(5);
        markRatingBar.setStepSize(1.0f);
        markRatingBar.setRating(mark.getStars());
        linearLayout.addView(markRatingBar);

        TextView markComment = new TextView(this);
        markComment.setText(mark.getCommentaire());
        linearLayout.addView(markComment);

        mMarkScrollView.addView(linearLayout);
    }

    public void getMarkListOnSuccess( List<Mark> marks){
        mMarksList = marks;
        for (Mark mark: marks) {
            createMarkScrollElem(mark);
        }
    }

    public void getMarkOnFailure(){
        Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    public void addMarkOnSuccess(){

    }

    public void addMarkOnFailure(){
        Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }
}