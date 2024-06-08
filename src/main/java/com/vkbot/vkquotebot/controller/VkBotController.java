package com.vkbot.vkquotebot.controller;

import com.vkbot.vkquotebot.model.VkCallbackRequest;
import com.vkbot.vkquotebot.service.VkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${vk.bot.confirmation_code}")
    private String confirmationCode;

    /**
     * Handles VK callback requests.
     *
     * @param request the VK callback request
     * @return the confirmation code for VK or "ok" for other types
     */
    @PostMapping("/callback")
    public String handleVkCallback(@RequestBody VkCallbackRequest request) {
        log.info("Received callback request: {}", request);
        if ("confirmation".equals(request.getType())) {
            return confirmationCode;
        } else if ("message_new".equals(request.getType())) {
            vkService.processIncomingMessage(request);
        }
        return "ok";
    }
}
