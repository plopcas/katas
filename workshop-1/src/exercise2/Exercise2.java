package exercise2;

import java.util.*;

public class Exercise2
{
    static String isFunny(String s)
    {
        // Complete this function
        return null;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++)
        {
            String s = in.next();
            String result = isFunny(s);
            System.out.println(result);
        }
        in.close();
    }
}