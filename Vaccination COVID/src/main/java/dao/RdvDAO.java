package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import model.Rdv;

public class RdvDAO extends DAO<Rdv>{

	MongoCollection<Document> collection = database.getCollection("rdv"); 
	
	
	@Override
	public void create(Rdv obj) {
		// TODO Auto-generated method stub
		Document document = new Document();
		try {
			document.put("id_rdv" , obj.getId_rdv());
			document.put("nom_centre" , obj.getNom_centre());
			document.put("num_secu_rdv", obj.getNum_secu_rdv());
			document.put("date", obj.getDate());
			
			collection.insertOne(document);
			
			System.out.println("Le rendez-vous numero : "+obj.getId_rdv()+" ajouté !!!");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void delete(Rdv obj) {
		// TODO Auto-generated method stub
		String name = obj.getId_rdv() +"  ";
		collection.deleteOne(Filters.eq("id_rdv",obj.getId_rdv()));
		System.out.println("Le rendez-vous : "+name+" supprimé(e)");
		
		
	}

	@Override
	public void update(long id_rdv, Rdv obj) {
		// TODO Auto-generated method stub
		try {
			Document document = new Document();
			document.put("id_rdv" , obj.getId_rdv());
			document.put("nom_centre" , obj.getNom_centre());
			document.put("num_secu_rdv", obj.getNum_secu_rdv());
			document.put("date", obj.getDate());
			
			UpdateResult updateResult = collection.updateOne(Filters.eq("id_rdv",id_rdv),new Document("$set", document));
			if (updateResult.getMatchedCount() == 1)
			    System.out.println("Modification reussie");
			else 
				System.err.println("Modification échouée");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Rdv find(long id) {
		// TODO Auto-generated method stub
		Document doc = collection.find(Filters.eq("id_rdv",id)).first();
			Rdv rdv = new Rdv(doc.getLong("id_rdv"), doc.getString("nom_centre"), doc.getLong("num_secu_rdv"), doc.getString("date"));
			System.out.println(rdv.toString());
			
			return rdv;
		
	}

	@Override
	public Rdv find(String num_secu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rdv> findAll() {
		// TODO Auto-generated method stub
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator(); 
		ArrayList<Rdv> rdv = new ArrayList<Rdv>();
		
		try {
			while(cursor.hasNext()) {
				
				Document doc = cursor.next();
				if(doc.getLong("id_rdv")!=null) 
				{
					Rdv r = new Rdv(doc.getLong("id_rdv"), 
							doc.getString("nom_centre"), 
							doc.getLong("num_secu_rdv"), 
							doc.getString("date"));
					rdv.add(r);
				}
			}
		} catch (Exception e) {
			System.out.println("Liste vide");
			e.printStackTrace();
		}
		return rdv;	
	}
	
	public ArrayList<Rdv> find_my_rdv(long num) {
		// TODO Auto-generated method stub
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator(); 
		ArrayList<Rdv> rdv = new ArrayList<Rdv>();
		
		while(cursor.hasNext()) {
			
			Document doc = cursor.next();
			if(doc.getLong("num_secu_rdv")==num) 
			{
				Rdv r = new Rdv(doc.getLong("id_rdv"), 
						doc.getString("nom_centre"), 
						doc.getLong("num_secu_rdv"), 
						doc.getString("date"));

				rdv.add(r);
			}
		}
		
		return rdv;
	}

}
