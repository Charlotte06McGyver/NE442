import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.File;
import java.io.FileOutputStream;


/**
 * Client basique TCP
 * 
 */
public class FileClient
{

    public static void main(String[] args) throws Exception
    {
        FileClient fc = new FileClient();
        //Sécurité au niveau du nombre d'arguments
        if(args.length!= 1) {
            System.out.println("Usage: <file>");
        }else {
            fc.execute(args[0]);
        }                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute(String file) throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();
        
        //Creation chemin du fichier
        String chemin_fichier = "/home/charlotte/Documents/client.txt";
        
        //Création taille du fichier client.txt
        long SizeClient;
        File file_client = new File(chemin_fichier);

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 3000);
        socket.connect(adrDest);  
        
        // Envoi de la requete (variante exercice 6)
        byte[] bufE = file.getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Fichier demandé : "+file);
        
        long start = System.currentTimeMillis();

        // Reception du fichier du serveur
        byte[] bufR = new byte[2048];
        FileOutputStream fos = new FileOutputStream(chemin_fichier);
        System.out.println("Début écriture du fichier");

        InputStream is = socket.getInputStream();
        
        //Le client récupère la taille finale du fichier demandé
        int len = is.read(bufR);
        String taille = new String(bufR, 0, len);
        long FileSize = Long.parseLong(taille);
        System.out.println("Taille fichier serveur : "+FileSize);
        
        int lenBufR = is.read(bufR);
        while (lenBufR!=-1) {
        	//Ecriture dans le fichier client.txt
        	fos.write(bufR,0,lenBufR);
            lenBufR = is.read(bufR);
            
            //Progression du téléversement dans le fichier client.txt
            SizeClient = file_client.length();
            System.out.println("Progression: " + ((SizeClient*100.0f)/FileSize)+ "%");
            
        }

        
        fos.close();
        
        //Temps mis pour le transfert des fichiers
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");
    	
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
