package com.epitech.foodielife;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epitech.foodielife.beans.Dish;

import java.util.List;

/**
 * Created by Tsy-jon on 04/05/2017.
 */

public class DishAdapter extends ArrayAdapter<Dish> {

    public DishAdapter(Context context, List<Dish> dishes) {
        super(context, 0, dishes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_dish_info,parent, false);
        }

        DishViewHolder viewHolder = (DishViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new DishViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.dishinfo_name);
            viewHolder.description = (TextView)  convertView.findViewById(R.id.dishinfo_description);
            convertView.setTag(viewHolder);
        }
       Dish dish = getItem(position);
        if ((dish != null))
        {
          if (dish.getName() != null)
            {
                Log.d("test", String.valueOf(viewHolder));
                Log.d("test1", String.valueOf(viewHolder.name));
                Log.d("test2", String.valueOf(dish));
                Log.d("test3", String.valueOf(dish.getName()));
                viewHolder.name.setText(dish.getName());
            }
            if (dish.getDescription() != null)
            {
                viewHolder.description.setText(dish.getDescription());
            }
        }
        return convertView;
    }

    public class DishViewHolder {
        public TextView name;
        public TextView description;
    }

}
