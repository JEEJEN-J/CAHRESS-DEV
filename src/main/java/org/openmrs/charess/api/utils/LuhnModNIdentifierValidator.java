package org.openmrs.charess.api.utils;

import org.openmrs.patient.IdentifierValidator;
import org.openmrs.patient.UnallowedIdentifierException;


public abstract class LuhnModNIdentifierValidator implements IdentifierValidator {

    public abstract String getBaseCharacters();

    public String standardizeValidIdentifier(String validIdentifier) {
        if (validIdentifier != null) {
            validIdentifier = validIdentifier.toUpperCase().trim();
        }
        return validIdentifier;
    }

    public String getAllowedCharacters() {
        StringBuilder sb = new StringBuilder();
        for (char c : getBaseCharacters().toCharArray()) {
            sb.append(Character.toLowerCase(c));
            sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
    }

    public String getName() {
        return "Luhn Mod-" + getBaseCharacters().length() + " Check-Digit Validator";
    }

    public String getValidIdentifier(String undecoratedIdentifier) throws UnallowedIdentifierException {
        String standardized = standardizeValidIdentifier(undecoratedIdentifier);
        return standardized + computeCheckDigit(standardized);
    }

    public boolean isValid(String identifier) throws UnallowedIdentifierException {
        try {
            identifier = standardizeValidIdentifier(identifier);
            String undecoratedIdentifier = identifier.substring(0, identifier.length() - 1);
            return identifier.equals(getValidIdentifier(undecoratedIdentifier));
        } catch (Exception e) {
            throw new UnallowedIdentifierException("Invalid identifier specified for validator", e);
        }
    }

    public char computeCheckDigit(String undecoratedIdentifier) {
        int factor = 2;
        int sum = 0;
        char[] inputChars = standardizeValidIdentifier(undecoratedIdentifier).toCharArray();
        char[] baseChars = getBaseCharacters().toCharArray();
        int mod = baseChars.length;

        for (int i = inputChars.length - 1; i >= 0; i--) {
            int codePoint = -1;
            for (int j = 0; j < baseChars.length; j++) {
                if (baseChars[j] == inputChars[i]) {
                    codePoint = j;
                }
            }
            if (codePoint == -1) {
                throw new UnallowedIdentifierException("Invalid character specified for validator");
            }
            int addend = factor * codePoint;

            factor = (factor == 2) ? 1 : 2;

            addend = (addend / mod) + (addend % mod);
            sum += addend;
        }

        int remainder = sum % mod;
        int checkCodePoint = mod - remainder;
        checkCodePoint %= mod;

        return baseChars[checkCodePoint];
    }

}
