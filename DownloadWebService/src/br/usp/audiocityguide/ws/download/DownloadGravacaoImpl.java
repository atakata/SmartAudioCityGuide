package br.usp.audiocityguide.ws.download;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.jws.WebService;

import org.apache.axiom.om.util.Base64;
import org.apache.log4j.Logger;

import br.usp.audiocityguide.dao.GravacaoDAO;
import br.usp.audiocityguide.jpa.entity.Gravacao;

//Service Implementation Bean

@WebService(endpointInterface = "br.usp.audiocityguide.ws.download.DownloadGravacao")
public class DownloadGravacaoImpl implements DownloadGravacao{
	
	static Logger logger = Logger.getLogger(DownloadGravacaoImpl.class);
	
	@Override
	public String[] downloadGravacao(String latitude, String longitude) {
		byte[] bFile;
		byte[] buffer = new byte[8192];
		String arquivoBase64 = null;
		String[] listaGravacoesBase64 = new String[10]; 
		
		String diretorio = "D:\\AudiosSACG\\";
		String distancia = "100";
		try {
			GravacaoDAO dao = new GravacaoDAO();
			List<Gravacao> listaGravacoes = dao.obterListaDeGravacoes(latitude, longitude, distancia);
			int i = 0;
			
			for (Gravacao gravacao : listaGravacoes) {
				File file = new File(diretorio + gravacao.getNome());
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis, 8192);
				ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
				int len = 0;
			
				while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
					baos.write(buffer, 0, len);
				}
				
				bFile = baos.toByteArray();
				arquivoBase64 = Base64.encode(bFile);
				
				listaGravacoesBase64[i] = arquivoBase64;
				i++;
				
				bis.close();
				fis.close();
			}

			return listaGravacoesBase64;
		}
		catch (Exception e) {
			return null;
		}
	}
}