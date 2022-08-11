package marsExploration;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InstructionsProcessor {
    private static String INVALID_INPUT = "Invalid input.";

/*    in what form/dataType the data will be received is at the moment unknown.
For simplicity, I will now assume it will be in the form of text.
For more simplicity, I will also assume that the data will be in correct form, with correct characters in correct places.
All this would require validation of course in 'reality'.
 */

    public static void main(String[] args) {
        //This is a temporary testing method. With more time, proper testing would be implemented.
        String instructions1 = "4 8\n" +
            "(2, 3, E) LFRFF\n" +
            "(0, 2, N) FFLFRFF";
        String response1 = processRequest(instructions1);
        System.out.println("result 1: \n" + response1 + "\n");

        String instructions2 = "4 8\n" +
            "(2, 3, N) FLLFR\n" +
            "(1, 0, S) FFRLF";
        String response2 = processRequest(instructions2);
        System.out.println("result 2: \n" + response2 + "\n");

        String instructions3 = "4 3 8";
        String response3 = processRequest(instructions3);
        System.out.println("result 3: \n" + response3);
    }

    public static String processRequest(String request) {
        String[] rows = request.split("\n");
        if (rows[0] == null) return INVALID_INPUT;
        //todo some error handling here
        if (!worldInputIsValid(rows[0])) return INVALID_INPUT;

        World world = buildWorld(rows[0]);
        return buildResponseUsingRobotsInTheWorld(world, Arrays.stream(rows).skip(1));
    }

    //example of simple validation
    private static boolean worldInputIsValid(String firstRow) {
        return firstRow.matches("[0-9]+ [0-9]+");
    }

    private static World buildWorld(String firstRowOfInput) {
        String[] worldInfo = firstRowOfInput.split(" ");
        return new World(Long.parseLong(worldInfo[0]), Long.parseLong(worldInfo[1]));
    }

    private static String buildResponseUsingRobotsInTheWorld(World world, Stream<String> robotsData) {
        return robotsData
            .map(robotData -> {
                    String[] positionsAndInstructions = robotData.split("\\) ");
                    String[] positions = positionsAndInstructions[0].substring(1).split(", ");
                    Robot.Instruction[] instructions = Arrays.stream(positionsAndInstructions[1].split(""))
                        .map(Robot.Instruction::valueOf)
                        .toArray(Robot.Instruction[]::new);

                    Robot robot = new Robot(world,
                        Long.parseLong(positions[0]),
                        Long.parseLong(positions[1]),
                        Robot.Orientation.valueOf(positions[2]));
                    robot.move(instructions);
                    return robot.getStatus();
                }
            )
            .collect(Collectors.joining("\n"));
    }
}
