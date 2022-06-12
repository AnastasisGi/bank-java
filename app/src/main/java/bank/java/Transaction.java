package bank.java;

import java.time.LocalDateTime;
import java.time.Clock;

enum TransactionType {
    DEPOSIT,
    WITHDRAWAL
}

public class Transaction implements ITransaction {
    private float amount;
    private LocalDateTime date;
    private TransactionType type;

    public Transaction(float amount) {
        this.amount = amount;
        this.type = amount > 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAWAL;
        this.date = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public Transaction(float amount, Clock clock) {
        this.amount = amount;
        this.type = amount > 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAWAL;
        this.date = LocalDateTime.now(clock);
    }

    public float getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public TransactionType getType() {
        return this.type;
    }
}
