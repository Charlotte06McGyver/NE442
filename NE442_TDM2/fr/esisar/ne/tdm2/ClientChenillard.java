import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class ClientChenillard {
	
    public static void main(String[] args) throws Exception
    {
        ClientChenillard cl = new ClientChenillard();
        //Pour avoir le bon nombre d'arguments
        if(args.length!= 1) {
            System.out.println("Usage: <start>");
        }else {
            cl.execute(args[0]);
        }
    }
    
    private void execute(String dernier) throws Exception {
    	//
        System.out.println("Demarrage du client chenillard ...");
	    
        // Le client se declare aupres de la couche transport
        // sur le port port (3000 est le port du serveur)
        DatagramSocket socket = new DatagramSocket(null);
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 3000);

    	byte[] bufE = new String(dernier).getBytes();
    	byte[] bufR = new byte[2048];
    	
        //Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Client_Chenillard");
        frame.setSize(300,300);

    	//Connexion au serveur
    	DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
    	socket.send(dpE);
    	String envoi = new String(bufE, dpE.getOffset(), dpE.getLength());
    	System.out.println("Envoi d'un paquet UDP avec "+envoi);

    	while(true){
    		//Attente de la réponse
    		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    		socket.receive(dpR);
    		String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
    		System.out.println("Le serveur a repondu "+reponse);	

    		if (reponse.equals("red")) {
    			//Affichage de la fenetre en rouge
    			frame.getContentPane().setBackground(Color.RED);
    			frame.setVisible(true); 
    		}
    		else if (reponse.equals("green")) {	
    			//Affichage de la fenetre en vert
    			frame.getContentPane().setBackground(Color.GREEN);
    			frame.setVisible(true); 
    		}	
    	}	
    }	
}
