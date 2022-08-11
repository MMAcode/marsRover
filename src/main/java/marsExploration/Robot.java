package marsExploration;

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

    //rotating methods should be re-thinked as there is a lot of repetition;
    //(eg storing orientations as array of size 4 could be alternative solution)
    private void rotateRight() {
        switch (orientation) {
            case E:
                orientation = Orientation.S;
                break;
            case S:
                orientation = Orientation.W;
                break;
            case W:
                orientation = Orientation.N;
                break;
            case N:
                orientation = Orientation.E;
                break;
        }
    }

    private void rotateLeft() {
        switch (orientation) {
            case E:
                orientation = Orientation.N;
                break;
            case S:
                orientation = Orientation.E;
                break;
            case W:
                orientation = Orientation.S;
                break;
            case N:
                orientation = Orientation.W;
                break;
        }
    }

    // enums
    public enum Orientation {
        N, E, S, W
    }

    public enum Instruction {
        L, R, F
    }
}
