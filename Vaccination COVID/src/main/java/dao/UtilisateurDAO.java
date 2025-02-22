package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import model.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur>{
	
	MongoCollection<Document> collection = database.getCollection("utilisateur"); 

	@Override
	public void create(Utilisateur obj) {
		// TODO Auto-generated method stub
		//long num_secu = collection.countDocuments()+1;
				Document document = new Document();
				try {
					document.put("num_secu" , obj.getNum_secu());
					document.put("nom" , obj.getNom());
					document.put("prenoms", obj.getPrenoms());
					document.put("civilite", obj.getCivilite());
					document.put("date_nais", obj.getDate_nais());
					document.put("adresse", obj.getAdresse());
					document.put("tel", obj.getTel());
					collection.insertOne(document);
					
					System.out.println("L'utilisateur "+obj.getPrenoms()+" ajouté !!!");
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
		
	}

	@Override
	public void delete(Utilisateur obj) {
		// TODO Auto-generated method stub
		String name = obj.getNom() +"  "+obj.getPrenoms();
		collection.deleteOne(Filters.eq("num_secu",obj.getNum_secu()));
		System.out.println("L'utilisateur : "+name+" supprimé(e)");
		
		
	}

	@Override
	public void update(long num_secu, Utilisateur obj) {
		// TODO Auto-generated method stub
		try {
			Document document = new Document();
			document.put("nom" , obj.getNom());
			document.put("prenoms", obj.getPrenoms());
			document.put("civilite", obj.getCivilite());
			document.put("date_nais", obj.getDate_nais());
			document.put("adresse", obj.getAdresse());
			document.put("tel", obj.getTel());
			
			UpdateResult updateResult = collection.updateOne(Filters.eq("num_secu",num_secu),new Document("$set", document));
			if (updateResult.getMatchedCount() == 1)
			    System.out.println("Modification reussie");
			else 
				System.err.println("Modification échouée");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Utilisateur find(long num_secu) {
		// TODO Auto-generated method stub
		Utilisateur utilisateur=null;
		try 
		{
			Document doc = collection.find(Filters.eq("num_secu",num_secu)).first();
			//	System.out.println(doc.toString());
			utilisateur = new Utilisateur(doc.getLong("num_secu"),
					doc.getString("nom"),doc.getString("prenoms"),
					doc.getString("civilite"),
					doc.getString("date_nais"),
					doc.getString("adresse"),doc.getLong("tel"));
				System.out.println(utilisateur.toString());
				
		}catch(Exception e) 
		{
			System.out.println("Veuillez resaisir");
		}
		return utilisateur;
	}

	@Override
	public Utilisateur find(String nom) {
		// TODO Auto-generated method stub
		Document doc = collection.find(Filters.eq("nom",nom)).first();
		//	System.out.println(doc.toString());
			Utilisateur utilisateur = new Utilisateur(doc.getLong("num_secu"),
					doc.getString("nom"),doc.getString("prenoms"),
					doc.getString("civilite"),doc.getString("date_nais"),
					doc.getString("adresse"),doc.getLong("tel"));
			System.out.println(utilisateur.toString());
			return utilisateur;
	}

	@Override
	public ArrayList<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator(); 
		ArrayList<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
		
		while(cursor.hasNext()) {
			
			Document doc = cursor.next();
			if(doc.getLong("num_secu")!=null)
			{
				Utilisateur u = new Utilisateur(doc.getLong("num_secu"),
						doc.getString("nom"),
						doc.getString("prenoms"),
						doc.getString("civilite"),
						doc.getString("date_nais"),
						doc.getString("adresse"),
						doc.getLong("tel"));

				
				utilisateur.add(u);
			}
			
		}
		
		return utilisateur;
	}

}
