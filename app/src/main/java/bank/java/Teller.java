package bank.java;

import java.util.Scanner;

public class Teller {
    private Scanner choiceScanner;
    private Account account;

    public Teller() {
        this.choiceScanner = new Scanner(System.in);
        this.account = new Account();
    }

    public void start() {
        System.out.println(
                "BANKBOT3000 Here.\n\nWhat would you like to do?\nEnter a choice between 1 and 4 and hit return\n\n1: Make deposit\n2: Make withdrawal\n3: Get statement\n4: Exit");

        int userChoice = this.choiceScanner.nextInt();
        
        if(userChoice == 1) {
            makeDeposit();
        }
        else if(userChoice == 2) {
            makeWithdrawal();
        }
        else if(userChoice == 3) {
            getStatement();
        }
        else if(userChoice == 4) {
            stop();
        }
        else {
            stop();
        }
    }

    public void makeDeposit() {
        System.out.println("How much would you like to deposit?\n");

        float amount = this.choiceScanner.nextFloat();

        Transaction t = new Transaction(amount, TransactionType.DEPOSIT);

        this.account.addTransaction(t);

        System.out.println("I'm all done with that deposit.\n");

        start();
    }

    public void makeWithdrawal() {
        System.out.println("How much would you like to withdraw?\n");

        float amount = this.choiceScanner.nextFloat();

        Transaction t = new Transaction(amount, TransactionType.WITHDRAWAL);

        this.account.addTransaction(t);

        System.out.println("I'm all done with that withdrawal.");
        
        start();
    }

    public void getStatement() {
        Printer p = new Printer();
        System.out.println(p.printHistory(this.account.getAccountHistory()));
        System.out.println("I'm all done with getting that statement.");
        start();
    }

    public void stop() {
        System.out.println("Goodbye!");
        choiceScanner.close();
    }
}
