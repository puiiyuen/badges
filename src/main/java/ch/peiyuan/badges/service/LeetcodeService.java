package ch.peiyuan.badges.service;

import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface LeetcodeService {
    Optional<LeetCode> getLeetcodeUserStats(@NotNull String username);

    Optional<byte[]> getLeetcodeBadgeTotalSubmissions(@NotNull String username, String difficulty);

    Optional<byte[]> getLeetcodeBadgeAcceptedSubmissions(@NotNull String username, String difficulty);

    Optional<byte[]> getLeetcodeBadgeSolvedProblems(@NotNull String username, String difficulty);

    Optional<byte[]> getLeetcodeBadgeRanking(@NotNull String username);

    Optional<byte[]> getLeetcodeBadgeName(@NotNull String username);

    Optional<ShieldBadge> getAllLeetcodeBadges(@NotNull String username);
}
