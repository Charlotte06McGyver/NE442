import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class ChenillardUDP {
	
    public static void main(String[] args) throws Exception
    {
        ChenillardUDP ch = new ChenillardUDP();
        ch.execute(args[0], args[1], args[2]);
    }
    
    private void execute(String portSrc, String portDest, String color) throws Exception {
    	//
        System.out.println("Demarrage du chenillard ...");
        int Port_src= Integer.parseInt(portSrc);
        int Port_dest = Integer.parseInt(portDest);
	    
        // Le serveur se declare aupres de la couche transport
        // sur le port portSrc
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(Port_src));
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", Port_dest);

    	byte[] bufE = new String("red").getBytes();
    	byte[] bufR = new byte[2048];

	    
        //Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);

	while (true){
		
		if (color.equals("red")) {
        		//Affichage de la fenetre en rouge
        		frame.getContentPane().setBackground(Color.RED);
        		frame.setVisible(true); 
			Thread.sleep(1000);

			// Creation et envoi du message
    			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
    			socket.send(dpE);
    			String envoi = new String(bufE, dpE.getOffset(), dpE.getLength());
    			System.out.println("Envoi d'un paquet UDP avec "+envoi);

			//Passage au vert
        		frame.getContentPane().setBackground(Color.GREEN);
        		frame.setVisible(true);

			//Attente de la réponse
			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    			socket.receive(dpR);
    			String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
    			System.out.println("Le serveur a repondu "+reponse);				
        	}
        
        	else if (color.equals("green")) {
			
        		//Affichage de la fenetre en vert
        		frame.getContentPane().setBackground(Color.GREEN);
        		frame.setVisible(true); 

			// Attente de la reponse 
    			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    			socket.receive(dpR);
    			String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
    			System.out.println("Le serveur a repondu "+reponse);

			//Affichage de la fenetre en rouge
        		frame.getContentPane().setBackground(Color.RED);
        		frame.setVisible(true); 
			Thread.sleep(1000);

			// Creation et envoi du message
    			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
    			socket.send(dpE);
    			String envoi = new String(bufE, dpE.getOffset(), dpE.getLength());
    			System.out.println("Envoi d'un paquet UDP avec "+envoi);
			
        	}
	}

}

