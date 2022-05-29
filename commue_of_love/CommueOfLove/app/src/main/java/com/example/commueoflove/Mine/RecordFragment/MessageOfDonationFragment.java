package com.example.commueoflove.Mine.RecordFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.commueoflove.Dao.ListItemTwo;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.RecordDonationAdapter;
import com.example.commueoflove.ToolClass.SetListView;

import java.util.ArrayList;
import java.util.List;


public class MessageOfDonationFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private List<ListItemTwo> recordList = new ArrayList<>();
    ListView listView;
    RecordDonationAdapter recordDonationAdapter;
    private static final String TAG = "MessageOfDonationFragme";

    public MessageOfDonationFragment() {
        // Required empty public constructor
    }

    public static MessageOfDonationFragment newInstance(String param1, String param2) {
        MessageOfDonationFragment fragment = new MessageOfDonationFragment();
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
        View view = inflater.inflate(R.layout.fragment_message_of_donation, container, false);
        listView = (ListView)view.findViewById(R.id.record_donation_view);

        //表单
        ListItemTwo listItemTwo = new ListItemTwo("旧衣新暖、快乐同伴","物资","未结束","甘孜藏族自治区","蚂蚁爱心组织","为山区儿童捐赠衣服，送温暖......","2022-01-22");
        recordList.add(0,listItemTwo);
        recordList.add(1,listItemTwo);
        recordList.add(2,listItemTwo);
        recordList.add(3,listItemTwo);
        //绑定listview和表单
        recordDonationAdapter = new RecordDonationAdapter(getContext(), R.layout.list_record_donation, recordList);
        listView.setAdapter(recordDonationAdapter);
        //设置高度
        SetListView.setListViewHeightBasedOnChildren(listView);

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
        builder.setTitle("提示").setMessage("请确认是否删除当前记录？").setPositiveButton("是",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 对话框事件处理");
                if(recordList.remove(position)!=null){
                    //删除操作

                    System.out.println("success");
                }else{
                    System.out.println("failed");
                }
                recordDonationAdapter.notifyDataSetChanged();
//                recordList.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        Log.i(TAG, "onItemLongClick: size=" + recordList.size());

        return true;
    }
}