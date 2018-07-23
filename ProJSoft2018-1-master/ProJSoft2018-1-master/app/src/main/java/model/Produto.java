package model;

import java.io.Serializable;

public class Produto implements Serializable{

    private int id;
    private String descricao;
    private Float valor;
    private Float estoque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getEstoque() {
        return estoque;
    }

    public void setEstoque(Float estoque) {
        this.estoque = estoque;
    }
}

