package fes.aragon.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class MailService {
	
	public String sendTextEmail(String email) throws IOException {
		    Email from = new Email("EMAIL");
		    String subject = "Bienvenido!";
		    Email to = new Email(email);
		    Content content = new Content("text/plain", "¡Gracias por registrarte, saludos!");
		    Mail mail = new Mail(from, subject, to, content);
		    SendGrid sg = new SendGrid("KEY");
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      return response.getBody();	     
		    } catch (IOException ex) {
		      throw ex;
		    }	   
	}
}




