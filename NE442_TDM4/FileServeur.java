import java.io.File;
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
		String chemin_fichier = "/home/charlotte/Documents/";
		
		//Attente connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();
        
        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        
        
        // Un client s'est connecte, le serveur lit le message envoye par le client (sans garantie de lire tout le message)
        //Le serveur récupère le nom du fichier à transmettre
        byte[] bufR = new byte[2048];
        InputStream is = socketConnexion.getInputStream();
        int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
            chemin_fichier += message;
        }
        
        // Lecture du contenu du fichier demandé
        FileInputStream fis = new FileInputStream(chemin_fichier);
        
        //Récupère la taille du fichier à transmettre
        File file = new File(chemin_fichier);
        long FileSize = file.length();
        
        //Création du buffer
        byte[] buf = new byte[2048];

        OutputStream os = socketConnexion.getOutputStream();
        
        //Envoi de la taille du fichier
        buf = new String(""+FileSize).getBytes();
        os.write(buf);
        System.out.println("Taille du fichier envoyée "+FileSize);
        
        //Transfert du fichier
        int len = fis.read(buf);
        while(len!=-1)
        {
            os.write(buf,0,len);
        	len = fis.read(buf);
        }
        
        System.out.println("Message envoye");
	}

}
