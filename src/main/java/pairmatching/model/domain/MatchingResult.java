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
                putSpecialResults(crews, shuffledCrew.subList(i, i+3), level);
                break;
            }
            results.add(List.of(shuffledCrew.get(i), shuffledCrew.get(i + 1)));
            addMatchingHistory(crews, shuffledCrew.get(i), shuffledCrew.get(i+1), level);
        }
    }

    private void putSpecialResults(Map<String, Crew> crews, List<String> names, String level) {
        results.add(names);
        for (int i = 0; i < names.size(); i++) {
            processNameCombinations(crews, names, level, i);
        }
    }

    private void processNameCombinations(Map<String, Crew> crews, List<String> names, String level, int index) {
        for (int j = 0; j < names.size(); j++) {
            if (index != j) {
                addMatchingHistory(crews, names.get(index), names.get(j), level);
            }
        }
    }

    private void addMatchingHistory(Map<String, Crew> crews, String firstName, String secondName, String level) {
        crews.get(firstName).addMatchingHistory(level, secondName);
        crews.get(secondName).addMatchingHistory(level, firstName);
    }

    private boolean hasMatchingHistory(Map<String, Crew> crews, List<String> shuffledCrew, String level) {
        int lastIndex = shuffledCrew.size() - 1;
        for (int i = 0; i < lastIndex; i += 2) {
            if (checkNormalCase(crews, shuffledCrew.get(i), shuffledCrew.get(i + 1), level)) {
                return true;
            }
        }
        if (lastIndex % 2 == 0) {
            return checkSpecialCase(crews, shuffledCrew.subList(lastIndex - 2, lastIndex + 1), level);
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
