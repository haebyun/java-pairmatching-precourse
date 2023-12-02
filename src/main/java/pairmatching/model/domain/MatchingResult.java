package pairmatching.model.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MatchingResult {
    private final List<List<String>> results = new ArrayList<>();
    private final List<String> crewNames;

    public MatchingResult(Map<String, Crew> crews, String level) {
        crewNames = crews.keySet().stream().toList();
        makeMatchingResults(crews, level);
    }

    public void makeMatchingResults(Map<String, Crew> crews, String level) {
        List<String> shuffledCrew = Randoms.shuffle(Objects.requireNonNull(crewNames));
        if (!hasMatchingHistory(crews, shuffledCrew, level)) {
            putResults(crews, shuffledCrew, level);
            return;
        }
        makeMatchingResults(crews, level);
    }

    private void putResults(Map<String, Crew> crews, List<String> shuffledCrew, String level) {
        for (int i = 0; i < shuffledCrew.size(); i += 2) {
            if (shuffledCrew.size() - i == 3) {
                putSpecialResults(crews, shuffledCrew, level);
                break;
            }
            results.add(List.of(shuffledCrew.get(i), shuffledCrew.get(i + 1)));
            crews.get(shuffledCrew.get(i)).addMatchingHistory(level, shuffledCrew.get(i + 1));
            crews.get(shuffledCrew.get(i + 1)).addMatchingHistory(level, shuffledCrew.get(i));
        }
    }

    private void putSpecialResults(Map<String, Crew> crews, List<String> shuffledCrew, String level) {
        int lastIndex = shuffledCrew.size() - 1;
        results.add(
                List.of(shuffledCrew.get(lastIndex), shuffledCrew.get(lastIndex - 1), shuffledCrew.get(lastIndex - 2)));
        crews.get(shuffledCrew.get(lastIndex)).addMatchingHistory(level, shuffledCrew.get(lastIndex - 1));
        crews.get(shuffledCrew.get(lastIndex)).addMatchingHistory(level, shuffledCrew.get(lastIndex - 2));
        crews.get(shuffledCrew.get(lastIndex - 1)).addMatchingHistory(level, shuffledCrew.get(lastIndex));
        crews.get(shuffledCrew.get(lastIndex - 1)).addMatchingHistory(level, shuffledCrew.get(lastIndex - 2));
        crews.get(shuffledCrew.get(lastIndex - 2)).addMatchingHistory(level, shuffledCrew.get(lastIndex));
        crews.get(shuffledCrew.get(lastIndex - 2)).addMatchingHistory(level, shuffledCrew.get(lastIndex - 1));
    }

    private boolean hasMatchingHistory(Map<String, Crew> crews, List<String> shuffledCrew, String level) {
        int shuffledCrewSize = shuffledCrew.size();
        for (int i = 0; i < shuffledCrewSize; i += 2) {
            String firstName = shuffledCrew.get(i);
            String secondName = shuffledCrew.get(i + 1);
            if (shuffledCrewSize - i == 3) {
                return checkSpecialCase(crews, shuffledCrew.subList(i, i + 3), level);
            }
            if (checkNormalCase(crews, firstName, secondName, level)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNormalCase(Map<String, Crew> crews, String firstName, String secondName, String level) {
        return crews.get(firstName).hasMatchingHistory(secondName, level);
    }

    private boolean checkSpecialCase(Map<String, Crew> crews, List<String> names, String level) {
        return crews.get(names.get(0)).hasMatchingHistory(names.get(1), level)
                || crews.get(names.get(0)).hasMatchingHistory(names.get(2), level);
    }

    public List<List<String>> getResults() {
        return results;
    }
}
