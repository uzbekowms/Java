import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        System.out.println(sumOfDigits(factorial(100)));
    }


    /**
     * Calculates the factorial of a number using BigInteger.
     * This is necessary since factorials grow extremely large,
     * exceeding the range of primitive data types like int or long.
     *
     * @param n The number to calculate the factorial of
     * @return The factorial of n as a BigInteger
     */
    public static BigInteger factorial(int n) {
        // Initialize result to 1 (as 0! = 1 and 1! = 1)
        BigInteger result = BigInteger.ONE;
        // Multiply result by every integer from 2 to n
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Calculates the sum of the digits in a given BigInteger.
     *
     * @param number The number whose digits need to be summed
     * @return The sum of the digits of the number
     */
    public static int sumOfDigits(BigInteger number) {
        // Convert the BigInteger to its string representation
        String digits = number.toString();
        int sum = 0;
        // Iterate over each character (digit) in the string
        for (char digit : digits.toCharArray()) {
            // Convert the character to its numeric value and add to the sum
            sum += digit - '0';
        }
        return sum;
    }
}
