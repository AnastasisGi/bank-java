package bank.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;

public class Account {
     private Comparator<Transaction> sortByTransactionDateAscending = new Comparator<Transaction>() {
        public int compare(Transaction t1, Transaction t2) {
            return t1.getDate().compareTo(t2.getDate());
        }
    };

    private Collector<Transaction, ArrayList<TransactionHistoryItem>, ArrayList<TransactionHistoryItem>> transactionHistoryCollector = Collector.of(ArrayList::new, (list, transaction) -> {
        float credit = transaction.getType() == TransactionType.DEPOSIT ? transaction.getAmount() : 0;
        float debit = transaction.getType() == TransactionType.WITHDRAWAL ? transaction.getAmount() : 0;
        float balance = list.size() > 0 ? list.get(list.size() - 1).getBalance() + credit - debit : credit - debit;
        list.add(new TransactionHistoryItem(transaction.getDate(), credit, debit, balance));
    }, (list1, list2) -> {
        list1.addAll(list2);
        return list1;
    });

    private ArrayList<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public ArrayList<TransactionHistoryItem> getAccountHistory() {
        return this.transactions
        .stream()
        .sorted(sortByTransactionDateAscending)
        .collect(transactionHistoryCollector);
    }
     
}
