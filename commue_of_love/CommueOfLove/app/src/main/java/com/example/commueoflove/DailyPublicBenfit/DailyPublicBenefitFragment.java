package com.example.commueoflove.DailyPublicBenfit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commueoflove.R;
import com.example.commueoflove.emergency.EmergencyAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyPublicBenefitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyPublicBenefitFragment extends Fragment {


    public DailyPublicBenefitFragment() {
        // Required empty public constructor
    }


    public static DailyPublicBenefitFragment newInstance(String param1, String param2) {
        DailyPublicBenefitFragment fragment = new DailyPublicBenefitFragment();
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
        View view = inflater.inflate(R.layout.fragment_daily_public_benefit, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 viewPager = view.findViewById(R.id.viewPage2);
        BenefitAdapter pageAdapter = new BenefitAdapter(this);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        String tabText[]=new String[]{"物资捐赠","志愿招募",};
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabText[position])
        ).attach();
    }
}