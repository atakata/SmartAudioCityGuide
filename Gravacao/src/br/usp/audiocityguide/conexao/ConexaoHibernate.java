package br.usp.audiocityguide.conexao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ConexaoHibernate {

	private static SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(ConexaoHibernate.class);
	private static Session session = null;

	// retorna uma sessao de comunicacao com o banco
	public static Session getInstance(){
		if (session == null) {
			String mensagem = "";
			try{
				AnnotationConfiguration ac = new AnnotationConfiguration();
				ac.configure();
				sessionFactory = ac.buildSessionFactory();
				//sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
				session = sessionFactory.openSession();
				mensagem = "Sessão de comunicação com Hibernate criada.";
			}catch (Throwable e) {
				mensagem = "Erro ao criar sessão de comunicação com Hibernate: " + e.getMessage();
				return null;
			} finally {
				logger.debug(mensagem);
			}
		}
		return session;
	}
	
	public static void fecharConexao() {
		if (session != null) {
			session.close();
			session = null;
		}
	}
}