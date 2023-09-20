package fr.esisar.ne.tdm2;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class PingPongColor1 {
	
    public static void main(String[] args) throws Exception
    {
        PingPongColor1 p1 = new PingPongColor1();
        p1.execute();
    }

    private void execute() throws Exception {
    	//
        System.out.println("Demarrage du programme 1 ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 4001
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4001));
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 4002);
        
    	//Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Ping");
        frame.setSize(400,400);
    	//Affichage de la fenetre en rouge
    	frame.getContentPane().setBackground(Color.RED);
    	frame.setVisible(true); 
    	
    	boolean end = true;
        
        while (end) {
 
        	Thread.sleep(1000); //attendre un moment
        	//Affichage de la fenetre en vert
        	frame.getContentPane().setBackground(Color.GREEN);
        	frame.setVisible(true);  
        
        	// Creation et envoi du message
        	byte[] bufE = new String("red").getBytes();
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
    	
        	if (reponse.equals("red")) {
        		frame.getContentPane().setBackground(Color.RED);
        		frame.setVisible(true);
        	}    
        
    	}
        
        //Fermeture de la fenetre
        frame.dispose();
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du serveur .");
        
    }
}
