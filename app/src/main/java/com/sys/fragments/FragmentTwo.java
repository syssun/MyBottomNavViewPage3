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

public class FragmentTwo extends Fragment {
    String title ;
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
        View view = inflater.inflate(R.layout.fragment_two, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView v = view.findViewById(R.id.two_text);
        v.setText(title);
    }
}
