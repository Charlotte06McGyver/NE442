package fr.esisar;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/***

TDM RMI NE442
Affichage du message "Hello World!"

Classe Client
A compléter
***/


//import ...
import java.rmi.*;


public class Client {
	
	private Client() {}

	public static void main(String[] args) {
        try {
        	int port = 1099;
        	//String URL = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":"+port+"/mon_serveur";
            //Appel de la méthode lookup()
        	Registry registry = LocateRegistry.getRegistry();
			Hello obj = (Hello) Naming.lookup("hello");
			
			//Appel distant de la méthode sayHello()
        	String msg = obj.sayHello();
			
			//Affichage en plusieurs langues
			System.out.println(msg);
			
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

	}

}
