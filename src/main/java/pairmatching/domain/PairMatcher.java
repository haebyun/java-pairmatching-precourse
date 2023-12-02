package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatcher {
    private List<MatchingInfo> matchingResults = new ArrayList<>();
    private CrewRepository crewRepository;

    public PairMatcher(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public boolean hasPreviousMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        MatchingInfo existingMatchingInfo = findMatchingInfo(courseLevelMissionInput);

        if (existingMatchingInfo == null) {
            return false;
        }
        return true;
    }

    public void matchPairs(CourseLevelMissionInput courseLevelMissionInput) {
        MatchingInfo existingMatchingInfo = findMatchingInfo(courseLevelMissionInput);

        if (existingMatchingInfo == null) {
            createMatchingInfo(courseLevelMissionInput);
            return;
        }

        updateMatchingInfo(existingMatchingInfo);
    }

    private MatchingInfo findMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        for (MatchingInfo matchingInfo : matchingResults) {
            if (matchingInfo.getCourse() == courseLevelMissionInput.getCourse() &&
                    matchingInfo.getLevel() == courseLevelMissionInput.getLevel() &&
                    matchingInfo.getMission() == courseLevelMissionInput.getMission()) {
                return matchingInfo;
            }
        }
        return null;
    }

    private void updateMatchingInfo(MatchingInfo matchingInfo) {
        List<Crew> selectedCrews = crewRepository.getCrewsByCourse(matchingInfo.getCourse());
        crewRepository.shuffleCrews(selectedCrews);
        List<Pair> pairs = performPairMatching(crewRepository.getCrews());

        matchingInfo.updatePairs(pairs);
    }

    private void createMatchingInfo(CourseLevelMissionInput courseLevelMissionInput) {
        List<Crew> selectedCrews = crewRepository.getCrewsByCourse(courseLevelMissionInput.getCourse());
        crewRepository.shuffleCrews(selectedCrews);
        List<Pair> pairs = performPairMatching(crewRepository.getCrews());
        MatchingInfo matchingInfo = new MatchingInfo(courseLevelMissionInput.getCourse(), courseLevelMissionInput.getLevel(), courseLevelMissionInput.getMission(), pairs);
        matchingResults.add(matchingInfo);
    }

    public List<Pair> performPairMatching(List<Crew> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size(); i += 2) {
            if (i == crews.size() - 1) {
                // 마지막 남은 한 명인 경우, 이전 페어에 추가
                pairs.get(pairs.size() - 1).addCrew(crews.get(i));
                continue;
            }
            pairs.add(new Pair(crews.get(i), crews.get(i + 1)));
        }
        return pairs;
    }

    public MatchingInfo getMatchingInfoByCourseLevelMission(Course course, Level level, Mission mission) {
        for (MatchingInfo matchingInfo : matchingResults) {
            if (matchingInfo.getCourse() == course && matchingInfo.getLevel() == level && matchingInfo.getMission() == mission) {
                return matchingInfo;
            }
        }
        return null;
    }

    public void initializeMatchingResults() {
        matchingResults = new ArrayList<>();
    }
}
