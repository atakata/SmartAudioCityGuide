package br.usp.audiocityguide.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Arquivo {

	public static String criarNome() {
		return (getDataHora() + ".3gpp");
	}

	private static String getDataHora() {
		SimpleDateFormat df = new SimpleDateFormat("yyMMddhhmmss");
		df.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		return df.format(new Date());
	}
	
}
