package com.android.covid.deepdive.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.deepdive.R;
import com.android.covid.deepdive.data.CovidCountryInfo;
import com.android.covid.network.NetworkState;

import java.util.Objects;

public class DeepDiveAdapterUnused extends PagedListAdapter<CovidCountryInfo, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private NetworkState networkState;

    public DeepDiveAdapterUnused() {
        super(CovidCountryInfo.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            View v = layoutInflater.inflate(R.layout.item_network_state, parent, false);
            return new NetworkStateItemViewHolder(v);

        } else {
            View v = layoutInflater.inflate(R.layout.covid_country_info_item, parent, false);
            return new CovidItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CovidItemViewHolder) {

            ((CovidItemViewHolder) holder).bindTo(Objects.requireNonNull(getItem(position)));

        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    public static class CovidItemViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private TextView totalCasesValTv;
        private TextView totalCasesNewValTv;
        private TextView totalCasesActiveValTv;
        private TextView totalCasesDeceasedValTv;
        private TextView totalCasesDeceasedNewValTv;
        private TextView totalCasesRecoveredValTv;

        CovidItemViewHolder(View view) {
            super(view);

            countryName = view.findViewById(R.id.country_name);
            totalCasesValTv = view.findViewById(R.id.total_cases_val_tv);
            totalCasesNewValTv = view.findViewById(R.id.total_cases_new_val_tv);
            totalCasesActiveValTv = view.findViewById(R.id.total_cases_active_val_tv);
            totalCasesDeceasedValTv = view.findViewById(R.id.total_cases_deceased_val_tv);
            totalCasesDeceasedNewValTv = view.findViewById(R.id.total_cases_deceased_new_val_tv);
            totalCasesRecoveredValTv = view.findViewById(R.id.total_cases_recovered_val_tv);
        }

        void bindTo(CovidCountryInfo item) {

            countryName
                    .setText(item.countryName);
            totalCasesValTv
                    .setText(item.totalCases);
            totalCasesNewValTv
                    .setText(item.newCases);
            totalCasesActiveValTv
                    .setText(item.activeCases);
            totalCasesDeceasedValTv
                    .setText(item.totalDeaths);
            totalCasesDeceasedNewValTv
                    .setText(item.newDeaths);
            totalCasesRecoveredValTv
                    .setText(item.totalRecovered);
        }
    }

    public static class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView errorMsg;

        NetworkStateItemViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progress_bar);
            errorMsg = view.findViewById(R.id.error_msg);
        }

        void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                errorMsg.setVisibility(View.VISIBLE);
                errorMsg.setText(networkState.getMsg());
            } else {
                errorMsg.setVisibility(View.GONE);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(String countryName);
    }

    private ItemClickListener onItemClickListener;

    public void setItemClickListener(ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }
}
