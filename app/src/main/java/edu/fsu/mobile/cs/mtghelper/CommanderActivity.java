package edu.fsu.mobile.cs.mtghelper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CommanderActivity extends AppCompatActivity {
    private int playerCount;
    private turnState currentTurn;
    private TextView currentPlayer;
    private ArrayList<turnState> turnLog;
    private Player p[] = new Player[6];

    private int turn = 1;
    private int phase = 1;
    private Button nextPhase;
    private TextView currentPhase;
    private TextView turnNum;
    private Button history;
    private int activePlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
        String tempCount = getIntent().getStringExtra("players");
        //determine whether commander or standard, difference is the starting health
        String format = getIntent().getStringExtra("flag");
        String health;
        int healthNumber;
        if (format.equals("0")) {
            health = "40";
            healthNumber = 40;
        }
        else {
            health = "20";
            healthNumber = 20;
        }
        history = findViewById(R.id.logBtton);
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
        currentTurn = new turnState(playerCount, healthNumber);
        turnLog = new ArrayList<turnState>();
        turnLog.add(new turnState(currentTurn));
        currentPlayer = findViewById(R.id.CurrentPlayer);

        for(int i = 0; i <4; i++) { // change the 1 to 6 after adding in all the frames
            final int x = i; // this is a final version of i to be used in onClickListeners
            p[i].health = (TextView) p[i].rl.findViewById(R.id.HealthLabel);
            p[i].energy = (TextView) p[i].rl.findViewById(R.id.EnergyLabel);
            p[i].poison = (TextView) p[i].rl.findViewById(R.id.PoisonLabel);
            p[i].health1 = (Button) p[i].rl.findViewById(R.id.health_1);
            p[i].health.setText(health);
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
                        builder.setMessage("The active player untaps all permanents they control. (A card is 'tapped' when it is rotated on its side. To untap it, turn it back.)");
                        break;
                    case 2:
                        builder.setTitle("Upkeep Phase Explanation");
                        builder.setMessage("This is when all abilities triggered during the untap step happen. This includes upkeep costs.");
                        break;
                    case 3:
                        builder.setTitle("Draw Phase Explanation");
                        builder.setMessage("The active player draws a card from their library. ('Library' is MTG speak for your deck)");
                        break;
                    case 4:
                        builder.setTitle("Main Phase Explanation");
                        builder.setMessage("Your main phase is the only time you can cast non-instant spells. You get two main phases; one before Combat and one after Combat.");
                        break;
                    case 5:
                        builder.setTitle("Combat Phase Explanation");
                        builder.setMessage("Combat multiple steps. First, the active player declares which of their creatures will be attacking, and what each of those creatures is attacking. Creatures may attack an enemy player or an enemy Planeswalker. Then, any players that are being attacked / that have Planeswalkers that are being attacked declare which of their creatures will be blocking, and which specific attacking creature they're blocking. Each defender can only block one attacker, but multiple defenders can block the same attacker. Then, deal damage. If an attacker wasn't blocked, it deals its damage to the declared target. If it was blocked, the attacker and its blocker(s) deal damage to each other.");
                        break;
                    case 6:
                        builder.setTitle("Second Main Phase Explanation");
                        builder.setMessage("Your main phase is the only time you can cast non-instant spells. You get two main phases; one before Combat and one after Combat.");
                        break;
                    case 7:
                        builder.setTitle("End Phase Explanation");
                        builder.setMessage("The end of a turn. Any end of turn effect triggers now.");
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
                    currentTurn.turnNum++;
                    turnState old = new turnState(currentTurn);
                    turnLog.add(old);
                    turnNum.setText("Turn " + turn);
                    activePlayer++;
                    if(activePlayer >= playerCount){activePlayer = 0;}
                    currentPlayer.setText("Player: " + (activePlayer + 1));
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

        final Context c2 = this;

        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //for setting the log information alert dialog
                String message = "";
                for (int i = 0; i < turn; i++){
                    message = message + "\n" + "*************************";
                    message = message + "\n" + "Turn: " + i;
                    message = message + "\n" + "*************************";
                    for(int player = 0; player < playerCount; player++)
                    {
                        int realPlayerNumber = player + 1;
                        message = message + "\n" + "Player: " + realPlayerNumber;
                        message = message + "\n" + "\t\t" +"Life: " + turnLog.get(i).health[player];
                        message = message + "\n" + "\t\t" + "Energy: " + turnLog.get(i).energy[player];
                        message = message + "\n" + "\t\t" + "Poison: " + turnLog.get(i).infect[player];
                    }
                }

                LayoutInflater inflater= LayoutInflater.from(c2);
                View view=inflater.inflate(R.layout.log_layout, null);

                TextView textview=(TextView)view.findViewById(R.id.textmsg);
                textview.setText(message);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(c2);
                alertDialog.setTitle("History");
                //alertDialog.setMessage("Here is a really long message.");
                alertDialog.setView(view);
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

    }
}
