package com.ucf.service;

import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public interface TwillioService {
    PhoneNumber getPhoneNumber(String phoneInput);
}
