package pairmatching.global.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomShuffler {
    public static List<String> shuffle(List<String> names) {
        return Randoms.shuffle(names);
    }
}
