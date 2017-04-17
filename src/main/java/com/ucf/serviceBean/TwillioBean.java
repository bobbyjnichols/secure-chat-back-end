package com.ucf.serviceBean;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;
import com.ucf.service.TwillioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The Twillio component provides services from Twillio; mainly to standardize user imput
* of phone numbers.
* */

@Component
public class TwillioBean implements TwillioService {

    static final Logger logger = Logger.getLogger(TwillioBean.class);

    @Value("${twillio.sid}")
    private String twillioSid;

    @Value("${twillio.secret}")
    private String twillioSecret;

    @Override
    public PhoneNumber getPhoneNumber(String phoneInput) throws com.twilio.exception.ApiException {
        Twilio.init(twillioSid, twillioSecret);

        try {
            PhoneNumber number = PhoneNumber
                    .fetcher(new com.twilio.type.PhoneNumber(phoneInput))
                    .fetch();
            return number;
        } catch (com.twilio.exception.ApiException e) {
            throw e;
        }
    }
}
