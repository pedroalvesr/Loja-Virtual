package teste;



import java.util.Date;


import persistencia.HibernateUtil;
import persistencia.PessoaDAO;
import beans.Pessoa;

public class Teste {
	public static void main(String[] args){
		
		HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoa = new Pessoa();
		pessoa.setPes_id(1);
		pessoa.setPes_nome("Julinha");
		pessoa.setPes_cpf((String) (Integer.toString(1234567898)));
		pessoa.setPes_rg("");
		pessoa.setPes_data_nasc(new Date(2000-07-07));
		pessoa.setPes_rua("Rua x");
		pessoa.setPes_bairro("Longe");
		pessoa.setPes_cidade("Distante");
		pessoa.setPes_uf("NN");
		pessoa.setPes_cep((long) (1234567898));
		pessoa.setPes_email("exemple@exemple.com.br");
		pessoa.setPes_senha("123");
		System.out.println(PessoaDAO.lsPessoa(""));
	}

}
