package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class MissionRepository {
    private List<Mission> missions;

    public MissionRepository() {
        missions = new ArrayList<>();
        initializeMissions();
    }

    private void initializeMissions() {
        missions.add(new Mission(Level.LEVEL1, "자동차경주"));
        missions.add(new Mission(Level.LEVEL1, "로또"));
        missions.add(new Mission(Level.LEVEL1, "숫자야구게임"));

        missions.add(new Mission(Level.LEVEL2, "장바구니"));
        missions.add(new Mission(Level.LEVEL2, "결제"));
        missions.add(new Mission(Level.LEVEL2, "지하철노선도"));

        missions.add(new Mission(Level.LEVEL4, "성능개선"));
        missions.add(new Mission(Level.LEVEL4, "배포"));

    }

    public List<Mission> getMissions() {
        return missions;
    }
}
