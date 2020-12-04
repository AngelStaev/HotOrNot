package com.example.hotornot.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentHomeBinding;
import com.example.hotornot.view.adapters.TabAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabAdapter fragmentAdapter = new TabAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        binding.viewpager.setAdapter(fragmentAdapter);
        binding.viewpager.setUserInputEnabled(false);

        TabLayout tabLayout = binding.tabs;
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabs, binding.viewpager, (tab, position) -> {
            Log.e("ASDASD", "" + position);
            if (position == 0) {
                tab.setText("OVERALL");
            } else {
                tab.setText("DETAILS");
            }

        });
        mediator.attach();
    }

}