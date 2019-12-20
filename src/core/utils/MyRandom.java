package core.utils;
import core.elements.features.Orientation;

import java.util.*;

public class MyRandom {
    public static Integer[] intsNoDuplicate(Integer howMany, Integer min, Integer max) throws IllegalArgumentException {
        Random rand = new Random();
        Set<Integer> set = new LinkedHashSet<Integer>();
        if (howMany > (max - min)) {
            throw new IllegalArgumentException("Cannot choose " + howMany + " random numbers with no duplicates in range [" + min + ", " + max + "].");
        }
        while (set.size() < howMany) {
            set.add(rand.nextInt(max - min) + min);
        }
        return set.toArray(new Integer[0]);
    }

    public static Integer getRandomElement(List<Integer> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static ArrayList<Integer> getRandomListInRange(Integer numberOfElems, Integer lowerLimit, Integer upperLimit) {
        Random rand = new Random();
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        while (randomList.size() < numberOfElems){
            randomList.add(rand.nextInt(upperLimit - lowerLimit) + lowerLimit);
        }
        return randomList;
    }

    public static Orientation getRandomOrientation() throws Exception {
        Random rand = new Random();
        return Orientation.fromInt(rand.nextInt(8));
    }

    public static Vector2d getRandomMoveVector() throws Exception {
        return getRandomOrientation().toUnitVector();
    }

    public static Vector2d getRandomVectorInArea(Vector2d lowerLeft, Vector2d upperRight) {
        Random rand = new Random();
        Integer maxX = upperRight.x;
        Integer maxY = upperRight.y;
        Integer minX = lowerLeft.x;
        Integer minY = lowerLeft.y;
        return new Vector2d(rand.nextInt(maxX - minX) + minX, rand.nextInt(maxY -minY) + minY);
    }
    public static Vector2d getRandomVectorOutsideTheArea(Vector2d lowerLeft, Vector2d lowerLeftArea, Vector2d upperRightArea, Vector2d upperRight) {
        Random rand = new Random();
        Integer maxX = upperRight.x;
        Integer maxY = upperRight.y;
        Integer maxXArea = upperRightArea.x;
        Integer maxYArea = upperRightArea.y;
        Integer minXArea = lowerLeftArea.x;
        Integer minYArea = lowerLeftArea.y;
        Integer minX = lowerLeft.x;
        Integer minY = lowerLeft.y;
        Vector2d potentialV = new Vector2d(rand.nextInt(maxX - minX) + minX, rand.nextInt(maxY - minY) + minY);
        while (potentialV.precedes(upperRightArea) && potentialV.follows(lowerLeftArea))
            potentialV = new Vector2d(rand.nextInt(maxX - minX) + minX, rand.nextInt(maxY - minY) + minY);
        return potentialV;
    }
}