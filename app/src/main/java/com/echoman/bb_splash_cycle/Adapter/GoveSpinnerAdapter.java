package com.echoman.bb_splash_cycle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;
import com.echoman.bb_splash_cycle.data.model.general.gove.Gove;
import com.echoman.bb_splash_cycle.data.model.general.gove.GoveData;

import java.util.ArrayList;
import java.util.List;

public class GoveSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<GoveData> govDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public GoveSpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GoveData> goveDataList, String hint) {
        this.govDataList = new ArrayList<>();
        this.govDataList.add(new GoveData(0, hint));
        this.govDataList.addAll(goveDataList);
    }

    @Override
    public int getCount() {
        return govDataList.size();
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

        names.setText(govDataList.get(i).getName());
        selectedId = govDataList.get(i).getId();

        return view;
    }
}