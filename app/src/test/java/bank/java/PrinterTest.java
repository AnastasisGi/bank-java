package bank.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class PrinterTest {
    private class MockDeposit implements ITransaction {
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

    private class MockWithdrawal implements ITransaction {
        public float getAmount() {
            return 3000;
        }

        public LocalDateTime getDate() {
            return LocalDateTime.parse("2022-06-11 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        public TransactionType getType() {
            return TransactionType.WITHDRAWAL;
        }
    }

    @Test
    public void canPrintTransaction() {
        Printer subject = new Printer();
        MockDeposit t = new MockDeposit();

        assertEquals(subject.printTransaction(t, 5000), "|| 2022-06-10    || 5000.00       ||               || 5000.00       \n");
    }

    @Test 
    public void canPrintTransactionHistory() {
        Printer subject = new Printer();
        MockDeposit t1 = new MockDeposit();
        MockWithdrawal t2 = new MockWithdrawal();
        MockDeposit t3 = new MockDeposit();

        String expectedOutput = "|| 2022-06-10    || 5000.00       ||               || 5000.00       \n|| 2022-06-10    || 5000.00       ||               || 10000.00      \n|| 2022-06-11    ||               || 3000.00       || 7000.00       \n";

        assertEquals(expectedOutput, subject.printHistory(Arrays.asList(t1, t2, t3)));
    }
}
