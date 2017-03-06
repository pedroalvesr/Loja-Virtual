package beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Itens_pedido {

	@Id
	@GeneratedValue
	@Column(name = "ipe_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "ped_id")
	private Pedido pedido;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "pro_id")
	private Produto produto;

	@Column
	private float ipe_qtde;

	@Column
	private float ipe_valorUnit;

	@Column
	private float ipe_subTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getIpe_qtde() {
		return ipe_qtde;
	}

	public void setIpe_qtde(float ipe_qtde) {
		this.ipe_qtde = ipe_qtde;
	}

	public float getIpe_valorUnit() {
		return ipe_valorUnit;
	}

	public void setIpe_valorUnit(float ipe_valorUnit) {
		this.ipe_valorUnit = ipe_valorUnit;
	}

	public float getIpe_subTotal() {
		return ipe_subTotal;
	}

	public void setIpe_subTotal(float ipe_subTotal) {
		this.ipe_subTotal = ipe_subTotal;
	}

}
