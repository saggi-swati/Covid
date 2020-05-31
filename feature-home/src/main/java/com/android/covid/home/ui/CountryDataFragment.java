package com.android.covid.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.ui.viewmodel.DeepDiveViewModel;
import com.android.covid.home.R;
import com.android.covid.ui.BaseFragment;
import com.covid.util.AndroidUtils;
import com.covid.util.CovidUtil;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

public class CountryDataFragment extends BaseFragment {

    private View parent;
    private DeepDiveViewModel deepDiveViewModel;

    private TextView countryName;
    private ImageView countryFlag;
    private TextView totalCasesValTv;
    private TextView totalCasesNewValTv;
    private TextView totalCasesActiveValTv;
    private TextView totalCasesDeceasedValTv;
    private TextView totalCasesDeceasedNewValTv;
    private TextView totalCasesRecoveredValTv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        return parent;
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {

        parent = inflater.inflate(R.layout.covid_deep_dive_item, container, false);

        countryName = parent.findViewById(com.android.covid.deepdive.R.id.country_name);
        countryFlag = parent.findViewById(com.android.covid.deepdive.R.id.country_flag);
        totalCasesValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_val_tv);
        totalCasesNewValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_new_val_tv);
        totalCasesActiveValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_active_val_tv);
        totalCasesDeceasedValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_deceased_val_tv);
        totalCasesDeceasedNewValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_deceased_new_val_tv);
        totalCasesRecoveredValTv = parent.findViewById(com.android.covid.deepdive.R.id.total_cases_recovered_val_tv);

        observeViewModel();
    }

    @Override
    protected void initViewModel() {
        deepDiveViewModel = new ViewModelProvider(this).get(DeepDiveViewModel.class);
    }

    private void observeViewModel() {
        deepDiveViewModel
                .getSpecificCountryData(AndroidUtils.getCarrierCountry(getContext()))
                .observe(getViewLifecycleOwner(), this::bindTo);
    }

    private void bindTo(NovelCovid item) {

        Picasso.get().load(item.countryInfo.countryFlag).resize(48, 48).into(countryFlag);
        countryName
                .setText(item.countryName);
        totalCasesValTv
                .setText(CovidUtil.formatNumberText(item.totalCases));
        totalCasesNewValTv
                .setText(CovidUtil.formatNumberText(item.todayCases));
        totalCasesActiveValTv
                .setText(CovidUtil.formatNumberText(item.totalActiveCases));
        totalCasesDeceasedValTv
                .setText(CovidUtil.formatNumberText(item.totalDeaths));
        totalCasesDeceasedNewValTv
                .setText(CovidUtil.formatNumberText(item.todayDeaths));
        totalCasesRecoveredValTv
                .setText(CovidUtil.formatNumberText(item.totalRecovered));
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_home);
    }
}
