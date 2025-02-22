package uco.ima.Projet_xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bson.Document;
import org.w3c.dom.Element;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.InsertOneModel;

import dao.AdresseDAO;
import dao.CentreDAO;
import dao.RdvDAO;
import dao.UtilisateurDAO;
import model.Adresse;
import model.Centre;
import model.Rdv;
import model.Utilisateur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    
    {
    	
    	
    
    	
    	
    	
    		CentreDAO cd = new CentreDAO();
    		AdresseDAO ad = new AdresseDAO();
    		UtilisateurDAO ua = new UtilisateurDAO();
    		RdvDAO rd = new RdvDAO();
	        int z = 0;
 	        Utilisateur u=null;
    		
            //Initialisation de la base de donnees	
        	if(cd.findAll().isEmpty()) 
        		
        	{
        		
        		List<InsertOneModel<Document>> docs = new ArrayList();
            	try  {
            		u =new Utilisateur(0,"admin","","","","",0);
				      ua.create(u);
            	    BufferedReader br = new BufferedReader(new FileReader("data/centres-vaccination.json"));
            	    String line;
            	    while ((line = br.readLine()) != null) {
            	        docs.add(new InsertOneModel<Document>(Document.parse(line)));
            	        
            	       // je récupère le premier élément de docs
            	        Document d = docs.get(0).getDocument();
           	   
            	        
            	        ArrayList<Document> numbers = new ArrayList<Document>();
            	        numbers = (ArrayList<Document>) d.get("features");
            	        
            	        
            			Iterator<Document> cursor = numbers.iterator();

        				while(cursor.hasNext()) {
        					Document doc = cursor.next();
        					
        					Document d1 = (Document) doc.get("properties");
        	
        					 int e =  (Integer) d1.get("c_gid");
        					
        					 Adresse adresse = new Adresse((String)d1.get("c_adr_num"), (String)d1.get("c_adr_voie"),(String)d1.get("c_com_nom"), (String)d1.get("c_com_insee"));
        					 Centre c = new Centre(e, (String)d1.get("c_nom"), (String)d1.get("c_date_ouverture"), (String)d1.get("c_date_fermeture"), (String)d1.get("c_rdv_tel"), (String)d1.get("c_rdv_modalites"), (Double)d1.get("c_lat_coor1"), (Double)d1.get("c_long_coor1"),(String) d1.get("c_rdv_lundi"), (String)d1.get("c_rdv_mardi"), (String)d1.get("c_rdv_mercredi"),(String) d1.get("c_rdv_jeudi"), (String)d1.get("c_rdv_vendredi"), (String)d1.get("c_rdv_samedi"), (String)d1.get("c_rdv_dimanche"),adresse);
        					
        					 //Je cree les centres
        				      cd.create(c);    	        
            	}
          	         
            	    }
            	}catch (Exception e){
            	    e.printStackTrace();
            	}            	
        	}
    
  	
    	//Connexion a l'application
    		
    		System.out.println("Taper 1 pour se connecter");
        	System.out.println("Taper 2 pour créer un nouvel utilisateur");
        	Scanner sc1 = new Scanner(System.in);
        	int sc2 = sc1.nextInt();
        	
        	if(sc2==2) 
        	{
        		
        		//Creation de nouvel utilisateur
        	
            	System.out.println("Creation d'un nouvel utilisateur");
            	System.out.println();
            	System.out.println("Saisir nom : ");
            	Scanner s = new Scanner(System.in);
            	String s_ = s.next();
            	System.out.println("Saisir prenom : ");
            	Scanner s1= new Scanner(System.in);
            	String s_1 = s1.next();
            	System.out.println("Saisir date de naissance : jj/mm/aaaa");
            	Scanner s2 = new Scanner(System.in);
            	String s_2 = s2.next();
            	System.out.println("Saisir adresse : ");
            	Scanner s3 = new Scanner(System.in);
            	String s_3 = s3.next();
            	System.out.println("Saisir numero de telephone : ");
            	Scanner s4 = new Scanner(System.in);
            	long s_4 = s4.nextLong();
            	System.out.println("Saisir mot de passe : ");
            	Scanner s5 = new Scanner(System.in);
            	String s_5 = s5.next();
            	System.out.println("Saisir la civilité : Monsieur/madame");
            	Scanner s6 = new Scanner(System.in);
            	String s_6 = s6.next();
            	System.out.println("Saisir le numero de securite social : ");
            	Scanner s7 = new Scanner(System.in);
            	long num_secu = s7.nextInt();
            	//controle du numsecu
            	
            	ArrayList<Utilisateur> user = new ArrayList<Utilisateur>();
            	user=ua.findAll();
            	Iterator<Utilisateur> it = user.iterator();
            	while(it.hasNext()) 
            	{
            		Utilisateur ux = it.next();
            		if(ux.getNum_secu()==num_secu)
            		{
            			System.out.println("--------Utilisateur deja existant, veuillez reprendre depuis le debut---------");
            			System.exit(0);
            		}
            	}
            	
            	u =new Utilisateur(num_secu,s_,s_1,s_6,s_2,s_3,s_4);
            	System.out.println();
            	System.out.println("Votre numéro de securité est : "+u.getNum_secu());
            	ua.create(u);
            	
            	
            	
        	}
        	
   	    	        
    	    	        
    	    	        	
    	    	        	long u_nu;
    	    	        	String u_no;
    	    	        	
    	    	        	do {
    	    	        		
    	    	        		//--------------------login-----------------------------
    	    	        		Scanner u_ = new Scanner(System.in); 
        	    	        	System.out.print("Saisir votre nom :");
         	    	        	u_ = new Scanner(System.in);
         	    	        	u_no = u_.next();
         	    	        	
         	    	        	System.out.println();
         	    	        	
         	    	        	System.out.print("Veuillez votre numero de securite sociale :");
        	    	        	u_= new Scanner(System.in);
        	    	        	u_nu = u_.nextLong();
        	    	        	
        	    	        	u = ua.find(u_nu);
        	    	        	
    	    	        		
    	    	        	}
    	    	        	while(u==null||!(u.getNom().equals(u_no)));
    	    	        	String ccp = null;
    	            		Scanner c_cp = new Scanner(System.in);
    	    	        	
    	            		do 
        	    	        {
    	    	        	
    	    	        	//----------------------- - Menu - ----------------------------------
    	    	        	int i;
    	    	            do 
    	    	            {
    	    	            	System.out.println("Taper 1 pour l'affichage des centres");
    	    	                
    	    	                System.out.println();
    	    	                
    	    	                System.out.println("Taper 2 pour prendre RDV");
    	    	                
    	    	                System.out.println();
    	    	                
    	    	                System.out.println("Taper 3 pour consulter ses RDV");
    	    	                
    	    	                System.out.println();
    	    	                
    	    	                System.out.print("Choix : ");
    	    	                  
    	    	                Scanner sc = new Scanner(System.in);
    	    	                
    	    	                i = sc.nextInt();
    	    	                
    	    	             
    	    	            }
    	    	            
    	    	            while(i!=1^i!=2^i!=3);
    	    	            
    	    	            //--------------- - Affichage des centres - ------------------------\\
    	    	            
    	    	            if(i==1) 
    	    	            {

    	    	            	Centre zx = null;
	    	            		Centre a=zx;
	    	                  	cd.findAll();
	    	                  	System.out.println();
	    	                  	System.out.print(" 1 pour saisir le code postale de la ville et 2 pour tout afficher : ");
	    	                	 c_cp = new Scanner(System.in);
	    	            		 ccp = c_cp.next();
	    	            		 int s = Integer.parseInt(ccp);

	    	                  	if(s==1){
	    	                  		
	    	                  	do
        						{
	    	        				System.out.print("saisir le code postale de la ville : ");
    	    	                	 c_cp = new Scanner(System.in);
    	    	            		 ccp = c_cp.next();
	    	        				
	    	        				ArrayList<Centre> rech = new ArrayList<Centre>();
    	    	        			rech = cd.findAll();
    	    	        			Iterator<Centre> itr = rech.iterator();
    	    	        			while(itr.hasNext()) 
    	    	        			{
    	    	        				
    	    	        					a = itr.next();
    	    	        					
    	    	        					if(ccp.equals(a.getAdresse().getCp()))
    	    	        					{
    	    	        						System.out.println(a.toString());
    	    	        						zx=a;	
    	    	        					}
    	    	        					
    	    	        			}
    	    	        			
	    	        				if(zx==null) 
	    	        					{
	    	        					System.out.println("Introuvable !!");
	        							System.out.println();
	        							System.out.print("Taper 0 pour quitter ou 1 pour continuer : ");
        	    	                	c_cp = new Scanner(System.in);
        	    	            		int q = c_cp.nextInt();
        	    	            		
        	    	                  	if(q==0)
        	    	                  	{
        	    	                  		System.exit(0);
        	    	                  	}
        	    	                  	
	    	        					}
        							
    	    	                  	
    	    	                  	
        						}
        						while(zx==null);
    	    	            }
    	    	            	
	    	                  	else if(s==2)
	    	                  	{	
    	    	            	//afficher tout
    	    	            	
    	    	            	ArrayList<Centre> center = cd.findAll();
    	    	            	Iterator<Centre> itr =center.iterator();
    	    	            	while(itr.hasNext()) 
    	    	            	{
    	    	            		Centre centre = itr.next();
    	    	            		System.out.println(centre.toString());
    	    	            		
    	    	            	}
    	    	            	
    	    	            }
    	    	            	
    	    	            	//Fonctionnalité reservée a l'admin, extraction des donnees
    	    	    	            if(u.getNum_secu()==0 || u.getNom()=="admin") 
    	    	    	            {
    	    	    	            	
    	    	    	            	System.out.println("Extraire les données des centres? ");
            	    	            	System.out.println("Taper 1 pour le format XML ");
            	    	            	System.out.println("Taper 2 pour le format HTML ");
            	    	            	
            	    	            	Xml xml = new Xml();
            	    	            	Centre c = null;
            	    	            	
            	    	            	
            	    	            	Scanner sc = new Scanner(System.in); 
            	    	                i = sc.nextInt();
            	    	                
            	    	                
            	    	                if(i==1) 
            	    	                {
            	    	                	xml.extract(cd, c);
            	    	                	System.out.println("Extraction sous formart XML réussie !!! ");
            	    	                }
            	    	                else if(i==2) 
            	    	                {
            	    	                	//xml.extract(cd, c);
            	    	                	Html toxml = new Html();
            	    	                	toxml.ToHtml();
            	    	                	System.out.println("Extraction sous formart HTML réussie !!! ");
            	    	                }
            	    	            	
    	    	    	            	
    	    	    	            	
    	    	    	            }
       
        	    	            
        	    	      		     
        	    	            	
        	    	            
    	    	            	
    	    	            	
    	    	            	
    	    	            	
    	    	           //-----------------------------Prise de rendez-vous----------------------------
    	    	            	
    	    	            }
    	    	            else if(i==2) 
    	    	            {
    	    	            	
    	    	            	
    	    	            	
    	    	            	System.out.println("Confirmer les informations suivantes : ");
    	    	            	
    	    	            	System.out.println();
    	    	            	
    	    	            	System.out.println("Nom : " + u.getNom());
    	    	                
    	    	                System.out.println("Prenom : "+u.getPrenoms());
    	    	                
    	    	                System.out.println("Adresse : "+u.getAdresse());
    	    	                
    	    	                System.out.println("Telephone : "+u.getTel());
    	    	                
    	    	                System.out.println("Numero de sécurité sociale : "+u.getNum_secu());
    	    	                
    	    	                System.out.println();
    	    	                
    	    	                
    	    	                System.out.print("Pour choisir le centre ");
    	    	                
    	    	                	int q;
    	    	                	String date=null;
    	    	                	
    	    	                	
    	    	                	
    	    	            		
    	    	            		Centre c = null;
    	    	            		Centre a=c;
    	    	                  	cd.findAll();
    	    	            		
    	    	                  	
    	    	        			
    	    	        			
    	    	        			do
	        						{
    	    	        				System.out.print("saisir le code postale de la ville : ");
        	    	                	 c_cp = new Scanner(System.in);
        	    	            		 ccp = c_cp.next();
    	    	        				
    	    	        				ArrayList<Centre> rech = new ArrayList<Centre>();
        	    	        			rech = cd.findAll();
        	    	        			Iterator<Centre> itr = rech.iterator();
        	    	        			while(itr.hasNext()) 
        	    	        			{
        	    	        				
        	    	        					a = itr.next();
        	    	        					
        	    	        					if(ccp.equals(a.getAdresse().getCp()))
        	    	        					{
        	    	        						System.out.println(a.toString());
        	    	        						c=a;	
        	    	        					}
        	    	        					
        	    	        			}
        	    	        			
    	    	        				if(c==null) 
    	    	        					{
    	    	        					System.out.println("Introuvable !!");
    	        							System.out.println();
    	        							System.out.print("Taper 0 pour quitter ou 1 pour continuer : ");
            	    	                	c_cp = new Scanner(System.in);
            	    	            		q = c_cp.nextInt();
            	    	            		
            	    	                  	if(q==0)
            	    	                  	{
            	    	                  		System.exit(0);
            	    	                  	}
            	    	                  	
    	    	        					}
	        							
        	    	                  	
        	    	                  	
	        						}
	        						while(c==null);
    	    	        			    	    	                  	
    	    	                	
    	    	                System.out.println(c.getNom_c());
    	    	                System.out.println();
    	    	                System.out.println("Creneaux disponible : "); 
    	    	                System.out.print("1 - Lundi : "+c.getRdv_lundi());
    	    	                System.out.println();
    	    	                System.out.print("2 - Mardi : "+c.getRdv_mardi());
    	    	                System.out.println();
    	    	                System.out.print("3 - Mercredi : "+c.getRdv_mercredi());
    	    	                System.out.println();
    	    	                System.out.print("4 - Jeudi : "+c.getRdv_jeudi());
    	    	                System.out.println();
    	    	                System.out.print("5 - Vendredi : "+c.getRdv_vendredi());
    	    	                System.out.println();
    	    	                System.out.print("6 - Samedi : "+c.getRdv_samedi());
    	    	                System.out.println();
    	    	                System.out.println("7 - Dimache : "+c.getRdv_dimanche());
    	    	                
    	    	                
    	    	                System.out.print("Choisir le numero du jour de rendez-vous : ");
    	    	                   	    	                
    	    	                c_cp = new Scanner(System.in);
	    	            		ccp = c_cp.next();
    	    	                int day = Integer.parseInt(ccp);
    	    	                
    	    	                
    	    	                switch (day) {
    	    	                  case 1:
    	    	                    date = "Lundi : "+c.getRdv_lundi();
    	    	                    break;
    	    	                  case 2:
    	    	                	date = "Mardi : "+c.getRdv_mardi();
    	    	                    break;
    	    	                  case 3:
    	    	                	date = "Mercredi : "+c.getRdv_mercredi();
    	    	                    break;
    	    	                  case 4:
    	    	                	date = "Jeudi : "+c.getRdv_jeudi();
    	    	                	break;
    	    	                  case 5:
    	    	                	 date = "Vendredi : "+c.getRdv_vendredi();
    	    	                	 break;
    	    	                  case 6:
    	    	                	date = "Samedi : "+c.getRdv_samedi();
    	    	                	break;
    	    	                  case 7:
    	    	                	date = "Dimanche : "+c.getRdv_dimanche();
    	    	                	break;
    	    	                }
    	    	                
    	    	                
    	    	               
    	    	                int id_rdv=rd.findAll().size()+1;
    	    	                if(date.length()<17)
    	    	                 {
    	    	                	System.out.println("Date choisie non conforme !!!");
    	    	                 }
    	    	                else
    	    	                
    	    	                {
    	    	                	
    	    	                	rd.findAll();
    	    	                	int cm=0;
        	    	            	
        	    	            	ArrayList<Rdv> rdv_ = rd.find_my_rdv(u.getNum_secu());
        	    	            	Iterator<Rdv> itr =rdv_.iterator();
        	    	            	while(itr.hasNext()) 
        	    	            	{
        	    	            		Rdv r = itr.next();
        	    	            		
        	    	            		if((r.getNom_centre()).equals(c.getNom_c()+", "+c.getAdresse().getVille())) 
    	    	                		{
    	    	                		cm++;
    	    	                		}
        	    	            		
        	    	            		System.out.println(r.toString());
        	    	            		System.out.println();    	    	            		
        	    	            	}
        	    	            	
        	    	            	if(cm>1) 
        	    	            	
        	    	            	{
        	    	            		System.out.println("Nombre de rendez-vous maximale du jour est atteint, veuillez choisir un autre jour");
        	    	            		System.exit(0);
        	    	            	}
        	    	            	else if(cm==0||cm==1) 
        	    	            	
        	    	            	{
        	    	            		Rdv rdv= new Rdv(id_rdv, c.getNom_c()+", "+c.getAdresse().getVille(), u.getNum_secu(), date);	
            	    	            	rd.create(rdv);
            	    	            	System.out.println();
        	    	            	}
    	    	                	  	
    	    	                	
    	    	                	
    	    	                }
    	    	            	
    	    	            	
    	    	             
    	    	           
    	    	            		
    	    	            //-----------------------------Consulter ces rendez-vous------------------------------------------	
    	    	            }
    	    	            
    	    	            else if(i==3) 
    	    	            
    	    	            {
    	    	            	System.out.println(u.getNum_secu());
    	    	            	if(u.getNum_secu()==0) 
    	    	            	{
    	    	            		ArrayList<Rdv> rdv = rd.findAll();
        	    	            	Iterator<Rdv> itr =rdv.iterator();
        	    	            	while(itr.hasNext()) 
        	    	            	{
        	    	            		Rdv r = itr.next();
        	    	            		System.out.println(r.toString());
        	    	            		System.out.println();    	    	            		
        	    	            	}
    	    	            	}
    	    	            	ArrayList<Rdv> rdv = rd.find_my_rdv(u.getNum_secu());
    	    	            	Iterator<Rdv> itr =rdv.iterator();
    	    	            	while(itr.hasNext()) 
    	    	            	{
    	    	            		Rdv r = itr.next();
    	    	            		System.out.println(r.toString());
    	    	            		System.out.println();    	    	            		
    	    	            	}
    	    	            	
    	    	            	if(rdv.size()==0) 
    	    	            	{
    	    	            		System.out.println("Aucun rendez-vous");
    	    	            		
    	    	            	} else if(rdv.size()!=0) 
    	    	            	
    	    	            	{
    	    	            		//Modifier ou supprimer un rendez-vous
    	    	            		System.out.println("Saisir le numero du rendez-vous à modifier ou suprimer : ");
        	    	            	c_cp = new Scanner(System.in);
    	    	            		ccp = c_cp.next();
        	    	                int chx = Integer.parseInt(ccp);      	    	                
        	    	                Rdv r_d_v = rd.find(chx);       	    	                
        	    	                System.out.println("1 pour modifier et 2 pour suprimer : ");
        	    	            	c_cp = new Scanner(System.in);
    	    	            		ccp = c_cp.next();    	            		
    	    	            		int chx1 = Integer.parseInt(ccp);
    	    	            		
        	    	                //----------------Modification-----------------
        	    	                if(chx1==1)
        	    	                { 	
        	    	                	System.out.print("Pour choisir le centre ");
            	    	                
        	    	                	int q;
        	    	                	String date=null;

        	    	            		Centre c = null;
        	    	            		Centre a=c;
        	    	            		cd.findAll();
        	    	                  	
        	    	        			do
    	        						{
        	    	        				System.out.print("saisir le code postale de la ville : ");
            	    	                	 c_cp = new Scanner(System.in);
            	    	            		 ccp = c_cp.next();
        	    	        				
        	    	        				ArrayList<Centre> rech = new ArrayList<Centre>();
            	    	        			rech = cd.findAll();
            	    	        			Iterator<Centre> itr1 = rech.iterator();
            	    	        			while(itr1.hasNext()) 
            	    	        			{
            	    	        				
            	    	        					a = itr1.next();
            	    	        					
            	    	        					if(ccp.equals(a.getAdresse().getCp()))
            	    	        					{
            	    	        						System.out.println(a.toString());
            	    	        						c=a;	
            	    	        					}
            	    	        					
            	    	        			}
            	    	        			
        	    	        				if(c==null) 
        	    	        					{
        	    	        					System.out.println("Introuvable !!");
        	        							System.out.println();
        	        							System.out.print("Taper 0 pour quitter ou 1 pour continuer : ");
                	    	                	c_cp = new Scanner(System.in);
                	    	            		q = c_cp.nextInt();
                	    	            		
                	    	                  	if(q==0)
                	    	                  	{
                	    	                  		System.exit(0);
                	    	                  	}
                	    	                  	
        	    	        					}
    	        							
            	    	                  	
            	    	                  	
    	        						}
    	        						while(c==null);
        	    	        			
        	    	                  	       	    	                  	
        	    	                System.out.println(c.getNom_c());
        	    	                System.out.println();
        	    	                System.out.println("Creneaux disponible : ");       	    	                
        	    	                System.out.print("1 - Lundi : "+c.getRdv_lundi());
        	    	                System.out.println();
        	    	                System.out.print("2 - Mardi : "+c.getRdv_mardi());
        	    	                System.out.println();
        	    	                System.out.print("3 - Mercredi : "+c.getRdv_mercredi());
        	    	                System.out.println();
        	    	                System.out.print("4 - Jeudi : "+c.getRdv_jeudi());
        	    	                System.out.println();
        	    	                System.out.print("5 - Vendredi : "+c.getRdv_vendredi());
        	    	                System.out.println();
        	    	                System.out.print("6 - Samedi : "+c.getRdv_samedi());
        	    	                System.out.println();
        	    	                System.out.println("7 - Dimache : "+c.getRdv_dimanche());
        	    	                
        	    	                
        	    	                System.out.print("Choisir le numero du jour de rendez-vous : ");
        	    	                   	    	                
        	    	                c_cp = new Scanner(System.in);
    	    	            		ccp = c_cp.next();
        	    	                int day = Integer.parseInt(ccp);
        	    	                
        	    	                
        	    	                switch (day) {
        	    	                  case 1:
        	    	                    date = "Lundi : "+c.getRdv_lundi();
        	    	                    break;
        	    	                  case 2:
        	    	                	date = "Mardi : "+c.getRdv_mardi();
        	    	                    break;
        	    	                  case 3:
        	    	                	date = "Mercredi : "+c.getRdv_mercredi();
        	    	                    break;
        	    	                  case 4:
        	    	                	date = "Jeudi : "+c.getRdv_jeudi();
        	    	                	break;
        	    	                  case 5:
        	    	                	 date = "Vendredi : "+c.getRdv_vendredi();
        	    	                	 break;
        	    	                  case 6:
        	    	                	date = "Samedi : "+c.getRdv_samedi();
        	    	                	break;
        	    	                  case 7:
        	    	                	date = "Dimanche : "+c.getRdv_dimanche();
        	    	                	break;
        	    	                }
        	    	                
        	    	                
        	    	               
        	    	                int id_rdv=rd.findAll().size()+1;
        	    	                if(date.length()<17)
        	    	                 {
        	    	                	System.out.println("Date choisie non conforme !!!");
        	    	                 }
        	    	                else
        	    	                
        	    	                {
        	    	                	
        	    	                	
        	    	                	
        	    	                	Rdv rdv1= new Rdv(id_rdv, c.getNom_c()+", "+c.getAdresse().getVille(), u.getNum_secu(), date);	
        	    	                	rd.update(chx, rdv1);
            	    	            	System.out.println();
        	    	                }
        	    	            	
        	    	                	
        	    	                	
        	    	                }
        	    	                //------------------------------------------Suppression---------------------------------------------------------
        	    	                else if (chx1==2) 
        	    	                {
        	    	                	rd.delete(r_d_v);
        	    	                }
        	    	                
        	    	                System.out.println();
        	    	            }
    	    	            	
    	    	            	}
    	    	            	
    	    	            	
    	    	            	
	            
    	    	            
    	    	            System.out.println();
    	    	            System.out.print("Pour continuer taper 1 pour continuer et 0 pour se deconnecter : ");
    	    	Scanner loop = new Scanner(System.in);
    	    	z = loop.nextInt();
    	
    	    }while(z==1);System.out.println();System.out.println();System.out.println("Merci! Au revoir "+u.getNom());
    	
    	
    	}
   
}
