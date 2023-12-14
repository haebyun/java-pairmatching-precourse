package pairmatching.controller.dto;

import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;

public record Stage(
        Course course,
        Level level,
        Mission mission
) {
}
