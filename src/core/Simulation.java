package core;

import core.elements.Animal;
import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.maps.Map;
import core.utils.MyRandom;
import core.utils.Vector2d;

public class Simulation extends AbstractSimulation {
    protected Integer animalStartingEnergy;
    protected Integer energyNeededToMultiply;
    protected Integer dailyEnergyLoss;

    public Simulation(Integer width, Integer height,
                      Integer jungleWidth, Integer jungleHeight,
                      Integer newGrassPerDay, Integer plantPower,
                      Integer startingFood,
                      Integer animalStartingEnergy, Integer energyNeededToMultiply, Integer dailyEnergyLoss){
        this.map = new Map(width, height, jungleWidth, jungleHeight, newGrassPerDay, plantPower);
        this.turnOfSimulation = 0;
        this.animalStartingEnergy = animalStartingEnergy;
        this.energyNeededToMultiply = energyNeededToMultiply;
        this.dailyEnergyLoss = dailyEnergyLoss;
        map.createFood(startingFood);
    }

    @Override
    public String toString(){
        return map.toString();
    }

    @Override
    public void initializeAnimalsRandomly(Integer howMany) throws Exception {
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
