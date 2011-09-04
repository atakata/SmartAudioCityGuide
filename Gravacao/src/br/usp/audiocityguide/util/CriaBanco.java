package br.usp.audiocityguide.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

// 1º criar database no mysql com nome banco_gravacoes (mysql> create database banco_gravacoes;)
// 2º rodar main de CriaBanco para criar o tabela gravacao (Gravacao.java)
public class CriaBanco {

	public static void main(String[] args) {
		AnnotationConfiguration ac = new AnnotationConfiguration();
		ac.configure();

		SchemaExport se = new SchemaExport(ac);
		se.create(true, true);
		
		System.out.println("Banco criado com sucesso!");
	}
}