package bank.java;

import java.time.LocalDateTime;
import java.time.Clock;

enum TransactionType {
    DEPOSIT,
    WITHDRAWAL
}

public class Transaction {
    private float amount;
    private LocalDateTime date;
    private TransactionType type;

    public Transaction(float amount, TransactionType type) {
        this(amount, type, Clock.systemDefaultZone());
    }

    public Transaction(float amount, TransactionType type, Clock clock) {
        if(amount < 0) {
            throw new RuntimeException("Amount must be positive");
        }

        this.amount = amount;
        this.type = type;
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
