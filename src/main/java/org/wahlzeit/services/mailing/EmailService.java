/*
 * SPDX-FileCopyrightText: 2006-2009 Dirk Riehle <dirk@riehle.org> https://dirkriehle.com
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.wahlzeit.services.mailing;

import org.wahlzeit.services.EmailAddress;

import org.wahlzeit.utils.PatternInstance;

/**
 * An EmailServer can send emails. Various implementations exist.
 *
 */
@PatternInstance(
	type = "Structural",
	patternName = "Decorator",
	participants = {"Abstract Decorated Class"}
)
public interface EmailService {

	@PatternInstance(
		type = "Behavioral",
		patternName = "Command",
		participants = {"Abstract Command Class"}
	)
	/**
	 * 
	 */
	public void sendEmail(EmailAddress from, EmailAddress to, String subject, String body) throws MailingException;
	public boolean sendEmailIgnoreException(EmailAddress from, EmailAddress to, String subject, String body);

	/**
	 * 
	 */
	public void sendEmail(EmailAddress from, EmailAddress to, EmailAddress bcc, String subject, String body) throws MailingException;
	public boolean sendEmailIgnoreException(EmailAddress from, EmailAddress to, EmailAddress bcc, String subject, String body);

}
