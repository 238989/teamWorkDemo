package com.example.commueoflove.emergency;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commueoflove.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class EmergencyFragment extends Fragment {






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_emergency, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager = view.findViewById(R.id.viewPage2);
        EmergencyAdapter pageAdapter = new EmergencyAdapter(this);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        String tabText[]=new String[]{"救助信息","资源信息",};
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabText[position])
        ).attach();
    }
}