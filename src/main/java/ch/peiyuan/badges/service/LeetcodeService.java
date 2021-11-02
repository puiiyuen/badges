package ch.peiyuan.badges.service;

import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;
import ch.peiyuan.badges.model.ShieldParams;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface LeetcodeService {
    Optional<LeetCode> getUserStats(@NotNull String username);

    Optional<byte[]> getBadgeTotalSubmissions(@NotNull String username, String difficulty, ShieldParams shieldParams);

    Optional<byte[]> getBadgeAcceptedSubmissions(@NotNull String username, String difficulty, ShieldParams shieldParams);

    Optional<byte[]> getBadgeSolvedProblems(@NotNull String username, String difficulty, ShieldParams shieldParams);

    Optional<byte[]> getBadgeAcceptedRate(@NotNull String username, String difficulty, ShieldParams shieldParams);

    Optional<byte[]> getBadgeRanking(@NotNull String username, ShieldParams shieldParams);

    Optional<byte[]> getBadgeName(@NotNull String username, ShieldParams shieldParams);

    Optional<ShieldBadge> getAllBadges(@NotNull String username, ShieldParams shieldParams);
}
