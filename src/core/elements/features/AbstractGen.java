package core.elements.features;

import core.utils.MyRandom;

import java.util.ArrayList;

public abstract class AbstractGen implements IGen {
    protected ArrayList<Integer> genotype = new ArrayList<Integer>();

    @Override
    public ArrayList<Integer> getGenotype(){
        return genotype;
    }
    @Override
    public abstract Gen combine(IGen other);
    @Override
    public Integer getTurn(){
        return MyRandom.getRandomElement(genotype);
    }
}
