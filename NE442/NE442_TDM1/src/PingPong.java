
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class PingPong {
	public static void main(String[] args) throws Exception
    {
        PingPong partie = new PingPong();
        partie.execute();

    }


    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //Début de la partie
        System.out.println("Demarrage de la partie ...");

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 29000);
     
        for (int i=1; i<11; i++) {
        	// Creation et envoi du message
        	System.out.println("====================================");
        	System.out.println("Debut de la partie "+i);
        	byte[] bufE = new String("JOUER").getBytes();
        	DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        	socket.send(dpE);
        	String envoi = new String(bufE, dpE.getOffset(), dpE.getLength());
        	System.out.println("Envoi d'un paquet UDP avec "+envoi);

        	// Attente de la reponse 
        	byte[] bufR = new byte[2048];
        	DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        	socket.receive(dpR);
        	String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        	System.out.println("Le serveur a repondu "+reponse);
        	
        	//Préparation du message à renvoyer au serveur
        	byte[] bufM = new String("PING").getBytes();
        	
        	if (reponse.equals("PING")) {
        		bufM = new String("PONG").getBytes();
        	}
      
        	//Envoi du message au serveur
        	DatagramPacket dpM = new DatagramPacket(bufM, bufM.length, adrDest);
        	socket.send(dpM);
        	String message = new String(bufM, dpM.getOffset(), dpM.getLength());
        	System.out.println("Envoi d'un paquet UDP avec "+message);
        	
        	// Attente de la reponse 
        	byte[] bufR2 = new byte[2048];
        	DatagramPacket dpR2 = new DatagramPacket(bufR2, bufR2.length);
        	socket.receive(dpR2);
        	String reponse2 = new String(bufR2, dpR2.getOffset(), dpR2.getLength());
        	System.out.println("Le serveur a repondu "+reponse2);
        	System.out.println("Fin de la partie "+i);
        	
        }

        // Fermeture de la socket
        socket.close();
        System.out.println("Partie terminee");
    }
}
