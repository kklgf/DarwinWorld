package core;

public interface ISimulation {
    public void initializeAnimalsRandomly(Integer howMany) throws Exception;
    public void start(boolean show) throws Exception;
    public void nextTurn(boolean show) throws Exception;
}
