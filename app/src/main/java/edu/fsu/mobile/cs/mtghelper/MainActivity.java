package edu.fsu.mobile.cs.mtghelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                Fragment styleChoice = new styleChoice();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.styleFragment, styleChoice);
                transaction.commit();
                break;
            case R.id.commanderButton:
                Fragment playerQuestion = new playerQuestion();
                FragmentTransaction playTransaction = getSupportFragmentManager().beginTransaction();
                playTransaction.replace(R.id.playerFragment, playerQuestion);
                playTransaction.commit();
                break;
            case R.id.standardButton:
                Fragment playerQuestion2 = new playerQuestion();
                FragmentTransaction playTransaction2 = getSupportFragmentManager().beginTransaction();
                playTransaction2.replace(R.id.playerFragment, playerQuestion2);
                playTransaction2.commit();
                break;
        }
    }
}
