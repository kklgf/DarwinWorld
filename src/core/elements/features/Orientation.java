package core.elements.features;

import core.utils.Vector2d;

public enum Orientation {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;



    public static Orientation fromInt(Integer arg) throws IllegalArgumentException {
        switch (arg) {
            case 0:
                return N;
            case 1:
                return NE;
            case 2:
                return E;
            case 3:
                return SE;
            case 4:
                return S;
            case 5:
                return SW;
            case 6:
                return W;
            case 7:
                return NW;
            default:
                throw new IllegalArgumentException("Unexpected value of Orientation -> fromInt");
        }
    }
    public Integer toInt() throws Exception {
        switch (this) {
            case N:
                return 0;
            case NE:
                return 1;
            case E:
                return 2;
            case SE:
                return 3;
            case S:
                return 4;
            case SW:
                return 5;
            case W:
                return 6;
            case NW:
                return 7;
            default:
                throw new Exception("Unexpected value of Orientation -> toInt");
        }
    }

    public Vector2d toUnitVector() throws Exception {
        switch (this) {
            case N:
                return new Vector2d(0,1);
            case NE:
                return new Vector2d(1,1);
            case E:
                return new Vector2d(1,0);
            case SE:
                return new Vector2d(1,-1);
            case S:
                return new Vector2d(0,-1);
            case SW:
                return new Vector2d(-1,-1);
            case W:
                return new Vector2d(-1,0);
            case NW:
                return new Vector2d(-1,1);
            default:
                throw new Exception("Unexpected value of Orientation -> toUnitVector");
        }
    }

    public Orientation rotate(Integer rotation) throws Exception {
        return fromInt((this.toInt() + rotation) % 8);
    }
}