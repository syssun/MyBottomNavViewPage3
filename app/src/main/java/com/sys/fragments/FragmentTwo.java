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
import com.wang.avi.AVLoadingIndicatorView;

public class FragmentTwo extends Fragment {
    View mview;
    String title ;
    TextView v ;
    LoadingFrame frame;
    AVLoadingIndicatorView indicatorView;
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
        mview = inflater.inflate(R.layout.fragment_two, container,false);
        v = mview.findViewById(R.id.two_text);
        indicatorView= (AVLoadingIndicatorView) mview.findViewById(R.id.avi);
        indicatorView.show();
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.setText(title);
    }
}
