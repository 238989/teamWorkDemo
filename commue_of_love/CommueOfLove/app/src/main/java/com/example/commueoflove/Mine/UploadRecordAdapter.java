package com.example.commueoflove.Mine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.commueoflove.Mine.RecordFragment.MessageOfDonationFragment;
import com.example.commueoflove.Mine.RecordFragment.MessageOfResourceFragment;
import com.example.commueoflove.Mine.RecordFragment.MessageOfUrgentFragment;
import com.example.commueoflove.Mine.RecordFragment.MessageOfVolunteerFragment;

public class UploadRecordAdapter extends FragmentStateAdapter {

    public UploadRecordAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new MessageOfUrgentFragment();
        }else if(position == 1){
            return new MessageOfResourceFragment();
        }else if(position == 2){
            return new MessageOfDonationFragment();
        }else{
            return new MessageOfVolunteerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
