package core.elements;

import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.utils.Vector2d;

import java.util.ArrayList;

public interface IAnimal extends IMapElement {
    public Orientation getOrientation();
    public void setPosition(Vector2d position);
    public Gen getGen();
    public Integer getEnergy();
    public void turn() throws Exception;
    public void move() throws Exception;
    public IPlant eat(ArrayList<IPlant> food);
    public IAnimal multiply(IAnimal other) throws Exception;
    public boolean canMultiply();
    public boolean aliveAfterThisDay();
}
