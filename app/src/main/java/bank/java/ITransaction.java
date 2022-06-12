package bank.java;

import java.time.LocalDateTime;

public interface ITransaction {
    public float getAmount();

    public LocalDateTime getDate();

    public TransactionType getType();
}
