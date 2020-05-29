package com.android.covid.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.covid.home.R;
import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.ui.viewmodel.HomeViewModel;
import com.android.covid.ui.BaseFragment;
import com.covid.util.CovidUtil;

import org.jetbrains.annotations.Nullable;


public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTotalCasesTv;
    private TextView mTotalCountryTv;

    private TextView mTotalClosedCasesTv;
    private TextView mTotalRecoveredCasesTv;
    private TextView mTotalDeceasedCasesTv;
    private TextView mTotalRecoveredPercentTv;
    private TextView mTotalDeceasedPercentTv;

    private TextView mTotalActiveCasesTv;
    private TextView mTotalMildCasesTv;
    private TextView mTotalSeriousCasesTv;
    private TextView mTotalActiveSeriousPercentTv;
    private TextView mTotalActiveMildPercentTv;

    private View parent;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        return parent;
    }

    @Override
    protected void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    protected void initViews(@Nullable LayoutInflater inflater, @Nullable ViewGroup container) {

        parent = inflater.inflate(R.layout.fragment_home, container, false);

        mSwipeRefreshLayout = parent.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            homeViewModel.fetchLatestCovidStats();
        });
        mTotalCasesTv = parent.findViewById(R.id.total_case_val_tv);
        mTotalCountryTv = parent.findViewById(R.id.total_countries_val_tv);

        mTotalClosedCasesTv = parent.findViewById(R.id.total_case_closed_val_tv);
        mTotalRecoveredCasesTv = parent.findViewById(R.id.total_case_recovered_val_tv);
        mTotalDeceasedCasesTv = parent.findViewById(R.id.total_case_deceased_val_tv);
        mTotalRecoveredPercentTv = parent.findViewById(R.id.total_case_recovered_val_per_tv);
        mTotalDeceasedPercentTv = parent.findViewById(R.id.total_case_deceased_val_per_tv);

        mTotalActiveCasesTv = parent.findViewById(R.id.total_active_cases_val_tv);
        mTotalMildCasesTv = parent.findViewById(R.id.total_case_active_mild_val_tv);
        mTotalSeriousCasesTv = parent.findViewById(R.id.total_case_active_serious_val_tv);
        mTotalActiveSeriousPercentTv = parent.findViewById(R.id.total_case_active_serious_val_per_tv);
        mTotalActiveMildPercentTv = parent.findViewById(R.id.total_case_active_mild_val_per_tv);


        observeViewModel();
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_home);
    }

    private void observeViewModel() {
        homeViewModel.getCovidSummary().observe(getViewLifecycleOwner(), this::bindData);
        homeViewModel.getIsLoading().observe(getViewLifecycleOwner(), value ->
                mSwipeRefreshLayout.setRefreshing(value));
    }

    private void bindData(@NonNull NovelCovidDetail detail) {

        mTotalCasesTv
                .setText(CovidUtil.formatNumberText(detail.getTotalCases()));

        String titleString = String.format(getString(R.string.total_affected_countries),
                CovidUtil.formatNumberText(detail.getTotalAffectedCountries()));

        mTotalCountryTv.setText(titleString);

        long totalActiveCases = detail.getTotalCases() - detail.getTotalRecovered() - detail.getTotalDeceased();

        mTotalActiveCasesTv
                .setText(CovidUtil.formatNumberText(totalActiveCases));

        mTotalMildCasesTv
                .setText(CovidUtil.formatNumberText(totalActiveCases - detail.getTotalSerious()));
        mTotalActiveMildPercentTv
                .setText(CovidUtil.formatPercent(totalActiveCases - detail.getTotalSerious(), totalActiveCases));

        mTotalSeriousCasesTv
                .setText(CovidUtil.formatNumberText(detail.getTotalSerious()));
        mTotalActiveSeriousPercentTv
                .setText(CovidUtil.formatPercent(detail.getTotalSerious(),
                        totalActiveCases));

        mTotalClosedCasesTv
                .setText(CovidUtil.formatNumberText(detail.getTotalRecovered() + detail.getTotalDeceased()));
        mTotalDeceasedCasesTv
                .setText(CovidUtil.formatNumberText(detail.getTotalDeceased()));
        mTotalRecoveredCasesTv
                .setText(CovidUtil.formatNumberText(detail.getTotalRecovered()));

        mTotalDeceasedPercentTv
                .setText(CovidUtil.formatPercent(detail.getTotalDeceased(),
                        detail.getTotalRecovered() + detail.getTotalDeceased()));
        mTotalRecoveredPercentTv
                .setText(CovidUtil.formatPercent(detail.getTotalRecovered(),
                        detail.getTotalRecovered() + detail.getTotalDeceased()));
    }
}
