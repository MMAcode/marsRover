package tests;

import marsExploration.Robot;
import marsExploration.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static marsExploration.Compass.Orientation.N;
import static marsExploration.Robot.*;
import static marsExploration.Robot.Instruction.F;
import static marsExploration.Robot.Instruction.L;
import static marsExploration.Robot.Instruction.R;
import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    World world = new World(5, 10);
    Robot robotAt00N;

    @BeforeEach
    void setUp() {
        robotAt00N = new Robot(world, 0, 0, N);
    }

    @Test
    void shouldCreateRobotInCorrectPosition() {
        Robot robot = new Robot(world, 1, 5, N);
        assertEquals("(1, 5, N)", robot.getStatus());
    }

    @Test
    void shouldMoveForwardAndFaceTheSameDirection() {
        robotAt00N.move(F);
        assertEquals("(0, 1, N)", robotAt00N.getStatus());
    }

    @Test
    void shouldRotate() {
        robotAt00N.move(Instruction.R);
        assertEquals("(0, 0, E)", robotAt00N.getStatus());
        robotAt00N.move(Instruction.R);
        assertEquals("(0, 0, S)", robotAt00N.getStatus());
        robotAt00N.move(Instruction.L);
        assertEquals("(0, 0, E)", robotAt00N.getStatus());
        robotAt00N.move(Instruction.L);
        assertEquals("(0, 0, N)", robotAt00N.getStatus());
    }


    @Test
    void straight_shouldLandOnX0Y3FacingNorth() {
        robotAt00N.move(F, F, F);
        assertEquals("(0, 3, N)", robotAt00N.getStatus());
    }
    @Test
    void straight_shouldLandOnX0Y10FacingNorthAndBeLost() {
        robotAt00N.move(F, F, F, F, F, F, F, F, F, F, F, F, F, F);
        assertEquals("(0, 10, N) LOST", robotAt00N.getStatus());
    }

    @Test
    void straight_shouldLandOnX5Y0AndBeLost() {
        robotAt00N.move(R, F, F, F, F, F, F, F, F, F, F);
        assertEquals("(5, 0, E) LOST", robotAt00N.getStatus());
    }

    @Test
    void complex_shouldLandOnX5Y2FacingEastAndBeLost() {
        robotAt00N.move(F, F, R, F, F, F, F, F, F, F, R, F);
        assertEquals("(5, 2, E) LOST", robotAt00N.getStatus());
    }

    @Test
    void complex_shouldLandOnX0Y0FacingWestAndBeLost() {
        robotAt00N.move(L, F, F, R, F, F, F, F, F, F, F, R, F);
        assertEquals("(0, 0, W) LOST", robotAt00N.getStatus());
    }

    @Test
    void complex_shouldLandOnX5Y10FacingNorth() {
        robotAt00N.move(F, F, R, F, F, F, F, F, L, F, F, F, F, F, F, F, F);
        assertEquals("(5, 10, N)", robotAt00N.getStatus());
    }
}