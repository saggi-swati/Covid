package com.android.covid.covidnews.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.covid.covidnews.R;
import com.android.covid.covidnews.databinding.CovidNewsBinding;
import com.android.covid.covidnews.ui.adapter.NewsAdapter;
import com.android.covid.covidnews.ui.viewmodel.NewsViewModel;
import com.android.covid.ui.BaseFragment;

public class CovidNewsFragment extends BaseFragment {

    private NewsAdapter adapter;
    private NewsViewModel newsViewModel;
    private CovidNewsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        observeViewModel();
        return binding.getRoot();
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater,
                             ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_covid_news, container, false);
        binding.listFeed.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        adapter = new NewsAdapter(getContext().getApplicationContext());
        binding.listFeed.setAdapter(adapter);
    }

    @Override
    protected void initViewModel() {
        newsViewModel = new NewsViewModel(getActivity().getApplication());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_about_covid);
    }

    private void observeViewModel() {
        newsViewModel.getArticleLiveData().observe(getViewLifecycleOwner(), pagedList -> {
            adapter.submitList(pagedList);
        });
        newsViewModel.getNetworkState().observe(getViewLifecycleOwner(), networkState -> {
            adapter.setNetworkState(networkState);
        });
    }
}
