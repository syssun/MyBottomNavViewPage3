package com.sys.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sys.R;
import com.sys.views.LoadingFrame;

public class FragmentTwo extends Fragment {
    View mview;
    String title ;
    TextView v ;
    LoadingFrame frame;
    private static final String TEXTKEY="title";
    public static FragmentTwo newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TEXTKEY,title);
        FragmentTwo fragmentTwo = new FragmentTwo();
        fragmentTwo.setArguments(bundle);
        return fragmentTwo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(TEXTKEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_two, null);
        v = mview.findViewById(R.id.two_text);
         frame = new LoadingFrame(getContext()) {
            @Override
            public View onSuccessView() {
                return mview;
            }

            @Override
            public int onLoad() {
                return -1;
            }
        };

        return frame;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frame.show();
        v.setText(title);
    }
}
