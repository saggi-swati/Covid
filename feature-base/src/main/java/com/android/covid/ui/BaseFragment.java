package com.android.covid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        initViewModel();
        initViews(inflater, container);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(getTitle());
    }

    protected abstract void initViews(@Nullable LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initViewModel();

    protected abstract String getTitle();
}