package com.example.wtcell;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String key, nomeCliente, email, senha;

    public Cliente() {
    }

    public Cliente(String key, String nomeCliente, String email, String senha) {
        this.key = key;
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.senha = senha;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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
}
