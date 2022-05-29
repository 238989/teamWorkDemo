package com.example.commueoflove;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.commueoflove.DailyPublicBenfit.DailyPublicBenefitFragment;
import com.example.commueoflove.Mine.MineFragment;
import com.example.commueoflove.emergency.EmergencyFragment;

public class MyPageAdapter extends FragmentStateAdapter {

    String[] titles=new String[]{"紧急救助","日常","我的"};

    public MyPageAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position == 0){
            return new EmergencyFragment();
        }else if(position ==1){
            return new DailyPublicBenefitFragment();
        }else{
            return new MineFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
