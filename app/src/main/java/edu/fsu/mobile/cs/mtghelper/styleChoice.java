package edu.fsu.mobile.cs.mtghelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
Fragment class used to inflate the "Choose a Style"
question asked by the user. Committed in MainActivity
 */

public class styleChoice extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.style_choice_fragment, container, false);
        return v;
    }
}
