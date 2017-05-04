package com.epitech.foodielife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by djena on 04/05/2017.
 */

public class DishViewActivity extends AppCompatActivity {
    private TextView mDishName;
    private TextView mDishDecription;
    private ScrollView mMarkScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDishName = (TextView)findViewById(R.id.dish_name);
        mDishDecription = (TextView)findViewById(R.id.dish_description);
        mMarkScrollView = (ScrollView)findViewById(R.id.marks_scroll_view);
    }
}
