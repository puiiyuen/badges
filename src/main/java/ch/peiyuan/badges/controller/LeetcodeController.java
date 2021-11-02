package ch.peiyuan.badges.controller;

import ch.peiyuan.badges.api.LeetcodeApi;
import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;
import ch.peiyuan.badges.model.ShieldParams;
import ch.peiyuan.badges.service.LeetcodeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class LeetcodeController implements LeetcodeApi {

    private final LeetcodeService leetcodeService;

    public LeetcodeController(LeetcodeService leetcodeService) {
        this.leetcodeService = leetcodeService;
    }


    public ResponseEntity<LeetCode> getLeetcodeUserStats(String username) {
        Optional<LeetCode> result = leetcodeService.getUserStats(username);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeSubmissionByUsername(String username, String difficulty,
                                                                       @NotNull Boolean accepted,
                                                                       ShieldParams shieldParams) {
        Optional<byte[]> result;
        if (accepted) {
            result = leetcodeService.getBadgeAcceptedSubmissions(username, difficulty, shieldParams);
        } else {
            result = leetcodeService.getBadgeTotalSubmissions(username, difficulty, shieldParams);
        }
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeRankingByUsername(String username, ShieldParams shieldParams) {
        Optional<byte[]> result = leetcodeService.getBadgeRanking(username, shieldParams);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeSolvedProblemByUsername(String username, String difficulty,
                                                                          ShieldParams shieldParams) {
        Optional<byte[]> result = leetcodeService.getBadgeSolvedProblems(username, difficulty,shieldParams);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeAcceptedRateByUsername(String username, String difficulty,
                                                                         ShieldParams shieldParams) {
        Optional<byte[]> result = leetcodeService.getBadgeAcceptedRate(username, difficulty, shieldParams);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeNameByUsername(String username, ShieldParams shieldParams) {
        Optional<byte[]> result = leetcodeService.getBadgeName(username, shieldParams);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<ShieldBadge> getAllLeetcodeBadgesByUsername(String username, ShieldParams shieldParams) {
        leetcodeService.getAllBadges(username, shieldParams);
        return ResponseEntity.ok(new ShieldBadge());
    }
}
