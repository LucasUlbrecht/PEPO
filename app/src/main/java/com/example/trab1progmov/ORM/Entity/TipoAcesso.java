package com.example.trab1progmov.ORM.Entity;

public enum TipoAcesso {
    ADMIN(2),
    PUBLIC(0);

    private final int levelAcess;

    TipoAcesso(int levelAcess) {
        this.levelAcess = levelAcess;
    }

    public int getLevelAcess() {
        return levelAcess;
    }

    public static TipoAcesso fromLevel(int levelAcess) {
        for (TipoAcesso tipo : TipoAcesso.values()) {
            if (tipo.getLevelAcess() == levelAcess) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nível de acesso não reconhecido: " + levelAcess);
    }
}
