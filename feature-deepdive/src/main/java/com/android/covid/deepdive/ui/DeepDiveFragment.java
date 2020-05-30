package com.android.covid.deepdive.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.deepdive.R;
import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.ui.adapter.DeepDiveAdapter;
import com.android.covid.deepdive.ui.viewmodel.DeepDiveViewModel;
import com.android.covid.ui.BaseFragment;
import com.covid.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class DeepDiveFragment extends BaseFragment {

    private DeepDiveAdapter mAdapter;

    private DeepDiveViewModel deepDiveViewModel;

    private View parent;
    private View stateLayout;

    private List<String> mCountryList = new ArrayList<>();
    private List<NovelCovid> novelCovids = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return parent;
    }

    @Override
    protected void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        parent = inflater.inflate(R.layout.covid_deep_dive_fragment, container, false);

        stateLayout = parent.findViewById(R.id.covid_deep_dive_state_layout);
        setUpRecyclerView();
        setupCountryListFilter();
        observeViewModel();
    }

    @Override
    protected void initViewModel() {
        deepDiveViewModel = new ViewModelProvider(this).get(DeepDiveViewModel.class);
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

        deepDiveViewModel.getIsLoading().observe(getViewLifecycleOwner(),
                value -> updateState(stateLayout, value));
    }

    private void setUpRecyclerView() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView covidCountryRecyclerView = parent.findViewById(R.id.covid_country_list);
        covidCountryRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DeepDiveAdapter();
        covidCountryRecyclerView.setAdapter(mAdapter);
    }

    private void setupCountryListFilter() {

        AutoCompleteTextView searchEt = parent.findViewById(R.id.search_et);
        searchEt.setOnItemClickListener(mItemClickListener);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, android.R.id.text1, mCountryList);
        searchEt.setAdapter(adapter);
        searchEt.addTextChangedListener(mFilterListTextWatcher);
    }

    private AdapterView.OnItemClickListener mItemClickListener = (parent, view, position, id) ->
            SystemUtils.
                    inputMethodManager(parent.getContext()).
                    hideSoftInputFromWindow(parent.getApplicationWindowToken(), 0);

    private TextWatcher mFilterListTextWatcher = new TextWatcher() {
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

            if (s != null && s.length() >= 2) {
                mAdapter.setDataSet(filter(novelCovids, s.toString()));
            }
        }
    };

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

    @Override
    protected String getTitle() {
        return getString(R.string.title_deep_dive);
    }
}
