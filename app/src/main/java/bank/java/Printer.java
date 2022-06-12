package bank.java;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class Printer {
   

    private String printCurrency(float amount) {
        if(amount == 0) {
            return "";
        }

        return String.format("%.2f", amount);
    }

    private String printColumn(String columnData) {
        return String.format("|| %-14s", columnData);
    }

    public String printTransaction(TransactionHistoryItem transaction) {
        String dateCol = printColumn(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(transaction.getDate()));
        String creditCol = printColumn(printCurrency(transaction.getCredit()));
        String debitCol = printColumn(printCurrency(transaction.getDebit()));
        String balanceCol = printColumn(printCurrency(transaction.getBalance()));
        return dateCol.concat(creditCol).concat(debitCol).concat(balanceCol).concat("\n");
    }

    public String printHistory(List<TransactionHistoryItem> transactions) {

        // transactions
        // .stream()
        // .sorted(sortByTransactionDateAscending)
        // .reduce(0.0f, (balance, t) -> {
        //     float newBalance = balance + t.getCredit() - t.getDebit();

        //     output.append(printTransaction(t));

        //     return newBalance;
        // }, Float::sum);

        return transactions.stream().map(t -> printTransaction(t)).reduce("", String::concat);
    }
}
