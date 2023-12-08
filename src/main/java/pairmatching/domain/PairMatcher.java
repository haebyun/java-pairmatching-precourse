package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PairMatcher {
    private final List<MatchingInfo> matchingResults = new ArrayList<>();
    private final CrewRepository crewRepository;

    public PairMatcher(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public boolean hasPreviousMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        return findMatchingInfo(courseLevelMissionInput).isPresent();
    }

    public void matchPairs(CourseLevelMissionInput courseLevelMissionInput) {
        Optional<MatchingInfo> existingMatchingInfo = findMatchingInfo(courseLevelMissionInput);

        if (existingMatchingInfo.isEmpty()) {
            createNewMatchingInfo(courseLevelMissionInput);
            return;
        }

        updateExistingMatchingInfo(existingMatchingInfo.get());
    }

    public Optional<MatchingInfo> findMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        return matchingResults.stream()
                .filter(matchingInfo -> matchingInfo.matchesCourseLevelMission(courseLevelMissionInput))
                .findFirst();
      }

    private void updateExistingMatchingInfo(MatchingInfo matchingInfo) {
        List<Crew> selectedCrews = crewRepository.getCrewsByCourse(matchingInfo.getCourse());
        crewRepository.shuffleCrews(selectedCrews);
        matchingInfo.updatePairs(performPairMatching(selectedCrews));
    }

    private void createNewMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        List<Crew> selectedCrews = crewRepository.getCrewsByCourse(courseLevelMissionInput.getCourse());
        crewRepository.shuffleCrews(selectedCrews);
        MatchingInfo newMatchingInfo = new MatchingInfo(courseLevelMissionInput.getCourse(), courseLevelMissionInput.getLevel(), courseLevelMissionInput.getMission(), performPairMatching(selectedCrews));
        matchingResults.add(newMatchingInfo);
    }

    public List<Pair> performPairMatching(List<Crew> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size(); i += 2) {
            if (i == crews.size() - 1) {
                pairs.get(pairs.size() - 1).addCrew(crews.get(i));
                continue;
            }
            pairs.add(new Pair(crews.get(i), crews.get(i + 1)));
        }
        return pairs;
    }

    public void initializeMatchingResults() {
        matchingResults.clear();
    }
}
