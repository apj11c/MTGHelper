package edu.fsu.mobile.cs.mtghelper;

public class turnState {
    public int test;
    public int[] health;
    public int[] infect;
    public int[][] commanderDamage;
    public int[] energy;
    public int[] experience;
    public int playercount;
    public int turnNum;
    public turnState(int s,int h){
        playercount = s;
        health = new int[s];
        infect = new int[s];
        commanderDamage = new int[s][s];
        energy = new int[s];
        experience = new int[s];
        turnNum = 0;
        for(int i = 0; i < s; i++){
            health[i] = h;
            infect[i] = 0;
            energy[i] = 0;
            experience[i] = 0;
            for(int x = 0; x < s; x++){
                commanderDamage[i][x] = 0;
            }
        }
    }
}
