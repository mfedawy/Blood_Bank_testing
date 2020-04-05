package com.echoman.bb_splash_cycle.Ui.mainui.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.echoman.bb_splash_cycle.Adapter.DonationAdapter;
import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.Helper.OnEndLess;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.api.RetrofitManger;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.echoman.bb_splash_cycle.Helper.GeneralRequest.getSpinnerData;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_APITOKEN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationsFragment extends Fragment {

    SharedPreferences prefs;
    @BindView(R.id.don_recycl)
    RecyclerView donRecycl;
    @BindView(R.id.don_refresh)
    SwipeRefreshLayout donRefresh;
    @BindView(R.id.search_button)
    ImageButton searchButton;
    @BindView(R.id.fragment_donations_sp_blood_type)
    Spinner fragmentDonationsSpBloodType;
    @BindView(R.id.fragment_donations_sp_government)
    Spinner fragmentDonationsSpGovernment;
    private LinearLayoutManager linearLayoutManager;
    private DonationAdapter donationAdapter;
    private List<DonationData> donationList = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private SpinnerAdapter bloodTypesAdapter, governmentsAdapter, citiesAdapter;
    private int bloodTypesSelectedId = 0, governmentSelectedId = 0, citiesSelectedId = 0;
    private AdapterView.OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donations, container, false);
        ButterKnife.bind(this, view);

        setSpinner();


        linearLayoutManager = new LinearLayoutManager(getActivity());
        donRecycl.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getDonations(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        donRecycl.addOnScrollListener(onEndLess);
        donationAdapter = new DonationAdapter(getContext(), donationList);
        donRecycl.setAdapter(donationAdapter);
        getDonations(1);
        donRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDonations(1);
            }
        });
        return view;
    }

    private void getDonations(int page) {
        String api = loadData(getActivity(), USER_APITOKEN);
        // Toast.makeText(getContext(), api, Toast.LENGTH_LONG).show();

        getRetrofitClient().getalldonations(api, page).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        if (page == 1) {
                            onEndLess.current_page = 1;
                            onEndLess.previous_page = 1;
                            onEndLess.previousTotal = 0;
                            donationList = new ArrayList<>();
                            donationAdapter = new DonationAdapter(getActivity(), donationList);
                            donRecycl.setAdapter(donationAdapter);
                        }

                        maxPage = response.body().getData().getLastPage();
                        donationList.addAll(response.body().getData().getData());
                        donationAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }
        });
    }

    private void onCall() {
        //     String api = prefs.getString(SharedPreferencesManger.USER_APITOKEN, "No name defined");//"No name defined" is the default value.

        //  Toast.makeText(getContext(), api, Toast.LENGTH_LONG).show();
//        GeneralRequest.getalldonations(getActivity(),getRetrofitClient().getdonations("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl") );
    }


    private void setSpinner() {
        bloodTypesAdapter = new SpinnerAdapter(getActivity());
        governmentsAdapter = new SpinnerAdapter(getActivity());
        citiesAdapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getActivity(), fragmentDonationsSpBloodType, bloodTypesAdapter, getString(R.string.select_blood_type),
                getRetrofitClient().getBloodTypes(), null, bloodTypesSelectedId, true);


        getSpinnerData(getActivity(), fragmentDonationsSpGovernment, governmentsAdapter, getString(R.string.select_government),
                getRetrofitClient().getGoves(), null, governmentSelectedId, true);
    }

    @OnClick(R.id.search_button)
    public void onViewClicked() {


        search(1);


    }

    private void search(int page) {
        governmentSelectedId = governmentsAdapter.selectedId;
        bloodTypesSelectedId = bloodTypesAdapter.selectedId;
        String api = loadData(getActivity(), USER_APITOKEN);
        if (bloodTypesSelectedId != 0 && governmentSelectedId != 0) {

            getRetrofitClient().getadonationsfilter(api, bloodTypesSelectedId, governmentSelectedId, 1).enqueue(new Callback<Donation>() {
                @Override
                public void onResponse(Call<Donation> call, Response<Donation> response) {
                    try {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            if (page == 1) {
                                onEndLess.current_page = 1;
                                onEndLess.previous_page = 1;
                                onEndLess.previousTotal = 0;
                                donationList = new ArrayList<>();
                                donationAdapter = new DonationAdapter(getActivity(), donationList);
                                donRecycl.setAdapter(donationAdapter);
                            }

                            maxPage = response.body().getData().getLastPage();
                            donationList.addAll(response.body().getData().getData());
                            donationAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Donation> call, Throwable t) {

                }
            });


        }
        else {
            Toast.makeText(getContext(), "أكمل البيانات", Toast.LENGTH_LONG).show();

        }
    }
}
