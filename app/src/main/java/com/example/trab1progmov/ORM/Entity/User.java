package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trab1progmov.ORM.Entity.TypeConverter.TipoAcessoConverter;

@Entity
@TypeConverters(TipoAcessoConverter.class)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String local;
    private String email;
    private String senha;
    private String datanasc;
    private String cpf;
    private int favtdMusics;
    private String linkPhoto;
    private TipoAcesso acesso; // Tipo de acesso do usuário

    public User(String nome, String local, String email, String senha, String datanasc, String cpf, int favtdMusics, String linkPhoto, TipoAcesso acesso) {
        this.nome = nome;
        this.local = local;
        this.email = email;
        this.senha = senha;
        this.datanasc = datanasc;
        this.cpf = cpf;
        this.favtdMusics = favtdMusics;
        this.linkPhoto = linkPhoto;
        this.acesso = acesso;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getFavtdMusics() {
        return favtdMusics;
    }

    public void setFavtdMusics(int favtdMusics) {
        this.favtdMusics = favtdMusics;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public TipoAcesso getAcesso() {
        return acesso;
    }

    public void setAcesso(TipoAcesso acesso) {
        this.acesso = acesso;
    }
}

