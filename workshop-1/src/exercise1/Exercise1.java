package exercise1;

import java.util.*;

public class Exercise1
{
    static int getSockPairs(int n, int[] c)
    {
        // Complete this function
        return -1;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++)
        {
            c[i] = in.nextInt();
        }
        int result = getSockPairs(n, c);
        System.out.println(result);
        in.close();
    }
}