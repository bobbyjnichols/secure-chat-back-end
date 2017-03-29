package com.ucf.serviceBean;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.ucf.service.TwillioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwillioBean implements TwillioService {

    static final Logger logger = Logger.getLogger(TwillioBean.class);

    @Value("${twillio.sid}")
    private String twillioSid;

    @Value("${twillio.secret}")
    private String twillioSecret;

    @Override
    public PhoneNumber getPhoneNumber(String phoneInput) {
        Twilio.init(twillioSid, twillioSecret);

        com.twilio.rest.lookups.v1.PhoneNumber number = com.twilio.rest.lookups.v1.PhoneNumber
                .fetcher(new com.twilio.type.PhoneNumber(phoneInput))
                .fetch();

        return number.getPhoneNumber();
    }
}
