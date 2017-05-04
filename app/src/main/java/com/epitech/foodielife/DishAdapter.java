package com.epitech.foodielife;

import android.content.Context;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_info,parent, false);
        }

        DishViewHolder viewHolder = (DishViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new DishViewHolder();
            viewHolder.description = (TextView)  convertView.findViewById(R.id.description);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture)
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    public class DishViewHolder {
        public TextView description;
        public ImageView picture;
    }

}
