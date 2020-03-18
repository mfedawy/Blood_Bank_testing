package com.echoman.bb_splash_cycle.Ui.mainui.ui.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.echoman.bb_splash_cycle.R;


public class MoreFragment extends Fragment {

    private MoreViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_more, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

        return root;
    }
}
