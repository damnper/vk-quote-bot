package com.vkbot.vkquotebot.service;

import com.vkbot.vkquotebot.model.VkCallbackRequest;
import com.vkbot.vkquotebot.utils.HttpClient;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class VkService {

    private final HttpClient httpClient;
    private final Dotenv dotenv;
    private final Random random = new Random();

    /**
     * Processes incoming VK messages.
     *
     * @param request the VK callback request containing the message
     */
    public void processIncomingMessage(VkCallbackRequest request) {
        Long userId = request.getObject().getMessage().getFrom_id();
        String text = request.getObject().getMessage().getText();
        sendMessage(userId, text);
    }

    /**
     * Sends a message to a VK user.
     *
     * @param userId the VK user ID
     * @param text   the message text
     */
    public void sendMessage(Long userId, String text) {
        try {
            text = "Вы сказали: " + text;
            int randomId = random.nextInt(Integer.MAX_VALUE);
            String url = String.format(
                    "%s/messages.send?user_id=%d&message=%s&random_id=%d&access_token=%s&v=%s",
                    dotenv.get("VK_API_URL"), userId, text, randomId, dotenv.get("VK_BOT_TOKEN"), dotenv.get("VK_API_VERSION"));
            String body = String.format("message=%s", URLEncoder.encode(text, UTF_8));

            log.info("Sending message to VK user {}: {}", userId, text);
            httpClient.sendPostRequest(url, body);
        } catch (Exception e) {
            log.error("Error sending message to VK", e);
        }
    }
}
