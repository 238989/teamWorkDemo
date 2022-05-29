package com.example.commueoflove.emergency;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.commueoflove.DailyPublicBenfit.UploadDonationActivity;
import com.example.commueoflove.Dao.ListItemOne;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.ResourcesListAdapter;
import com.example.commueoflove.ToolClass.SetListView;
import com.example.commueoflove.ToolClass.UrgentListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragment extends Fragment {

    private List<ListItemOne> resourceList=new ArrayList<>();
    ResourcesListAdapter resourcesListAdapter;
    ListView listView;
    FloatingActionButton add;

    public ResourcesFragment() {
        // Required empty public constructor
    }


    public static ResourcesFragment newInstance(String param1, String param2) {
        ResourcesFragment fragment = new ResourcesFragment();
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
        View view = inflater.inflate(R.layout.fragment_resources, container, false);
        listView = (ListView)view.findViewById(R.id.resource_list_view);

        //表单
        ListItemOne listItemOne = new ListItemOne(R.drawable.img_resource,"疫情隔离急需生活物资","2022.1-2022.2","物资","已捐完","成都","王先生","目前本人有以下物资：医用药箱......");
        resourceList.add(0, listItemOne);
        resourceList.add(1, listItemOne);
        resourceList.add(2, listItemOne);
        resourceList.add(3, listItemOne);
        resourceList.add(4, listItemOne);
        //绑定listview和表单
        resourcesListAdapter = new ResourcesListAdapter(getContext(), R.layout.list_view_resouce, resourceList);
        listView.setAdapter(resourcesListAdapter);

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
        add = (FloatingActionButton)view.findViewById(R.id.add_resource);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadResourcesActivity.class);
                //传递用户账号信息
                startActivity(intent);
            }
        });

        return view;
    }
}