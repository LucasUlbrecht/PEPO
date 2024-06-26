package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = @ForeignKey(entity = Music.class,
        parentColumns = "id",
        childColumns = "favtdMusics",
        onDelete = ForeignKey.CASCADE))
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String local;
    private TipoAcesso acesso;
    private String datanasc;
    private String cpf;
    private int favtdMusics;
    private String linkPhoto;

}
enum TipoAcesso{
    ADMIN(2),
    PUBLIC(0);

    private int levelAcess;

    TipoAcesso(int levelAcess){
        this. levelAcess=levelAcess;
    }

    public static TipoAcesso parseTipoAcesso(int levelAcess) {
        for (TipoAcesso tipo : TipoAcesso.values()) {
            if (tipo.getLevelAcess() == levelAcess) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nível de acesso não reconhecido: " + levelAcess);
    }


    public int getLevelAcess() {
        return levelAcess;
    }
}
