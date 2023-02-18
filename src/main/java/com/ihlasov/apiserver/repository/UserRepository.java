package com.ihlasov.apiserver.repository;

import com.ihlasov.apiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatusAndLastStatusChangeAfter(Boolean status, LocalDateTime time);
}
