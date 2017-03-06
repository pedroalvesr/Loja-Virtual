package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;




import org.postgresql.jdbc2.ArrayAssistantRegistry;
import org.primefaces.event.FlowEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import beans.FormaPgto;
import beans.Itens_pedido;
import beans.Pedido;
import beans.Pessoa;
import beans.Produto;
import persistencia.FormaPgtoDAO;
import persistencia.Itens_pedidoDAO;
import persistencia.PedidoDAO;
import persistencia.PessoaDAO;
import persistencia.ProdutoDAO;

@ManagedBean
@SessionScoped
public class CarrinhoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Produto> escolhas;
	private Produto produto;
	private Pessoa pessoa;
	private FormaPgto pgto = new FormaPgto();
	private Pedido pedido = new Pedido();
	private Itens_pedido itens_pedido = new Itens_pedido();
	private List<FormaPgto> pgtos = new ArrayList<FormaPgto>();
	private String filtro = "";
	private int qtd = 1;
	private float valor;
	private boolean skip;
	private float qtdTotal = 0;
	private float subTotal = 0;
	private float somaTotal = 0;

	/////////// METODOS ///////////

	public String actionNovoPedido() {
		
		pgtos = new ArrayList<>();
		return "/pessoa/carrinho?faces-redirect=true";
	}
	

	public String Compra() {
		Date date = new Date();
		FacesContext context = FacesContext.getCurrentInstance();
		pessoa = PessoaDAO.pesqUsuario(getUsuarioLogado());

		pedido.setPgto(pgto);
		pedido.setPessoa(pessoa);
		pedido.setPed_dataEmissao(date);
		pedido.setPed_dataAutorizacao(date);
		pedido.setPed_status("Aberto");
		pedido.setPed_total(subTotal);
		PedidoDAO.inserir(pedido);
		gravarItens();
		context.addMessage(null, new FacesMessage("Compra finalizada"));
		escolhas = new ArrayList<>();
		pgto = new FormaPgto();
		pedido = new Pedido();
		
		this.subTotal = 0;
		return "/publico/index";

	}

	public void gravarItens() {
		for (Produto pro : escolhas) {
			Itens_pedido itens = new Itens_pedido();
			float valor1;
			valor1 = pro.getPro_preco() * qtd;

			itens.setProduto(pro);
			itens.setIpe_qtde(itens.getIpe_qtde());
			itens.setPedido(pedido);
			itens.setIpe_valorUnit(pro.getPro_preco());
			itens.setIpe_subTotal(valor1);
			Itens_pedidoDAO.inserir(itens);
		}
	}

	//// ADICIONAR PRODUTO AO CARRINHO /////
	public void comprar(Produto p) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (escolhas == null) {
			escolhas = new ArrayList<Produto>();
			
		}
		
		somaTotal = somaTotal + p.getPro_preco();
		this.qtdTotal = escolhas.size();
		this.subTotal = somaTotal;
		escolhas.add(p);
		
		context.addMessage(null, new FacesMessage("Produto adicionado ao seu carrinho"));
	}

	/////// LISTA DE PRODUTOS //////
	public List<Produto> getListagem() {
		return ProdutoDAO.listagem("");
	}

	////// LISTA DE FORMAS DE PAGAMENTO //////
	public List<FormaPgto> getPgtos() {
		pgtos = FormaPgtoDAO.lsPgto(filtro);
		return pgtos;
	}


	public String getUsuarioLogado() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	public String voltar() {
		getListagem();
		return "/publico/index?faces-redirect=true";
	}
	///////////// GET SET //////////////

	public List<Produto> getEscolhas() {
		return escolhas;
	}

	public void setEscolhas(List<Produto> escolhas) {
		this.escolhas = escolhas;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setPgtos(List<FormaPgto> pgtos) {
		this.pgtos = pgtos;
	}

	public Itens_pedido getItens_pedido() {
		return itens_pedido;
	}

	public void setItens_pedido(Itens_pedido itens_pedido) {
		this.itens_pedido = itens_pedido;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		valor = produto.getPro_preco() * qtd;
		this.valor = valor;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
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

	public float getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(float qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public float getSomaTotal() {
		return somaTotal;
	}

	public void setSomaTotal(float somaTotal) {
		this.somaTotal = somaTotal;
	}


	public String getFiltro() {
		return filtro;
	}


	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
