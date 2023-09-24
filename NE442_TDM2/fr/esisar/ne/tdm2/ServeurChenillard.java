import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JFrame;

public class ServeurChenillard {

   public static void main(String[] args) throws Exception{
     ServeurChenillard s = new ServeurChenillard();
     s.execute();
   }

  private void execute() throws IOException, InterruptedException {
    //
    System.out.println("Demarrage du serveur chenillard ...");
    
    // Le serveur se declare aupres de la couche transport
    // sur le port 3000
    DatagramSocket socket = new DatagramSocket(null);
    socket.bind(new InetSocketAddress(3000));

    byte[] bufR = new byte[2048];
    byte[] bufEr = new String("red").getBytes();
    byte[] bufEg = new String("green").getBytes();
    
    ArrayList<Integer> PortTab = new ArrayList<>();
    //ArrayList<InetAddress> AdresseTab = new ArrayList<>();
    int dernier = 0;
    int indice = 0;

    while(dernier == 0){
      
      // Attente du premier message
      DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
      socket.receive(dpR);
      String message = new String(bufR, dpR.getOffset(), dpR.getLength());
      System.out.println("Message recu = "+message);
      
      PortTab.add(dpR.getPort());
      //AdresseTab.add(dpR.getAddress());

      indice ++;

      if (message.equals("1")){
        dernier = 1;
      }
    }
    
    //conversion de l'adresse localhost en inetadresse
    InetAddress ipAddress = InetAddress.getByName("127.0.0.1");

    while(true){

    	for(Integer port : PortTab) {
    		// Emission d'un message en retour
    		DatagramPacket dpEr = new DatagramPacket(bufEr, bufEr.length, ipAddress,port);
    		socket.send(dpEr);
    		System.out.println("Message envoye = "+dpEr);

    		Thread.sleep(1000);

    		// Emission d'un message en retour
    		DatagramPacket dpEg = new DatagramPacket(bufEg, bufEg.length, ipAddress,port);
    		socket.send(dpEg);
    		System.out.println("Message envoye = "+dpEg);
 
    	}  
    }
    
  }

}
