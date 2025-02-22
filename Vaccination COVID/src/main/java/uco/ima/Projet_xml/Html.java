package uco.ima.Projet_xml;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;



public class Html 
{
	public void ToHtml() {
	try {
		
        TransformerFactory tFactory=TransformerFactory.newInstance();

        Source xslDoc=new StreamSource("centre.xsl");
        Source xmlDoc=new StreamSource("XML_DOM.xml");

        String outputFileName="Centre_vaccination.html";

        OutputStream htmlFile=new FileOutputStream(outputFileName);
        Transformer trasform=tFactory.newTransformer(xslDoc);
        trasform.transform(xmlDoc, new StreamResult(htmlFile));
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
	}
    
}


