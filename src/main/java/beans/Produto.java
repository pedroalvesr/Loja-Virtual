package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue
	@Column(name = "prod_id")
	private int prod_id;
	
	@Column(name = "pro_nome", length = 60, nullable = true)
	private String pro_nome;
	
	
	@Column(name = "pro_preco", nullable = true)
	private float pro_preco;

	@Column
	private String foto;

	@Column
	private String caminho;

	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getPro_nome() {
		return pro_nome;
	}
	public void setPro_nome(String pro_nome) {
		this.pro_nome = pro_nome;
	}
	public float getPro_preco() {
		return pro_preco;
	}
	public void setPro_preco(float pro_preco) {
		this.pro_preco = pro_preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
