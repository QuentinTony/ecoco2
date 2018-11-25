package fr.adaming.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vAddPanier")
public class ValidatorAddPanier implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {
		
		int saisie1 = (Integer) valeur;
		String saisie = Integer.toString(saisie1);

		int longueur = saisie.length();
		String nb = "01234565789";
		
		for(int i = 0;i<longueur;i++){
			String test = Character.toString(saisie.charAt(i));
			
				if(!nb.contains(test)){
					throw new ValidatorException(new FacesMessage("La quantité est un nombre !"));
			}else if(Integer.parseInt(saisie)==0){
				throw new ValidatorException(new FacesMessage("La quantité est de 0 !"));
			}
		}
		
	}

}
