package fr.esisar.ne.tdm2;

import java.awt.Color;
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
        
        //Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        
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
        
        // Le serveur se declare aupres de la couche transport
        // sur le port portSrc
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4002));
    	InetSocketAddress adrDest = new InetSocketAddress("localhost", 4001);
        
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
        
        

    }

}
