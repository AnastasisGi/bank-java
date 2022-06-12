package bank.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterTest {
    private class MockTransaction implements ITransaction {
        public float getAmount() {
            return 5000;
        }

        public LocalDateTime getDate() {
            return LocalDateTime.parse("2022-06-10 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        public TransactionType getType() {
            return TransactionType.DEPOSIT;
        }
    }

    @Test
    public void canPrintTransaction() {
        Printer subject = new Printer();
        MockTransaction t = new MockTransaction();

        assertEquals(subject.printTransaction(t, 5000), "|| 2022-06-10    || 5000.00       ||               || 5000.00       ");
    }
}
