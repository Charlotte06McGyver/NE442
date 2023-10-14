package tdm5;

public class SeqSimple extends Thread{
	
	int time;
	int numero;
	Thread[] dependance; //tableau renseignant les threads dont depend le thread concerné
	
	public SeqSimple(int numero, Thread ... dependance) {
		time = (int) (Math.random() * 5000);
		this.numero = numero;
		this.dependance = dependance;
	}
	
	
	
    public void run() {
    	
    	try {
    		if (dependance != null) {
    			for (int i=0; i<dependance.length; i++) {
    				dependance[i].join();
    			}
    		}
    		System.out.println("Le thread "+numero+" effectue sa tâche. Il attend "+time+" ms");
    		Thread.sleep(time);
    		System.out.println("Fin de tâche du thread " +numero);
    		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	
	public static void main(String[] args) throws InterruptedException{
		
		SeqSimple t1 = new SeqSimple(1);
		SeqSimple t2 = new SeqSimple(2, t1);
		SeqSimple t3 = new SeqSimple(3, t1);
		SeqSimple t4 = new SeqSimple(4, t1);
		SeqSimple t5 = new SeqSimple(5, t2, t3);
		SeqSimple t6 = new SeqSimple(6, t4);
		SeqSimple t7 = new SeqSimple(7, t5, t6);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		
		
	}
}
