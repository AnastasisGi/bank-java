package bank.java;

import java.time.LocalDateTime;

public class TransactionHistoryItem {
    private float balance;
    private float credit;
    private float debit;
    private LocalDateTime date;

    public TransactionHistoryItem(LocalDateTime date, float credit, float debit, float balance) {
        this.balance = balance;
        this.credit = credit;
        this.debit = debit;
        this.date = date;
    }

    public float getBalance() {
        return this.balance;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public float getCredit() {
        return this.credit;
    }

    public float getDebit() {
        return this.debit;
    }
}
