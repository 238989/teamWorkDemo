package com.example.commueoflove.Mine;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.commueoflove.R;


public class MineFragment extends Fragment {

    LinearLayout openUpload,openConfig,openContact;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        View  view = inflater.inflate(R.layout.fragment_mine, container, false);
        //打开上传记录
        openUpload = (LinearLayout)view.findViewById(R.id.open_upload_label);
        openUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UploadRecordActivity.class);
                startActivity(intent);
            }
        });

        //打开修改个人资料
        openConfig = (LinearLayout)view.findViewById(R.id.open_config_label);
        openConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ConfigPersonalActivity.class);
//                String name = nameView.getText().toString();
//                String account = accountView.getText().toString();
//                //需传入个人的信息
//                Intent intent =new Intent(getActivity(),ConfigPersonalActivity.class);
//                intent.putExtra("name",name);
//                intent.putExtra("account",account);
                startActivityForResult(intent,1);
            }
        });

        //打卡联系管理员
        openContact = (LinearLayout)view.findViewById(R.id.open_contact_label);
        openContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ContactAdministratorActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}