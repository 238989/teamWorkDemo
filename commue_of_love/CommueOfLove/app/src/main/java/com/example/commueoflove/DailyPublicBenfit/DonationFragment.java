package com.example.commueoflove.DailyPublicBenfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.commueoflove.Dao.ListItemTwo;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.DonationListAdapter;
import com.example.commueoflove.ToolClass.SetListView;
import com.example.commueoflove.ToolClass.VolunteerListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonationFragment extends Fragment {

    private List<ListItemTwo> donationList = new ArrayList<>();
    ListView listView;
    DonationListAdapter donationListAdapter;
    FloatingActionButton add;


    public DonationFragment() {
        // Required empty public constructor
    }

    public static DonationFragment newInstance(String param1, String param2) {
        DonationFragment fragment = new DonationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_donation, container, false);
        listView = (ListView)view.findViewById(R.id.donation_list_view);

        //表单
        ListItemTwo listItemTwo = new ListItemTwo(R.id.donation_img,"旧衣新暖、快乐同伴","物资","未结束","甘孜藏族自治区","蚂蚁爱心组织","为山区儿童捐赠衣服，送温暖......");
        donationList.add(0,listItemTwo);
        donationList.add(1,listItemTwo);
        donationList.add(2,listItemTwo);
        donationList.add(3,listItemTwo);
        //绑定listview和表单
        donationListAdapter = new DonationListAdapter(getContext(),R.layout.list_view_donation,donationList);
        listView.setAdapter(donationListAdapter);
        //设置高度
        SetListView.setListViewHeightBasedOnChildren(listView);
        //设置事件处理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情页
//                Intent intent = new Intent(getActivity(), GongziDetailActivity.class);
//                startActivity(intent);
            }
        });


        //打开上传记录Activity
        add = (FloatingActionButton)view.findViewById(R.id.add_donation);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UploadDonationActivity.class);
                //传递用户账号信息
                startActivity(intent);
            }
        });

        return view;
    }
}