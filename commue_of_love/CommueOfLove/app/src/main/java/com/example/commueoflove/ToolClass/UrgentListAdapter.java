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

public class UrgentListAdapter extends ArrayAdapter {

    public UrgentListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_urgent,parent,false);
        }
        ListItemOne item = (ListItemOne)getItem(position);
        ImageView img = (ImageView)itemView.findViewById(R.id.urgent_image);
        TextView title = (TextView)itemView.findViewById(R.id.urgent_title);
        TextView level = (TextView)itemView.findViewById(R.id.urgent_level);
        TextView type = (TextView)itemView.findViewById(R.id.urgent_class);
        TextView region = (TextView)itemView.findViewById(R.id.urgent_region);
        TextView name = (TextView)itemView.findViewById(R.id.urgent_name);
        TextView available = (TextView)itemView.findViewById(R.id.urgent_available);
        TextView detail = (TextView)itemView.findViewById(R.id.urgent_detail);


        img.setImageResource(R.drawable.img_urgent);
        title.setText(item.getTitle());
        level.setText(item.getLevel());
        type.setText(item.getKind());
        region.setText(item.getRegion());
        name.setText(item.getName());
        available.setText(item.getAvailable());
        detail.setText(item.getDetail());

        return itemView;
    }
}
