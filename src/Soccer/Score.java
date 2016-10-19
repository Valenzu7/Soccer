package Soccer;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Score extends Extractor{
	
private String local,visit;
private int goLocal,goVisit;
	
	public Score() {
	}

	public Score(String local, String visit, int goLocal, int goVisit) {
		super();
		this.local = local;
		this.visit = visit;
		this.goLocal = goLocal;
		this.goVisit = goVisit;
	}

	public String getLocal(){return local;}
	public void setLocal(String local){this.local=local;}

	public String getVisit(){return visit;}
	public void setVisit(String visit){this.visit=visit;}

	public int getGoLocal(){return goLocal;}
	public void setGoLocal(int goLocal){this.goLocal=goLocal;}
	
	public int getGoVisit(){return goVisit;}
	public void setGoVisit(int goVisit){this.goVisit=goVisit;}

	@Override
	public ArrayList<Score> extract(String xml) {
		ArrayList<Score> list=new ArrayList<Score>();
		//Podemos usar dos parseadores, DOM()Mejor para crear y modificar) y SAX
		try{
			//Le tenemos que dar una serie de pautas y el nos saca la info
			XPath xpath=XPathFactory.newInstance().newXPath();
			String ex="/matchs/match/local/text()";//Este string le pasará donde tiene que sacar y que tiene que sacar texto
			String ex2="/matchs/match/visitor/text()";
			String go="/matchs/match/local_goals/text()";
			String go2="/matchs/match/visitor_goals/text()";
			
			InputSource is=new InputSource(new StringReader(xml));
			NodeList listLocal=(NodeList)xpath.evaluate(ex, is,XPathConstants.NODESET);
			
			InputSource is2=new InputSource(new StringReader(xml));
			NodeList listVisit=(NodeList)xpath.evaluate(ex2, is2,XPathConstants.NODESET);
			
			InputSource is3=new InputSource(new StringReader(xml));
			NodeList listGoLocal=(NodeList)xpath.evaluate(go, is3,XPathConstants.NODESET);
			
			InputSource is4=new InputSource(new StringReader(xml));
			NodeList listGoVisit=(NodeList)xpath.evaluate(go2, is4,XPathConstants.NODESET);
			
			for(int i=0;i<listLocal.getLength();i++){
				Node n=listLocal.item(i);
				String vLocal=n.getNodeValue();
				
				Node n2=listVisit.item(i);
				String vVisit=n2.getNodeValue();

				Node n3=listGoLocal.item(i);
				int vGLoc=Integer.parseInt(n3.getNodeValue());
				
				Node n4=listGoVisit.item(i);
				int vGVis=Integer.parseInt(n4.getNodeValue());
				
			/*	System.out.println(vLocal);
				System.out.println(vVisit);
				System.out.println(vGLoc);
				System.out.println(vGVis);  */
				
				Score s=new Score();
				s.setLocal(vLocal);
				s.setVisit(vVisit);
				//En caso de que aún no se haya jugado el partido
				if(n3.equals("x")){s.setGoLocal(-1);
				}else{s.setGoLocal(vGLoc);}
				
				if(n4.equals("x")){s.setGoVisit(-1);
				}else{s.setGoVisit(vGVis);}
				
				list.add(s);
			}
		}catch(XPathExpressionException e){e.printStackTrace();}
		return list;	}
	
	public String toString(){return local+" "+goLocal+" - "+goVisit+" "+visit;}

}
