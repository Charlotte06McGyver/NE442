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
        if(args.length!= 3) {
            System.out.println("Usage: <adresse> <port> <start>");
        }else {
            cl.execute(args[0],args[1],args[2]);
        }
    }
    
    private void execute(String Addr, String port, String dernier) throws Exception {
    	//
        System.out.println("Demarrage du chenillard ...");
        int Port= Integer.parseInt(port);
        //On déclare que 1 est le dernier
        int der = Integer.parseInt(dernier);
	    
        // Le client se declare aupres de la couche transport
        // sur le port portSrc
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(Port));
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 3000);

    	byte[] bufE = new String(Addr +" " + port +" " + dernier).getBytes();
    	byte[] bufR = new byte[2048];

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

		if (color.equals("red")) {
        		//Affichage de la fenetre en rouge
        		frame.getContentPane().setBackground(Color.RED);
        		frame.setVisible(true); 
		}
		else if (color.equals("green")) {	
        		//Affichage de la fenetre en vert
        		frame.getContentPane().setBackground(Color.GREEN);
        		frame.setVisible(true); 
		}
    	
    	
    	
    }
	
}
