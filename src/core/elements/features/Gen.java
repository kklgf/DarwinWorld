package core.elements.features;

import core.utils.MyRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gen extends AbstractGen{
    private Integer genotypeLength = 32;
    public Gen(List<Integer> genotype){
        this.genotype.addAll(fixedGenotype((ArrayList<Integer>) genotype));
    }

    @Override
    public Gen combine(IGen other) {
        Integer cuts2Combine = 2;
        Integer[] indexes = MyRandom.intsNoDuplicate(cuts2Combine, 0, genotypeLength);
        Arrays.sort(indexes);
        List<Integer> newGen = this.getGenotype().subList(0, indexes[0]);
        newGen.addAll(other.getGenotype().subList(indexes[0], indexes[1]));
        newGen.addAll(this.getGenotype().subList(indexes[1], genotypeLength));
        return new Gen(new ArrayList<Integer>(newGen));
    }
    private List<Integer> fixedGenotype(ArrayList<Integer> genotype){
        Integer [] values = new Integer [] {0,1,2,3,4,5,6,7};
        ArrayList<Integer> needToAdd = new ArrayList<>();
        for (Integer v: values) {
            if (!genotype.contains(v)){
                needToAdd.add(v);
            }
        }
        Integer [] indexes = MyRandom.intsNoDuplicate(needToAdd.size(), 0, genotypeLength);
        for (Integer i = 0; i < needToAdd.size(); i++){
            genotype.set(indexes[i], (Integer) needToAdd.toArray()[i]);
        }
        Collections.sort(genotype);
        return genotype;
    }
}
