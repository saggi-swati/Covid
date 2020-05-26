package com.android.covid.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.android.covid.home.R;
import com.android.covid.home.data.CovidStats;
import com.android.covid.home.databinding.ActiveCasesBinding;
import com.android.covid.home.databinding.ClosedCasesBinding;
import com.android.covid.home.databinding.FragmentHomeBinding;
import com.android.covid.home.ui.viewmodel.HomeViewModel;
import com.android.covid.ui.BaseFragment;

import org.jetbrains.annotations.Nullable;

import static com.covid.util.Utility.formatDate;
import static com.covid.util.Utility.formatPercentString;


public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;

    private FragmentHomeBinding mDatabinding;
    private ActiveCasesBinding mActiveCasesBinding;
    private ClosedCasesBinding mClosedCasesBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mDatabinding.getRoot();
    }

    @Override
    protected void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {

        mDatabinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mActiveCasesBinding = mDatabinding.activeCasesLayout;
        mClosedCasesBinding = mDatabinding.closeCasesLayout;

        mDatabinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            homeViewModel.fetchLatestCovidStats();
            mDatabinding.swipeRefreshLayout.setRefreshing(false);
        });
        observeViewModel();
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_home);
    }

    private void observeViewModel() {
        homeViewModel.getCovidSummary().observe(getViewLifecycleOwner(), this::bindData);
    }

    private void bindData(@NonNull CovidStats covidStats) {
        mDatabinding.swipeRefreshLayout.setRefreshing(false);
        mDatabinding.totalCaseValTv.setText(covidStats.data.totalCases);

        mActiveCasesBinding.totalActiveCasesValTv
                .setText(covidStats.data.currentlyInfected);
        mActiveCasesBinding.totalCaseActiveMildValTv
                .setText(covidStats.data.mildConditionActiveCases);
        mActiveCasesBinding.totalCaseActiveSeriousValTv
                .setText(covidStats.data.criticalConditionActiveCases);

        mActiveCasesBinding.totalCaseActiveMildValPerTv
                .setText(formatPercentString(covidStats.data.activeCasesMildPercentage));
        mActiveCasesBinding.totalCaseActiveSeriousValPerTv
                .setText(formatPercentString(covidStats.data.activeCasesCriticalPercentage));

        mClosedCasesBinding.totalCaseClosedValTv
                .setText(covidStats.data.casesWithOutcome);
        mClosedCasesBinding.totalCaseDeceasedValTv
                .setText(covidStats.data.deathCases);
        mClosedCasesBinding.totalCaseRecoveredValTv
                .setText(covidStats.data.recoveredClosedCases);

        mClosedCasesBinding.totalCaseDeceasedValPerTv
                .setText(formatPercentString(covidStats.data.closedCasesDeathPercentage));
        mClosedCasesBinding.totalCaseRecoveredValPerTv
                .setText(formatPercentString(covidStats.data.closedCasesRecoveredPercentage));

        mDatabinding.lastUpdateTv
                .setText(String.format("%s%s",
                        getResources().getString(R.string.last_updated),
                        formatDate(covidStats.data.lastUpdate)));
    }
}
