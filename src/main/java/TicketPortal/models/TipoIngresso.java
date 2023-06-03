package TicketPortal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoIngresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    private Double valorNormal;
    private Double desconto;
    private Integer lote;

    // Getters and Setters

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public Double getValorNormal() {
        return valorNormal;
    }

    public void setValorNormal(Double valorNormal) {
        this.valorNormal = valorNormal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }
}
