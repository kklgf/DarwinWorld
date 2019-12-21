package main;

import core.ISimulation;
import core.Simulation;
import core.fileOperations.IFile;

public class Main {
    public static void main(String[] arg) throws Exception {
        String filename = "./src/main/simulationConfig.json";
        ISimulation mySim = IFile.loadSimulationFromJson(filename);
        Simulation mySim2 = new Simulation(10,10,
                5,5,5,
                10,10,100,
                30,3);
        mySim.initializeAnimalsRandomly(10);
        for (Integer i = 0; i < 5; i++)
            mySim.nextTurn(true);
    }
}
