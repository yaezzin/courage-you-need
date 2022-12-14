package com.app.zero.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageReadCondition {
    private Long memberId;
    private Long lastMessageId = Long.MAX_VALUE;
    private int size;
}
