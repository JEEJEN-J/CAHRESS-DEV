package org.openmrs.charess.api.utils;


public class GeneratedIdentifier extends LuhnModNIdentifierValidator {

    public GeneratedIdentifier() {
    }

    @Override
    public String getBaseCharacters() {
        return "0123456789ACDEFGHJKLMNPRTUVWXY";
    }

    public char randomCharValue() {
        char[] baseCharacters = getBaseCharacters().toCharArray();
        int index = (int) (Math.random() * 30);
        return baseCharacters[index];
    }

    public String identifierToDigits() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            builder.append(randomCharValue());
        }
        return builder.toString();
    }
}
