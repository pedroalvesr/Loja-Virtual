package negocio;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import persistencia.ProdutoDAO;
import beans.Produto;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto;
	private String filtro = "";

	// ///////// METODOS ///////////

	public String actionGravar() {
		if (produto.getProd_id() == 0) {

			try {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				FacesContext context = FacesContext.getCurrentInstance();
				Path origem = Paths.get(produto.getCaminho());
				Path destino = Paths
						.get("C:/Users/PedroHenrirque/workspace/exercicios/LojaVirtual/src/main/webapp/resources/imagens/"
								+ produto.getFoto());
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				produtoDAO.merge(produto);
				context.addMessage(null, new FacesMessage("Upload realizado com sucesso"));
				produto = new Produto();
				return actionInserir();

			} catch (RuntimeException | IOException erro) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Ocorreu um erro ao tentar salvar o produto"));
				erro.printStackTrace();
			}
		} else {
			try {
				
				Path origem = Paths.get(produto.getCaminho());
				Path destino = Paths
						.get("//resources/imagens/"
								+ produto.getFoto());
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				ProdutoDAO.alterar(produto);
				produto = new Produto();
				return actionInserir();

			} catch (RuntimeException | IOException erro) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Ocorreu um erro ao tentar salvar o produto"));
				erro.printStackTrace();
			}
			
			return "lista_produto?faces-redirect=true";
		}
		return null;
	}

	public String actionInserir() {
		produto = new Produto();
		return "/admin/form_produto?faces-redirect=true";
	}

	public String actionExcluir() {
		ProdutoDAO.excluir(produto);
		;
		return "lista_produto?faces-redirect=true";
	}

	public String actionAlterar(Produto p) {
		produto = p;
		return "form_produto?faces-redirect=true";
	}

	public List<Produto> getlistagem() {
		return ProdutoDAO.listagem("");
	}

	public void upload(FileUploadEvent evento) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			UploadedFile arquivoUpload = evento.getFile();
			String nome = arquivoUpload.getFileName();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp,
					StandardCopyOption.REPLACE_EXISTING);

			produto.setCaminho(arquivoTemp.toString());
			produto.setFoto(nome);

			context.addMessage(null, new FacesMessage("Upload realizado com sucesso"));
		} catch (IOException erro) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro ao tentar realizar o upload de arquivo"));
			erro.printStackTrace();
		}
	}

	// /////////// GET SET //////////////

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
