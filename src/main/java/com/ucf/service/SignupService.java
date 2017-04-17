package com.ucf.service;

import org.springframework.stereotype.Service;

/*
* Service classes provide interfaces for implementation beans.
* */

@Service
public interface SignupService {
    void signup(String phone, String password);
}
