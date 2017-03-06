package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void inserir(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(produto);
		t.commit();
	}
	
	public static void alterar(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Produto> listagem(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta; 
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Produto");
		}else{
			consulta = sessao.createQuery("from produto where prod_id like: parametro order by pro_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Produto pesqId(int valor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from produto where codigo = : parametro");
		consulta.setInteger("parametro", valor);
		return (Produto) consulta.uniqueResult();
		
	}
	
	public Produto merge(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Produto retorno = (Produto) sessao.merge(produto);
			transacao.commit();
			return retorno;
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}



}
