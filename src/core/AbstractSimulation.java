package core;

import core.elements.Animal;
import core.elements.features.Gen;
import core.maps.Map;
import core.utils.MyRandom;
import core.utils.Vector2d;

import java.util.concurrent.TimeUnit;

public abstract class AbstractSimulation implements ISimulation{

    protected Map map;
    protected Integer turnOfSimulation;

    @Override
    public void start(boolean show) throws Exception {
        while (true){
            this.nextTurn(show);
//            TimeUnit.MINUTES.sleep(1);
        }
    }

    @Override
    public void nextTurn(boolean show) throws Exception {
        map.turn();
        map.run();
        map.eat();
        map.multiply();
        map.clean();
        map.createFood();
        if (show){
            System.out.println(this);
        }
    }

}
