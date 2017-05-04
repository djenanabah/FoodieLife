package com.epitech.foodielife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.epitech.foodielife.beans.Info;
import com.epitech.foodielife.beans.Restaurant;
import com.epitech.foodielife.beans.UserClientInfo;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Tsy-jon on 04/05/2017.
 */

public class RestaurantInfoActivity  extends AppCompatActivity {

    private Restaurant restaurant;
    private UserClientInfo userClientInfo;
    private RestClientUsage client;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantform);

        client = new RestClientUsage(this);
        restaurant = (Restaurant)getIntent().getSerializableExtra("Restaurant");
        userClientInfo = (UserClientInfo)getIntent().getSerializableExtra("UserClientInfo");
        List<Info> infos = generateInfo(restaurant);

        listView = (ListView)findViewById(R.id.restaurant_list);
        InfoAdapter adapter = new InfoAdapter(RestaurantInfoActivity.this, infos);
        listView.setAdapter(adapter);

    }

    private List<Info> generateInfo(Restaurant restaurant) {
        List<Info> infos = new ArrayList<Info>();
        infos.add(new Info("Name", restaurant.getName()));
        infos.add(new Info("Address", restaurant.getAdresse()));
        infos.add(new Info("Description", restaurant.getDescription()));
        return infos;
    }

}
