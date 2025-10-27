package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35;
    private static final int MIN_EXPERIENCE = 10;
    private static final String COUNTRY_CANDIDATE_NAME = "Ukrainian";

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }

        if (candidate.getAge() < MIN_AGE) {
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
            return (to - from) >= MIN_EXPERIENCE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
