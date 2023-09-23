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
            System.out.println("Usage: <port_src> <port_dest> <start>");
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
    	
    	
    	
    }
	
}
