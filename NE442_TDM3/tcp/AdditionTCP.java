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
        InetSocketAddress adrDest = new InetSocketAddress("localhost", 7500);
        socket.connect(adrDest);  
        
        // Buffer de la reponse 
        byte[] bufR = new byte[2048];
        
    	//Reception
    	InputStream is = socket.getInputStream();
    	int lenBufR = is.read(bufR);

        //Buffer du calcul incomplet
        String bufCalInc = new String("");
        //Buffer qui contiendra une seule question à traiter
        String calcul = new String();
        //Résultat du calcul
        int addition = 0;
        //Indice de fin de partie
        int fin = 0;
        
        while(fin == 0) {

        	if (lenBufR!=-1) {
        		
        		
        		String message = new String(bufR, 0 , lenBufR );
        		System.out.println("message recu : "+message);
        		
        		//On récupère la fin de l'ancienne question (s'il y en a) + nouvelle question
        		String question = bufCalInc + message;
        		
        		//On récupère des caractères tant que l'on n'a pas une question entière (avec un ?)
        		while(!question.contains("?")) {
        			lenBufR = is.read(bufR);
        			question += new String(bufR, 0 , lenBufR );
        		}
        		
        		//Si on a plusieurs equations dans la question, on les traite toutes sans avoir besoin 
        		//d'avoir un nouveau message du serveur
        		while (question.contains("?")){
        			
        			//on récupère le premier calcul à effectuer
            		calcul = question.substring(0, question.indexOf("?")+1);
            		//on récupère la fin de la question, qui contient peut-être des bouts incomplets
            		bufCalInc = question.substring(question.indexOf("?")+1);
            		//on enlève la question que l'on vient de traiter pour voir ensuite s'il y a une autre question complète à traiter
            		question = question.substring(question.indexOf("?")+1);
            		
                  		
            		System.out.println("Question: "+calcul);
            		
            		//On effectue le calcul grace àa la fonction codée ci après
            		addition = Calcul(calcul);
            		
            		byte[] bufM = new String(addition +";").getBytes();
        			
                
            		//Envoi de la reponse
            		OutputStream os = socket.getOutputStream();
            		os.write(bufM);
            		
        		}
        		
        		lenBufR = is.read(bufR);
        	}
        	else {
        		fin = 1;
        	}
        }
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");

    }
    
    public int Calcul(String message) {
    	//indicateurs de début et fin d'operande
		int prem = 0;
		int der = 0;
		
		while(!"+".equals(message.substring(der,der+1))) {
			//System.out.println("Caractere lu : " + reponse.substring(der,der+1));
			der++;
		}       		
		int operande1 = Integer.parseInt(message.substring(prem, der));
		System.out.println("Operande 1 : " + message.substring(prem,der));
		der ++;
		prem = der;
		while(!"=".equals(message.substring(der,der+1))) {
			//System.out.println("Caractere lu : " + reponse.substring(der,der+1));
			der++;
		}        		
		int operande2 = Integer.parseInt(message.substring(prem,der));
		System.out.println("Operande 2 : " + message.substring(prem,der));
		der ++;
    
		//Preparation de la reponse
		int result = operande1 + operande2;
		System.out.println(operande1 + " + " +operande2+"=" + result);
		return result;
    	
    }
}

