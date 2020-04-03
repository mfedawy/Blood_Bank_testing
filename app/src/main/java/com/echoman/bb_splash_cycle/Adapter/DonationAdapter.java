package com.echoman.bb_splash_cycle.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.echoman.bb_splash_cycle.Helper.BaseActivity;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.client.ClientData;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadUserData;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    private Context context;
    public List<DonationData> donationdata_list;
    //  private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private ClientData clientData;
    private String lang;
//    private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    public DonationAdapter(Context context, List<DonationData> donationdata_list) {
        this.donationdata_list = donationdata_list;
        this.context = context;
        //    this.activity = (BaseActivity) activity;
        //       clientData = loadUserData(activity);
        //   lang = "eg";

    }

    @Override
    public DonationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donation_item,
                parent, false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view, context, donationdata_list);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonationAdapter.ViewHolder holder, int position) {
        // Receive data as string (the description)


        //  setData(holder, position);
        String donation_id = String.valueOf(donationdata_list.get(position).getId());
        String name = donationdata_list.get(position).getPatientName();
        String hospital = donationdata_list.get(position).getHospitalName();
        String city = donationdata_list.get(position).getCity().getName();
        String bloodtype = donationdata_list.get(position).getBloodType().getName();

        holder.setname(name);
        holder.sethos(hospital);
        holder.setblood(bloodtype);
        holder.setcity(city);

   /*     recyclerName.setText(name);
        recyclerHosp.setText(hospital);
        recyclerCity.setText(city);
        recyclerBloodtype.setText(bloodtype);
*/
        //setData(holder, position);
        //setAction(holder, position);

    }

    @Override
    public int getItemCount() {
        return donationdata_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final List<DonationData> donationDataList;
        private View mView;

        private TextView cityView, nameView, hosView, bloodView;


        private Context context;

        private String property_id;


        public ViewHolder(View itemView, Context context, List<DonationData> donationDataList) {
            super(itemView);
            this.donationDataList = donationDataList;
            this.context = context;
            itemView.setOnClickListener(this);

            mView = itemView;
        }

        //-------------When user clicks on a card----------------///
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DonationData donationData = this.donationDataList.get(position);

        }

      public void setcity(String city) {
            cityView=mView.findViewById(R.id.recycler_city);
            cityView.setText(city);

        }

        public void sethos(String hospital) {
            hosView=mView.findViewById(R.id.recycler_hosp);
            hosView.setText(hospital);
        }

        public void setblood(String bloodtype) {
            bloodView=mView.findViewById(R.id.recycler_blood_type);
            bloodView.setText(bloodtype);
        }

        public void setname(String name) {
            nameView=mView.findViewById(R.id.recycler_name);
           nameView.setText(name);
        }




    }



}
