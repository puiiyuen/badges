package ch.peiyuan.badges.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BadgeGeneratorImplement implements BadgeGenerator {

    private OkHttpClient okHttpClient;

    public BadgeGeneratorImplement() {
        this.okHttpClient = new OkHttpClient();
    }

    @Override
    public byte[] getBadge(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().bytes();
//            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
