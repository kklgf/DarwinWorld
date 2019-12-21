package core;

import core.maps.Map;

import java.io.IOException;

public abstract class AbstractSimulation implements ISimulation{

    protected Map map;
    protected Integer turnOfSimulation;
    protected Integer numberOfSimulatedTurns;

    @Override
    public void start(boolean show) throws Exception {
        for(int i = 0; i < numberOfSimulatedTurns; i++){
            this.nextTurn(show);
            if (i % 100 == 0){
                System.out.print(i);
            }
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
        this.turnOfSimulation ++;
        if (show){
            System.out.println(this);
        }
    }
}
