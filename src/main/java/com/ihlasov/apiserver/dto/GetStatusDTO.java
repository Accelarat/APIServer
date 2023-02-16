package com.ihlasov.apiserver.dto;

import com.ihlasov.apiserver.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetStatusDTO {
    private LocalDateTime requestId;
    private List<User> statuses;
}
