package TicketPortal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "tipo_ingresso")
public class TipoIngresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_ingresso")
    private Long idTipoIngresso;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "valor_normal")
    private Double valorNormal;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "lote_numero")
    private Integer loteNumero;

	public Long getIdTipoIngresso() {
		return idTipoIngresso;
	}

	public void setIdTipoIngresso(Long idTipoIngresso) {
		this.idTipoIngresso = idTipoIngresso;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getValorNormal() {
		return valorNormal;
	}

	public void setValorNormal(Double valorNormal) {
		this.valorNormal = valorNormal;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getLoteNumero() {
		return loteNumero;
	}

	public void setLoteNumero(Integer loteNumero) {
		this.loteNumero = loteNumero;
	}

}
