package bank.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistoryItemTest {
    @Test
    public void canCreateTransactionHistoryItem() {
        String date = "2022-06-10 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        TransactionHistoryItem subject = new TransactionHistoryItem(dateTime, 1000, 0, 2000);
        assertEquals(dateTime, subject.getDate());
        assertEquals(1000, subject.getCredit(), 0);
        assertEquals(0, subject.getDebit(), 0);
        assertEquals(2000, subject.getBalance(), 0);
    }
}
