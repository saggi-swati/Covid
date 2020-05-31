package com.covid.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.covid.covidnews.ui.CovidNewsFragment;
import com.android.covid.deepdive.ui.DeepDiveFragment;
import com.android.covid.home.ui.HomeFragment;
import com.covid.R;
import com.covid.databinding.BaseActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    FragmentManager fm;
    Fragment homeFragment;
    Fragment deepDiveFragment;
    Fragment covidNewsFragment;
    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.covid_base_layout);

        setSupportActionBar(binding.bhCollectionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        homeFragment = new HomeFragment();
        deepDiveFragment = new DeepDiveFragment();
        covidNewsFragment = new CovidNewsFragment();
        fm = getSupportFragmentManager();
        active = homeFragment;

        fm.beginTransaction().add(R.id.nav_host_layout, covidNewsFragment, HomeFragment.TAG).hide(covidNewsFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_layout, deepDiveFragment, DeepDiveFragment.TAG).hide(deepDiveFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_layout, homeFragment, CovidNewsFragment.TAG).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;

                case R.id.navigation_deep_dive:
                    fm.beginTransaction().hide(active).show(deepDiveFragment).commit();
                    active = deepDiveFragment;
                    return true;

                case R.id.navigation_covid_news:
                    fm.beginTransaction().hide(active).show(covidNewsFragment).commit();
                    active = covidNewsFragment;
                    return true;
            }
            return false;
        }
    };
}
