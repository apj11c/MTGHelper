package edu.fsu.mobile.cs.mtghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StandardActivity extends AppCompatActivity {
    private int playerCount = 4; // update this in onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

    }
}
