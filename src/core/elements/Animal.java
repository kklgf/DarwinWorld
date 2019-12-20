package core.elements;

import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.utils.MyRandom;
import core.utils.Vector2d;

public class Animal extends AbstractAnimal {

    public Animal(Vector2d position, Orientation orientation,
                  Gen gen, Integer energy,
                  Integer energyNeededToMultiply, Integer dailyEnergyLoss){
        super(position, orientation, gen, energy, energyNeededToMultiply, dailyEnergyLoss);
    }

    @Override
    public IAnimal multiply(IAnimal other) throws Exception {
        Vector2d childPosition = this.getPosition();
        childPosition.add(MyRandom.getRandomMoveVector());
        Orientation childOrientation = MyRandom.getRandomOrientation();
        Gen childGen = this.getGen().combine(other.getGen());
        Integer childEnergy = this.looseEnergyMultiplying() + ((AbstractAnimal) other).looseEnergyMultiplying();
        return new Animal(childPosition, childOrientation, childGen, childEnergy, this.energyNeededToMultiply, this.dailyEnergyLoss);
    }
}
