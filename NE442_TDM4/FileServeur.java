import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;

public class FileServeur {
	
    public static void main(String[] args) throws Exception
    {
        FileServeur fs = new FileServeur();
        fs.execute();
    }

	private void execute() throws IOException{
		
		System.out.println("Demarrage du serveur");
		
		//Le serveur se déclare auprès de la couche transport
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(3000));
		
		//Creation du chemin du fichier
		String chemin_fichier = "/home/charlotte/TDM4_NE442/";
		
		//Attente connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();
        
        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        
        
        // Un client s'est connecte, le serveur lit le message envoye par le client (sans garantie de lire tout le message)
        byte[] bufR = new byte[2048];
        InputStream is = socketConnexion.getInputStream();
        int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
            chemin_fichier += message;
        }
        
        // Lecture du contenu du fichier serveur.txt
        FileInputStream fis = new FileInputStream(chemin_fichier);
        byte[] buf = new byte[10];

        OutputStream os = socketConnexion.getOutputStream();
        
        int len = fis.read(buf);
        while(len!=-1)
        {
            os.write(buf,0,len);
        	len = fis.read(buf);
        }
        
        System.out.println("Message envoye");
	}

}
