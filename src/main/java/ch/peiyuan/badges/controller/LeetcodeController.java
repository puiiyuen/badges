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
//        return ResponseEntity.ok(result.get());
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
        //        return ResponseEntity.ok("Ok");
    }

    public ResponseEntity<ShieldBadge> getLeetcodeBadgesByUsername(String username) {
        return ResponseEntity.ok(new ShieldBadge());
    }
}
