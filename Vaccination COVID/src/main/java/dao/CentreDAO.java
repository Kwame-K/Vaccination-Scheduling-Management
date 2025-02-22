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

public class CentreDAO extends DAO<Centre>{
	
	MongoCollection<Document> collection = database.getCollection("centres_vaccination");

	@Override
	public void create(Centre obj) {
		// TODO Auto-generated method stub
		Document document = new Document();
		Document document1 = new Document();
		try {
			document.put("id_c" , obj.getId_c());
			document.put("nom_c" , obj.getNom_c());
			document.put("date_ouverture", obj.getDate_ouverture());
			document.put("date_fermeture", obj.getDate_fermeture());
			document.put("tel_c", obj.getTel_c());
			document.put("Mod_rdv", obj.getMod_rdv());
			document.put("lat_c", obj.getLat_c());
			document.put("long_c", obj.getLong_c());
			document.put("rdv_lundi" , obj.getRdv_lundi());
			document.put("rdv_mardi" , obj.getRdv_mardi());
			document.put("rdv_mercredi", obj.getRdv_mercredi());
			document.put("rdv_jeudi", obj.getRdv_jeudi());
			document.put("rdv_vendredi", obj.getRdv_vendredi());
			document.put("rdv_samedi", obj.getRdv_samedi());
			document.put("rdv_dimache", obj.getRdv_samedi());
			
			
			
				document1.put("numero" , obj.getAdresse().getNumero());
				document1.put("voie", obj.getAdresse().getVoie());
				document1.put("ville", obj.getAdresse().getVille());
				document1.put("cp", obj.getAdresse().getCp());
			document.put("adresse", document1);
			
			collection.insertOne(document);
			
			System.out.println("Le centre "+obj.getNom_c()+" ajouté !!!");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Centre obj) {
		// TODO Auto-generated method stub
		String name = obj.getNom_c() +"  ";
		collection.deleteOne(Filters.eq("id_c",obj.getNom_c()));
		System.out.println("Le centre : "+name+" supprimé(e)");
		
		
	}

	@Override
	public void update(long id_c, Centre obj) {
		// TODO Auto-generated method stub
Document document = new Document();
		
		try {
			document.put("id_c" , obj.getId_c());
			document.put("nom_c" , obj.getNom_c());
			document.put("date_ouverture", obj.getDate_ouverture());
			document.put("date_fermeture", obj.getDate_fermeture());
			document.put("tel_c", obj.getTel_c());
			document.put("Mod_rdv", obj.getMod_rdv());
			document.put("lat_c", obj.getLat_c());
			document.put("long_c", obj.getLong_c());
			document.put("rdv_lundi" , obj.getRdv_lundi());
			document.put("rdv_mardi" , obj.getRdv_mardi());
			document.put("rdv_mercredi", obj.getRdv_mercredi());
			document.put("rdv_jeudi", obj.getRdv_jeudi());
			document.put("rdv_vendredi", obj.getRdv_vendredi());
			document.put("rdv_samedi", obj.getRdv_samedi());
			document.put("rdv_dimache", obj.getRdv_samedi());
			Adresse ad = new Adresse(obj.getAdresse().getNumero(),
					obj.getAdresse().getVoie(), 
					obj.getAdresse().getVille(), 
					obj.getAdresse().getCp());
			document.put("adresse", ad);
			
			UpdateResult updateResult = collection.updateOne(Filters.eq("id_c",id_c),new Document("$set", document));
			if (updateResult.getMatchedCount() == 1)
			    System.out.println("Modification reussie");
			else 
				System.err.println("Modification échouée");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	public Centre find_v(String cp) {
		// TODO Auto-generated method stub
		
			
			CentreDAO cd= new CentreDAO();		
			ArrayList<Centre> rech = new ArrayList<Centre>();
			rech = cd.findAll();
			Iterator<Centre> itr = rech.iterator();
			while(itr.hasNext()) 
			{
				
					Centre a = itr.next();
					if(cp.equals(a.getAdresse().getCp()))
					{
						System.out.println(a.toString());
						return a;	
					}	
			}
		
		return null;
		
	}

	@Override
	public Centre find(String nom) {
		// TODO Auto-generated method stub
		Document doc = collection.find(Filters.eq("nom_c",nom)).first();
		Centre centre=null;
		try {
			Document doc1=(Document) doc.get("adresse");
			Adresse adresse = new Adresse(doc1.getString("numero"),
					(String)doc1.get("voie"),
					(String)doc1.get("ville"),
					(String)doc1.get("cp"));
				centre = new Centre(doc.getDouble("id_c"),
						doc.getString("nom_c"),
						doc.getString("date_ouverture"),
						doc.getString("date_ouverture"), 
						doc.getString("tel_c"), 
						doc.getString("Mod_rdv"), 
						doc.getDouble("lat_c"), 
						doc.getDouble("long_c"), 
						doc.getString("rdv_lundi"), 
						doc.getString("rdv_mardi"),
						doc.getString("rdv_mercredi"), 
						doc.getString("rdv_jeudi"), 
						doc.getString("rdv_vendredi"), 
						doc.getString("rdv_samedi"),
						doc.getString("rdv_dimanche"),
						adresse);
				System.out.println(centre.toString());
				
		} catch (Exception e) {
			System.out.println("Centre non trouvé");
			e.printStackTrace();
		}
			
			return centre;
	}

	@Override
	public ArrayList<Centre> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Centre> centre = new ArrayList<Centre>();
		
			FindIterable<Document> documents = collection.find();
			MongoCursor<Document> cursor = documents.iterator(); 
			
			Document doc1;
			
			try {
				while(cursor.hasNext()) {
				
					Document doc = cursor.next();
					doc1=(Document) doc.get("adresse");
					
					Adresse adresse = new Adresse(doc.getString("numero"),
							(String)doc1.get("voie"),
							(String)doc1.get("ville"), 
							(String)doc1.get("cp"));
					
					Centre c = new Centre(doc.getDouble("id_c"),
							doc.getString("nom_c"), 
							doc.getString("date_ouverture"), 
							doc.getString("date_ouverture"), 
							doc.getString("tel_c"), 
							doc.getString("Mod_rdv"), 
							doc.getDouble("lat_c"), 
							doc.getDouble("long_c"), 
							doc.getString("rdv_lundi"), 
							doc.getString("rdv_mardi"), 
							doc.getString("rdv_mercredi"),
							doc.getString("rdv_jeudi"), 
							doc.getString("rdv_vendredi"),
							doc.getString("rdv_samedi"), 
							doc.getString("rdv_dimanche"),
							adresse);
					centre.add(c);
				}
			} catch (Exception e) {
				System.out.println("Liste vide!!!");
				e.printStackTrace();
			}
			
			
		
		return centre;
	}

	@Override
	public Centre find(long num_secu) {
		// TODO Auto-generated method stub
		return null;
	} 

}
