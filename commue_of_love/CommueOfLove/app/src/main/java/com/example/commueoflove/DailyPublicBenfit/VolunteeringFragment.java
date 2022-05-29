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
import com.example.commueoflove.ToolClass.SetListView;
import com.example.commueoflove.ToolClass.UrgentListAdapter;
import com.example.commueoflove.ToolClass.VolunteerListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolunteeringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolunteeringFragment extends Fragment {

    private List<ListItemTwo> volunteerList = new ArrayList<>();
    VolunteerListAdapter volunteerListAdapter;
    ListView listView;
    FloatingActionButton add;

    public VolunteeringFragment() {
        // Required empty public constructor
    }


    public static VolunteeringFragment newInstance(String param1, String param2) {
        VolunteeringFragment fragment = new VolunteeringFragment();
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
        View view = inflater.inflate(R.layout.fragment_volunteering, container, false);
        listView = (ListView)view.findViewById(R.id.volunteer_list_view);

        //表单设置
        ListItemTwo listItemTwo = new ListItemTwo(R.id.volunteer_img,"爱心志愿者招募","短期","未结束","成都","善意志愿组织","关爱空巢老人，陪伴更温暖......");
        volunteerList.add(0,listItemTwo);
        volunteerList.add(1,listItemTwo);
        volunteerList.add(2,listItemTwo);
        //绑定listview和表单
        volunteerListAdapter = new VolunteerListAdapter(getContext(), R.layout.list_view_volunteer, volunteerList);
        listView.setAdapter(volunteerListAdapter);
        //设置高度
        SetListView.setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情页
//                Intent intent = new Intent(getActivity(), GongziDetailActivity.class);
//                startActivity(intent);
            }
        });

        //打开上传记录Activity
        add = (FloatingActionButton)view.findViewById(R.id.add_volunteer);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UploadVolunteerActivity.class);
                //传递用户账号信息
                startActivity(intent);
            }
        });

        return view;
    }
}