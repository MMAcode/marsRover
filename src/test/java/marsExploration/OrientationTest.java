package marsExploration;

import org.junit.jupiter.api.Test;

import static marsExploration.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {
    @Test
    void shouldTurnLeft() {
        assertEquals(W, leftOf(N));
        assertEquals(N, leftOf(E));
        assertEquals(E, leftOf(S));
        assertEquals(S, leftOf(W));
    }

    @Test
    void shouldTurnRight() {
        assertEquals(E, rightOf(N));
        assertEquals(S, rightOf(E));
        assertEquals(W, rightOf(S));
        assertEquals(N, rightOf(W));
    }
}