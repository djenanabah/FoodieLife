package com.epitech.foodielife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epitech.foodielife.beans.Info;
import com.epitech.foodielife.beans.Restaurant;

import java.sql.ResultSet;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Tsy-jon on 04/05/2017.
 */

public class InfoAdapter extends ArrayAdapter<Info> {

    public InfoAdapter(Context context, List<Info> infos) {
        super(context, 0, infos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_info,parent, false);
        }

        InfoViewHolder viewHolder = (InfoViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new InfoViewHolder();
            viewHolder.title = (TextView)  convertView.findViewById(R.id.title);
            viewHolder.content  = (TextView)  convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    public class InfoViewHolder {
        public TextView title;
        public TextView content;
    }

}
