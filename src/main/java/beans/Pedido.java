package beans;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue
	@Column(name = "ped_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "pes_id")
	private Pessoa pessoa;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "fpg_id")
	private FormaPgto pgto;

	@Column
	private Date ped_dataEmissao;

	@Column
	private String ped_status;

	@Column
	private Date ped_dataAutorizacao;

	@Column
	private float ped_total;

	@Column
	private float ped_desconto;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Itens_pedido> itens = new ArrayList<Itens_pedido>();

	/////////////// GET SET /////////////

	public List<Itens_pedido> getItens() {
		return itens;
	}

	public void setItens(List<Itens_pedido> itens) {
		this.itens = itens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public FormaPgto getPgto() {
		return pgto;
	}

	public void setPgto(FormaPgto pgto) {
		this.pgto = pgto;
	}

	public Date getPed_dataEmissao() {
		return ped_dataEmissao;
	}

	public void setPed_dataEmissao(Date ped_dataEmissao) {
		this.ped_dataEmissao = ped_dataEmissao;
	}

	public String getPed_status() {
		return ped_status;
	}

	public void setPed_status(String ped_status) {
		this.ped_status = ped_status;
	}

	public Date getPed_dataAutorizacao() {
		return ped_dataAutorizacao;
	}

	public void setPed_dataAutorizacao(Date ped_dataAutorizacao) {
		this.ped_dataAutorizacao = ped_dataAutorizacao;
	}

	public float getPed_total() {
		return ped_total;
	}

	public void setPed_total(float ped_total) {
		this.ped_total = ped_total;
	}

	public float getPed_desconto() {
		return ped_desconto;
	}

	public void setPed_desconto(float ped_desconto) {
		this.ped_desconto = ped_desconto;
	}

}
