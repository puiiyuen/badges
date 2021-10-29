package ch.peiyuan.badges.controller;

import ch.peiyuan.badges.api.LeetcodeApi;
import ch.peiyuan.badges.model.LeetCode;
import ch.peiyuan.badges.model.ShieldBadge;
import ch.peiyuan.badges.service.LeetcodeService;
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
        Optional<LeetCode> result = leetcodeService.getLeetcodeUserStats(username);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeSubmissionByUsername(String username, String difficulty, Boolean accepted) {
        Optional<byte[]> result;
        if(accepted) {
            result = leetcodeService.getLeetcodeBadgeAcceptedSubmissions(username, difficulty);
        } else {
            result = leetcodeService.getLeetcodeBadgeTotalSubmissions(username, difficulty);
        }
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeRankingByUsername(String username) {
        Optional<byte[]> result = leetcodeService.getLeetcodeBadgeRanking(username);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeSolvedProblemByUsername(String username, String difficulty) {
        Optional<byte[]> result = leetcodeService.getLeetcodeBadgeSolvedProblems(username, difficulty);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeAcceptedRateByUsername(String username, String difficulty) {
        Optional<byte[]> result = leetcodeService.getLeetcodeBadgeAcceptedRate(username, difficulty);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<byte[]> getLeetcodeBadgeNameByUsername(String username) {
        Optional<byte[]> result = leetcodeService.getLeetcodeBadgeName(username);
        return ResponseEntity.of(result);
    }

    public ResponseEntity<ShieldBadge> getLeetcodeBadgesByUsername(String username) {
        leetcodeService.getAllLeetcodeBadges(username);
        return ResponseEntity.ok(new ShieldBadge());
    }
}
