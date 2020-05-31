package com.android.covid.covidnews.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.covidnews.R;
import com.android.covid.covidnews.ui.adapter.NewsAdapter;
import com.android.covid.covidnews.ui.viewmodel.NewsViewModel;
import com.android.covid.ui.BaseFragment;

public class CovidNewsFragment extends BaseFragment {

    public static final String TAG = CovidNewsFragment.class.getSimpleName();

    private NewsAdapter adapter;
    private NewsViewModel newsViewModel;

    private View parent;
    private View stateLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        observeViewModel();
        return parent;
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater,
                             ViewGroup container) {
        parent = inflater.inflate(R.layout.fragment_covid_news, container, false);

        stateLayout = parent.findViewById(R.id.covid_news_state_layout);
        RecyclerView newsFeedRecyclerView = parent.findViewById(R.id.list_feed);
        newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        adapter = new NewsAdapter(getContext().getApplicationContext());
        newsFeedRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initViewModel() {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_covid_news);
    }

    private void observeViewModel() {
        newsViewModel.getArticleLiveData().observe(getViewLifecycleOwner(),
                pagedList -> adapter.submitList(pagedList));
        newsViewModel.getNetworkState().observe(getViewLifecycleOwner(),
                state -> updateState(stateLayout, state));
    }
}
