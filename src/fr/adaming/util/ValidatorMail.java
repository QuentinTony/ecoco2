package fr.adaming.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vMail")
public class ValidatorMail implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {

		String saisie = (String) valeur;

		if (!saisie.contains("@")) {
			throw new ValidatorException(new FacesMessage("L'adresse mail doit contenir un @"));
		} 

	}

}
