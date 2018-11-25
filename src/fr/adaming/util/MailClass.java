package fr.adaming.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

public class MailClass {

	public static void sendMailToCl(Commande co, Client cl, Panier pa) {

		final String username = "supertestjava@gmail.com";
		final String password = "e123289k!";

		// Recipient's email ID needs to be mentioned.
		String to = "supertestjava@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "web@gmail.com";

		// Assuming you are sending email from smtp?
		String host = "smtp.gmail.com";

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the default Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("JVCommerce : commande numéro " + co.getId());

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Now set the actual message
			String recap = "";
			for (LigneCommande lc : pa.getListeLigneCommandes()) {
				recap = recap + "\n - " + lc.getQuantite() + " " + lc.getProduit().getDesignation() + " à "
						+ lc.getProduit().getPrix() + "€ pièce";
			}

			messageBodyPart.setText("Mr/Mme " + cl.getNom()
					+ ", \nBonjour,\nNous vous confirmons l'enregistrement de votre commande numéro " + co.getId()
					+ ".\nRécapitulatif de la commande :" + recap + "\n\nMontant total de la commande :"
					+ pa.getPrixTotal() + "€"
					+ "\n\nElle vous sera envoyé à l'adresse que vous nous avez indiquée : "+cl.getAdresse()+"\n\n"
					+ "\n\nVous trouverez le détail de votre facture en pièce jointe au format pdf.\n\n"
					+ "En espérant vous revoir bientôt sur notre site, cordialement\n\nToute l'équipe de JVCommerce");

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(
					"C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest.pdf");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Récapitulatif commande n°" + co.getId() + ".pdf");
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void sendMailToClVente(Commande co, Client cl, LigneCommande lcIn) {

		final String username = "supertestjava@gmail.com";
		final String password = "e123289k!";

		// Recipient's email ID needs to be mentioned.
		String to = "supertestjava@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "web@gmail.com";

		// Assuming you are sending email from smtp?
		String host = "smtp.gmail.com";

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the default Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("JVCommerce : vente de " + lcIn.getQuantite() + " de vos articles : "
					+ lcIn.getProduit().getDesignation());

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Now set the actual message

			messageBodyPart.setText("Mr/Mme " + cl.getNom() + ", \nBonjour,\nNous vous signalons la vente de "
					+ lcIn.getQuantite() + " de votre article : " + lcIn.getProduit().getDesignation() + " le "
					+ co.getDateCommande() + "\n\nMontant total de la vente :" + lcIn.getPrix() + "€"
					+ "\n\nVous trouverez le détail de votre vente en pièce jointe au format pdf.\n\n"
					+ "En espérant vous revoir bientôt sur notre site, cordialement\n\nToute l'équipe de JVCommerce");

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(
					"C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest1.pdf");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Détails vente.pdf");
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
