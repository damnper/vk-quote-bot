package com.vkbot.vkquotebot.model;

import lombok.Data;

import java.util.List;

@Data
public class VkCallbackRequest {
    private String type;
    private Long group_id;
    private String event_id;
    private String v;
    private VkObject object;

    @Data
    public static class VkObject {
        private VkMessage message;
        private VkClientInfo client_info;
    }

    @Data
    public static class VkMessage {
        private Long date;
        private Long from_id;
        private Long id;
        private Long peer_id;
        private String text;
        private Long random_id;
        private Integer conversation_message_id;
        private boolean important;
        private List<VkAttachment> attachments;
        private List<VkMessage> fwd_messages;
        private boolean is_hidden;

        @Data
        public static class VkAttachment {
            private String type;
        }
    }

    @Data
    public static class VkClientInfo {
        private List<String> button_actions;
        private boolean keyboard;
        private boolean inline_keyboard;
        private boolean carousel;
        private int lang_id;
    }
}
