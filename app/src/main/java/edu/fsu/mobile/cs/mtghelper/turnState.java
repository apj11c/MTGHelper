package edu.fsu.mobile.cs.mtghelper;

public class turnState {
    public int[] health;
    public int[] infect;
    public int[] energy;
    public int playercount;
    public int turnNum;

    public turnState(turnState t){
        turnNum = t.turnNum;
        playercount = t.playercount;
        health = new int[playercount];
        infect = new int[playercount];
        energy = new int[playercount];
        for(int i = 0; i < playercount; i++){
            health[i] = t.health[i];
            infect[i] = t.infect[i];
            energy[i] = t.energy[i];
        }
    }

    public turnState(int s,int h){
        playercount = s;
        health = new int[s];
        infect = new int[s];
        energy = new int[s];
        turnNum = 0;
        for(int i = 0; i < s; i++){
            health[i] = h;
            infect[i] = 0;
            energy[i] = 0;
        }
    }
}
