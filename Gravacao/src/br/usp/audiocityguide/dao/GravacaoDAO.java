package br.usp.audiocityguide.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.usp.audiocityguide.conexao.ConexaoHibernate;
import br.usp.audiocityguide.jpa.entity.Gravacao;

public class GravacaoDAO {

	private Session session;
	static Logger logger = Logger.getLogger(GravacaoDAO.class); 
	 
	// insere gravacao no banco
	// em caso de erro: rollback e retorna mensagem com erro
	public boolean insereGravacao(Gravacao gravacao) {
		String mensagem = "";

		session = ConexaoHibernate.getInstance();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.persist(gravacao);
			tx.commit();
			mensagem = "Inserção da gravação: Id: " + gravacao.getId() + " Nome: " + gravacao.getNome() + " realizada com sucesso.";
			return true;
		} catch (Exception e) {
			mensagem = "Erro ao inserir gravacao: Id: " + gravacao.getId() + " Nome: " + gravacao.getNome() + " : " + e.getMessage();
			tx.rollback();
			return false;
		} finally {
			ConexaoHibernate.fecharConexao();
			logger.debug(mensagem);
		}
	}
	
	// Obtem lista de no máximo 10 gravacoes mais próximas
	public List<Gravacao> obterListaDeGravacoes(String latitude, String longitude, String distancia) {
		List<Gravacao> listaGravacoes = new ArrayList<Gravacao>();
		
		String mensagem = "";

		session = ConexaoHibernate.getInstance();
		
		try {
			Query query = session.createSQLQuery(" select a.*, ACOS( COS( RADIANS( a.latitude ) ) * COS( RADIANS( :paramLatitude )) * COS( RADIANS( a.longitude ) - RADIANS( :paramLongitude )) + SIN( RADIANS( a.latitude )) * SIN( RADIANS( :paramLatitude ) )) * 6380000 as distancia FROM Gravacao a ORDER BY distancia asc LIMIT 10").addEntity(Gravacao.class);
			query.setParameter("paramLatitude", latitude);
			query.setParameter("paramLongitude", longitude);
			//query.setParameter("paramDistancia", distancia);
			
			listaGravacoes = query.list();
			mensagem = "Listagem de gravações realizada com sucesso.";
			return listaGravacoes;
		} catch (Exception e) {
			mensagem = "Erro ao obter lista de gravacoes : " + e.getMessage();
			return null;
		} finally {
			ConexaoHibernate.fecharConexao();
			logger.debug(mensagem);
		}
	}
	
}
