package tdm5;

public class SeqSimple extends Thread{
	
	int time;
	
	public SeqSimple(int time) {
		time = (int) (Math.random() * 5000); //temps d'attente aléatoire entre 0 et 5s
	}
	
	
	
    public void run() {
    	
    	System.out.println("Debut de la tache...");
    	
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Fin de la tache...");
    	
    }
	
	
	public static void main(String[] args) throws InterruptedException{
		   
		SeqSimple t1 = new SeqSimple();
		
	}
}
