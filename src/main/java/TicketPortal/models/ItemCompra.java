package TicketPortal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Ingresso ingresso;

    private Integer quantidade;
    private Double valorItem;

    // Getters and Setters

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }
}
