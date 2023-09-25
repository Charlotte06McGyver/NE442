package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Programme qui joue avec le serveur addition en TCP
 * 
 */
public class AdditionTCP
{

    public static void main(String[] args) throws Exception
    {
        AdditionTCP joueurTCP = new AdditionTCP();
        joueurTCP.execute();                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //
        System.out.println("Debut de la partie ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("192.168.130.150", 7500);
        socket.connect(adrDest);        

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        int fin = 0;
        
        while(fin == 0) {
        	//Reception
        	InputStream is = socket.getInputStream();
        	int lenBufR = is.read(bufR);
        	if (lenBufR!=-1) {
        		
        		String reponse = new String(bufR, 0 , lenBufR );
        		System.out.println("Question: "+reponse);
        		//indicateurs de début et fin d'operande
        		int prem = 0;
        		int der = 0;
        		
        		while(!"+".equals(reponse.substring(der,der+1))) {
        			//System.out.println("Caractere lu : " + reponse.substring(der,der+1));
        			der++;
        		}       		
        		int operande1 = Integer.parseInt(reponse.substring(prem, der));
    			System.out.println("Operande 1 : " + reponse.substring(prem,der));
        		der ++;
        		prem = der;
        		while(!"=".equals(reponse.substring(der,der+1))) {
        			//System.out.println("Caractere lu : " + reponse.substring(der,der+1));
        			der++;
        		}        		
        		int operande2 = Integer.parseInt(reponse.substring(prem,der));
    			System.out.println("Operande 2 : " + reponse.substring(prem,der));
    			der ++;
            
        		//Preparation de la reponse
        		int result = operande1 + operande2;
        		byte[] bufM = new String(result +";").getBytes();
    			System.out.println(operande1 + " + " +operande2+"=" + result);
            
        		//Envoi de la reponse
        		OutputStream os = socket.getOutputStream();
        		os.write(bufM);
        	}
        	else {
        		fin = 1;
        	}
        }
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");

    }
}

