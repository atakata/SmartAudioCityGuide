package br.usp.audiocityguide.teste;

import java.util.List;

import br.usp.audiocityguide.dao.GravacaoDAO;
import br.usp.audiocityguide.jpa.entity.Gravacao;

public class Teste {
	
	public static void insereGravacoes(int totalRegistros) {	
		GravacaoDAO dao = new GravacaoDAO();
		
		for (int i = 0; i < totalRegistros; i++) {
			Gravacao gravacao = new Gravacao();
			gravacao.setNome("Nome " + i);
			gravacao.setTitulo("Titulo " + i);
			gravacao.setPrecisao("5");
			gravacao.setLatitude(String.valueOf((int) 1 + (Math.random() * 100)));
			gravacao.setLongitude(String.valueOf((int) 1 + (Math.random() * 100)));
			dao.insereGravacao(gravacao);
		}
	}
	
	public static void listaGravacoes(String latitude, String longitude, String distancia) {
		GravacaoDAO dao = new GravacaoDAO();
		
		List<Gravacao> lista = dao.obterListaDeGravacoes(latitude, longitude, distancia);
		
		if (lista != null && lista.size() > 0) {
			for (Gravacao gravacao : lista) {
				System.out.println("Nome: " + gravacao.getNome());
				System.out.println("Latitude: " + gravacao.getLatitude());
				System.out.println("Longitude: " + gravacao.getLongitude());
			}
		}
	}
	
	public static void main(String[] args) {
		
		//insereGravacoes(20);
		listaGravacoes("23.606425", "84.696728", "10000000");
	}
}
