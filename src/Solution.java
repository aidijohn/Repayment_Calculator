import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Solution {

    public static void calculateLoanRepayment(double loanAmount, int loanTermInMonths, double annualInterestRate,
                                              String repaymentFrequency) {
        // converting annual interest rate to monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12.0;

        // Here we calculate the repayment amount based on repayment frequency
        double repaymentAmount = 0.0;
        int numberOfRepayments = 0;

        switch (repaymentFrequency.toLowerCase()) {
            case "monthly":
                repaymentAmount = loanAmount * monthlyInterestRate /
                        (1 - Math.pow(1 + monthlyInterestRate, -loanTermInMonths));
                numberOfRepayments = loanTermInMonths;
                break;
            case "bi-monthly":
                repaymentAmount = loanAmount * monthlyInterestRate /
                        (1 - Math.pow(1 + monthlyInterestRate / 2, -loanTermInMonths * 2));
                numberOfRepayments = loanTermInMonths * 2;
                break;
            case "weekly":
                repaymentAmount = loanAmount * monthlyInterestRate /
                        (1 - Math.pow(1 + monthlyInterestRate / 52, -loanTermInMonths * 52));
                numberOfRepayments = loanTermInMonths * 52;
                break;
            default:
                System.out.println("Invalid repayment frequency. Please enter monthly, bi-monthly, or weekly.");
                return;
        }

        // calculate total interest to be paid
        double totalInterest = 0.0;
        double remainingBalance = loanAmount;
        System.out.println("Month\tPrincipal\tInterest\tRepayment\tBalance");
        for (int i = 1; i <= numberOfRepayments; i++) {
            double interest = remainingBalance * monthlyInterestRate;
            double principal = repaymentAmount - interest;
            totalInterest += interest;
            remainingBalance -= principal;
            System.out.println(i + "\t" +
                    formatCurrency(principal) + "\t\t" +
                    formatCurrency(interest) + "\t\t" +
                    formatCurrency(repaymentAmount) + "\t" +
                    formatCurrency(remainingBalance));
        }

        // calculate total amount to be repaid
        double totalAmount = loanAmount + totalInterest;

        // display results
        System.out.println("Total interest to be paid over the loan term: " + formatCurrency(totalInterest));
        System.out.println("Total amount to be repaid over the loan term: " + formatCurrency(totalAmount));
    }

    private static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        BigDecimal bd = BigDecimal.valueOf(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return currencyFormatter.format(bd);
    }

    public static void main(String[] args) {

        //Input parameters
        Solution.calculateLoanRepayment(10000.0, 2, 0.05, "monthly");

    }

}

