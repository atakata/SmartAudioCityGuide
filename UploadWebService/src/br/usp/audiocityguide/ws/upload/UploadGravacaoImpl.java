package br.usp.audiocityguide.ws.upload;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.jws.WebService;

import org.apache.axiom.om.util.Base64;
import org.apache.commons.codec.DecoderException;
import org.apache.log4j.Logger;

import br.usp.audiocityguide.dao.GravacaoDAO;
import br.usp.audiocityguide.jpa.entity.Gravacao;
import br.usp.audiocityguide.util.Arquivo;

@WebService(endpointInterface = "br.usp.audiocityguide.ws.upload.UploadGravacao")
public class UploadGravacaoImpl implements UploadGravacao{

	static Logger logger = Logger.getLogger(UploadGravacaoImpl.class);
	
	@Override
	public boolean uploadGravacao(String stringInBase64, String latitude, String longitude, String titulo, String precisao) {
		try {
			byte[] filecontent = Base64.decode(stringInBase64);
			if (filecontent.length == 0) {
				throw new DecoderException("Erro no decoder");
			}
			else {
				String nomeArquivo = Arquivo.criarNome();
				FileOutputStream fileOuputStream = new FileOutputStream(nomeArquivo);
				fileOuputStream.write(filecontent);
				fileOuputStream.close();
				
				GravacaoDAO dao = new GravacaoDAO();
				Gravacao gravacao = new Gravacao();
				gravacao.setLatitude(latitude);
				gravacao.setLongitude(longitude);
				gravacao.setNome(nomeArquivo);
				gravacao.setPrecisao(precisao);
				gravacao.setTitulo(titulo);
				
				dao.insereGravacao(gravacao);
				
				logger.debug("Upload realizado com sucesso!");
				return true;
			}
		}
		catch (DecoderException e) {
			logger.debug("Decoder Exception: " + e.getMessage());
			return false;
		}
		catch (IOException e) {
			logger.debug("IO Exception: " + e.getMessage());
			return false;
		}
		catch (Exception e) {
			logger.debug("Exception: " + e.getMessage());
			return false;
		}
	}
}