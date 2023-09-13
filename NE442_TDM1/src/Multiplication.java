import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Multiplication {
	
	public static void main(String[] args) throws Exception
    {
        Multiplication partie = new Multiplication();
        partie.execute();

    }
	
	private void execute() throws IOException{
        //Début de la partie
        System.out.println("Demarrage de la partie ...");

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 11000);
    	
        for (int i=1; i<11; i++) {
        	// Creation et envoi du message
        	System.out.println("====================================");
        	System.out.println("Debut de la partie "+i);
        	byte[] bufE = new String("JOUER").getBytes();
        	DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        	socket.send(dpE);
        	String envoi = new String(bufE, dpE.getOffset(), dpE.getLength());
        	System.out.println("Envoi d'un paquet UDP avec "+envoi);
        	
        	// Attente des 2 reponses 
        	byte[] bufR1 = new byte[2048]; //reponse 1
        	DatagramPacket dpR1 = new DatagramPacket(bufR1, bufR1.length);
        	socket.receive(dpR1);
        	String operande1 = new String(bufR1, dpR1.getOffset(), dpR1.getLength());
        	int x1 = Integer.parseInt(operande1.substring(0,1));
        	byte[] bufR2 = new byte[2048]; //reponse 2
        	DatagramPacket dpR2 = new DatagramPacket(bufR2, bufR2.length);
        	socket.receive(dpR2);
        	String operande2 = new String(bufR2, dpR2.getOffset(), dpR2.getLength());
        	int x2 = Integer.parseInt(operande2.substring(0,1)); //car il y a 2 messages envoyés
        	System.out.println("Le serveur a repondu "+x1+" et "+x2);
        	
        	//Préparation de la réponse
        	int result = x1 * x2;
        	byte[] bufM = new String(result+";").getBytes();
        	//Envoi du message au serveur
        	DatagramPacket dpM = new DatagramPacket(bufM, bufM.length, adrDest);
        	socket.send(dpM);
        	String message = new String(bufM, dpM.getOffset(), dpM.getLength());
        	System.out.println("Envoi d'un paquet UDP avec "+message);
        	
        	// Attente de la reponse du gain
        	byte[] bufG = new byte[2048];
        	DatagramPacket dpG = new DatagramPacket(bufG, bufG.length);
        	socket.receive(dpG);
        	String reponse2 = new String(bufG, dpG.getOffset(), dpG.getLength());
        	System.out.println("Le serveur a repondu "+reponse2);
        	System.out.println("Fin de la partie "+i);
        }
	}
}
