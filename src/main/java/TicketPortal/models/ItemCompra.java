package TicketPortal.models;
import javax.persistence.*;

@Entity
@Table(name = "item_compra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_compra")
    private Long idItemCompra;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor_item")
    private Double valorItem;

	public Long getIdItemCompra() {
		return idItemCompra;
	}

	public void setIdItemCompra(Long idItemCompra) {
		this.idItemCompra = idItemCompra;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
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
