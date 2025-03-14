package com.example.userservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<UserEntity> findByUid(String uid);

}
