
import java.util.concurrent.Semaphore;
class SweetCounter {
private final Semaphore semaphore = new Semaphore(0);
public void prepareSweetBox() throws InterruptedException {
System.out.println("Chef preparing sweet box");
semaphore.acquire();
System.out.println("Chef has prepared the sweet box.");
}
public void pickSweetBox() throws InterruptedException {
System.out.println("Salesboy is waiting for a sweet box...");
semaphore.acquire();
System.out.println("Salesboy picked up a sweet box.");
semaphore.release();
}
public void putSweetBoxOnCounter() {
semaphore.release();
}
}
public class counter {
public static void main(String[] args) throws InterruptedException {
SweetCounter sweetCounter = new SweetCounter();
for(int i=0;i<5;i++){
Thread chefThread = new Thread(() -> {
try {
sweetCounter.prepareSweetBox();
} catch (InterruptedException e) {
e.printStackTrace();
}
});
Thread salesboyThread = new Thread(() -> {
try {
sweetCounter.pickSweetBox();
} catch (InterruptedException e) {
e.printStackTrace();
}
});
chefThread.start();
Thread.sleep(2000);
salesboyThread.start();
Thread.sleep(2000);
sweetCounter.putSweetBoxOnCounter();
Thread.sleep(2000);
sweetCounter.putSweetBoxOnCounter();
Thread.sleep(2000);
sweetCounter.putSweetBoxOnCounter();
}
}
}
