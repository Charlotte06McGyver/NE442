package tdm5;

public class MonoThread {


    public static void main(String[] args) throws Exception{
    	MonoThread mt = new MonoThread();
    	mt.execute(); 
    }
    
	
	
	private void execute() {
		
		long N = 2000000000;
		double res = 0;
		
		System.out.println("Demarrage du programme");
		
		long start = System.currentTimeMillis();
		
		for(long k=1; k<N; k++) {
			res = res + 1d/(k*k);
		}
		
		//Affichage résultat
		System.out.println("Resultat : "+res);
		
		//Affichage du temps du calcul
    	long stop = System.currentTimeMillis();
    	System.out.println("Elapsed Time = "+(stop-start)+" ms");
		
		
		
	}
}
