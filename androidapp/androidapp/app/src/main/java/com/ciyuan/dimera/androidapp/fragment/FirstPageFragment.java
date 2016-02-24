package com.ciyuan.dimera.androidapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciyuan.dimera.androidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment {


    public FirstPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return View.inflate(getActivity(),R.layout.tap_home,null);
       // return inflater.inflate(R.layout.tap_home,container,false);
    }


}
