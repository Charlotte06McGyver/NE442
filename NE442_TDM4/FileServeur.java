import java.io.FileInputStream;
import java.io.IOException;
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
		
		//Attente connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();
        
        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        
        // Lecture du contenu du fichier serveur.txt
        FileInputStream fis = new FileInputStream("/home/userir/file_serveur.txt");
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
