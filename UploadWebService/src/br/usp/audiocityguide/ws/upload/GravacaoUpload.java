package br.usp.audiocityguide.ws.upload;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface GravacaoUpload{
	
	@WebMethod boolean gravacaoUpload(
			@WebParam(name="arquivoBase64")String stringInBase64, 
			@WebParam(name="latitude")String latitude, 
			@WebParam(name="longitude")String longitude,
			@WebParam(name="titulo") String titulo,
			@WebParam(name="precisao") String precisao
			);
	
}