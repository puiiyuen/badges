package ch.peiyuan.badges.service;

import ch.peiyuan.badges.enums.LeetCodeDifficulty;
import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;
import ch.peiyuan.badges.model.ShieldParams;
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
    public Optional<LeetCode> getUserStats(@NotNull String username) {
        try {
            return Optional.of(leetCodeGenerator.getLeetCodeUser(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ShieldBadge> getAllBadges(@NotNull String username, ShieldParams shieldParams) {
        ShieldBadge shieldBadge = new ShieldBadge();
        shieldBadge.setCategory("LeetCode");
//        shieldBadge.addLinksItem(getBadgeSolvedProblems(username, "all", shieldParams).get());

        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeName(@NotNull String username, ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);
            String name = shieldsIoUrl
                    + "LeetCode"
                    + "-"
                    + leetCode.getUsername()
                    + "-"
                    + "orange"
                    + "?"
                    + shieldParamsExt;
            byte[] badge = badgeGenerator.getBadge(name);
            return Optional.of(badge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeRanking(@NotNull String username, ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);
            String ranking = shieldsIoUrl
                    + "Ranking"
                    + "-"
                    + leetCode.getRanking()
                    + "-"
                    + "orange"
                    + "?"
                    + shieldParamsExt;
            byte[] badge = badgeGenerator.getBadge(ranking);
            return Optional.of(badge);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeSolvedProblems(@NotNull String username, String difficulty,
                                                   ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);
            String acProblems = shieldsIoUrl
                    + "Solved"
                    + "-"
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getCount()
                    + "/"
                    + leetCode.getAllQuestionsCount().get(leetCodeDifficulty.getOrder()).getCount()
                    + "-"
                    + leetCodeDifficulty.getColor()
                    + "?"
                    + shieldParamsExt;

            byte[] badge = badgeGenerator.getBadge(acProblems);
            return Optional.of(badge);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeAcceptedRate(@NotNull String username, String difficulty,
                                                 ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);

            int acNum = leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions();
            int totalNum = leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions();
            double acRate = (double) acNum / totalNum * 100;
            String acceptedRate = shieldsIoUrl
                    + "Accepted"
                    + "-"
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + String.format("%.1f", acRate)
                    + "%25"
                    + "-"
                    + leetCodeDifficulty.getColor()
                    + "?"
                    + shieldParamsExt;
            byte[] badge = badgeGenerator.getBadge(acceptedRate);
            return Optional.of(badge);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeAcceptedSubmissions(@NotNull String username, String difficulty,
                                                        ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);

            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String acSubmissions = shieldsIoUrl
                    + "Accepted"
                    + "-"
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions()
                    + "-"
                    + leetCodeDifficulty.getColor()
                    + "?"
                    + shieldParamsExt;

            byte[] badge = badgeGenerator.getBadge(acSubmissions);
            return Optional.of(badge);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getBadgeTotalSubmissions(@NotNull String username, String difficulty,
                                                     ShieldParams shieldParams) {
        try {
            LeetCode leetCode = leetCodeGenerator.getLeetCodeUser(username);
            ShieldParams shieldParamsExt = new ShieldParamsImplement(shieldParams);
            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String totalSubmissions = shieldsIoUrl
                    + "Total"
                    + "-"
                    + leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + " "
                    + leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getSubmissions()
                    + "-"
                    + leetCodeDifficulty.getColor()
                    + "?"
                    + shieldParamsExt;

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

class ShieldParamsImplement extends ShieldParams {

    private final ShieldParams shieldParams;

    ShieldParamsImplement(ShieldParams shieldParams) {
        this.shieldParams = shieldParams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (shieldParams.getStyle() != null) {
            sb.append("&style=");
            sb.append(shieldParams.getStyle());
        }
        if (shieldParams.getLabel() != null) {
            sb.append("&label=");
            sb.append(shieldParams.getLabel());
        }
        if (shieldParams.getLabelColor() != null) {
            sb.append("&labelColor=");
            sb.append(shieldParams.getLabelColor());
        }
        if (shieldParams.getColor() != null) {
            sb.append("&color=");
            sb.append(shieldParams.getColor());
        }
        if (shieldParams.getLogo() != null) {
            sb.append("&logo=");
            sb.append(shieldParams.getLogo());
        }
        if (shieldParams.getLogoColor() != null) {
            sb.append("&logoColor=");
            sb.append(shieldParams.getLogoColor());
        }
        if (shieldParams.getLogoWidth() != null) {
            sb.append("&logoWidth=");
            sb.append(shieldParams.getLogoWidth());
        }
        if (shieldParams.getLink() != null) {
            for (String link : shieldParams.getLink()) {
                sb.append("&link=");
                sb.append(link);
            }
        }
        return sb.toString();
    }
}