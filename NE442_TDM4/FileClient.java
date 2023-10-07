import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
        FileOutputStream fos = new FileOutputStream("/home/userir/file_client.txt");
        System.out.println("Début écriture du fichier");

        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        while (lenBufR!=-1) {
        	fos.write(bufR,0,lenBufR);
            lenBufR = is.read(bufR);
        }

        
        fos.close();
        
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");
    	
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
