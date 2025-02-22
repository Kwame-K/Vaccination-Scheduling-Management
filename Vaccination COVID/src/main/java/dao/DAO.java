package dao;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;

import uco.ima.Projet_xml.MongoDBConnection;


public abstract class DAO<T> {
	
	
	//Instance de la connexion à la base de données MongoBD
		protected MongoDatabase database = MongoDBConnection.getDatabase();
		
		//Creation
	    public abstract void create (T obj);
		
	    //Suppression
		public abstract void delete (T obj);
		
		//Modification
		public abstract void update (long num_secu,T obj);
		
		//Recherche unique
		public abstract T find (long num_secu);
		
		public abstract T find (String num_secu);
		
		//Recherhe totale
		public abstract ArrayList<T> findAll ();


}
