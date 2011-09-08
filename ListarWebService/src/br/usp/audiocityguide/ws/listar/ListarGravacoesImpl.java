package br.usp.audiocityguide.ws.listar;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import br.usp.audiocityguide.dao.GravacaoDAO;
import br.usp.audiocityguide.jpa.entity.Gravacao;

@WebService(endpointInterface = "br.usp.audiocityguide.ws.listar.ListarGravacoes")
public class ListarGravacoesImpl implements ListarGravacoes{

	static Logger logger = Logger.getLogger(ListarGravacoesImpl.class);

	@Override
	public String[] listarGravacoes(String latitude, String longitude) {
		String distancia = "100";
		GravacaoDAO dao = new GravacaoDAO();
		List<Gravacao> listaGravacoes = dao.obterListaDeGravacoes(latitude, longitude, distancia);
		String[] lista = new String[10];
		int i = 0;
		for (Gravacao gravacao : listaGravacoes) {
			lista[i] = gravacao.getTitulo() + ";" + gravacao.getLatitude() + ";" + gravacao.getLongitude();
			i++;
		}
		return lista;
	}
}