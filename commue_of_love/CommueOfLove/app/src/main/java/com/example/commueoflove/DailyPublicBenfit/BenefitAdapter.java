package com.example.commueoflove.DailyPublicBenfit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BenefitAdapter extends FragmentStateAdapter {
    public BenefitAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new DonationFragment();
        }else{
            return new VolunteeringFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
