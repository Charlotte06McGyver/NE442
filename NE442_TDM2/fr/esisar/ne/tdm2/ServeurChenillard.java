import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class ServeurChenillard {

   public static void main(String[] args) throws Exception{
     ServeurChenillard s = new ServeurChenillard();
     s.execute();
   }

  private void execute(String dernier) throws Exception {
    //
    System.out.println("Demarrage du serveur chenillard ...");
    
    // Le serveur se declare aupres de la couche transport
    // sur le port 3000
    DatagramSocket socket = new DatagramSocket(null);
    socket.bind(new InetSocketAddress(3000));

    byte[] bufR = new byte[2048];
    byte[] bufEr = new String("red").getBytes();
    byte[] bufEg = new String("green").getBytes();

    int [] PortTab;
    InetAddress [] AdresseTab;
    int dernier = 0;
    int indice = 0;

    while(dernier == 0){
      
      // Attente du premier message
      DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
      socket.receive(dpR);
      String message = new String(bufR, dpR.getOffset(), dpR.getLength());
      System.out.println("Message recu = "+message);
      
      PortTab[i] = dpR.getPort();
      AdresseTab[i] = dpR.getAddress();

      indice ++;

      if (message.equals("1")){
        dernier = 1;
      }
    }

    while(true){

      for(a=0; a<=indice; a++){
        
       // Emission d'un message en retour
       DatagramPacket dpEr = new DatagramPacket(bufEr, bufE.length, AdresseTab[a],PortTab[a]);
       socket.send(dpEr);
        System.out.println("Message envoye = "+dpEr);

        Thread.sleep(1000);

        // Emission d'un message en retour
       DatagramPacket dpEg = new DatagramPacket(bufEg, bufE.length, AdresseTab[a],PortTab[a]);
       socket.send(dpEg);
       System.out.println("Message envoye = "+dpEg);
      }
      
    }

      // Fermeture de la socket
      socket.close();
      System.out.println("Arret du serveur .");
    
  }

}
