package com.app.zero.dto.message;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
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
