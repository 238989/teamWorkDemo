package com.example.commueoflove.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.ShowPickerClass;

import java.util.Map;

public class UploadUrgentActivity extends AppCompatActivity {

    private OptionsPickerView pvOptions;
    Map<String,OptionsPickerView> map;
    ShowPickerClass showPickerClass;
    String region;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_urgent);

        location = findViewById(R.id.upload_urgent_location);

        //初始化地区选择器
        showPickerClass = new ShowPickerClass();
//        map = showPickerClass.initPicker("province.json",this,location);
        pvOptions = showPickerClass.initPicker("province.json",this,location);

    }

    public void onClick(View view) {
        //显示Picker
        pvOptions.show();
//        Log.i(TAG, "onClick: 选择的地址为："+region);
    }
}