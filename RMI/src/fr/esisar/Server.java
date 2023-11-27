package fr.esisar;

/***
TDM RMI NE442
Affichage du message "Hello World!"
Classe Server
***/
//import
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends UnicastRemoteObject implements Hello{

	public Server() throws java.rmi.RemoteException{} ;

    public String sayHello() throws java.rmi.RemoteException{
     return "Hello world !";
	}
    
    public static void main(String args[]) {
    	
    	int port=1099;
    	String URL;

        try {
            //instanciation d'un objet Server
			Server obj = new Server();
            
            //Création du registry          
            Registry registry = LocateRegistry.createRegistry(port);
            		
            //Appel de la méthode rebind pour enregistrer l'object serveur
            //URL = "rmi://"+ InetAddress.getLocalHost().getHostAddress()+":"+port+"/mon_serveur";
			registry.rebind("hello", obj);
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
