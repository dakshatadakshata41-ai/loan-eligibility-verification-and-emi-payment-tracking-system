import java.util.Scanner;

class Loan {

    String name;
    double income;
    double loanAmount;
    double interestRate = 10;
    int tenure;
    double emi;
    double balance;

    public Loan(String name, double income,
                double loanAmount, int tenure) {

        this.name = name;
        this.income = income;
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.balance = loanAmount;

        calculateEMI();
    }

    public void calculateEMI() {

        double r = interestRate / (12 * 100);

        emi = (loanAmount * r * Math.pow(1 + r, tenure))
              / (Math.pow(1 + r, tenure) - 1);
    }
}

class LoanManager {

    Loan loan;
    Scanner sc = new Scanner(System.in);

    public void applyLoan() {

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Monthly Income: ");
        double income = sc.nextDouble();

        System.out.print("Enter Loan Amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter Tenure (months): ");
        int tenure = sc.nextInt();

        if (income < 20000) {
            System.out.println("Loan Rejected: Income too low!");
            return;
        }

        if (amount > income * 20) {
            System.out.println("Loan Rejected: Amount too high!");
            return;
        }

        loan = new Loan(name, income, amount, tenure);

        System.out.println("Loan Approved!");
        System.out.println("EMI: " + loan.emi);
    }

    public void payEMI() {

        if (loan == null) {
            System.out.println("No loan found!");
            return;
        }

        if (loan.balance <= 0) {
            System.out.println("Loan already paid!");
            return;
        }

        loan.balance -= loan.emi;

        if (loan.balance < 0) {
            loan.balance = 0;
        }

        System.out.println("EMI Paid: " + loan.emi);
        System.out.println("Remaining Balance: " + loan.balance);
    }

    public void viewDetails() {

        if (loan == null) {
            System.out.println("No loan details available!");
            return;
        }

        System.out.println("\n--- Loan Details ---");
        System.out.println("Name: " + loan.name);
        System.out.println("Loan Amount: " + loan.loanAmount);
        System.out.println("EMI: " + loan.emi);
        System.out.println("Remaining Balance: " + loan.balance);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LoanManager manager = new LoanManager();

        int choice;

        do {
            System.out.println("\n===== LOAN SYSTEM MENU =====");
            System.out.println("1. Apply Loan");
            System.out.println("2. Pay EMI");
            System.out.println("3. View Loan Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    manager.applyLoan();
                    break;

                case 2:
                    manager.payEMI();
                    break;

                case 3:
                    manager.viewDetails();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}