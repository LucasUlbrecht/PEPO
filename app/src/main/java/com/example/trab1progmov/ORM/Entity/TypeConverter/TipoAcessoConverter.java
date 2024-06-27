package com.example.trab1progmov.ORM.Entity.TypeConverter;

import androidx.room.TypeConverter;

import com.example.trab1progmov.ORM.Entity.TipoAcesso;

public class TipoAcessoConverter {

    @TypeConverter
    public static String fromTipoAcesso(TipoAcesso acesso) {
        return acesso.name(); // Salva o nome da enumeração
    }

    @TypeConverter
    public static TipoAcesso toTipoAcesso(String nome) {
        return TipoAcesso.valueOf(nome); // Converte o nome de volta para a enumeração
    }
}
