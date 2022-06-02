package org.openmrs.charess.api.configuration;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Authenticate extends Thread {

    private String username;
    private String password;
    private PasswordAuthentication passwordAuthentication;


    public Authenticate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authenticate(String username, String password, PasswordAuthentication passwordAuthentication) {
        this.username = username;
        this.password = password;
        this.passwordAuthentication = passwordAuthentication;
    }

    @Override
    public void run() {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                passwordAuthentication = new PasswordAuthentication(username, password.toCharArray());
                return passwordAuthentication;
            }
        });
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return this.passwordAuthentication;
    }

}
