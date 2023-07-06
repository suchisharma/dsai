
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Semaphore;
class ProcessNode implements Runnable {
 private int processId;
 private Set<Integer> deferredSet;
 private Semaphore mutex;
 private boolean usingResource;
 public ProcessNode(int id) {
 processId = id;
 deferredSet = new HashSet<>();
 mutex = new Semaphore(1);
 usingResource = false;
 }
 private void executeCriticalSection() {
 System.out.println("Process " + processId + " is executing the critical section.");
 try {
 // Simulating critical section execution
 Thread.sleep(2000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 }
 private void nonCriticalSection() {
 System.out.println("Process " + processId + " is executing non-critical section.");
 }
 private void requestCriticalSection() {
 deferredSet.clear();
 usingResource = false;
 // Send request messages to all other processes
 for (int i = 0; i < ricartAgrawala.NUM_PROCESSES; i++) {
 if (i != processId) {
 Message requestMsg = new Message(processId);
 sendMessage(requestMsg, i);
 }
 }
 // Wait until all replies are received
 mutex.acquireUninterruptibly();
 while (deferredSet.size() < ricartAgrawala.NUM_PROCESSES - 1) {
 mutex.release();
 Thread.yield();
 mutex.acquireUninterruptibly();
 }
 usingResource = true;
 }
 private void releaseCriticalSection() {
 usingResource = false;
 // Send release messages to all deferred processes
 for (int deferredProcessId : deferredSet) {
 Message releaseMsg = new Message(processId);
 sendMessage(releaseMsg, deferredProcessId);
 }
 deferredSet.clear();
 mutex.release();
 }
 private void sendMessage(Message message, int receiverId) {
 // Simulating message passing
 try {
 Thread.sleep(500);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 // Process the received message
 receiveMessage(message, receiverId);
 }
 private void receiveMessage(Message message, int senderId) {
 if (message.getType() == MessageType.REQUEST) {
 if (usingResource || (mutex.availablePermits() == 0 && message.getTimestamp() < 
ricartAgrawala.getTimestamp())) {
 deferredSet.add(senderId);
 } else {
 Message replyMsg = new Message(processId, MessageType.REPLY);
 sendMessage(replyMsg, senderId);
 }
 } else if (message.getType() == MessageType.REPLY) {
 deferredSet.add(senderId);
 }
 }
 @Override
 public void run() {
 for (int i = 0; i < ricartAgrawala.NUM_ITERATIONS; i++) {
 nonCriticalSection();
 requestCriticalSection();
 executeCriticalSection();
 releaseCriticalSection();
 }
 }
}
class Message {
 private int senderId;
 private MessageType type;
 private long timestamp;
 public Message(int senderId) {
 this.senderId = senderId;
 this.type = MessageType.REQUEST;
 this.timestamp = ricartAgrawala.getTimestamp();
 }
 public Message(int senderId, MessageType type) {
 this.senderId = senderId;
 this.type = type;
 this.timestamp = ricartAgrawala.getTimestamp();
 }
 public int getSenderId() {
 return senderId;
 }
 public MessageType getType() {
 return type;
 }
 public long getTimestamp() {
 return timestamp;
 }
}
enum MessageType {
 REQUEST,
 REPLY
}
public class ricartAgrawala {
 public static int NUM_PROCESSES;
 public static int NUM_ITERATIONS;
 private static long[] timestamps;
 public static void setTimestamp(int processId, long timestamp) {
 timestamps[processId] = timestamp;
 }
 public static long getTimestamp() {
 return timestamps[getCurrentProcessId()];
 }
 private static int getCurrentProcessId() {
 return Integer.parseInt(Thread.currentThread().getName());
 }
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 System.out.print("Enter the number of processes: ");
 NUM_PROCESSES = scanner.nextInt();
 System.out.print("Enter the number of iterations: ");
 NUM_ITERATIONS = scanner.nextInt();
 Thread[] threads = new Thread[NUM_PROCESSES];
 timestamps = new long[NUM_PROCESSES];
 for (int i = 0; i < NUM_PROCESSES; i++) {
 threads[i] = new Thread(new ProcessNode(i));
 threads[i].setName(Integer.toString(i));
 threads[i].start();
 }
 for (int i = 0; i < NUM_PROCESSES; i++) {
 try {
 threads[i].join();
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 }
 }
}
