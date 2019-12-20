package main;

import core.Simulation;

public class Main {
    public static void main(String[] arg) throws Exception {
        Simulation mySim = new Simulation(10,10,
                5,5,5,
                10,10,100,
                30,3);
        mySim.initializeAnimalsRandomly(10);
        for (Integer i = 0; i < 50; i++)
            mySim.nextTurn(true);
    }
}
