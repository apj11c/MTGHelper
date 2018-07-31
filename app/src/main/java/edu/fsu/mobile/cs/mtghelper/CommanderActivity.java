package edu.fsu.mobile.cs.mtghelper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CommanderActivity extends AppCompatActivity {
    private int playerCount;
    private turnState currentTurn;
    private ArrayList<turnState> turnLog;
    private Player p[] = new Player[6];

    private int turn = 1;
    private int phase = 1;
    private Button nextPhase;
    private TextView currentPhase;
    private TextView turnNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
        String tempCount = getIntent().getStringExtra("players");
        Log.e("playercount","tempCount = " + tempCount);
        if (tempCount.equals("2")) {
            playerCount = 2;
        }
        else if (tempCount.equals("3")) {
            playerCount = 3;
        }
        else {
            playerCount = 4;
        }
        p[0] = new Player(this);
        p[0].rl = findViewById(R.id.P1Frame);

        p[1] = new Player(this);
        p[1].rl = findViewById(R.id.P2Frame);

        p[2] = new Player(this);
        p[2].rl = findViewById(R.id.P3Frame);

        Log.e("playercount test", "playercount = " + playerCount);
        p[3] = new Player(this);
        p[3].rl = findViewById(R.id.P4Frame);
        if(playerCount < 4){
            p[3].rl.setVisibility(View.INVISIBLE);
            if(playerCount < 3){
                p[2].rl.setVisibility(View.INVISIBLE);
            }
        }
        //        p[0].rl = (RelativeLayout) findViewById(R.id.P1Frame);
       // p[1].rl = (RelativeLayout) findViewById(R.id.P2Frame);

        // set up turnStates
        currentTurn = new turnState(playerCount,40);
        turnLog = new ArrayList<turnState>();
        turnLog.add(currentTurn);

        for(int i = 0; i <4; i++) { // change the 1 to 6 after adding in all the frames
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
                        p[x].health.setText(""+currentTurn.health[x]);
                    }
                    else{
                        //subtract the life
                        currentTurn.health[x]--;
                        p[x].health.setText("" +currentTurn.health[x]);
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
                        p[x].health.setText("" +currentTurn.health[x]);
                    }
                    else{
                        //subtract the life
                        currentTurn.health[x]-= 10;
                        p[x].health.setText("" +currentTurn.health[x]);
                        // check if player is dead
                    }
                }
            });
            p[i].energyUp = (Button) p[i].rl.findViewById(R.id.energyUp);
            p[i].energyUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.energy[x]++;
                    p[x].energy.setText("" +currentTurn.energy[x]);
                }
            });
            p[i].energyDown = (Button) p[i].rl.findViewById(R.id.energyDown);
            p[i].energyDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.energy[x]--;
                    p[x].energy.setText("" + currentTurn.energy[x]);
                }
            });
            p[i].poisonUp = (Button) p[i].rl.findViewById(R.id.poisonUp);
            p[i].poisonUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTurn.infect[x]++;
                    p[x].poison.setText("" + currentTurn.infect[x]);
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
                        p[x].lifegain.setText("Damage");
                        p[x].health10.setText("+10");
                        p[x].health1.setText("+1");
                    }
                }
            });
        }

        turnNum = findViewById(R.id.CurrentTurn);
        currentPhase = findViewById(R.id.currentPhase);
        final Context c = this;
        currentPhase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch a dialog explaining the current phase
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                switch(phase){
                    // **** Explanations need to be fleshed out ****
                    case 1:
                        builder.setTitle("Untap Phase Explanation");
                        builder.setMessage("Untap phase");
                        break;
                    case 2:
                        builder.setTitle("Upkeep Phase Explanation");
                        builder.setMessage("Upkeep phase");
                        break;
                    case 3:
                        builder.setTitle("Draw Phase Explanation");
                        builder.setMessage("Draw Phase");
                        break;
                    case 4:
                        builder.setTitle("Main Phase Explanation");
                        builder.setMessage("Main Phase");
                        break;
                    case 5:
                        builder.setTitle("Combat Phase Explanation");
                        builder.setMessage("Combat Phase");
                        break;
                    case 6:
                        builder.setTitle("Second Main Phase Explanation");
                        builder.setMessage("Second Main Phase");
                        break;
                    case 7:
                        builder.setTitle("End Phase Explanation");
                        builder.setMessage("End Phase");
                        break;
                }

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });

        nextPhase = findViewById(R.id.NextPhaseButton);
        nextPhase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phase++;
                if(phase > 7){
                    phase = 1;
                    turn++; // this doesn't keep track of whose turn it is.
                    turnLog.add(currentTurn);
                    turnNum.setText("Turn " + turn);
                }
                String phasename= "";
                switch (phase){
                    case 1:
                        phasename = "Untap";
                        break;
                    case 2:
                        phasename = "Upkeep";
                        break;
                    case 3:
                        phasename = "Draw";
                        break;
                    case 4:
                        phasename = "Main";
                        break;
                    case 5:
                        phasename = "Combat";
                        break;
                    case 6:
                        phasename = "Second Main";
                        break;
                    case 7:
                        phasename = "End";
                        break;
                }
                currentPhase.setText(phasename);
            }
        });

    }
}
