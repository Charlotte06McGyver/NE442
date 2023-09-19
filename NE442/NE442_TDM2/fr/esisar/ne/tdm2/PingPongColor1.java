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

    private void execute() throws IOException {
    	//
        System.out.println("Demarrage du programme 1 ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 4001
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4001));
        
      //Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Ping");
        frame.setSize(400,400);
        
        //Affichage de la fenetre en rouge
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);  
        Thread.sleep(1000); //attendre un moment
        //Affichage de la fenetre en vert
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);  
        
    }
}
