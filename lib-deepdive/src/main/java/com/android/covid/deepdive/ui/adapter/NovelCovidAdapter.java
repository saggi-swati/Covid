package com.android.covid.deepdive.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.databinding.CountryDetailItemBinding;
import com.covid.util.CovidUtil;

import java.util.List;
import java.util.Objects;

public class NovelCovidAdapter extends RecyclerView.Adapter<NovelCovidAdapter.NovelCovidViewHolder> {

    private List<NovelCovid> dataSet;

    @NonNull
    @Override
    public NovelCovidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        CountryDetailItemBinding itemBinding = CountryDetailItemBinding.inflate(layoutInflater, parent, false);
        return new NovelCovidViewHolder(itemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull NovelCovidViewHolder holder, int position) {
        holder.bindTo(Objects.requireNonNull(dataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    static class NovelCovidViewHolder extends RecyclerView.ViewHolder {

        private CountryDetailItemBinding binding;

        NovelCovidViewHolder(CountryDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(NovelCovid item) {
            binding.countryName
                    .setText(item.countryName);
            binding.totalCasesValTv
                    .setText(CovidUtil.formatNumberText(item.totalCases));
            binding.totalCasesNewValTv
                    .setText(CovidUtil.formatNumberText(item.todayCases));
            binding.totalCasesActiveValTv
                    .setText(CovidUtil.formatNumberText(item.totalActiveCases));
            binding.totalCasesDeceasedValTv
                    .setText(CovidUtil.formatNumberText(item.totalDeaths));
            binding.totalCasesDeceasedNewValTv
                    .setText(CovidUtil.formatNumberText(item.todayDeaths));
            binding.totalCasesRecoveredValTv
                    .setText(CovidUtil.formatNumberText(item.totalRecovered));
        }
    }

    public void setDataSet(List<NovelCovid> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

}
