package exercise3;

import java.util.*;

public class Exercise3
{
    static int getHeight(int n)
    {
        // Complete this function
        return -1;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++)
        {
            int n = in.nextInt();
            int result = getHeight(n);
            System.out.println(result);
        }
        in.close();
    }
}