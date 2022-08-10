package marsExploration;

public class World {
    private final long x_size;
    private final long y_size;

    public World(long x_size, long y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
    }

    public long getY_size() {
        return y_size;
    }

    public long getX_size() {
        return x_size;
    }
}
