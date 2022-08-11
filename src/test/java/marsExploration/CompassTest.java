package marsExploration;

import org.junit.jupiter.api.Test;

import static marsExploration.Compass.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompassTest {
    @Test
    void shouldTurnLeft() {
        assertEquals(W, Compass.leftOf(N));
        assertEquals(N, Compass.leftOf(E));
        assertEquals(E, Compass.leftOf(S));
        assertEquals(S, Compass.leftOf(W));
    }

    @Test
    void shouldTurnRight() {
        assertEquals(E, Compass.rightOf(N));
        assertEquals(S, Compass.rightOf(E));
        assertEquals(W, Compass.rightOf(S));
        assertEquals(N, Compass.rightOf(W));
    }
}