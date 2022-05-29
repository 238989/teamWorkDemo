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
import com.example.commueoflove.Dao.ListItemTwo;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.RecordUrgentAdapter;
import com.example.commueoflove.ToolClass.SetListView;
import com.example.commueoflove.emergency.UploadUrgentActivity;

import java.util.ArrayList;
import java.util.List;


public class MessageOfUrgentFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private static final String TAG = "MessageOfUrgentFragment";
    private List<ListItemOne> recordList = new ArrayList<>();
    ListView listView;
    RecordUrgentAdapter recordUrgentAdapter;

    public static MessageOfUrgentFragment newInstance(String param1, String param2) {
        MessageOfUrgentFragment fragment = new MessageOfUrgentFragment();
        return fragment;
    }

    public MessageOfUrgentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_of_urgent, container, false);
        listView = (ListView)view.findViewById(R.id.record_urgent_view);

        //表单
        ListItemOne listItemOne = new ListItemOne("疫情隔离急需生活物资","一级","物资","未解决","成都","张女士","由于已经隔离了有3个月，所以物资缺乏，目前需要......","2022-01-12");
        recordList.add(0, listItemOne);
        recordList.add(1, listItemOne);
        recordList.add(2, listItemOne);
        recordList.add(3, listItemOne);
        recordList.add(4, listItemOne);
        //绑定listview和表单
        recordUrgentAdapter = new RecordUrgentAdapter(getContext(),R.layout.list_record_urgent,recordList);
        listView.setAdapter(recordUrgentAdapter);
        //设置高度
        SetListView.setListViewHeightBasedOnChildren(listView);

        //短按进入修改
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), UploadUrgentActivity.class);
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
        builder.setTitle("提示").setMessage("请确认是否删除当前紧急项目，同时删除当前记录？").setPositiveButton("是",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 对话框事件处理");
                if(recordList.remove(position)!=null){
                    //删除操作

                    System.out.println("success");
                }else{
                    System.out.println("failed");
                }
                recordUrgentAdapter.notifyDataSetChanged();
//                recordList.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        Log.i(TAG, "onItemLongClick: size=" + recordList.size());

        return true;
    }
}