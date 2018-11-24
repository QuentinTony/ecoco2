package fr.adaming.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

public class PdfClass {

	public static final String chemin = "C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest.pdf";
	public static final String chemin1 = "C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest1.pdf";

	public static void createPdf(Commande co, Client cl, Panier pa) throws IOException, DocumentException {
		Document document = new Document();
		// On créer un objet table dans lequel on intialise ça taille.
		PdfPTable table = new PdfPTable(4);
		PdfPTable tableCommande = new PdfPTable(4);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin));
			document.open();
			document.add(new Paragraph("Mme/Mr " + cl.getNom() + "\n\nRécapitulatif de votre commande, numéro "
					+ co.getId() + " effectué le " + co.getDateCommande()));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			// On créer l'objet cellule.
			PdfPCell cell;

			cell = new PdfPCell(new Phrase("Vos coordonnées"));
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Nom"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Adresse"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Mail"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Téléphone"));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(cl.getNom()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getAdresse()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getMail()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getTel()));
			table.addCell(cell);

			document.add(table);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			document.add(Chunk.NEWLINE);
			LineSeparator ls = new LineSeparator();
			document.add(new Chunk(ls));

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			cell = new PdfPCell(new Phrase("Détails de la commande"));
			cell.setColspan(4);
			tableCommande.addCell(cell);

			cell = new PdfPCell(new Phrase("Produit"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Quantité"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Prix unitaire"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Prix total"));
			tableCommande.addCell(cell);

			for (LigneCommande lc : pa.getListeLigneCommandes()) {
				cell = new PdfPCell(new Phrase(lc.getProduit().getDesignation()));
				tableCommande.addCell(cell);
				String qtite = Integer.toString(lc.getQuantite());
				cell = new PdfPCell(new Phrase(qtite));
				tableCommande.addCell(cell);
				String prixU = Double.toString(lc.getProduit().getPrix());
				cell = new PdfPCell(new Phrase(prixU + "€"));
				tableCommande.addCell(cell);
				String prixT = Double.toString(lc.getPrix());
				cell = new PdfPCell(new Phrase(prixT + "€"));
				tableCommande.addCell(cell);
			}

			cell = new PdfPCell(new Phrase("Montant totale de la commande : " + pa.getPrixTotal() + "€"));
			cell.setColspan(4);
			tableCommande.addCell(cell);

			document.add(tableCommande);

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		document.close();

	}

	public static void createPdf(Commande co, Client cl, LigneCommande lc) throws IOException, DocumentException {
		Document document = new Document();
		// On créer un objet table dans lequel on intialise ça taille.
		PdfPTable table = new PdfPTable(4);
		PdfPTable tableCommande = new PdfPTable(4);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin1));
			document.open();
			document.add(new Paragraph(
					"Mme/Mr " + cl.getNom() + "\n\nRécapitulatif de votre vente réalisée le" + co.getDateCommande()));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			// On créer l'objet cellule.
			PdfPCell cell;

			cell = new PdfPCell(new Phrase("Vos coordonnées"));
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Nom"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Adresse"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Mail"));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Téléphone"));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(cl.getNom()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getAdresse()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getMail()));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(cl.getTel()));
			table.addCell(cell);

			document.add(table);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			document.add(Chunk.NEWLINE);
			LineSeparator ls = new LineSeparator();
			document.add(new Chunk(ls));

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			cell = new PdfPCell(new Phrase("Détails de la vente"));
			cell.setColspan(4);
			tableCommande.addCell(cell);

			cell = new PdfPCell(new Phrase("Produit"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Quantité"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Prix unitaire"));
			tableCommande.addCell(cell);
			cell = new PdfPCell(new Phrase("Montant total"));
			tableCommande.addCell(cell);

			cell = new PdfPCell(new Phrase(lc.getProduit().getDesignation()));
			tableCommande.addCell(cell);
			String qtite = Integer.toString(lc.getQuantite());
			cell = new PdfPCell(new Phrase(qtite));
			tableCommande.addCell(cell);
			String prixU = Double.toString(lc.getProduit().getPrix());
			cell = new PdfPCell(new Phrase(prixU + "€"));
			tableCommande.addCell(cell);
			String prixT = Double.toString(lc.getPrix());
			cell = new PdfPCell(new Phrase(prixT + "€"));
			tableCommande.addCell(cell);

			document.add(tableCommande);

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		document.close();

	}

}
