package Soccer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public abstract class Extractor{

	public Extractor(){}

	//Leer� la url de las ligas y nos devolver� la informaci�n
	public static String readUrl(String route){
		BufferedReader in=null;
		URL url=null;
		String inputLine,inputText="";
			
		//Usando un ImputStreamReadre leeremos el xml y devolverremos la informaci�n
		try{url=new URL(route);
			in=new BufferedReader(new InputStreamReader(url.openStream(),"UTF8"));//Volcamos lo recibido al buffer
			while((inputLine=in.readLine())!=null){inputText=inputText+inputLine;}
		}catch(IOException ioe){
		}finally{
			try{if(in!=null){in.close();}
			}catch (IOException e){e.printStackTrace();}
		}
			
		return inputText;
	}
	
	//As� el m�todo es abstracto y cada hijo endr� que definir el suyo propio
	public abstract ArrayList<? extends Extractor> extract(String xml);
	/*Con ? devolver�a n array de cualquier valor que se le pase,
	 *mientras que con el extrct Extractor, se le puede pasasr toda clase que tenga */
}
