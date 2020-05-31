package com.android.covid.deepdive.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.deepdive.R;
import com.android.covid.deepdive.data.NovelCovid;
import com.covid.util.CovidUtil;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class DeepDiveAdapter extends RecyclerView.Adapter<DeepDiveAdapter.NovelCovidViewHolder> {

    private List<NovelCovid> dataSet;

    @NonNull
    @Override
    public NovelCovidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View v = layoutInflater.inflate(R.layout.covid_deep_dive_item, parent, false);
        return new NovelCovidViewHolder(v);

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

        private TextView countryName;
        private ImageView countryFlag;
        private TextView totalCasesValTv;
        private TextView totalCasesNewValTv;
        private TextView totalCasesActiveValTv;
        private TextView totalCasesDeceasedValTv;
        private TextView totalCasesDeceasedNewValTv;
        private TextView totalCasesRecoveredValTv;

        NovelCovidViewHolder(View view) {
            super(view);

            countryName = view.findViewById(R.id.country_name);
            countryFlag = view.findViewById(R.id.country_flag);
            totalCasesValTv = view.findViewById(R.id.total_cases_val_tv);
            totalCasesNewValTv = view.findViewById(R.id.total_cases_new_val_tv);
            totalCasesActiveValTv = view.findViewById(R.id.total_cases_active_val_tv);
            totalCasesDeceasedValTv = view.findViewById(R.id.total_cases_deceased_val_tv);
            totalCasesDeceasedNewValTv = view.findViewById(R.id.total_cases_deceased_new_val_tv);
            totalCasesRecoveredValTv = view.findViewById(R.id.total_cases_recovered_val_tv);
        }

        void bindTo(NovelCovid item) {

            Picasso.get().load(item.countryInfo.countryFlag).resize(48, 48).into(countryFlag);
            countryName
                    .setText(item.countryName);
            totalCasesValTv
                    .setText(CovidUtil.formatNumberText(item.totalCases));
            totalCasesNewValTv
                    .setText(CovidUtil.formatNumberText(item.todayCases));
            totalCasesActiveValTv
                    .setText(CovidUtil.formatNumberText(item.totalActiveCases));
            totalCasesDeceasedValTv
                    .setText(CovidUtil.formatNumberText(item.totalDeaths));
            totalCasesDeceasedNewValTv
                    .setText(CovidUtil.formatNumberText(item.todayDeaths));
            totalCasesRecoveredValTv
                    .setText(CovidUtil.formatNumberText(item.totalRecovered));
        }
    }

    public void setDataSet(List<NovelCovid> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(String countryName);
    }

    private ItemClickListener onItemClickListener;

    public void setItemClickListener(ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }

}
