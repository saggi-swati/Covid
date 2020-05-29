package com.android.covid.deepdive.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.databinding.DeepDiveFragmentBinding;
import com.android.covid.deepdive.ui.adapter.DeepDiveAdapter;
import com.android.covid.deepdive.ui.adapter.NovelCovidAdapter;
import com.android.covid.deepdive.ui.viewmodel.NovelCovidViewModel;
import com.android.covid.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class DeepDiveFragment extends BaseFragment implements DeepDiveAdapter.ItemClickListener {

    private NovelCovidAdapter mAdapter;

    private NovelCovidViewModel deepDiveViewModel;

    private DeepDiveFragmentBinding binding;

    private List<String> mCountryList = new ArrayList<>();

    private List<NovelCovid> novelCovids = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.covid_deep_dive_fragment, container, false);

        setUpRecyclerView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, android.R.id.text1, mCountryList);

        binding.searchEt.setAdapter(adapter);

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() == 0)
                    mAdapter.setDataSet(novelCovids);

                if (s != null && s.length() >= 3) {
                    mAdapter.setDataSet(filter(novelCovids, s.toString()));
                }
            }
        });

        observeViewModel();
    }

    private static List<NovelCovid> filter(List<NovelCovid> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<NovelCovid> filteredModelList = new ArrayList<>();
        for (NovelCovid model : models) {
            final String text = model.countryName.toLowerCase();
            if (text.startsWith(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void setUpRecyclerView() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        binding.covidCountryList.setLayoutManager(mLayoutManager);
        mAdapter = new NovelCovidAdapter();
        binding.covidCountryList.setAdapter(mAdapter);
    }

    @Override
    protected void initViewModel() {
        deepDiveViewModel = ViewModelProviders.of(this).get(NovelCovidViewModel.class);
    }

    private void observeViewModel() {
        deepDiveViewModel.getNovelCovidAllCountryData().observe(getViewLifecycleOwner(), covidCountrySearch -> {
            novelCovids.clear();
            novelCovids.addAll(covidCountrySearch);
            mAdapter.setDataSet(novelCovids);
            for (NovelCovid info :
                    covidCountrySearch) {
                mCountryList.add(info.countryName);
            }
        });
    }

    @Override
    public void onItemClick(String countryName) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_deep_dive);
    }
}
