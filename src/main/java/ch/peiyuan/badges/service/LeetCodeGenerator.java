package ch.peiyuan.badges.service;

import ch.peiyuan.badges.model.LeetCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LeetCodeGenerator {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public LeetCodeGenerator() {
        this.okHttpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
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
