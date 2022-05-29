package com.example.commueoflove.ToolClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.commueoflove.Dao.ListItemTwo;
import com.example.commueoflove.R;

import java.util.List;

public class RecordVolunteerAdapter extends ArrayAdapter {
    public RecordVolunteerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_record_volunteer,parent,false);
        }
        ListItemTwo item = (ListItemTwo) getItem(position);
        TextView title = (TextView)itemView.findViewById(R.id.record_volunteer_title);
        TextView type = (TextView)itemView.findViewById(R.id.record_volunteer_kind);
        TextView region = (TextView)itemView.findViewById(R.id.record_volunteer_region);
        TextView name = (TextView)itemView.findViewById(R.id.record_volunteer_name);
        TextView available = (TextView)itemView.findViewById(R.id.record_volunteer_available);
        TextView detail = (TextView)itemView.findViewById(R.id.record_volunteer_detail);
        TextView time = (TextView)itemView.findViewById(R.id.record_volunteer_time);

        title.setText(item.getTitle());
        type.setText(item.getKind());
        region.setText(item.getRegion());
        name.setText(item.getName());
        available.setText(item.getAvailable());
        detail.setText(item.getDetail());
        time.setText(item.getTime());

        return itemView;
    }
}
