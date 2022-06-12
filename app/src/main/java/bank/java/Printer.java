package bank.java;

import java.time.format.DateTimeFormatter;

public class Printer {
    private String printCurrency(float amount) {
        return String.format("%.2f", amount);
    }
    private String printColumn(String columnData) {
        return String.format("|| %-14s", columnData);
    }
    public String printTransaction(ITransaction transaction, float balance) {
        String dateCol = printColumn(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(transaction.getDate()));
        String creditCol = printColumn(transaction.getType() == TransactionType.WITHDRAWAL ? "" : printCurrency(transaction.getAmount()));
        String debitCol = printColumn(transaction.getType() == TransactionType.DEPOSIT ? "" : printCurrency(transaction.getAmount()));
        String balanceCol = printColumn(printCurrency(balance));
        return dateCol.concat(creditCol).concat(debitCol).concat(balanceCol);
    }
}
