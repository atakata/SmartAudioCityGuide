package br.usp.audiocityguide.ws.download;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface DownloadGravacao{
	
	@WebMethod
	String[] downloadGravacao(
			@WebParam(name="latitude")String latitude,
			@WebParam(name="longitude")String longitude);
	
}