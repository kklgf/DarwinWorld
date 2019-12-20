package core.elements;

import core.utils.Vector2d;

public abstract class AbstractPlant implements IPlant{
    protected Integer energyGiven;
    protected Vector2d position;
    protected AbstractPlant(Vector2d position, Integer energy){
        this.position = position;
        this.energyGiven = energy;
    }
    @Override
    public boolean eatable(){
        return true;
    }
    @Override
    public Integer getEnergyGiven() {
        return energyGiven;
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }
}
