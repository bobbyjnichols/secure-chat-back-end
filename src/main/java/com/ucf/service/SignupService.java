package com.ucf.service;

import org.springframework.stereotype.Service;

@Service
public interface SignupService {
    void signup(String phone, String password);
}
