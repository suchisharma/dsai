// import java.util.*;
public class Lamport {
static int max1(int a, int b)
{
// Return the greatest of the two
if (a > b)
return a;
else
return b;
}
static void display(int e1, int e2, int p1[], int p2[])
{
int i;
System.out.println(" ");
for (i = 0; i < e1; i++) {
System.out.println("\n e1" + (i + 1)+" "+p1[i]);
}
for (i = 0; i < e2; i++)
System.out.println("\n e2" + (i + 1)+" "+p2[i]);
}
static void lamportLogicalClock(int e1, int e2,int m[][])
{
int i, j, k;
int p1[] = new int[e1];
int p2[] = new int[e2];
// Initialize p1[] and p2[]
for (i = 0; i < e1; i++)
p1[i] = i + 1;
for (i = 0; i < e2; i++)
p2[i] = i + 1;
for (i = 0; i < e2; i++)
System.out.print("\te2" + (i + 1));
for (i = 0; i < e1; i++) {
System.out.print("\n e1" + (i + 1) + "\t");
for (j = 0; j < e2; j++)
System.out.print(m[i][j] + "\t");
}
for (i = 0; i < e1; i++) {
for (j = 0; j < e2; j++) {
if (m[i][j] == 1) {
p2[j] = max1(p2[j], p1[i] + 1);
for (k = j + 1; k < e2; k++)
p2[k] = p2[k - 1] + 1;
}
if (m[i][j] == -1) {
p1[i] = max1(p1[i], p2[j] + 1);
for (k = i + 1; k < e1; k++)
p1[k] = p1[k - 1] + 1;
}
}
}
display(e1, e2, p1, p2);
}
public static void main(String args[])
{
int e1 = 4, e2 = 3;
int m[][] = new int[4][3];
m[0][0] = 0;
m[0][1] = 1;
m[0][2] = 1;
m[1][0] = 0;
m[1][1] = 0;
m[1][2] = 1;
m[2][0] = 0;
m[2][1] = -1;
m[2][2] = 1;
m[3][0] = -1;
m[3][1] = -1;
m[3][2] = 1;
lamportLogicalClock(e1, e2, m);
}
}

