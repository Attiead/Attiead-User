package com.example.userservice.application.port.out;

import com.example.userservice.domain.User;

public interface UpdateUserPort {

  User update(User user);
}
