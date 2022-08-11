package marsExploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public enum Orientation {
        N, E, S, W;

        private static List<Orientation> directions = new ArrayList<>(Arrays.asList(Orientation.N, Orientation.E, Orientation.S, Orientation.W));

        public static Orientation rightOf(Orientation orientation) {
            return directions.get(getValidIndex(directions.indexOf(orientation) + 1));
        }

        public static Orientation leftOf(Orientation orientation) {
            return directions.get(getValidIndex(directions.indexOf(orientation) - 1));
        }

        private static int getValidIndex(int index) {
            if (index > 3) return 0;
            if (index < 0) return 3;
            return index;
        }
    }

