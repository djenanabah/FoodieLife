package com.epitech.foodielife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.epitech.foodielife.beans.Dish;
import com.epitech.foodielife.beans.Info;
import com.epitech.foodielife.beans.Restaurant;
import com.epitech.foodielife.beans.UserClientInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tsy-jon on 04/05/2017.
 */

public class RestaurantInfoActivity  extends AppCompatActivity {

    private Restaurant restaurant;
    private UserClientInfo userClientInfo;
    private RestClientUsage client;
    private ListView infoListView;
    private ListView dishListView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantform);

        client = new RestClientUsage(this);
        restaurant = (Restaurant)getIntent().getSerializableExtra("Restaurant");
        userClientInfo = (UserClientInfo)getIntent().getSerializableExtra("UserClientInfo");

        List<Info> infos = retrieveInfo();
        infoListView = (ListView)findViewById(R.id.info_list);
        InfoAdapter infoAdapter = new InfoAdapter(RestaurantInfoActivity.this, infos);
        infoListView.setAdapter(infoAdapter);
        Utility.setListViewHeightBasedOnChildren(infoListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveDishes();
    }

    private List<Info> retrieveInfo() {
        List<Info> infos = new ArrayList<Info>();
        infos.add(new Info("Name", restaurant.getName()));
        infos.add(new Info("Address", restaurant.getAdresse()));
        infos.add(new Info("Description", restaurant.getDescription()));
        return infos;
    }

    private void retrieveDishes() {
        Dish req_dish = new Dish;
        req_dish.setIdRestaurant(restaurant.getIdRestaurant());
        client.get_dish((Context)this, userClientInfo, req_dish);
    }

    public void retrieveDishesSuccess(List<Dish> dishes) {
        dishListView = (ListView)findViewById(R.id.dish_info_list);
        DishAdapter dishAdapter = new DishAdapter(RestaurantInfoActivity.this, dishes);
        dishListView.setAdapter(dishAdapter);
        Utility.setListViewHeightBasedOnChildren(dishListView);
        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ArrayAdapter<Dish> adapter = (ArrayAdapter<Dish>) parent.getAdapter();
                Dish dish = adapter.getItem((int)id);

                Intent intent = new Intent(this, DishViewActivity.class);
                intent.putExtra("Dish", dish);
                intent.putExtra("UserClientInfo", userClientInfo);
                startActivity(intent);
            }
        });
    }

    public void retrieveDishesFailure() {
        Toast.makeText(this, R.string.get_dishes_failed, Toast.LENGTH_SHORT).show();
    }
}
