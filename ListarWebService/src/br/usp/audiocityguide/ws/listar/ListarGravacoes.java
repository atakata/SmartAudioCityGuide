package br.usp.audiocityguide.ws.listar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ListarGravacoes{
	
	@WebMethod String[] listarGravacoes(
			@WebParam(name="latitude")String latitude, 
			@WebParam(name="longitude")String longitude
			);
	
}