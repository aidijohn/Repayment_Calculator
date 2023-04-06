
public class TestCases {

    public static void main(String[] args) {

        // Test case 1: Monthly repayments
        System.out.println("Test case 1: Monthly repayments");
        Solution.calculateLoanRepayment(5000, 6, 8.5, "monthly");

        // Test case 2: Bi-monthly repayments
        System.out.println("\nTest case 2: Bi-monthly repayments");
        Solution.calculateLoanRepayment(10000, 5, 7.2, "bi-monthly");

        // Test case 3: Weekly repayments
        System.out.println("\nTest case 2: Weekly repayments");
        Solution.calculateLoanRepayment(1000, 1, 7.2, "weekly");
    }
}