package core.elements.features;

import java.util.ArrayList;

public interface IGen {
    public ArrayList<Integer> getGenotype();
    public Gen combine(IGen other);
    public Integer getTurn();
}
