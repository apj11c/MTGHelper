package edu.fsu.mobile.cs.mtghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CommanderActivity extends AppCompatActivity {
    private int playerCount = 4; // Set this through an intent

    private turnState currentTurn;
    private ArrayList<turnState> turnLog;
    private Player p[] = new Player[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
        Log.e("bug","started");
        p[0] = new Player(this);
        Log.e("bug","p[0] initiated");
        p[0].rl = findViewById(R.id.P1Frame);
        Log.e("bug","rl assigned");
        //        p[0].rl = (RelativeLayout) findViewById(R.id.P1Frame);
       // p[1].rl = (RelativeLayout) findViewById(R.id.P2Frame);

        // set up turnStates
        currentTurn = new turnState(playerCount,40);
        Log.e("bug","currentTurn created");
       // turnLog.add(currentTurn);
       // Log.e("bug","currentTurn added to turnLog");

        for(int i = 0; i <1; i++) { // change the 1 to 6 after adding in all the frames
            final int x = i; // this is a final version of i to be used in onClickListeners
            p[i].health = (TextView) p[i].rl.findViewById(R.id.HealthLabel);
            p[i].energy = (TextView) p[i].rl.findViewById(R.id.EnergyLabel);
            p[i].poison = (TextView) p[i].rl.findViewById(R.id.PoisonLabel);
            p[i].health1 = (Button) p[i].rl.findViewById(R.id.health_1);
            p[i].health1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(p[x].gain){
                        // Add the life
                        currentTurn.health[x]++;
                        p[x].health.setText("Health:" + currentTurn.health[x]);
                    }
                    else{
                        //subtract the life
                        currentTurn.health[x]--;
                        p[x].health.setText("Health:" + currentTurn.health[x]);
                        // check if player is dead
                    }
                }
            });
            p[i].health10 = (Button) p[i].rl.findViewById(R.id.health_10);
            p[i].health10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(p[x].gain){
                        // Add the life
                        currentTurn.health[x]+= 10;
                        p[x].health.setText("Health:" + currentTurn.health[x]);
                    }
                    else{
                        //subtract the life
                        currentTurn.health[x]-= 10;
                        p[x].health.setText("Health:" + currentTurn.health[x]);
                        // check if player is dead
                    }
                }
            });
            p[i].energyUp = (Button) p[i].rl.findViewById(R.id.energyUp);
            p[i].energyUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.energy[x]++;
                    p[x].energy.setText("Energy:"+currentTurn.energy[x]);
                }
            });
            p[i].energyDown = (Button) p[i].rl.findViewById(R.id.energyDown);
            p[i].energyDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.energy[x]--;
                    p[x].energy.setText("Energy:"+currentTurn.energy[x]);
                }
            });
            p[i].poisonUp = (Button) p[i].rl.findViewById(R.id.poisonUp);
            p[i].poisonUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.infect[x]++;
                    p[x].poison.setText("Poison:" + currentTurn.infect[x]);
                    // add something to check if poison just killed this player.
                }
            });
            p[i].lifegain = (Button) p[i].rl.findViewById(R.id.toggle_gain);
            p[i].lifegain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(p[x].gain){
                        // Turn off Life Gain Mode
                        p[x].gain = false;
                        p[x].lifegain.setText("Life Gain");
                        p[x].health10.setText("-10");
                        p[x].health1.setText("-1");
                    }
                    else{
                        // Turn on Life Gain Mode
                        p[x].gain = true;
                        p[x].lifegain.setText("Normal Damage");
                        p[x].health10.setText("+10");
                        p[x].health1.setText("+1");
                    }
                }
            });
        }
    }
}
