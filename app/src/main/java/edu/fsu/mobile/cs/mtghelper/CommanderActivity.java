package edu.fsu.mobile.cs.mtghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommanderActivity extends AppCompatActivity {
    private int playerCount = 4; // Set this through an intent

    private Player p[] = new Player[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
        p[0].rl = (RelativeLayout) findViewById(R.id.P1Frame);
       // p[1].rl = (RelativeLayout) findViewById(R.id.P2Frame);

        for(int i = 0; i <1; i++) { // change the 1 to 6 after adding in all the frames
            p[i].health = (TextView) p[i].rl.findViewById(R.id.HealthLabel);
            p[i].energy = (TextView) p[i].rl.findViewById(R.id.EnergyLabel);
            p[i].poison = (TextView) p[i].rl.findViewById(R.id.PoisonLabel);
            p[i].health1 = (Button) p[i].rl.findViewById(R.id.health_1);
            p[i].health10 = (Button) p[i].rl.findViewById(R.id.health_10);
            p[i].energyUp = (Button) p[i].rl.findViewById(R.id.energyUp);
            p[i].energyDown = (Button) p[i].rl.findViewById(R.id.energyDown);
            p[i].poisonUp = (Button) p[i].rl.findViewById(R.id.poisonUp);
            p[i].lifegain = (Button) p[i].rl.findViewById(R.id.toggle_gain);
        }
    }
}
