package pairmatching.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.model.constants.Course;
import pairmatching.model.domain.Crew;
import pairmatching.model.domain.MatchingResult;
import pairmatching.util.CrewMaker;

public class PairMatchingService {
    private final Map<String, Crew> backendCrews;
    private final Map<String, Crew> frontendCrews;
    private final Map<String, MatchingResult> results = new HashMap<>();

    public PairMatchingService() {
        backendCrews = CrewMaker.makeCrews(Course.BACKEND);
        frontendCrews = CrewMaker.makeCrews(Course.FRONTEND);
    }

    public void makeMatching(String sequenceOfOptions, List<String> options) {
        String course = options.get(0);
        String level = options.get(1);
        if (course.equals("백엔드")) {
            results.put(sequenceOfOptions, new MatchingResult(backendCrews, level));
            return;
        }
        if (course.equals("프론트엔드")) {
            results.put(sequenceOfOptions, new MatchingResult(frontendCrews, level));
        }
    }

    public List<List<String>> getMatchingResult(String sequenceOfOptions) {
        return results.get(sequenceOfOptions).getResults();
    }

    public void resetMatchingResults() {
        backendCrews.forEach((key, value) -> value.resetHistories());
        frontendCrews.forEach((key, value) -> value.resetHistories());
        results.clear();
    }


    public boolean hasResult(String sequenceOfOptions) {
        return results.containsKey(sequenceOfOptions);
    }
}
