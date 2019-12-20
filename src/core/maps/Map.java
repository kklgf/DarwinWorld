package core.maps;

import core.elements.Plant;
import core.utils.MyRandom;
import core.utils.Vector2d;

public class Map extends AbstractMap{
    protected Vector2d jungleLowerLeft;
    protected Vector2d jungleUpperRight;
    protected Integer newGrassPerDayOutsideJungle;
    protected Integer newGrassPerDayInJungle;
    protected Integer plantPower;

    public Map(Integer width, Integer height, Integer jungleWidth, Integer jungleHeight, Integer newGrassPerDay, Integer plantPower){
        super(width, height);
        if (width < jungleWidth || height < jungleHeight){
            throw new IllegalArgumentException("Jungle can't have bigger dimensions than whole map.");
        }
        this.jungleLowerLeft = new Vector2d((width - jungleWidth)/2, (height - jungleHeight)/2);
        this.jungleUpperRight = new Vector2d((width + jungleWidth)/2, (height + jungleHeight)/2);
        this.newGrassPerDayInJungle = newGrassPerDay / 2;
        this.newGrassPerDayOutsideJungle = newGrassPerDay - this.newGrassPerDayInJungle;
        this.plantPower = plantPower;
    }

    public void createFood(){
        this.createFood(this.newGrassPerDayInJungle);
    }

    @Override
    public void createFood(Integer amount){
        for (Integer i=0; i < amount; i++){
            Vector2d placeInJungle = MyRandom.getRandomVectorInArea(this.jungleLowerLeft, this.jungleUpperRight);
            Integer plantingGroundSize = (this.jungleUpperRight.x - this.jungleLowerLeft.x) * (this.jungleUpperRight.y - this.jungleLowerLeft.y);
            Integer j = 0;
            while (plantsAt(placeInJungle) != null){
                placeInJungle = MyRandom.getRandomVectorInArea(this.jungleLowerLeft, this.jungleUpperRight);
                j ++;
                if (j > plantingGroundSize)
                    break;
            }
            if (j > plantingGroundSize)
                break;
            this.addPlant(new Plant(placeInJungle, plantPower));
        }
        for (Integer i=0; i < amount; i++){
            Vector2d placeOutsideJungle = MyRandom.getRandomVectorOutsideTheArea(this.lowerLeft, this.jungleLowerLeft, this.jungleUpperRight, this.upperRight);
            Integer plantingGroundSize = (this.jungleUpperRight.x - this.jungleLowerLeft.x) * (this.jungleUpperRight.y - this.jungleLowerLeft.y);
            Integer j = 0;
            while (plantsAt(placeOutsideJungle) != null){
                placeOutsideJungle = MyRandom.getRandomVectorOutsideTheArea(this.lowerLeft, this.jungleLowerLeft, this.jungleUpperRight, this.upperRight);
                j ++;
                if (j > plantingGroundSize)
                    break;
            }
            if (j > plantingGroundSize)
                break;
            this.addPlant(new Plant(placeOutsideJungle, plantPower));
        }
    }
}
