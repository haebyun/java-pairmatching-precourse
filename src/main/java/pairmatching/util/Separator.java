package pairmatching.util;

import java.util.Arrays;
import java.util.List;

public class Separator {
    public static List<String> separateByComma(String options) {
        return Arrays.asList(options.split(", ", -1));
    }
}
