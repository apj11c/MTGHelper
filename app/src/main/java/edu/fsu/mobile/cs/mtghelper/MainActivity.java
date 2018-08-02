package edu.fsu.mobile.cs.mtghelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

/*
MainActivity, launches question fragments to set up the CommanderActivity,
which is the main game-play screen
 */

public class MainActivity extends AppCompatActivity {

    //initialize the spinner and flag for which format it is
    Spinner numberOfPlayers;
    String flag = "0";

    @Override
    //onCreate called when app is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected final Context c = this;
    //OnClickListener defined for the buttons of the fragments
    public void onClickListener(View v) {
        switch (v.getId()) {
            //newGame button launches the styleFragment where you choose which format you wish to play
            case R.id.newGameButton:
                Fragment styleChoice = new styleChoice();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.styleFragment, styleChoice);
                transaction.commit();
                break;
                //commanderButton launches the playerFragment where you choose how many players are playing
            //the game
            case R.id.commanderButton:
                Fragment playerQuestion = new playerQuestion();
                FragmentTransaction playTransaction = getSupportFragmentManager().beginTransaction();
                playTransaction.replace(R.id.playerFragment, playerQuestion);
                playTransaction.commit();
                break;
                //commanderHelp Button shows information about commander mode
            case R.id.CommanderHelp:
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Commander Explanation");
                builder.setMessage("Commander is a multiplayer format where each player starts with 40 health. Gameplay wise Commander is very similar to Standard. The difference is in deck building. Commander is a Legacy format, so you get to use every card ever printed (as long as it's not on the Commander Ban List). Commander decks contain exactly 100 cards with no repeats other than basic lands. 1 of your 100 cards is the deck's 'Commander.' Commanders must be Legendary Creatures, or Planeswalkers that say they can be Commanders. Your Commander is set aside and not shuffled into your deck. You can cast your Commander at any time as if it was in your hand. All other cards in your deck cannot be of any color that isn't in your Commander.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
                //Standard Help shows information about standard mode
            case R.id.StandardHelp:
                AlertDialog.Builder b = new AlertDialog.Builder(c);
                b.setTitle("Standard Explanation");
                b.setMessage("Standard is a traditionally 2 player format in which each player starts with 20 health. It can be played with more than 2 players, but you won't see that at any tournament. In standard, each player gets a 60 (minimum) card deck with no more than 4 copies of any given card, except for basic lands. This mode will also work for the Modern and Vintage formats. The only difference between Standard, Modern, and Vintage is which cards can be used. Standard only uses cards from the past two years. Modern uses all cards printed after July 29, 2003. Vintage uses all cards ever printed.");
                b.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                b.create().show();
                break;
                //clicking the standard button sets the standard flag to launch
            //CommanderActivity with appropriate stats for standard mode and launches the playerFragment
            case R.id.standardButton:
                flag = "1";
                Fragment playerQuestion2 = new playerQuestion();
                FragmentTransaction playTransaction2 = getSupportFragmentManager().beginTransaction();
                playTransaction2.replace(R.id.playerFragment, playerQuestion2);
                playTransaction2.commit();
                break;
                //startActivity actually launches the Commander Activity with all of the
            //appropriate variables
            case R.id.startGameButton:
                numberOfPlayers = findViewById(R.id.numPlayersSpinner);
                //get the number of players from the spinner
                String players = numberOfPlayers.getSelectedItem().toString();
                Intent i = new Intent(this, CommanderActivity.class);
                i.putExtra("flag", flag);
                i.putExtra("players", players);
                startActivity(i);
                break;
        }
    }
}
