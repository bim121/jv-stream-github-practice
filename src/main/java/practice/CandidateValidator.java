package practice;

import model.Candidate;
import java.util.function.Predicate;

public class CandidateValidator implements Predicate<Candidate> {
    private final String COUNTRY_CANDIDATE_NAME = "Ukrainian";

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }

        if (candidate.getAge() < 35) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        if (!COUNTRY_CANDIDATE_NAME.equalsIgnoreCase(candidate.getNationality())) {
            return false;
        }

        String[] years = candidate.getPeriodsInUkr().split("-");
        if (years.length != 2) {
            return false;
        }

        try {
            int from = Integer.parseInt(years[0].trim());
            int to = Integer.parseInt(years[1].trim());
            return (to - from) >= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}