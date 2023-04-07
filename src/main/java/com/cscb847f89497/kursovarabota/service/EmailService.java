package com.cscb847f89497.kursovarabota.service;

import com.cscb847f89497.kursovarabota.dto.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
