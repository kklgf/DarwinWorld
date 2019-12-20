package core.elements;

import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.utils.Vector2d;

import java.util.ArrayList;

public abstract class AbstractAnimal implements IAnimal {
    protected Vector2d position;
    protected Orientation orientation;
    protected Gen gen;
    protected Integer energy;
    protected Integer energyNeededToMultiply;
    protected Integer dailyEnergyLoss;

    public AbstractAnimal(Vector2d position, Orientation orientation, Gen gen, Integer energy, Integer energyNeededToMultiply, Integer dailyEnergyLoss){
        this.position = position;
        this.orientation = orientation;
        this.gen = gen;
        this.energy = energy;
        this.energyNeededToMultiply = energyNeededToMultiply;
        this.dailyEnergyLoss = dailyEnergyLoss;
    }

    @Override
    public Vector2d getPosition() { return position; }

    @Override
    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public Orientation getOrientation() { return orientation; }

    @Override
    public Gen getGen() { return gen; }

    @Override
    public Integer getEnergy(){ return energy; }

    @Override
    public void turn() throws Exception {
        this.orientation = orientation.rotate(gen.getTurn());
    }

    @Override
    public void move() throws Exception {
        this.position = this.position.add(this.orientation.toUnitVector());
    }

    @Override
    public IPlant eat(ArrayList<IPlant> food) {
        IPlant BestFood = null;
        for(IPlant f : food){
            if (f.eatable() && BestFood == null ){
                BestFood = f;
            }
            else if (f.eatable() && BestFood != null){
                if (f.getEnergyGiven() > BestFood.getEnergyGiven())
                    BestFood = f;
            }
        }
        if (BestFood != null){
            this.energy += BestFood.getEnergyGiven();
        }
        return BestFood;
    }

    protected Integer looseEnergyMultiplying(){
        Integer energyForChild = this.getEnergy() / 4;
        this.energy = this.energy - energyForChild;
        return energyForChild;
    }

    @Override
    public boolean canMultiply(){
        return this.energy > this.energyNeededToMultiply;
    }

    @Override
    public boolean aliveAfterThisDay() {
        this.energy -= this.dailyEnergyLoss;
        return this.energy > 0;
    }

    @Override
    public String toString(){
        try {
            return getOrientation().toInt().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "A";
    }
}
