package marsExploration;

import static marsExploration.Compass.Orientation;

public class Robot {
    private final World world; //not sure if robot should 'have' a world, but it is practical here for now;
    // also instead of new class we could just have array here.
    //Word is probably more flexible as it is possible to enrich with other properties and methods in future.

    private long coordinateX;
    private long coordinateY;
    private Orientation orientation; //orientation could be implemented as double linked list of 4 elements to simulate rotating;
    private boolean lost = false;

    public Robot(World world, long x, long y, Orientation orientation) {
        this.world = world;
        this.coordinateX = x;
        this.coordinateY = y;
        this.orientation = orientation;
    }

    public void move(Instruction... instructions) {
        //This could be changed to accept String and then validate characters of that string.
        for(Instruction instruction : instructions) {
            if (lost) break;
            switch (instruction) {
                case R:
                    rotateRight();
                    break;
                case L:
                    rotateLeft();
                    break;
                case F:
                    moveForward();
                    break;
                default:
                    //TODO, but this should never happen;
            }
        }
    }

    public String getStatus() {
        //(to clarify/todo: does the output need to match the color scheme of the example?)
        return "(" + coordinateX + ", " + coordinateY +  ", " + orientation + ")" + (lost ? " LOST" : "");
    }

    private void moveForward() {
        switch (orientation) {
            case E:
                if (coordinateX < world.getX_size()) coordinateX++;
                else lost = true;
                break;
            case W:
                if (coordinateX > 0) coordinateX--;
                else lost = true;
                break;
            case S:
                if (coordinateY > 0) coordinateY--;
                else lost = true;
                break;
            case N:
                if (coordinateY < world.getY_size()) coordinateY++;
                else lost = true;
                break;
        }
    }

    private void rotateRight() {
        orientation = Compass.rightOf(orientation);
    }

    private void rotateLeft() {
        orientation = Compass.leftOf(orientation);
    }

    public enum Instruction {
        L, R, F
    }
}
