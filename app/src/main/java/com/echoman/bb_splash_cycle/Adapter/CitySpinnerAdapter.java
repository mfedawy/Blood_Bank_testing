package com.echoman.bb_splash_cycle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.general.city.CityData;
import com.echoman.bb_splash_cycle.data.model.general.gove.GoveData;

import java.util.ArrayList;
import java.util.List;

public class CitySpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<CityData> cityDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public CitySpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<CityData> cityDataList, String hint) {
        this.cityDataList = new ArrayList<>();
        this.cityDataList.add(new CityData(0, hint));
        this.cityDataList.addAll(cityDataList);
    }

    @Override
    public int getCount() {
        return cityDataList.size();
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

        names.setText(cityDataList.get(i).getName());
        selectedId = cityDataList.get(i).getId();

        return view;
    }
}