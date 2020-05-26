package com.android.covid.deepdive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.covid.deepdive.R;
import com.android.covid.deepdive.databinding.DeepDiveFragmentBinding;
import com.android.covid.deepdive.ui.adapter.DeepDiveAdapter;
import com.android.covid.deepdive.ui.viewmodel.DeepDiveViewModel;
import com.android.covid.model.CountryInfo;
import com.android.covid.ui.BaseFragment;
import com.android.covid.ui.viewmodel.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeepDiveFragment extends BaseFragment implements DeepDiveAdapter.ItemClickListener {

    private DeepDiveAdapter mAdapter;

    private DeepDiveViewModel deepDiveViewModel;
    private CountryViewModel countryDataViewModel;

    private DeepDiveFragmentBinding binding;

    private List<String> mCountryList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_dashboard, container, false);

        setUpRecyclerView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, android.R.id.text1, mCountryList);

        binding.searchEt.setAdapter(adapter);
        binding.searchEt.setOnItemClickListener((parent, view, position, id) -> {
            onItemClick(parent.getAdapter().getItem(position).toString());
            System.out.println(parent.getAdapter().getItem(position));
        });

        observeViewModel();
    }

    private void setUpRecyclerView() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        binding.covidCountryList.setLayoutManager(mLayoutManager);
        mAdapter = new DeepDiveAdapter();
        mAdapter.setItemClickListener(this);
        binding.covidCountryList.setAdapter(mAdapter);
    }

    @Override
    protected void initViewModel() {
        deepDiveViewModel = ViewModelProviders.of(this).get(DeepDiveViewModel.class);
        countryDataViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
    }

    private void observeViewModel() {
        deepDiveViewModel.getAllCountryData().observe(getViewLifecycleOwner(), covidCountrySearch -> {
            mAdapter.submitList(covidCountrySearch);
        });

        deepDiveViewModel.getNetworkState().observe(getViewLifecycleOwner(), networkState -> {
            mAdapter.setNetworkState(networkState);
        });

        countryDataViewModel.getCountryList().observe(getViewLifecycleOwner(), countryList -> {
            for (CountryInfo info :
                    countryList) {
                mCountryList.add(info.countryName);
            }
        });
    }

    @Override
    public void onItemClick(String countryName) {

       /* FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, CountryDetailFragment.getInstance(countryName), CountryDetailFragment.class.getSimpleName());
        ft.addToBackStack(null);
        ft.commit();*/
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_deep_dive);
    }
}
