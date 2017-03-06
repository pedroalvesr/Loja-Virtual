package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formapgto")
public class FormaPgto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fpg_id")
	private int id;
	
	@Column(name = "fpg_descricao", length = 20, nullable = true)
	private String descricao;
	
	@Column(name = "fpg_num_max_parc", length = 20, nullable = true)
	private int numMaxParc;
	
	@Column(name = "fpg_num_padrao_parc", length = 20, nullable = true)
	private int numPadraoParc;
	
	@Column(name = "fpg_intervalo_dias", length = 20, nullable = true)
	private int intervaloDias;
	
	@Column(name = "fpg_percentual_acres", length = 20, nullable = true)
	private int percentualAcres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumMaxParc() {
		return numMaxParc;
	}
	public void setNumMaxParc(int numMaxParc) {
		this.numMaxParc = numMaxParc;
	}
	public int getNumPadraoParc() {
		return numPadraoParc;
	}
	public void setNumPadraoParc(int numPadraoParc) {
		this.numPadraoParc = numPadraoParc;
	}
	public int getIntervaloDias() {
		return intervaloDias;
	}
	public void setIntervaloDias(int intervaloDias) {
		this.intervaloDias = intervaloDias;
	}
	public int getPercentualAcres() {
		return percentualAcres;
	}
	public void setPercentualAcres(int percentualAcres) {
		this.percentualAcres = percentualAcres;
	}
	
	@Override
	public String toString() {
		return "FormaPgto [id=" + id + ", descricao=" + descricao + ", numMaxParc=" + numMaxParc + ", numPadraoParc="
				+ numPadraoParc + ", interValoDias=" + intervaloDias + ", percentualAcres=" + percentualAcres
				+ ", getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getNumMaxParc()=" + getNumMaxParc()
				+ ", getNumPadraoParc()=" + getNumPadraoParc() + ", getInterValoDias()=" + getIntervaloDias()
				+ ", getPercentualAcres()=" + getPercentualAcres() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
