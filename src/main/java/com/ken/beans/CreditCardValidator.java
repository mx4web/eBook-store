package com.ken.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * This is a custom validator that must implement the Validator interface. The
 * annotation provides the name that is used in the tag This implementation
 * performs the Luhn test
 *
 */
@FacesValidator("com.kenfogel.CreditCardValidator")
public class CreditCardValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) {
        if (value == null) {
            return;
        }
        String cardNumber;
        if (value instanceof CreditCard) {
            cardNumber = value.toString();
        } else {
            cardNumber = value.toString().replaceAll("\\D", ""); // remove
        }

        // non-digits
        if (!luhnCheck(cardNumber)) {
            FacesMessage message = com.ken.util.Messages.getMessage(
                    "com.cejv539.ebook.bundles.messages", "badLuhnCheck", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        // empty
        if (cardNumber.length() == 0) {
            FacesMessage message = com.ken.util.Messages.getMessage(
                    "com.cejv539.ebook.bundles.messages", "checkoutCardRequired", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    private static boolean luhnCheck(String cardNumber) {
        int sum = 0;

        for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
            sum += Integer.parseInt(cardNumber.substring(i, i + 1));
            if (i > 0) {
                int d = 2 * Integer.parseInt(cardNumber.substring(i - 1, i));
                if (d > 9) {
                    d -= 9;
                }
                sum += d;
            }
        }

        return sum % 10 == 0;
    }
}
