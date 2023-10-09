package tdm5;


public class MultiThread extends Thread
{
    long start;
    long end;
    double res=0;


    public MultiThread(long start,long end)
    {
        this.start = start;
        this.end = end;
    }

    public void run()
    {
        for (long k = start; k <= end; k++)
        {
            res = res + (1d/(k*k));
        }   
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException
    {

    	long N = 2000000000;
    	int nbThread = 32;
    	double result = 0;
    	
    	//Creation d un tableau de threads
    	MultiThread threadTab[] = new MultiThread[nbThread];
    	
        long start = System.currentTimeMillis();
        System.out.println("Demarrage du calcul...");
    	
    	for (int i = 0; i<nbThread; i++) {
    		threadTab[i] = new MultiThread(((i*N/nbThread))+1, (((i+1)*N/nbThread)));
    	}

    	for (int i = 0; i<nbThread; i++) {	
    		threadTab[i].start();	
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		threadTab[i].join();
    	}
    	
    	for (int i = 0; i<nbThread; i++) {
    		result += threadTab[i].res;	
    	}
    	
        System.out.println("Resultat = "+result);
        
		//Affichage du temps du calcul
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");
    	
    	
    	
    	/*
        MultiThread t1 = new MultiThread(1, N/2);
        MultiThread t2 = new MultiThread(N/2, N);
        
        long start = System.currentTimeMillis();
        System.out.println("Demarrage du calcul...");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        double result = t1.res+t2.res;
        
        */


    }

}
