package kata;

public class FizzBuzz implements NumberCalculator
{
    private static final int FIVE = 5;
    private static final int THREE = 3;

    // Has a bug
    public String calculate(int number)
    {
        if (isDivisibleBy(number, THREE))
        {
            return "Fizz";
        }

        if (isDivisibleBy(number, FIVE))
        {
            return "Buzz";
        }

        if (isDivisibleBy(number, THREE) && isDivisibleBy(number, FIVE))
        {
            return "FizzBuzz";
        }

        return "" + number;
    }

    private boolean isDivisibleBy(int dividend, int divisor)
    {
        return dividend % divisor == 0;
    }
}
