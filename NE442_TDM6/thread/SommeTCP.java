package thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * Calcul de la somme des montants de chaque port en TCP
 * Utilisation d'un thread par port
 */
public class SommeTCP extends Thread{

	int port;
	int montant;
	
	public SommeTCP(int port) {
		this.port = port;
	}

	
	public void run() {
		try {
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execute() throws IOException{
		
        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port);
        socket.connect(adrDest);  
        
        // Envoi de la requete
        byte[] bufE = new String("COMBIEN").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        while (lenBufR!=-1)
        {
            String reponse = new String(bufR, 0 , lenBufR );
            
            if (reponse.contains("MONTANT=") & reponse.contains("EUROS")) {
                int indice_debut = reponse.indexOf('=') +1; 
                int indice_fin = reponse.indexOf('E');
                montant = Integer.parseInt(reponse.substring(indice_debut, indice_fin));
                //System.out.println("Indice debut "+indice_debut+"  Indice fin "+indice_fin+"  montant "+montant);
                socket.close();
            }

        }
        
	}
		
	
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	
    	int nbThread = 2001;
    	int montantMax = 0;
    	int portMax = 0;
    	long sommeMontant = 0;
    	//Creation d un tableau de threads
    	SommeTCP threadTab[] = new SommeTCP[nbThread];
    	
        long start = System.currentTimeMillis();
    	System.out.println("Debut de la recherche...");

    	for (int i = 0; i<nbThread; i++) {
    		threadTab[i] = new SommeTCP(21000 + i);
    	}

    	for (int i = 0; i<nbThread; i++) {	
    		threadTab[i].start();	
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		threadTab[i].join();
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		
    		if (threadTab[i].montant > montantMax) {
    			montantMax = threadTab[i].montant;
    			portMax = threadTab[i].port;    	
    		}
    		
    		sommeMontant += threadTab[i].montant;
    	}
    	
    	System.out.println("Le montant maximum est "+montantMax+" euros");
    	System.out.println("Le port d'écoute correspondant à ce maximum est "+portMax);
    	System.out.println("La somme des montants retournée par tous les ports est "+sommeMontant);
    	System.out.println("Fin du programme.");
    	
		//Affichage du temps du calcul
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");   	
    	
    }



}

