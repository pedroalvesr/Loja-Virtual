package persistencia;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Itens_pedido;

public class Itens_pedidoDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static void inserir(Itens_pedido itens_pedido) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(itens_pedido);
		t.commit();
		sessao.close();
	}
}
