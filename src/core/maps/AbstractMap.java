package core.maps;

import core.elements.Animal;
import core.elements.IAnimal;
import core.elements.IMapElement;
import core.elements.IPlant;
import core.elements.features.Gen;
import core.elements.features.Orientation;
import core.utils.MyRandom;
import core.utils.Vector2d;

import java.util.*;

public abstract class AbstractMap implements IMap, IWorldMap{
    public static Vector2d lowerLeft = new Vector2d(0,0);
    public Vector2d upperRight;
    protected HashMap<Vector2d, TreeSet<IAnimal>> animals;
    protected HashMap<Vector2d, ArrayList<IPlant>> plants;
    protected MapVisualizer mapVision;

    public AbstractMap(Integer width, Integer height) throws Exception {
        this.upperRight = new Vector2d(width, height);
        this.mapVision = new MapVisualizer(this);
        this.animals = new HashMap<Vector2d, TreeSet<IAnimal>>();
        this.plants = new HashMap<Vector2d, ArrayList<IPlant>>();
    }

    @Override
    public String toString(){
        return mapVision.draw(lowerLeft, upperRight);
    }

    public void turn() throws Exception {
        for (TreeSet<IAnimal> animalsInCell : animals.values())
            for (IAnimal animal : animalsInCell){
                animal.turn();
            }
    }
    public void run() throws Exception {
        ArrayList<TreeSet<IAnimal>> animalsSnap = new ArrayList<TreeSet<IAnimal>>(animals.values());
        for (TreeSet<IAnimal> animalsInCell : animalsSnap)
            for (IAnimal animal : new TreeSet<IAnimal>(animalsInCell)){
                this.removeAnimal(animal);
                animal.move();
                if (this.didThisAnimalJustJumpedFromTheEdgeOfTheWolrd(animal))
                    this.teleportTheAnimalBackFromTheAbbys(animal);
                this.addAnimal(animal);
            }
    }
    public void eat() {
        for (Vector2d cellsWithPlants : new ArrayList<Vector2d>(plants.keySet())) {
            if (this.animalsAt(cellsWithPlants) != null){
                for (IAnimal animal : this.animalsAt(cellsWithPlants)) {
                    IPlant eatenPlant = animal.eat(plants.get(cellsWithPlants));
                    this.removePlant(eatenPlant);
                    if (this.plantsAt(cellsWithPlants) == null)
                        break;
                }
            }
        }
    }
    public void multiply() throws Exception {
        for (TreeSet<IAnimal> animalsInCell : new ArrayList<TreeSet<IAnimal>>(animals.values())) {
            while (animalsInCell.size() > 1){
                animalsInCell = new TreeSet<IAnimal>(animalsInCell);
                try{
                    IAnimal male = animalsInCell.pollFirst();
                    while (!male.canMultiply())
                        male = animalsInCell.pollFirst();
                    IAnimal female = animalsInCell.pollFirst();
                    while (!female.canMultiply())
                        female = animalsInCell.pollFirst();
                    IAnimal child = male.multiply(female);
                    if (this.didThisAnimalJustJumpedFromTheEdgeOfTheWolrd(child))
                        this.teleportTheAnimalBackFromTheAbbys(child);
                    addAnimal(child);
                } catch (NullPointerException e){
                    break;
                }
            }
        }
    }

    public void clean(){
        for (Vector2d cell : new ArrayList<Vector2d>(this.animals.keySet())){
            try {
                for (IAnimal animal : new TreeSet<IAnimal>(this.animalsAt(cell))){
                    if (! animal.aliveAfterThisDay()){
                        this.removeAnimal(animal);
                    }
                }// za szybko usuwana celka w 82?
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void addPlant(IPlant plant){
        ArrayList<IPlant> cell = new ArrayList<IPlant>();
        if(this.plants.get(plant.getPosition()) != null){
            cell = this.plantsAt(plant.getPosition());
        }
        cell.add(plant);
        this.plants.put(plant.getPosition(), cell);
    }

    protected void removePlant(IPlant plant){
        ArrayList<IPlant> cell = this.plants.get(plant.getPosition());
        if(cell.size() == 1){
            this.plants.remove(plant.getPosition());
        } else {
            cell.remove(plant);
            this.plants.replace(plant.getPosition(), cell);
        }
    }

    @Override
    public Object objectAt(Vector2d position){
        return elementAt(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return elementAt(position) != null;
    }

    protected IMapElement elementAt(Vector2d position){
        TreeSet<IAnimal> animalsAtPosition = this.animalsAt(position);
        ArrayList<IPlant> plantsAtPosition = this.plantsAt(position);
//        if (animalsAtPosition != null)
        if (animalsAtPosition != null && animalsAtPosition.size() != 0)
            return animalsAtPosition.first();
        else if (plantsAtPosition != null)
            return plantsAtPosition.get(0);
        return null;
    }

    protected ArrayList<IPlant> plantsAt(Vector2d position){
        return this.plants.get(position);
    }

    protected TreeSet<IAnimal> animalsAt(Vector2d position){
        return this.animals.get(position);
    }

    protected void removeAnimal(IAnimal animal){
        TreeSet<IAnimal> cell = this.animalsAt(animal.getPosition());
        try{
            if(cell.size() == 1){
                this.animals.remove(animal.getPosition());
            } else {
                cell.remove(animal);
                this.animals.replace(animal.getPosition(), cell);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAnimal(IAnimal animal){
        TreeSet<IAnimal> cell = new TreeSet<IAnimal>(Comparator .comparing(IAnimal::getEnergy) .thenComparing(IAnimal::hashCode));
        if(this.animalsAt(animal.getPosition()) != null){
            cell = this.animalsAt(animal.getPosition());
        }
        cell.add(animal);
        this.animals.put(animal.getPosition(), cell);
    }

    protected boolean didThisAnimalJustJumpedFromTheEdgeOfTheWolrd(IAnimal animal){
        return ! (animal.getPosition().precedes(this.upperRight) && animal.getPosition().follows(this.lowerLeft));
    }

    protected void teleportTheAnimalBackFromTheAbbys(IAnimal animal){
        Integer x = animal.getPosition().x;
        Integer y = animal.getPosition().y;
        animal.setPosition(new Vector2d(
                (x + this.upperRight.x+1) % (this.upperRight.x+1),
                (y + this.upperRight.y+1) % (this.upperRight.y+1)));
    }
}
