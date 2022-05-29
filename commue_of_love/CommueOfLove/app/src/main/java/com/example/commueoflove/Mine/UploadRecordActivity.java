package com.example.commueoflove.Mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.commueoflove.DailyPublicBenfit.BenefitAdapter;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.UrgentListAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UploadRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_record);

        ViewPager2 viewPager = findViewById(R.id.viewPage2);
        UploadRecordAdapter uploadRecordAdapter = new UploadRecordAdapter(this);
        viewPager.setAdapter(uploadRecordAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        String tabText[]=new String[]{"紧急救助","资源信息","物资捐赠","志愿招募"};
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabText[position])
        ).attach();
    }
}