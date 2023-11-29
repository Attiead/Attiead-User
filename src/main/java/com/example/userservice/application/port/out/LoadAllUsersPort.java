package com.example.userservice.application.port.out;

import com.example.userservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadAllUsersPort {

  Page<User> getAllUsers(Pageable pageable);

}
