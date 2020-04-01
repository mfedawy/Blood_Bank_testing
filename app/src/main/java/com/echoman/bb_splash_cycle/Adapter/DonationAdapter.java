package com.echoman.bb_splash_cycle.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;

import java.util.List;

import butterknife.ButterKnife;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    public List<DonationData> donationdata_list;

//    private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    public DonationAdapter(Context context,List<DonationData>donationdata_list){
        this.donationdata_list=donationdata_list;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donation_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationAdapter.ViewHolder holder, int position) {
        // Receive data as string (the description)
        String donation_id= String.valueOf(donationdata_list.get(position).getId());
        String name = donationdata_list.get(position).getPatientName();
        String hospital = donationdata_list.get(position).getHospitalName();
        String city = donationdata_list.get(position).getCity().getName();
        String bloodtype = donationdata_list.get(position).getBloodType().getName();


        holder.setDate();
        //setData(holder, position);
        //setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return donationdata_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setDate() {
        }
    }


}
