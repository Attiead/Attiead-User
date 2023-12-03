package com.example.userservice.application.port.out;

import com.example.userservice.domain.User;

public interface LoadUserPort {

  User getUser(String uid);

}
