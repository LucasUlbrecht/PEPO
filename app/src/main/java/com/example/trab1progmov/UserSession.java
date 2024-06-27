package com.example.trab1progmov;

import com.example.trab1progmov.ORM.Entity.TipoAcesso;

public class UserSession {

    private static UserSession instance;
    private boolean loggedIn = false;
    private String userEmail;
    private TipoAcesso tipoAcesso;

    private UserSession() {
        // Construtor privado para garantir singleton
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        tipoAcesso = TipoAcesso.PUBLIC;
    }
    public boolean setLoggedIn(boolean loggedIn, String senha) {
        if(senha.compareTo("senha")==0){
            loggedIn=loggedIn;
            tipoAcesso = TipoAcesso.ADMIN;
            return true;
        }
        return false;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void logout() {
        loggedIn = false;
        userEmail = null;
        // Adicione qualquer outra limpeza de dados da sessão, se necessário
    }
}
