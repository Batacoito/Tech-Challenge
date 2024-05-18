package br.com.fiap.postech.TechChallenge.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "carrinho")

public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID produtoId;
    private UUID usrId;
    private int qnt;

    public Carrinho() {}

    public Carrinho(UUID id, UUID produtoId, UUID usrId, int qnt) {
        this.produtoId = produtoId;
        this.usrId = usrId;
        this.qnt = qnt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public UUID getUsrId() {
        return usrId;
    }

    public void setUsrId(UUID usrId) {
        this.usrId = usrId;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return qnt == carrinho.qnt && Objects.equals(id, carrinho.id) && Objects.equals(produtoId, carrinho.produtoId) && Objects.equals(usrId, carrinho.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtoId, usrId, qnt);
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", usrId=" + usrId +
                ", qnt=" + qnt +
                '}';
    }
}
