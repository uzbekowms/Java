import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter: ");
        int n = scanner.nextInt(); // Number of pairs of parentheses
        // Output the number of correct parentheses expressions for given n
        System.out.println(countCorrectParentheses(n));
    }

    /**
     * Calculates the number of valid parentheses expressions
     * for a given number of pairs of parentheses (N open and N close).
     *
     * @param n Number of pairs of parentheses
     * @return Number of valid expressions
     */
    public static int countCorrectParentheses(int n) {
        // Base case: for 0 pairs of parentheses, there's exactly 1 valid expression (empty string)
        if (n == 0) return 1;
        // Array to store the results of subproblems
        int[] dp = new int[n + 1];
        dp[0] = 1; // One valid way for 0 pairs of parentheses

        // Fill the dp array for all counts from 1 to n
        for (int i = 1; i <= n; i++) {
            dp[i] = 0; // Initialize the current value to 0
            // Calculate dp[i] using the formula:
            // dp[i] = sum(dp[j] * dp[i - j - 1]) for all j from 0 to i - 1
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        // Return the result for n pairs of parentheses
        return dp[n];
    }
}
