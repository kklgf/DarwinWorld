package core.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SimulationParameters {
    public final int width;
    public final int height;
    public final int newGrassPerDay;
    public final int plantPower;
    public final int startingFood;
    public final double jungleProportions;
    public final int initialNumberOfAnimals;
    public final int animalStartingEnergy;
    public final int energyNeededToMultiply;
    public final int dailyEnergyLoss;
    public final int numberOfSimulatedTurns;

    private static Gson gson = new Gson();

    public SimulationParameters(Integer width, Integer height,
                                double jungleProportions,
                                Integer newGrassPerDay, Integer plantPower,
                                Integer startingFood, Integer initialNumberOfAnimals,
                                Integer animalStartingEnergy, Integer energyNeededToMultiply,
                                Integer dailyEnergyLoss, Integer numberOfSimulatedTurns) {
        this.width = width;
        this.height = height;
        this.jungleProportions = jungleProportions;
        this.newGrassPerDay = newGrassPerDay;
        this.plantPower = plantPower;
        this.startingFood = startingFood;
        this.initialNumberOfAnimals = initialNumberOfAnimals;
        this.animalStartingEnergy = animalStartingEnergy;
        this.energyNeededToMultiply = energyNeededToMultiply;
        this.dailyEnergyLoss = dailyEnergyLoss;
        this.numberOfSimulatedTurns = numberOfSimulatedTurns;
    }

    public static SimulationParameters fromFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return SimulationParameters.gson.fromJson(bufferedReader, SimulationParameters.class);
        } catch (IOException error) {
            throw error;
        }
    }
}
