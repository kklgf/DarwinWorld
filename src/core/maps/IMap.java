package core.maps;

import core.elements.IAnimal;
import core.elements.IPlant;

public interface IMap {
    public void turn() throws Exception;
    public void run() throws Exception;
    public void eat();
    public void multiply() throws Exception;
    public void clean();
    public void createFood(Integer amount);
    public void addAnimal(IAnimal animal);
}
