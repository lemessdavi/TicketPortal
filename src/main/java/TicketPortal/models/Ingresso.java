package TicketPortal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngresso;

    @ManyToOne
    private Evento evento;

    @ManyToOne
    private TipoIngresso tipoIngresso;

    // Getters and Setters

    public Long getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Long idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public TipoIngresso getTipoPromocao() {
        return tipoIngresso;
    }

    public void setTipoPromocao(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }
}
