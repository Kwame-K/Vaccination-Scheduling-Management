package dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import model.Adresse;
import model.Centre;

public class AdresseDAO extends DAO<Adresse> {
	MongoCollection<Document> collection = database.getCollection("centres_vaccination");

	@Override
	public void create(Adresse obj) {
		Document document = new Document();
		try {

			document.put("numero" , obj.getNumero());
			document.put("voie", obj.getVoie());
			document.put("cp", obj.getCp());
			document.put("ville", obj.getVille());
			collection.insertOne(document);
			System.out.println("L'adresse de "+obj.getVille()+" ajoutée !!!");
					
			
		} catch (Exception e) {
			
			System.out.println("Erreur dans l'ajout !!!!!");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void delete(Adresse obj) {
		// TODO Auto-generated method stub
		String name = obj.getVille() +"  ";
		//collection.deleteOne(Filters.eq("id_ad",obj.getId_ad()));
		System.out.println("L'adresse : "+name+" supprimé(e)");
		
		
	}

	@Override
	public void update(long id_ad, Adresse obj) {
		// TODO Auto-generated method stub
		try {
			Document document = new Document();
			document.put("numero" , obj.getNumero());
			document.put("voie", obj.getVoie());
			document.put("cp", obj.getCp());
			document.put("ville", obj.getVille());
		
			UpdateResult updateResult = collection.updateOne(Filters.eq("id_ad",id_ad),new Document("$set", document));
			if (updateResult.getMatchedCount() == 1)
			    System.out.println("Modification reussie");
			else 
				System.err.println("Modification échouée");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Modification échouée");
		}
		
		
	}

	@Override
	public Adresse find(String cp) {
		
		AdresseDAO ad= new AdresseDAO();
		
		ArrayList<Adresse> rech = new ArrayList<Adresse>();
		rech = ad.findAll();
		Iterator<Adresse> itr = rech.iterator();
		
		System.out.println(rech);
		
		while(itr.hasNext()) 
		{
			Adresse a = itr.next();
			if(cp.equals(a.getCp()))
			{
				System.out.println(a.toString());
				return a;	
			}
			
		}
			return null;
	}

	@Override
	public Adresse find(long num_secu) {
		return null;
	}

	@Override
	public ArrayList<Adresse> findAll() {
		
		 FindIterable<Document> documents = collection.find();
			MongoCursor<Document> cursor = documents.iterator(); 
			ArrayList<Adresse> adresse = new ArrayList<Adresse>();
			Document doc1;
			
			try {
				while(cursor.hasNext()) {
				
					Document doc = cursor.next();
					doc1=(Document) doc.get("adresse");
					Adresse a = new Adresse((String)doc1.get("numero"),
							(String)doc1.get("voie"),
							(String)doc1.get("ville"), 
							(String)doc1.get("cp"));
					adresse.add(a);
				}
			} catch (Exception e) {
				System.out.println("Liste des centres vide!!!!");
				e.printStackTrace();
				
			}
			
			return adresse;
	}

}
