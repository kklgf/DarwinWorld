package main;

import core.ISimulation;
import core.Simulation;
import core.fileOperations.IFile;

public class Main {
    public static void main(String[] arg) throws Exception {
        String filename = null;
        if (arg.length == 0){
            filename = "./src/main/simulationConfig.json";
        }
        else {
            try {
                filename = arg[0];
            } catch (Exception e) {
                System.out.println("Use: Main [path_to_config_file]\n" + e.getMessage());
            }
        }
        ISimulation mySim = IFile.loadSimulationFromJson(filename);
        mySim.start(true);

//        Simulation mySim2 = new Simulation(10,10,
//                0.5, 5,
//                10, 10,10,100,
//                30,3, 5);
//        for (int i = 0; i < 500; i++) {
//            mySim.nextTurn(true);
//            if (i==100){
//                System.out.println(i);
//            }
//        }
    }
}
