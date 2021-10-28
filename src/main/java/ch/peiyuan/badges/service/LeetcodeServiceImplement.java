package ch.peiyuan.badges.service;

import ch.peiyuan.badges.enums.LeetCodeDifficulty;
import ch.peiyuan.badges.model.LeetCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;


@Service
public class LeetcodeServiceImplement implements LeetcodeService {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String shieldsIoUrl = "https://img.shields.io/badge/";
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final BadgeGenerator badgeGenerator;

    public LeetcodeServiceImplement(BadgeGenerator badgeGenerator) {
        this.badgeGenerator = badgeGenerator;
        this.okHttpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public Optional<LeetCode> getLeetcodeUserStats(@NotNull String username) {
        try {
            return Optional.of(getLeetCodeUser(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeAcceptedProblems(@NotNull String username, String difficulty) {
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getLeetcodeBadgeAcceptedSubmissions(@NotNull String username, String difficulty) {
        try {
            LeetCode leetCode = getLeetCodeUser(username);

            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String acSubmissions = shieldsIoUrl
                    + leetCode.getAcSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + "-"
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
            LeetCode leetCode = getLeetCodeUser(username);

            LeetCodeDifficulty leetCodeDifficulty = getLeetCodeDifficulty(difficulty);

            String totalSubmissions = shieldsIoUrl
                    + leetCode.getTotalSubmissionNum().get(leetCodeDifficulty.getOrder()).getDifficulty()
                    + "-"
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

    public LeetCode getLeetCodeUser(String username) throws IOException {
        String result = fetchFromLeetCode(username);
        JsonNode jsonNode = objectMapper.readTree(result).get("data");

        LeetCode leetCode = new LeetCode();

        JsonNode matchedUser = jsonNode.get("matchedUser");
        leetCode.setUsername(matchedUser.get("username").asText());
        leetCode.setRanking(matchedUser.get("profile").get("ranking").asInt());
        leetCode.setReputation(matchedUser.get("profile").get("reputation").asInt());

        leetCode.setAllQuestionsCount(objectMapper.convertValue(jsonNode.get("allQuestionsCount"),
                new TypeReference<>() {
                }));

        JsonNode submission = matchedUser.get("submitStats");
        leetCode.setAcSubmissionNum(objectMapper.convertValue(submission.get("acSubmissionNum"),
                new TypeReference<>() {
                }));
        leetCode.setTotalSubmissionNum(objectMapper.convertValue(submission.get("totalSubmissionNum"),
                new TypeReference<>() {
                }));

        return leetCode;
    }

    public String fetchFromLeetCode(String username) throws IOException {
        String url = "https://leetcode.com/graphql";
        String json = "{\n" +
                "    \"operationName\": \"getUserProfile\",\n" +
                "    \"variables\": {\n" +
                "        \"username\": \"" + username + "\"\n" +
                "    },\n" +
                "    \"query\": \n" +
                "    \"query getUserProfile($username: String!) {\\n" +
                "  allQuestionsCount {\\n" +
                "    difficulty\\n" +
                "    count\\n" +
                "    }\\n" +
                "  matchedUser(username: $username) {\\n" +
                "    username\\n" +
                "    profile {\\n" +
                "      reputation\\n" +
                "      ranking\\n" +
                "    }\\n" +
                "    submitStats: submitStatsGlobal {\\n" +
                "      acSubmissionNum {\\n" +
                "        difficulty\\n" +
                "        count\\n" +
                "        submissions\\n" +
                "      }\\n" +
                "      totalSubmissionNum {\\n" +
                "        difficulty\\n" +
                "        count\\n" +
                "        submissions\\n" +
                "      }\\n" +
                "    }\\n" +
                "  }\\n" +
                "}\\n\"\n" +
                "}";

        return post(url, json);
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
