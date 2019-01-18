
 class Job implements Runnable{
	
	 synchronized public void run() {
		
		for(int i=0;i<5;i++) {
			
			System.out.println(Thread.currentThread().getName() + " i is "+ i);
			
		}
		
	}
	
}

public class MultiThreadDemo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Job job= new Job();
		Thread worker1 = new Thread(job,"thread1");
		Thread worker2= new Thread(job, "thread2");
		worker1.setPriority(Thread.MIN_PRIORITY);
		worker1.start();
		worker2.start();

	}

}
