package fr.esisar.ne.tdm2;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Television {
	
    public static void main(String[] args) throws Exception
    {
        Television t = new Television();
        t.execute();
    }
    
    private void execute() throws IOException{
    	
    	//
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 7050
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7050));
        
        //Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Television");
        frame.setSize(500,500);

        //Affichage de la fenetre en vert
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);   
        
        int stop = 0;
    	
    	while (stop == 0) {
    		
        	// Attente de la reponse 
        	byte[] bufR = new byte[2048];
        	DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        	socket.receive(dpR);
        	String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        	System.out.println("Le client a envoye "+reponse);
        	
        
    		if (reponse.equals("red\n")) {
    			//Affichage de la fenetre en rouge
    			frame.getContentPane().setBackground(Color.RED);
    			frame.setVisible(true);
    		}
    	
    		else if (reponse.equals("green\n")) {
    			//Affichage de la fenetre en vert
    			frame.getContentPane().setBackground(Color.GREEN);
    			frame.setVisible(true);
    		}
    	
    		else if (reponse.equals("end\n")) {
    			//Fermeture de la fenetre
    			frame.dispose();
    			stop = 1;

    		}
    	}
    	
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du serveur .");
    	
    	
    	


    }

}
