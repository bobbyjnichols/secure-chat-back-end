package com.ucf.service;

import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public interface TwillioService {
    PhoneNumber getPhoneNumber(String phoneInput);
}
