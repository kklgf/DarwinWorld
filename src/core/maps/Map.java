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

    public Map(Integer width, Integer height, double jungleProportions,
               Integer newGrassPerDay, Integer plantPower) throws Exception {
        super(width, height);
        if (jungleProportions > 1){
            throw new IllegalArgumentException("Jungle can't be bigger than whole map.");
        }
        int junglePaddingX = (int) (width * (1-jungleProportions) / 2);
        int junglePaddingY = (int) (height * (1-jungleProportions) / 2);

        this.jungleLowerLeft = this.lowerLeft.add(new Vector2d(junglePaddingX, junglePaddingY));
        this.jungleUpperRight = this.upperRight.subtract(new Vector2d(junglePaddingX, junglePaddingY));
        this.newGrassPerDayInJungle = newGrassPerDay / 2;
        this.newGrassPerDayOutsideJungle = newGrassPerDay - this.newGrassPerDayInJungle;
        this.plantPower = plantPower;
    }

    @Override
    public void createFood(){
        this.createFood(this.newGrassPerDayInJungle);
    }

    public void createFood(Integer amount){
        for (Integer i=0; i < amount; i++){
            if( i % 2 == 0){
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
            else {
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
}
