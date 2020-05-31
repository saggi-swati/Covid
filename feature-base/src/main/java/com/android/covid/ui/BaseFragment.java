package com.android.covid.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.covid.R;
import com.android.covid.network.State;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

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

    protected abstract void initViews(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initViewModel();

    protected abstract String getTitle();

    protected void updateState(View v, State state) {

        if (v == null)
            Log.e(TAG, "Network state view is null");

        if (state == null)
            Log.e(TAG, "Network state is null");

        assert state != null;
        switch (state.getStatus()) {
            case FAILED:
                assert v != null;
                v.setVisibility(View.VISIBLE);
                v.findViewById(R.id.covid_progressbar).setVisibility(View.GONE);
                v.findViewById(R.id.covid_error_tv).setVisibility(View.VISIBLE);
                ((TextView) v.findViewById(R.id.covid_error_tv)).setText(state.getMsg());
                break;
            case LOADING:
                assert v != null;
                v.setVisibility(View.VISIBLE);
                v.findViewById(R.id.covid_progressbar).setVisibility(View.VISIBLE);
                v.findViewById(R.id.covid_error_tv).setVisibility(View.GONE);
                break;
            case SUCCESS:
                assert v != null;
                v.setVisibility(View.GONE);
                break;
        }
    }
}