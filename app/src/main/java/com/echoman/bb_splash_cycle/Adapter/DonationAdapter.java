package com.echoman.bb_splash_cycle.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.home.DonationsDetailsFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.home.HomeFragment;
import com.echoman.bb_splash_cycle.data.model.client.ClientData;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    @BindView(R.id.recycler_details)
    ImageButton recyclerDetails;
    @BindView(R.id.recycler_call)
    ImageButton recyclerCall;
    private Context context;
    private Activity activity;
    public List<DonationData> donationdata_list;
    //  private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private ClientData clientData;
    private String lang;
  //  public int donationId;
  int donation_id;
//    private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    public DonationAdapter(Context context, List<DonationData> donationdata_list) {
        this.donationdata_list = donationdata_list;
        this.context = context;
        //    this.activity = (BaseActivity) activity;
        //       clientData = loadUserData(activity);
        //   lang = "eg";

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donation_item,
                parent, false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view, context, donationdata_list);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Receive data as string (the description)


        //  setData(holder, position);
       donation_id =donationdata_list.get(position).getId();
        String name = donationdata_list.get(position).getPatientName();
        String hospital = donationdata_list.get(position).getHospitalName();
        String city = donationdata_list.get(position).getCity().getName();
        String bloodtype = donationdata_list.get(position).getBloodType().getName();

        holder.setname(name);
        holder.sethos(hospital);
        holder.setblood(bloodtype);
        holder.setcity(city);
        holder.setDetail_button(donation_id);

   /*     recyclerName.setText(name);
        recyclerHosp.setText(hospital);
        recyclerCity.setText(city);
        recyclerBloodtype.setText(bloodtype);
*/
        //setData(holder, position);
        //setAction(holder, position);

    }

    private void openDetails(int donation_id) {
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        DonationsDetailsFragment donationsDetailsFragment = new DonationsDetailsFragment();
        donationsDetailsFragment.donationId=donation_id;
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, donationsDetailsFragment).addToBackStack(null).commit();

    }

    @Override
    public int getItemCount() {
        return donationdata_list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final List<DonationData> donationDataList;
        private View mView;

        private TextView cityView, nameView, hosView, bloodView;
        private Button detail_button, call_button;

        private Context context;

        private String property_id;


        public ViewHolder(View itemView, Context context, List<DonationData> donationDataList) {
            super(itemView);
            this.donationDataList = donationDataList;
            this.context = context;
            itemView.setOnClickListener(this);
           // detail_button.setOnClickListener(this);
            mView = itemView;
        }


        //-------------When user clicks on a card----------------///
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DonationData donationData = this.donationDataList.get(position);

        }

        public void setcity(String city) {
            cityView = mView.findViewById(R.id.recycler_city);
            cityView.setText(city);

        }

        public void sethos(String hospital) {
            hosView = mView.findViewById(R.id.recycler_hosp);
            hosView.setText(hospital);
        }

        public void setblood(String bloodtype) {
            bloodView = mView.findViewById(R.id.recycler_blood_type);
            bloodView.setText(bloodtype);
        }

        public void setname(String name) {
            nameView = mView.findViewById(R.id.recycler_name);
            nameView.setText(name);
        }


        public void setDetail_button(int donation) {
            detail_button = mView.findViewById(R.id.recycler_details);
            detail_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                    DonationsDetailsFragment donationsDetailsFragment = new DonationsDetailsFragment();
                    donationsDetailsFragment.donationId=donation;
                    fragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment, donationsDetailsFragment).addToBackStack(null).commit();
                }
            });

        }
    }


}
