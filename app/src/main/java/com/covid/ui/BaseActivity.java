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

    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.covid_base_layout);

        setSupportActionBar(binding.bhCollectionToolbar);
        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment homeFragment = new HomeFragment();
        active = homeFragment;

        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_layout, homeFragment, HomeFragment.TAG).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment homeFragment = fm.findFragmentByTag(HomeFragment.TAG);
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        fm.beginTransaction().hide(active).add(R.id.nav_host_layout, homeFragment, HomeFragment.TAG).commit();
                    } else
                        fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    setTitle(getString(R.string.title_home));
                    return true;

                case R.id.navigation_deep_dive:
                    Fragment deepDiveFragment = fm.findFragmentByTag(DeepDiveFragment.TAG);
                    if (deepDiveFragment == null) {
                        deepDiveFragment = new DeepDiveFragment();
                        fm.beginTransaction().hide(active).add(R.id.nav_host_layout, deepDiveFragment, DeepDiveFragment.TAG).commit();
                    } else
                        fm.beginTransaction().hide(active).show(deepDiveFragment).commit();
                    active = deepDiveFragment;
                    setTitle(getString(R.string.title_deep_dive));
                    return true;

                case R.id.navigation_covid_news:
                    Fragment covidNewsFragment = fm.findFragmentByTag(CovidNewsFragment.TAG);
                    if (covidNewsFragment == null) {
                        covidNewsFragment = new CovidNewsFragment();
                        fm.beginTransaction().hide(active).add(R.id.nav_host_layout, covidNewsFragment, CovidNewsFragment.TAG).commit();
                    } else
                        fm.beginTransaction().hide(active).show(covidNewsFragment).commit();
                    active = covidNewsFragment;
                    setTitle(getString(R.string.title_covid_news));
                    return true;
            }
            return false;
        }
    };
}
