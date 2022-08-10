package tests;

import marsExploration.Robot;
import marsExploration.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static marsExploration.Robot.*;
import static marsExploration.Robot.Instruction.F;
import static marsExploration.Robot.Instruction.L;
import static marsExploration.Robot.Instruction.R;
import static marsExploration.Robot.Orientation.E;
import static marsExploration.Robot.Orientation.N;
import static marsExploration.Robot.Orientation.S;
import static marsExploration.Robot.Orientation.W;
import static org.junit.jupiter.api.Assertions.*;

//todo: replace multiple assertions in single test with assertAll(a1,a2,a3...)
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
        assertTrue(robot.getCoordinateX() == 1
            && robot.getCoordinateY() == 5
            && robot.getOrientation().equals(N));
    }

    @Test
    void shouldMoveForwardAndFaceTheSameDirection() {
        robotAt00N.move(F);
        assertEquals(0, robotAt00N.getCoordinateX());
        assertEquals(1, robotAt00N.getCoordinateY());
        assertEquals(N, robotAt00N.getOrientation());
        assertFalse(robotAt00N.isLost());
    }

    @Test
    void shouldRotateRight() {
        robotAt00N.move(Instruction.R);
        assertEquals(E, robotAt00N.getOrientation());
        robotAt00N.move(Instruction.R);
        assertEquals(S, robotAt00N.getOrientation());
        robotAt00N.move(Instruction.R);
        assertEquals(W, robotAt00N.getOrientation());
        robotAt00N.move(Instruction.R);
        assertEquals(N, robotAt00N.getOrientation());

        assertEquals(0, robotAt00N.getCoordinateX());
        assertEquals(0, robotAt00N.getCoordinateY());
        assertFalse(robotAt00N.isLost());
    }

    @Test
    void shouldRotateLeft() {
        robotAt00N.move(L);
        assertEquals(W, robotAt00N.getOrientation());
        robotAt00N.move(L);
        assertEquals(S, robotAt00N.getOrientation());
        robotAt00N.move(L);
        assertEquals(E, robotAt00N.getOrientation());
        robotAt00N.move(L);
        assertEquals(N, robotAt00N.getOrientation());

        assertEquals(0, robotAt00N.getCoordinateX());
        assertEquals(0, robotAt00N.getCoordinateY());
        assertFalse(robotAt00N.isLost());
    }

    @Test
    void shouldMoveForward3x() {
        robotAt00N.move(F, F, F);
        assertEquals(3, robotAt00N.getCoordinateY());
        assertEquals(0, robotAt00N.getCoordinateX());
        assertFalse(robotAt00N.isLost());
    }

    @Test
    void shouldLandOnX5Y2AndBeLost() {
        robotAt00N.move(F, F, R, F, F, F, F, F, F, F, R, F);
        assertEquals(2, robotAt00N.getCoordinateY());
        assertEquals(5, robotAt00N.getCoordinateX());
        assertTrue(robotAt00N.isLost());
    }

    @Test
    void shouldLandOnX0Y0AndBeLost() {
        robotAt00N.move(L, F, F, R, F, F, F, F, F, F, F, R, F);
        assertEquals(0, robotAt00N.getCoordinateY());
        assertEquals(0, robotAt00N.getCoordinateX());
        assertTrue(robotAt00N.isLost());
    }

    @Test
    void shouldLandOnX5Y10AndNotBeLost() {
        robotAt00N.move(F, F, R, F, F, F, F, F, L, F, F, F, F, F, F, F, F);
        assertEquals(10, robotAt00N.getCoordinateY());
        assertEquals(5, robotAt00N.getCoordinateX());
        assertFalse(robotAt00N.isLost());
    }

    @Test
    void shouldLandOnX5Y0AndBeLost() {
        robotAt00N.move(R, F, F, F, F, F, F, F, F, F, F);
        assertEquals(0, robotAt00N.getCoordinateY());
        assertEquals(5, robotAt00N.getCoordinateX());
        assertTrue(robotAt00N.isLost());
    }

    @Test
    void shouldLandOnX0Y10AndBeLost() {
        robotAt00N.move(F, F, F, F, F, F, F, F, F, F, F, F, F, F);
        assertEquals(10, robotAt00N.getCoordinateY());
        assertEquals(0, robotAt00N.getCoordinateX());
        assertTrue(robotAt00N.isLost());
    }

    @Test
    void shouldPrintCorrectStatusIfLost() {
        robotAt00N.move(F, F, F, F, F, F, F, F, F, F, F, F, F, F);
        System.out.println(robotAt00N.getStatus());
        assertEquals("(0, 10, N) LOST", robotAt00N.getStatus());
    }

    @Test
    void shouldPrintCorrectStatusIfNotLost() {
        robotAt00N.move(F, F, R, F, R);
        System.out.println(robotAt00N.getStatus());
        assertEquals("(1, 2, S)", robotAt00N.getStatus());
    }
}