package com.example.commueoflove.emergency;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EmergencyAdapter extends FragmentStateAdapter {
    public EmergencyAdapter(@NonNull Fragment fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new UrgentFragment();
        }else{
            return new ResourcesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
