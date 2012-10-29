package com.gdroid.webservice;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Environment;
import android.util.Log;

public class WebService {

	public final String[] get(String url) {

		String[] result = new String[2];
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;

		try {
			response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine().getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = toString(instream);
				instream.close();
				Log.i("get", "Result from get JsonPost : " + result[0] + " : " + result[1]);
			}
		} catch (Exception e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "0";
			result[1] = "Falha de rede!";
		}
		return result;
	}

	public final String[] post(String url, String json) {
		String[] result = new String[2];
		try {

			HttpPost httpPost = new HttpPost(new URI(url));
			httpPost.setHeader("Content-type", "application/json");
			StringEntity sEntity = new StringEntity(json, "UTF-8");
			httpPost.setEntity(sEntity);

			HttpResponse response;
			response = HttpClientSingleton.getHttpClientInstace().execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine().getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = toString(instream);
				instream.close();
				Log.d("post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
			}

		} catch (Exception e) {
			Log.e("NGVL", "Falha ao acessar Web service", e);
			result[0] = "0";
			result[1] = "Falha de rede!";
		}
		return result;
	}
	
	public void postMidia(String url, String nomeMidia, String caminhoMidia) throws Exception {

        try {            
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(new URI(url));
            
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            
            FileBody bin = new FileBody( new File( caminhoMidia) );
            reqEntity.addPart("attachment_field", bin);
            reqEntity.addPart("nomeMidia", new StringBody(nomeMidia));
            postRequest.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();
            
            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
            }
        } catch (Exception e) {
            // handle exception here
            Log.e(e.getClass().getName(), e.getMessage());
        }
	}
	
	public File getMidia(String url, String nomeFoto) throws Exception {
		File outputFile = new File("");
		try {
	        HttpURLConnection c = (HttpURLConnection) (new URL(url)).openConnection();
	        c.setRequestMethod("GET");
	        c.setDoOutput(true);
	        c.connect();

	        String PATH = Environment.getExternalStorageDirectory()+"/";
	        File file = new File(PATH);

	        outputFile = new File(file, nomeFoto);
	        FileOutputStream fos = new FileOutputStream(outputFile);
	        	        
	        InputStream is = c.getInputStream();

	        byte[] buffer = new byte[1024];
	        int len1 = 0;
	        while ((len1 = is.read(buffer)) != -1) {
	            fos.write(buffer, 0, len1);
	        }
	        fos.close();
	        is.close();
	    } catch (IOException e) {
	        Log.d("FILTRO", "Error: " + e);
	    }
	    
	    return outputFile.getAbsoluteFile();
	}

	private String toString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}
}
