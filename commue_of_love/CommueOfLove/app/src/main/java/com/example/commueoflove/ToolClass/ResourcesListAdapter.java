package com.example.commueoflove.ToolClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.commueoflove.Dao.ListItemOne;
import com.example.commueoflove.R;

import java.util.List;

public class ResourcesListAdapter extends ArrayAdapter {
    public ResourcesListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_resouce,parent,false);
        }
        ListItemOne item = (ListItemOne)getItem(position);
        ImageView img = (ImageView)itemView.findViewById(R.id.resource_img);
        TextView title = (TextView)itemView.findViewById(R.id.resource_title);
        TextView time = (TextView)itemView.findViewById(R.id.resource_time);
        TextView type = (TextView)itemView.findViewById(R.id.resource_class);
        TextView region = (TextView)itemView.findViewById(R.id.resource_region);
        TextView name = (TextView)itemView.findViewById(R.id.resource_name);
        TextView available = (TextView)itemView.findViewById(R.id.resource_available);
        TextView detail = (TextView)itemView.findViewById(R.id.resource_detail);


        img.setImageResource(R.drawable.img_urgent);
        title.setText(item.getTitle());
        time.setText(item.getLevel());
        type.setText(item.getKind());
        region.setText(item.getRegion());
        name.setText(item.getName());
        available.setText(item.getAvailable());
        detail.setText(item.getDetail());

        return itemView;
    }
}
