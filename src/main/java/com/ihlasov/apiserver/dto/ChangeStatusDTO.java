package com.ihlasov.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangeStatusDTO {
    private Long id;
    private String newStatus;
    private String oldStatus;
}
