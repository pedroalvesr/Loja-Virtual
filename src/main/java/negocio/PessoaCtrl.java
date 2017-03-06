package negocio;

import java.io.Serializable;
import java.util.List;






import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FlowEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import persistencia.PessoaDAO;
import beans.Fone;
import beans.Pessoa;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Fone fone;
	private boolean skip;
	
	///// MÉTODOS /////
	
	public String actionGravar() {
		if (pessoa.getPes_id() == 0) {
			PessoaDAO.inserir(pessoa);
			return actionInserir();
		} else {
			PessoaDAO.alterar(pessoa);
			return "/admin/lista_pessoa?faces-redirect=true";
		}

	}

	public String actionInserir() {
		pessoa = new Pessoa();
		return "/publico/login?faces-redirect=true";
	}
	

	public String actionExcluir() {
		PessoaDAO.excluir(pessoa);
		return "lista_pessoa?faces-redirect=true";
	}

	public String actionAlterar(Pessoa p) {
		pessoa = p;
		return "/publico/form_pessoa?faces-redirect=true";
	}

	public List<Pessoa> getlistagem() {
		return PessoaDAO.lsPessoa("");
	}
	
	public String actionInserirFone(){
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);		
		return "form_pessoa?faces-redirect=true";
	}
	
	public String actionExcluirFone(Fone fone) {
		fone.setPessoa(pessoa);
		pessoa.getFones().remove(fone);
		PessoaDAO.excluirFone(fone);
		return "form_pessoa?faces-redirect=true";
	}
	
	public String actionCliente() {
		pessoa = new Pessoa();
		return "/pessoa/principal?faces-redirect=true";
	}
	
	//////GET & SET//////
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Fone getFone() {
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}
	
	public String getUsuarioLogado() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	public Pessoa getUsuario() {
		pessoa = PessoaDAO.pesqUsuario(getUsuarioLogado());
		return pessoa;
	}

	public String onFlowProcess(FlowEvent event) {
		pessoa = PessoaDAO.pesqUsuario(getUsuarioLogado());

		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

}
