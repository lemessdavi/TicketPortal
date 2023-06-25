package TicketPortal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
@Table(name = "ingresso")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingresso")
    private Long idIngresso;

    @ManyToOne
    @JoinColumn(name = "id_tipo_promocao")
    private TipoIngresso tipoPromocao;

    @ManyToOne
    @JoinColumn(name = "id_item_compra")
    private ItemCompra itemCompra;

	public Long getIdIngresso() {
		return idIngresso;
	}

	public void setIdIngresso(Long idIngresso) {
		this.idIngresso = idIngresso;
	}

	public TipoIngresso getTipoPromocao() {
		return tipoPromocao;
	}

	public void setTipoPromocao(TipoIngresso tipoPromocao) {
		this.tipoPromocao = tipoPromocao;
	}

	public ItemCompra getItemCompra() {
		return itemCompra;
	}

	public void setItemCompra(ItemCompra itemCompra) {
		this.itemCompra = itemCompra;
	}


    
}

