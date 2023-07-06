import java.util.Arrays;
import java.util.Scanner;
class LamportMutual {
 static int[] Global_queue = new int[6];
 static class Process implements Runnable {
 private int pno;
 Process(int pno) {
 this.pno = pno;
 }
 @Override
 public void run() {
 int[] Request_queue = new int[4];
 int[] Reply_queue = new int[4];
 int[] Release_queue = new int[4];
 int i, j;
 for (i = 0; i < 4; i++) {
 Request_queue[i] = Global_queue[i];
 }
 j = 0;
 for (i = 0; i < 4; i++) {
 if (Request_queue[i] != 0) {
 if (Request_queue[i] != pno) {
 Reply_queue[j] = Request_queue[i];
 j = j + 1;
 }
 }
 }
 while (Request_queue[0] != pno) {
 for (i = 0; i < 4; i++) {
 Request_queue[i] = Global_queue[i];
 }
 }
 System.out.println("\n\tProcess[" + pno + "] executes its CS");
 j = 0;
 for (i = 0; i < 4; i++) {
 if (i != pno) {
 Release_queue[j] = i;
 j = j + 1;
 }
 }
 for (i = 0; i < 3; i++) {
 Global_queue[i] = Global_queue[i + 1];
 }
 Global_queue[i] = 0;
 System.out.println("\n\tProcess[" + pno + "] exits its CS");
 }
 }
 public static void main(String[] args) throws InterruptedException {
 Thread[] pro = new Thread[4];
 int i = 0;
 int[] a = {1, 2, 3, 4};
 int k = -1;
 int[] timestamp = new int[4];
 int min = 0;
 Scanner scanner = new Scanner(System.in);
 System.out.println("\n--------- Lamport Mutual Exclusion Algorithm --------");
 System.out.println("\n\tLet all the 4 processes want to enter their CS");
 System.out.println("\tTheir request will be fulfilled as per their Request timestamp: tsi\n");
 for (i = 0; i < 4; i++) {
 System.out.print("\n\tRequest Message Timestamp of Process[" + (i + 1) + "] = ");
 k = scanner.nextInt();
 timestamp[i] = k;
 }
 for (i = 0; i < 4; i++) {
 System.out.println("\n\tProcess[" + (i + 1) + "] Timestamp = " + timestamp[i]);
 }
 for (i = 0; i < 4; i++) {
 min = 0;
 for (k = 1; k < 4; k++) {
 if (timestamp[k] < timestamp[min])
 min = k;
 }
 timestamp[min] = 999;
 Global_queue[i] = min + 1;
 }
 System.out.print("\n\tGlobal queue of Processes: ");
 for (i = 0; i < 4; i++) {
 System.out.print(Global_queue[i] + "-");
 }
 for (i = 0; i < 4; i++) {
 pro[i] = new Thread(new Process(a[i]));
 pro[i].start();
 }
 for (i = 0; i < 4; i++) {
 pro[i].join();
 }
 }
}

