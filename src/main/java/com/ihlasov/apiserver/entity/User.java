package com.ihlasov.apiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String uri;
    String name;
    String email;
    String status;
    LocalDateTime lastStatusChange;
}
