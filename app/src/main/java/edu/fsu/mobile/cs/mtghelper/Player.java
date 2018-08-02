package edu.fsu.mobile.cs.mtghelper;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
Public class used to store a player's stats for
easier access to log each turn
*/
public class Player {
    public RelativeLayout rl;
    public TextView health;
    public TextView energy;
    public TextView poison;
    public Button health1;
    public Button health10;
    public Button energyUp;
    public Button energyDown;
    public Button poisonUp;
    public Button lifegain;
    public Boolean gain = false;
/*
Copy constructor necessary for copying a new player's
stats in order for the stats to update properly
inside of CommanderActivity
 */
    public Player(Context c){
        rl = new RelativeLayout(c);
    }

}
