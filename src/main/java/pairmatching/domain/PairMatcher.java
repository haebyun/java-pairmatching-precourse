package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatcher {
    private List<MatchingInfo> matchingResults = new ArrayList<>();
    private CrewRepository crewRepository;

    public PairMatcher(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public void matchPairs(Course course, Level level, Mission mission) {
        List<Crew> selectedCrews = crewRepository.getCrewsByCourse(course);

        crewRepository.shuffleCrews(selectedCrews);
        List<Pair> pairs = performPairMatching(crewRepository.getCrews());

        MatchingInfo matchingInfo = new MatchingInfo(course, level, mission, pairs);

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

    public List<MatchingInfo> getMatchingResults() {
        return matchingResults;
    }

}
