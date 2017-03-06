package persistencia;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.FormaPgto;
import beans.Pedido;

public class PedidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void inserir(Pedido pedido) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(pedido);
		t.commit();
		sessao.close();
	}

}
