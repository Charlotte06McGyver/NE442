import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	
    public static void main(String[] args) throws Exception
    {
        FileCopy fc = new FileCopy();
        fc.execute();
    }
    
    private void execute() throws IOException{
    	long start = System.currentTimeMillis();
    	
    	
        System.out.println("Début lecture du fichier 1");
        
        FileInputStream fis = new FileInputStream("/home/userir/file1.txt");
        FileOutputStream fos = new FileOutputStream("/home/userir/file2.txt");
        
        byte[] buf = new byte[1000000];
        
        System.out.println("Début ecriture du fichier 2");
        
        int len = fis.read(buf);
  
        while(len!=-1)
        {
            fos.write(buf,0,len);
            len = fis.read(buf);   

        }
        
        
        // Fermeture des fichiers
        fis.close();
        fos.close();
    	
        System.out.println("Fin écriture du fichier");
    	
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");
		
	}


}
