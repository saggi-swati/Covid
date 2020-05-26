package com.android.covid.covidnews.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.covid.covidnews.R;
import com.android.covid.covidnews.databinding.FeedActivityBinding;
import com.android.covid.covidnews.ui.adapter.FeedListAdapter;
import com.android.covid.covidnews.ui.viewmodel.FeedViewModel;
import com.android.covid.ui.BaseFragment;

public class AboutCovidFragment extends BaseFragment {

    private FeedListAdapter adapter;
    private FeedViewModel feedViewModel;
    private FeedActivityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater,
                             ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_notifications, container, false);
        binding.listFeed.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        adapter = new FeedListAdapter(getContext().getApplicationContext());
        binding.listFeed.setAdapter(adapter);
    }

    @Override
    protected void initViewModel() {
        feedViewModel = new FeedViewModel(getActivity().getApplication());
        feedViewModel.getArticleLiveData().observe(getViewLifecycleOwner(), pagedList -> {
            adapter.submitList(pagedList);
        });
        feedViewModel.getNetworkState().observe(getViewLifecycleOwner(), networkState -> {
            adapter.setNetworkState(networkState);
        });
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_about_covid);
    }
}
