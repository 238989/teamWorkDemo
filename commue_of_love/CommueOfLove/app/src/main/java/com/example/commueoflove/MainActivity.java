package com.example.commueoflove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.commueoflove.DailyPublicBenfit.DailyPublicBenefitFragment;
import com.example.commueoflove.Mine.MineFragment;
import com.example.commueoflove.emergency.EmergencyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.main_navigation_bar);
        initFragment();
        initListener();
    }

    private BottomNavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private static final String TAG = "MainActivity";
    private Fragment[] fragments;
    private int lastFragment;


    private void initFragment() {
        EmergencyFragment emergencyFragment = new EmergencyFragment();
        DailyPublicBenefitFragment dailyPublicBenefitFragment = new DailyPublicBenefitFragment();
        MineFragment mMineFragment=new MineFragment();
        fragments = new Fragment[]{emergencyFragment, dailyPublicBenefitFragment, mMineFragment};
        mFragmentManager = getSupportFragmentManager();
        //默认显示HomeFragment
        mFragmentManager.beginTransaction()
                .replace(R.id.main_page_controller, emergencyFragment)
                .show(emergencyFragment)
                .commit();

    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.emergency:
                        if (lastFragment != 0) {
                            MainActivity.this.switchFragment(lastFragment, 0);
                            lastFragment = 0;
                        }
                        return true;
                    case R.id.benefit:
                        if (lastFragment != 1) {
                            MainActivity.this.switchFragment(lastFragment, 1);
                            lastFragment = 1;
                        }
                        return true;

                    case R.id.mine:
                        if (lastFragment != 2) {
                            MainActivity.this.switchFragment(lastFragment, 2);
                            lastFragment = 2;
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if (!fragments[index].isAdded()){
            transaction.add(R.id.main_page_controller,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    @Override
    protected void onResume() {
        int fragmentFlag = this.getIntent().getIntExtra("fragment_flag", 0);
        switch (fragmentFlag){
            case 1:
                //紧急救助
                // 控制跳转到底部导航项(navigation_home为该Fragment的对应控件的id值)
                MainActivity.this.switchFragment(lastFragment, 1);
                break;

            case 2:
                //日常公益
                MainActivity.this.switchFragment(lastFragment, 2);
                break;
            case 3:
                //我的
                MainActivity.this.switchFragment(lastFragment, 3);
                break;
        }
        super.onResume();
    }
}