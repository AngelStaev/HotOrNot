package com.example.hotornot.view.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hotornot.view.fragments.DetailsForecastTabFragment;
import com.example.hotornot.view.fragments.OverallForecastTabFragment;

public class TabAdapter extends FragmentStateAdapter {

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public TabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new OverallForecastTabFragment();
            case 1: return new DetailsForecastTabFragment();
            default: throw new IllegalArgumentException("There are not that much fragments");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
