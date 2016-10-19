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

public class League extends Extractor{

	private int id;
	private String name;
	private int totalRounds;
	private int currentRound;
	private int valor2;
	
	public League(int id,String name,int totalRounds,int currentRound){
		super();
		this.id=id;
		this.name=name;
		this.totalRounds=totalRounds;
		this.currentRound=currentRound;
		
	}
	
	public League(){}
	
	public int getId(){return id;}
	public void setId(int id){this.id=id;}
	
	public String getName(){return name;}
	public void setName(String name){this.name=name;}
	
	public int getTotalRounds(){return totalRounds;}
	public void setTotalRounds(int totalRounds){this.totalRounds=totalRounds;}
	
	public int getCurrentRound(){return currentRound;}
	public void setCurrentRound(int currentRound){this.currentRound=currentRound;}

	@Override
	public ArrayList<League> extract(String xml) {
		ArrayList<League> list=new ArrayList<League>();
		//Podemos usar dos parseadores, DOM()Mejor para crear y modificar) y SAX
		try{
			//Le tenemos que dar una serie de pautas y el nos saca la info
			XPath xpath=XPathFactory.newInstance().newXPath();
			String expresion="/leagues/league/id/text()";//Este string le pasará donde tiene que sacar y que tiene que sacar texto
			String expresion2="/leagues/league/name/text()";
			String expresion3="/leagues/league/current_round/text()";
			String expresion4="/leagues/league/total_rounds/text()";
			
			InputSource is=new InputSource(new StringReader(xml));
			NodeList listNames=(NodeList)xpath.evaluate(expresion, is,XPathConstants.NODESET);
			
			InputSource is2=new InputSource(new StringReader(xml));
			NodeList listNames2=(NodeList)xpath.evaluate(expresion2, is2,XPathConstants.NODESET);
			
			InputSource is3=new InputSource(new StringReader(xml));
			NodeList listNames3=(NodeList)xpath.evaluate(expresion3, is3,XPathConstants.NODESET);
			
			InputSource is4=new InputSource(new StringReader(xml));
			NodeList listNames4=(NodeList)xpath.evaluate(expresion4, is4,XPathConstants.NODESET);
			
			for(int i=0;i<listNames.getLength();i++){
				Node n=listNames.item(i);
				int value=Integer.parseInt(n.getNodeValue());
				
				Node n2=listNames2.item(i);
				String value2=n2.getNodeValue();
				
				Node n3=listNames3.item(i);
				int value3=Integer.parseInt(n3.getNodeValue());
				
				Node n4=listNames4.item(i);
				int value4=Integer.parseInt(n4.getNodeValue());
				
				
				System.out.println(value);
				
				League l=new League();
				l.setId(value);
				l.setName(value2);
				l.setCurrentRound(value3);
				l.setTotalRounds(value4);
				list.add(l);
			}
		}catch(XPathExpressionException  e){e.printStackTrace();}
		return list;	
	}
	
	@Override
	public String toString(){return name;}
}
