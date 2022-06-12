package bank.java;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class PrinterTest {
    @Test
    public void canPrintTransactionHistoryItem() {
        String date = "2022-06-10 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        Printer subject = new Printer();
        TransactionHistoryItem t = new TransactionHistoryItem(dateTime, 5000, 0, 5000);

        assertEquals(subject.printTransaction(t), "|| 2022-06-10    || 5000.00       ||               || 5000.00       \n");
    }

    @Test 
    public void canPrintTransactionHistory() {
        String date1 = "2022-06-10 12:30";
        String date2 = "2022-06-11 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);

        Printer subject = new Printer();
        TransactionHistoryItem t1 = new TransactionHistoryItem(dateTime1, 5000, 0, 5000);
        TransactionHistoryItem t2 = new TransactionHistoryItem(dateTime1, 5000, 0, 10000);
        TransactionHistoryItem t3 = new TransactionHistoryItem(dateTime2, 0, 3000, 7000);

        String expectedOutput = "|| 2022-06-10    || 5000.00       ||               || 5000.00       \n|| 2022-06-10    || 5000.00       ||               || 10000.00      \n|| 2022-06-11    ||               || 3000.00       || 7000.00       \n";

        assertEquals(expectedOutput, subject.printHistory(Arrays.asList(t1, t2, t3)));
    }
}
