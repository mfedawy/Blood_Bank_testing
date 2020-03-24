package com.echoman.bb_splash_cycle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;

import java.util.ArrayList;
import java.util.List;

public class BloodSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<BloodData> bloodDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public BloodSpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<BloodData> bloodDataList, String hint) {
        this.bloodDataList = new ArrayList<>();
        this.bloodDataList.add(new BloodData(0, hint));
        this.bloodDataList.addAll(bloodDataList);
    }

    @Override
    public int getCount() {
        return bloodDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner, null);

        TextView names =  view.findViewById(R.id.spinner_tv_text);

        names.setText(bloodDataList.get(i).getName());
        selectedId = bloodDataList.get(i).getId();

        return view;
    }
}