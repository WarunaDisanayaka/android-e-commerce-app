package com.example.babybuy.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.babybuy.R;

public class ProfileFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.fragment_profile,container,false);




    return root;
    }
    

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}