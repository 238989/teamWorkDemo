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
import com.example.commueoflove.Dao.ListItemTwo;
import com.example.commueoflove.R;

import java.util.List;

public class DonationListAdapter extends ArrayAdapter {
    public DonationListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_donation,parent,false);
        }
        ListItemTwo item = (ListItemTwo)getItem(position);
        ImageView img = (ImageView)itemView.findViewById(R.id.donation_img);
        TextView title = (TextView)itemView.findViewById(R.id.donation_title);
        TextView type = (TextView)itemView.findViewById(R.id.donation_kind);
        TextView region = (TextView)itemView.findViewById(R.id.donation_region);
        TextView name = (TextView)itemView.findViewById(R.id.donation_name);
        TextView available = (TextView)itemView.findViewById(R.id.donation_available);
        TextView detail = (TextView)itemView.findViewById(R.id.donation_detail);


        img.setImageResource(R.drawable.img_donation);
        title.setText(item.getTitle());
        type.setText(item.getKind());
        region.setText(item.getRegion());
        name.setText(item.getName());
        available.setText(item.getAvailable());
        detail.setText(item.getDetail());

        return itemView;
    }
}
