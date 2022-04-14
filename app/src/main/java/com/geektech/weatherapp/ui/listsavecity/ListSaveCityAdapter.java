package com.geektech.weatherapp.ui.listsavecity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.weatherapp.common.OnClick;
import com.geektech.weatherapp.data.remote.dto.MainResponse;
import com.geektech.weatherapp.databinding.ItemListCityBinding;

import java.util.ArrayList;
import java.util.List;

public class ListSaveCityAdapter extends RecyclerView.Adapter<ListSaveCityAdapter.ViewHolder> {

    private OnClick click;
    private List<MainResponse> mainResponseList = new ArrayList<>();

    public void setMainResponseList(List<MainResponse> mainResponseList) {
        this.mainResponseList = (ArrayList<MainResponse>) mainResponseList;
        notifyDataSetChanged();
    }

    public void setClick(OnClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListCityBinding binding = ItemListCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mainResponseList.get(position));
        holder.itemView.setOnClickListener(view -> {
            click.click((mainResponseList.get(holder.getAdapterPosition())).getIdDao());
        });
    }

    @Override
    public int getItemCount() {
        return mainResponseList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ItemListCityBinding binding;

        public ViewHolder(@NonNull ItemListCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MainResponse mainResponse) {
            binding.textTime.setText(mainResponse.getName() + "");
        }
    }
}
