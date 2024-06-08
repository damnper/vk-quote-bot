package com.vkbot.vkquotebot.controller;

import com.vkbot.vkquotebot.model.VkCallbackRequest;
import com.vkbot.vkquotebot.service.VkService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for processing requests from VK Callback API.
 */
@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
@Slf4j
public class VkBotController {

    private final VkService vkService;
    private final Dotenv dotenv;

    /**
     * Handles VK callback requests.
     *
     * @param request the VK callback request
     * @return the confirmation code for VK or "ok" for other types
     */
    @PostMapping("/callback")
    public String handleVkCallback(@RequestBody VkCallbackRequest request) {
        log.info("Received callback request: {}", request);
        String confirmationCode = dotenv.get("VK_CONFIRMATION_CODE");
        if ("confirmation".equals(request.getType())) {
            return confirmationCode;
        } else if ("message_new".equals(request.getType())) {
            vkService.processIncomingMessage(request);
        }
        return "ok";
    }
}
