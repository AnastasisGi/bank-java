package bank.java;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PrinterTest {
    private static Clock clock1 = Clock.systemDefaultZone();
    private static Clock clock2 = Clock.systemDefaultZone();
    private static ZoneId zoneId = ZoneId.systemDefault();

    @Before
    public void setupTests() {
        String time1 = "2022-06-10 12:30";
        String time2 = "2022-06-11 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);

        clock1 = Clock.fixed(dateTime1.atZone(zoneId).toInstant(), zoneId);
        clock2 = Clock.fixed(dateTime2.atZone(zoneId).toInstant(), zoneId);
    }

    @Test
    public void canPrintAListOfTransactions() {
        Transaction testTransaction = new Transaction(1000, clock1);
        Transaction testTransaction2 = new Transaction(-500, clock2);
        ArrayList<Transaction> testData = new ArrayList<Transaction>(Arrays.asList(testTransaction, testTransaction2));
    
        Printer subject = new Printer();

        String output = "date || credit || debit || balance\n2022-06-10 || 1,000.00 || || 1,000.00\n2022-06-11 || || 500.00 || 500.00";
        assertEquals(output, subject.printStatement(testData));
    }
}
