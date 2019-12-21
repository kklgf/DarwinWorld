package core.fileOperations;

import core.Simulation;
import core.utils.SimulationParameters;

import java.io.*;

public interface IFile {
    static Simulation loadSimulationFromJson(String file_path) {
        Simulation createdSim = null;
        try {
            SimulationParameters simulationParams = SimulationParameters.fromFile(new File(file_path));
            createdSim = new Simulation(simulationParams);
        } catch (Exception error) {
            System.out.println("Failed to initialize the simulation: " + error.getMessage());
        }
        return createdSim;
    }
}
