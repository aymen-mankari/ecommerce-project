package com.ecommerceproject.email;

import javax.mail.internet.MimeMessage;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.Customer;
import com.ecommerceproject.entities.Order;
import com.ecommerceproject.export.ExcelExporter;

@Component
public class EmailSender {

	private static final String domaineEmail = "ecommerce_project@domaine.org";
	@Autowired
	private EmailConfig emailConfig;

	public void sendEmail(Order order, Customer customer) {
		// Create a mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailConfig.getHost());
		mailSender.setPort(this.emailConfig.getPort());
		mailSender.setUsername(this.emailConfig.getUsername());
		mailSender.setPassword(this.emailConfig.getPassword());

		// Create an email instance
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(domaineEmail);
//        mailMessage.setTo(customer.getEmailAddress());
//        mailMessage.setSubject("New feedback from " + "AYMEN");
//        mailMessage.setText("JUST TYPING");

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);
			mimeMessage.setFrom(domaineEmail);
			mimeMessage.setTo(customer.getEmailAddress());
			mimeMessage.setSubject("New feedback from " + "AYMEN");
			mimeMessage.setText("Hello " + customer.getFullName() + System.getProperty("line.separator")
					+ "Please, see the attached file to see you order details.");

			mimeMessage.addAttachment("order_file.xlsx",
					new ByteArrayResource(IOUtils.toByteArray(ExcelExporter.exportToExcel(order))));
			// mimeMessage.addaddAttachment("order_file", new
			// ByteArrayInputStream(ExcelExporter.exportToExcel(order));
			// Send mail
			mailSender.send(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
