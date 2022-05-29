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

public class RecordResourceAdapter extends ArrayAdapter {
    public RecordResourceAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_record_resource,parent,false);
        }
        ListItemOne item = (ListItemOne)getItem(position);

        TextView title = (TextView)itemView.findViewById(R.id.record_resource_title);

        TextView time = (TextView)itemView.findViewById(R.id.record_resource_level);
        TextView type = (TextView)itemView.findViewById(R.id.record_resource_class);
        TextView available = (TextView)itemView.findViewById(R.id.record_resource_available);
        TextView uploadTime = (TextView)itemView.findViewById(R.id.record_resource_time);

        TextView region = (TextView)itemView.findViewById(R.id.record_resource_region);
        TextView name = (TextView)itemView.findViewById(R.id.record_resource_name);

        TextView detail = (TextView)itemView.findViewById(R.id.record_resource_detail);

        //设置显示文字
        title.setText(item.getTitle());
        time.setText(item.getLevel());
        type.setText(item.getKind());
        available.setText(item.getAvailable());
        uploadTime.setText(item.getTime());

        region.setText(item.getRegion());
        name.setText(item.getName());

        detail.setText(item.getDetail());

        return itemView;
    }
}
