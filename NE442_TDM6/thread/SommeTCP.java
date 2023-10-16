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

	private void execute() throws IOException{
		
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
        String reponse = "";
        while (lenBufR!=-1) //marqueur de fin de la socket
        {
            reponse = reponse + new String(bufR, 0 , lenBufR );
            lenBufR = is.read(bufR);
        }
        
        //Recupere le montant indique dans le message du serveur
        int indice_debut = reponse.indexOf('=') +1; 
        int indice_fin = reponse.indexOf('E');
        montant = Integer.parseInt(reponse.substring(indice_debut, indice_fin));
        socket.close();
      
        
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
    		threadTab[i] = new SommeTCP(21000 + i); //Creation de tous les threads
    	}

    	for (int i = 0; i<nbThread; i++) {	
    		threadTab[i].start();	
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		threadTab[i].join();
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		
    		//Calcul du montant maximum et du port associe
    		if (threadTab[i].montant > montantMax) {
    			montantMax = threadTab[i].montant;
    			portMax = threadTab[i].port;    	
    		}
    		//Calcul de la somme de tous les montants
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

