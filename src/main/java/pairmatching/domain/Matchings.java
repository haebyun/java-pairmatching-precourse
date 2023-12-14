package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.MemberSubstitution.Substitution.ForMethodInvocation.MethodResolver.Matching;

public class Matchings {
    private Map<CourseMission, Matching> matchings;

    public Matchings() {
        matchings = new HashMap<>();
    }
}
