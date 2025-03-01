package com.lgkk.spada.screen.notification.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgkk.spada.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotiMailFragment extends Fragment {


    public NotiMailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noti_mail, container, false);
    }

}
