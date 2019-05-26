package com.sys.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sys.R;
import com.sys.views.LoadingFrame;

public class FragmentThree extends Fragment {
     View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, null);
        LoadingFrame frame = new LoadingFrame(getContext()) {
            @Override
            public View onSuccessView() {
                return view;
            }

            @Override
            public int onLoad() {
                return 404;
            }
        };
        frame.show();
        return frame;
    }
}
