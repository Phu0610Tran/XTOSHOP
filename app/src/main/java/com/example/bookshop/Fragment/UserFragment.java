package com.example.bookshop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class UserFragment extends Fragment {

    private BottomNavigationView mbottomNavigationView;
    private View mView;
    Button btn_Login;
    TextView txt_Tentaikhoan;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user, container, false);

        AnhXa();

        return mView;
    }

    private void AnhXa() {
        txt_Tentaikhoan = mView.findViewById(R.id.txtUsername);
    }

}