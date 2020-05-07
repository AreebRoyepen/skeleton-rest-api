package com.example.skeleton.services;

import java.io.ByteArrayInputStream;

import javax.mail.MessagingException;

public interface EmailService {

	void sendEmailWithAttachment(String reportName, String to, String body, String subject, ByteArrayInputStream report) throws MessagingException;
	
	void sendSimpleEmail(String to, String body, String subject) throws MessagingException;
	
}
