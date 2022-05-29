package com.example.commueoflove.Mine.RecordFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.commueoflove.Dao.ListItemOne;
import com.example.commueoflove.Mine.UploadRecordActivity;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.RecordResourceAdapter;
import com.example.commueoflove.ToolClass.SetListView;
import com.example.commueoflove.emergency.UploadResourcesActivity;

import java.util.ArrayList;
import java.util.List;


public class MessageOfResourceFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private List<ListItemOne> recordList = new ArrayList<>();
    ListView listView;
    RecordResourceAdapter recordResourceAdapter;
    private static final String TAG = "MessageOfResourceFragme";

    public MessageOfResourceFragment() {
        // Required empty public constructor
    }

    public static MessageOfResourceFragment newInstance(String param1, String param2) {
        MessageOfResourceFragment fragment = new MessageOfResourceFragment();
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
        View view = inflater.inflate(R.layout.fragment_message_of_resource, container, false);
        listView = (ListView)view.findViewById(R.id.record_resource_view);

        //表单
        ListItemOne listItemOne = new ListItemOne("疫情隔离急需生活物资","2022.1-2022.2","物资","已捐完","成都","王先生","目前本人有以下物资：医用药箱......","2022-02-01");
        recordList.add(0, listItemOne);
        recordList.add(1, listItemOne);
        recordList.add(2, listItemOne);
        recordList.add(3, listItemOne);
        recordList.add(4, listItemOne);
        //绑定listview和表单
        recordResourceAdapter = new RecordResourceAdapter(getContext(),R.layout.list_record_resource,recordList);
        listView.setAdapter(recordResourceAdapter);
        //设置高度
        SetListView.setListViewHeightBasedOnChildren(listView);

        //短按进入修改
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), UploadResourcesActivity.class);
                startActivity(intent);
            }
        });

        //设置长按事件监听
        listView.setOnItemLongClickListener(this);

        return view;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i(TAG, "onItemLongClick: 长按列表项position=" + position);

        Object itemAtPosition = listView.getItemAtPosition(position);
        //删除操作
        //构造对话框进行确认操作
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示").setMessage("请确认是否删除当前资源信息，同时删除当前记录？").setPositiveButton("是",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 对话框事件处理");
                if(recordList.remove(position)!=null){
                    //删除操作

                    System.out.println("success");
                }else{
                    System.out.println("failed");
                }
                recordResourceAdapter.notifyDataSetChanged();
//                recordList.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        Log.i(TAG, "onItemLongClick: size=" + recordList.size());


        return true;
    }
}