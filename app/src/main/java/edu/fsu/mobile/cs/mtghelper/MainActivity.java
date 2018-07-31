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

public class MainActivity extends AppCompatActivity {

    Spinner numberOfPlayers;
    String flag = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected final Context c = this;
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                Fragment styleChoice = new styleChoice();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.styleFragment, styleChoice);
                transaction.commit();
                /*ImageView commanderHelp = findViewById(R.id.CommanderHelp);
                commanderHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Commander Explanation");
                        builder.setMessage("Commander is a multiplayer format where each player starts with 40 health. Gameplay wise Commander is very similar to Standard. The difference is in deck building. Commander is a Legacy format, so you get to use every card ever printed (as long as it's not on the Commander Ban List). Commander decks contain exactly 100 cards with no repeats other than basic lands. 1 of your 100 cards is the deck's 'Commander.' Commanders must be Legendary Creatures, or Planeswalkers that say they can be Commanders. Your Commander is set aside and not shuffled into your deck. You can cast your Commander at any time as if it was in your hand. All other cards in your deck cannot be of any color that isn't in your Commander.");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }

                });
                ImageView standardHelp = findViewById(R.id.StandardHelp);
                standardHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Standard Explanation");
                        builder.setMessage("Standard is a traditionally 2 player format in which each player starts with 20 health. It can be played with more than 2 players, but you won't see that at any tournament. In standard, each player gets a 60 (minimum) card deck with no more than 4 copies of any given card, except for basic lands. This mode will also work for the Modern and Vintage formats. The only difference between Standard, Modern, and Vintage is which cards can be used. Standard only uses cards from the past two years. Modern uses all cards printed after July 29, 2003. Vintage uses all cards ever printed.");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }

                });*/
                break;
            case R.id.commanderButton:
                Fragment playerQuestion = new playerQuestion();
                FragmentTransaction playTransaction = getSupportFragmentManager().beginTransaction();
                playTransaction.replace(R.id.playerFragment, playerQuestion);
                playTransaction.commit();
                break;
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
            case R.id.standardButton:
                flag = "1";
                Fragment playerQuestion2 = new playerQuestion();
                FragmentTransaction playTransaction2 = getSupportFragmentManager().beginTransaction();
                playTransaction2.replace(R.id.playerFragment, playerQuestion2);
                playTransaction2.commit();
                break;
            case R.id.startGameButton:
                numberOfPlayers = findViewById(R.id.numPlayersSpinner);
                String players = numberOfPlayers.getSelectedItem().toString();
                Intent i = new Intent(this, CommanderActivity.class);
                i.putExtra("flag", flag);
                i.putExtra("players", players);
                startActivity(i);
                break;
        }
    }
}
