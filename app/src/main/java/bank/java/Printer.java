package bank.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.lang.StringBuilder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Printer {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String printHeader() {
        return "date || credit || debit || balance\n";
    }

    private String formatCurrency(float amount) {
       return String.format("%,.2f", amount);
    }

    private String printLine(LocalDateTime date, float amount, float balance) {
        String creditDebit = amount > 0 ? formatCurrency(amount) + "|| " : "|| " + formatCurrency(amount);
        return date.format(formatter) + "|| " + creditDebit + "|| " + formatCurrency(balance);
    }

    private ArrayList<Transaction> sortTransactionsByDateAscending(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> sorted = new ArrayList<Transaction>(transactions);
        
        Collections.sort(sorted, new Comparator<Transaction>(){
            public int compare(Transaction o1, Transaction o2){
                Instant d1 = o1.getDate().toInstant(ZoneOffset.UTC);
                Instant d2 = o2.getDate().toInstant(ZoneOffset.UTC);
                if(d1 == d2)
                    return 0;
                return d1.isBefore(d2) ? -1 : 1;
            }
       });

       return sorted;
    }

    public String printStatement(ArrayList<Transaction> transactions) {
        StringBuilder output = new StringBuilder();

        output.append(printHeader());

        float balance = 0;
        ArrayList<Transaction> sortedTransactions = sortTransactionsByDateAscending(transactions);
        
        sortedTransactions
            .forEach((t) -> {
                output.append(printLine(t.getDate(), t.getAmount(), balance + t.getAmount()));
            });
        
        return output.toString();
    }
}
