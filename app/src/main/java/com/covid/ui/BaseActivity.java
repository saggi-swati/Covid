package com.covid.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.android.covid.ui.viewmodel.CountryViewModel;
import com.covid.R;
import com.covid.databinding.BaseActivityBinding;

public class BaseActivity extends AppCompatActivity {

    CountryViewModel countryDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.covid_base_layout);

        setSupportActionBar(binding.bhCollectionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);

        countryDataViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryDataViewModel.getCountryList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
