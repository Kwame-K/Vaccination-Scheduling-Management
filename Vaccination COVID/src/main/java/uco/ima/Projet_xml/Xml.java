package uco.ima.Projet_xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dao.CentreDAO;
import model.Centre;

public class Xml {

	
	
	//Methodes pour la creations des differentes balises
	public void ajout_centre(Document document,Element rootElement,Centre c)
	 {
		//Creation des balises
		 Element elem = document.createElement("centre");
		 Element em = document.createElement("id");
		 Element em1 = document.createElement("nom");
		 Element em2 = document.createElement("date_ouverture");
		 Element em3 = document.createElement("date_fermeture");
		 Element em4 = document.createElement("modalite");
		 Element em5 = document.createElement("telephone");
		 Element em7 = document.createElement("latitude");
		 Element em8 = document.createElement("longitude");

		 //appendChild l-d
		 Element emh = document.createElement("date");

		 Element eml = document.createElement("lundi");
		 Element emm = document.createElement("mardi");
		 Element emme = document.createElement("mercredi");
		 Element emj = document.createElement("jeudi");
		 Element emv = document.createElement("vendredi");
		 Element ems = document.createElement("samedi");
		 Element emd = document.createElement("dimanche");

		 //appendChild au em6
		 Element em6 = document.createElement("adresse");
		 Element em9 = document.createElement("numero");
		 Element em0 = document.createElement("ville");
		 Element em10 = document.createElement("voie");
		 Element em11 = document.createElement("code_postal");

		 //Remplissage des balises avec les données correspondantes

		 em.appendChild(document.createTextNode(""+c.getId_c()));
		 em1.appendChild(document.createTextNode(c.getNom_c()));
		 em2.appendChild(document.createTextNode(c.getDate_ouverture()));
		 em3.appendChild(document.createTextNode(c.getDate_fermeture()));
		 em4.appendChild(document.createTextNode(c.getMod_rdv()));
		 em5.appendChild(document.createTextNode(c.getTel_c()));
		 em7.appendChild(document.createTextNode(""+c.getLat_c()));
		 em8.appendChild(document.createTextNode(""+c.getLong_c()));
		 em9.appendChild(document.createTextNode(c.getAdresse().getNumero()));
		 em0.appendChild(document.createTextNode(c.getAdresse().getVille()));
		 em10.appendChild(document.createTextNode(c.getAdresse().getVoie()));
		 em11.appendChild(document.createTextNode(c.getAdresse().getCp()));
		 eml.appendChild(document.createTextNode(c.getRdv_lundi()));
		 emm.appendChild(document.createTextNode(c.getRdv_mardi()));
		 emme.appendChild(document.createTextNode(c.getRdv_mercredi()));
		 emj.appendChild(document.createTextNode(c.getRdv_jeudi()));
		 emv.appendChild(document.createTextNode(c.getRdv_vendredi()));
		 ems.appendChild(document.createTextNode(c.getRdv_samedi()));
		 emd.appendChild(document.createTextNode(c.getRdv_dimanche()));


		 //Pour le centre :
		 elem.appendChild(em);
		 elem.appendChild(em1);
		 elem.appendChild(em2);
		 elem.appendChild(em3);
		 elem.appendChild(em4);
		 elem.appendChild(em5);
		 elem.appendChild(em7);
		 elem.appendChild(em8);



		 //Pour les horaire des dates :
		 emh.appendChild(eml);
		 emh.appendChild(emm);
		 emh.appendChild(emme);
		 emh.appendChild(emj);
		 emh.appendChild(emv);
		 emh.appendChild(ems);
		 emh.appendChild(emd);

		 //Pour l'adresse :
		 em6.appendChild(em9);
		 em6.appendChild(em0);
		 em6.appendChild(em10);
		 em6.appendChild(em11);



		 //Pour l'horaire et l'adresse
		 elem.appendChild(emh);
		 elem.appendChild(em6);

		 //Ajout a la racine
		 rootElement.appendChild(elem);

	 }
	
	
	//Methode pour la creation du document xml
	 public void extract(CentreDAO cd,Centre c) throws Exception
	  {
		 BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		 DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		 Document document = documentBuilder.newDocument();

		 Element rootElement = document.createElement("Centres");

		 document.appendChild(rootElement);
		 
		ArrayList<Centre> center = cd.findAll();
     	Iterator<Centre> itr =center.iterator();
     	while(itr.hasNext()) 
     	{
     		Centre centre = itr.next();
     		System.out.println(centre.toString());
     		ajout_centre(document,rootElement,centre);     		
     	}

		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		 Transformer transformer = transformerFactory.newTransformer();
		 DOMSource source = new DOMSource(document);
		 StreamResult result =  new StreamResult(System.out);
		 transformer.transform(source, result);

		 transformer.transform(new DOMSource(document),new StreamResult(new File("XML_DOM.xml")));
 }
	 
}

