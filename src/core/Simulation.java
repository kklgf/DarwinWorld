package core;

import core.elements.Animal;
import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.maps.Map;
import core.utils.MyRandom;
import core.utils.SimulationParameters;
import core.utils.Vector2d;

public class Simulation extends AbstractSimulation {
    protected Integer animalStartingEnergy;
    protected Integer energyNeededToMultiply;
    protected Integer dailyEnergyLoss;

    public Simulation(SimulationParameters params) throws Exception {
        this.map = new Map(params.width, params.height,
                params.jungleProportions, params.newGrassPerDay, params.plantPower);
        this.turnOfSimulation = 0;
        this.animalStartingEnergy = params.animalStartingEnergy;
        this.energyNeededToMultiply = params.energyNeededToMultiply;
        this.dailyEnergyLoss = params.dailyEnergyLoss;
        this.numberOfSimulatedTurns = params.numberOfSimulatedTurns;
        this.createFood(params.startingFood);
        this.initializeAnimalsRandomly(params.initialNumberOfAnimals);
    }

    public Simulation(Integer width, Integer height,
                      double jungleProportions,
                      Integer newGrassPerDay, Integer plantPower,
                      Integer startingFood, Integer initialNumberOfAnimals,
                      Integer animalStartingEnergy, Integer energyNeededToMultiply,
                      Integer dailyEnergyLoss, Integer numberOfSimulatedTurns) throws Exception {
        this.map = new Map(width, height, jungleProportions, newGrassPerDay, plantPower);
        this.turnOfSimulation = 0;
        this.animalStartingEnergy = animalStartingEnergy;
        this.energyNeededToMultiply = energyNeededToMultiply;
        this.dailyEnergyLoss = dailyEnergyLoss;
        this.numberOfSimulatedTurns = numberOfSimulatedTurns;
        this.createFood(startingFood);
        this.initializeAnimalsRandomly(initialNumberOfAnimals);
    }

    @Override
    public String toString(){
        return map.toString();
    }

    protected void createFood(Integer amount){
        map.createFood(amount);
    }

    protected void initializeAnimalsRandomly(Integer howMany) throws IllegalArgumentException {
        for(Integer i = 0; i < howMany; i++){
            Vector2d position = MyRandom.getRandomVectorInArea(map.lowerLeft, map.upperRight);
            Orientation orientation = MyRandom.getRandomOrientation();
            Gen gen = new Gen(MyRandom.getRandomListInRange(32, 0, 7));
            this.map.addAnimal(
                    new Animal(position, orientation, gen,
                    this.animalStartingEnergy, this.energyNeededToMultiply, this.dailyEnergyLoss));
        }
    }
}
