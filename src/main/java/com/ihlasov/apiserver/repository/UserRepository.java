package com.ihlasov.apiserver.repository;

import com.ihlasov.apiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * from users where status = :status and last_status_change > :time",
    nativeQuery = true)
    List<User> findUsersByStatusAndLastStatusChange(String status, LocalDateTime time);
}
