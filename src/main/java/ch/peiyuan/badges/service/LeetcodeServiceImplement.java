package ch.peiyuan.badges.service;

import ch.peiyuan.badges.enums.LeetCodeDifficulty;
import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;


@Service
public class LeetcodeServiceImplement implements LeetcodeService {

    private static final String shieldsIoUrl = "https://img.shields.io/badge/";
    private final BadgeGenerator badgeGenerator;
    private final LeetCodeGenerator leetCodeGenerator;

    public LeetcodeServiceImplement(BadgeGenerator badgeGenerator, LeetCodeGenerator leetCodeGenerator) {
        this.badgeGenerator = badgeGenerator;
        this.leetCodeGenerator = leetCodeGenerator;
    }


    @Override
    public Optional<LeetCode> getLeetcodeUserStats(@NotNull String username) {
        try {
            return Optional.of(leetCodeGenerator.getLeetCodeUser(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ShieldBadge> getAllLeetcodeBadges(@NotNull String username) {
        ShieldBadge shieldBadge = new ShieldBadge();
        shieldBadge.setCategory("LeetCode");
        shieldBadge.addLinksItem(getLeetcodeBadgeSolvedProblems(username, "all").get());

        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeName(@NotNull String username) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            String name = shieldsIoUrl
                    + "LeetCode"
                    + "-"
                    + leetCode.getUsername()
                    + "-"
                    + "orange";
            byte[] badge = badgeGenerator.getBadge(name);
            return Optional.of(badge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeRanking(@NotNull String username) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            String ranking = shieldsIoUrl
                    + "Ranking"
                    + "-"
                    + leetCode.getRanking()
                    + "-"
                    + "orange";
            byte[] badge = badgeGenerator.getBadge(ranking);
            return Optional.of(badge);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeSolvedProblems(@NotNull String username, String difficulty) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String acProblems = shieldsIoUrl
                    + "Solved"
                    + "-"
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getCount()
                    + "/"
                    + leetCode.getAllQuestionsCount().get(leetCodeDifficulty.getOrder()).getCount()
                    + "-"
                    + leetCodeDifficulty.getColor();

            byte[] badge = badgeGenerator.getBadge(acProblems);
            return Optional.of(badge);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeAcceptedSubmissions(@NotNull String username, String difficulty) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);

            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String acSubmissions = shieldsIoUrl
                    + "Accepted"
                    + "-"
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions()
                    + "-"
                    + leetCodeDifficulty.getColor();

            byte[] badge = badgeGenerator.getBadge(acSubmissions);
            return Optional.of(badge);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeTotalSubmissions(@NotNull String username, String difficulty) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);

            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String totalSubmissions = shieldsIoUrl
                    + "Total"
                    + "-"
                    + leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions()
                    + "-"
                    + leetCodeDifficulty.getColor();

            byte[] badge = badgeGenerator.getBadge(totalSubmissions);
            return Optional.of(badge);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public LeetCodeDifficulty getLeetCodeDifficulty(String difficulty) {
        if ("easy".equals(difficulty)) {
            return LeetCodeDifficulty.EASY;
        } else if ("medium".equals(difficulty)) {
            return LeetCodeDifficulty.MEDIUM;
        } else if ("hard".equals(difficulty)) {
            return LeetCodeDifficulty.HARD;
        } else {
            return LeetCodeDifficulty.ALL;
        }
    }


}
