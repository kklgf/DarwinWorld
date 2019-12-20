package core.elements;

import core.utils.Vector2d;

public class Plant extends AbstractPlant {
    private Integer plantPower;
    public Plant(Vector2d position, Integer plantPower){
        super(position, plantPower);
    }
}
