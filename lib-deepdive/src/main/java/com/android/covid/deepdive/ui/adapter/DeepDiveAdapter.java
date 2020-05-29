package com.android.covid.deepdive.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.databinding.NetworkItemBinding;
import com.android.covid.deepdive.data.CovidCountryInfo;
import com.android.covid.deepdive.databinding.CountryDetailItemBinding;
import com.android.covid.network.NetworkState;

import java.util.Objects;

public class DeepDiveAdapter extends PagedListAdapter<CovidCountryInfo, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private NetworkState networkState;

    public DeepDiveAdapter() {
        super(CovidCountryInfo.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            NetworkItemBinding headerBinding = NetworkItemBinding.inflate(layoutInflater, parent, false);
            return new NetworkStateItemViewHolder(headerBinding);

        } else {
            CountryDetailItemBinding itemBinding = CountryDetailItemBinding.inflate(layoutInflater, parent, false);
            return new CovidItemViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CovidItemViewHolder) {
            ((CovidItemViewHolder) holder).bindTo(Objects.requireNonNull(getItem(position)));

            ((CovidItemViewHolder) holder).binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(Objects.requireNonNull(getItem(position)).countryName);
            });
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

        private CountryDetailItemBinding binding;

        CovidItemViewHolder(CountryDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(CovidCountryInfo item) {

            binding.countryName
                    .setText(item.countryName);
            binding.totalCasesValTv
                    .setText(item.totalCases);
            binding.totalCasesNewValTv
                    .setText(item.newCases);
            binding.totalCasesActiveValTv
                    .setText(item.activeCases);
            binding.totalCasesDeceasedValTv
                    .setText(item.totalDeaths);
            binding.totalCasesDeceasedNewValTv
                    .setText(item.newDeaths);
            binding.totalCasesRecoveredValTv
                    .setText(item.totalRecovered);
        }
    }

    public static class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkItemBinding binding;

        NetworkStateItemViewHolder(NetworkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                binding.errorMsg.setVisibility(View.VISIBLE);
                binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.setVisibility(View.GONE);
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
