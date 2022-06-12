package bank.java;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class Printer {
    private Comparator<ITransaction> sortByTransactionDateAscending = new Comparator<ITransaction>() {
        public int compare(ITransaction t1, ITransaction t2) {
            return t1.getDate().compareTo(t2.getDate());
        }
    };

    private String printCurrency(float amount) {
        return String.format("%.2f", amount);
    }

    private String printColumn(String columnData) {
        return String.format("|| %-14s", columnData);
    }

    public String printTransaction(ITransaction transaction, float balance) {
        String dateCol = printColumn(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(transaction.getDate()));
        String creditCol = printColumn(
                transaction.getType() == TransactionType.WITHDRAWAL ? "" : printCurrency(transaction.getAmount()));
        String debitCol = printColumn(
                transaction.getType() == TransactionType.DEPOSIT ? "" : printCurrency(transaction.getAmount()));
        String balanceCol = printColumn(printCurrency(balance));
        return dateCol.concat(creditCol).concat(debitCol).concat(balanceCol).concat("\n");
    }

    public String printHistory(List<ITransaction> transactions) {
        StringBuilder output = new StringBuilder();

        transactions
        .stream()
        .sorted(sortByTransactionDateAscending)
        .reduce(0.0f, (balance, t) -> {
            float newBalance = t.getType() == TransactionType.DEPOSIT ? balance + t.getAmount()
                    : balance - t.getAmount();

            output.append(printTransaction(t, newBalance));

            return newBalance;
        }, Float::sum);

        return output.toString();
    }
}
