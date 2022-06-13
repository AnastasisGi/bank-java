package bank.java;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class AccountTest {
    @Test
    public void canGetAccountHistory() {
        String date1 = "2022-06-10 12:30";
        String date2 = "2022-06-11 12:30";
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);

        Clock clock1 = Clock.fixed(dateTime1.atZone(zoneId).toInstant(), zoneId);
        Clock clock2 = Clock.fixed(dateTime2.atZone(zoneId).toInstant(), zoneId);

        Transaction t1 = new Transaction(1000, TransactionType.DEPOSIT, clock1);
        Transaction t2 = new Transaction(2000, TransactionType.DEPOSIT, clock1);
        Transaction t3 = new Transaction(1000, TransactionType.WITHDRAWAL, clock2);

        Account subject = new Account(new ArrayList<Transaction>(Arrays.asList(t1, t3, t2)));

        assertEquals(3, subject.getAccountHistory().size());
        assertEquals(dateTime1, subject.getAccountHistory().get(0).getDate());
        assertEquals(dateTime1, subject.getAccountHistory().get(1).getDate());
        assertEquals(dateTime2, subject.getAccountHistory().get(2).getDate());

        assertEquals(1000, subject.getAccountHistory().get(0).getCredit(), 0);
        assertEquals(2000, subject.getAccountHistory().get(1).getCredit(), 0);
        assertEquals(0, subject.getAccountHistory().get(2).getCredit(), 0);

        assertEquals(0, subject.getAccountHistory().get(0).getDebit(), 0);
        assertEquals(0, subject.getAccountHistory().get(1).getDebit(), 0);
        assertEquals(1000, subject.getAccountHistory().get(2).getDebit(), 0);

        assertEquals(1000, subject.getAccountHistory().get(0).getBalance(), 0);
        assertEquals(3000, subject.getAccountHistory().get(1).getBalance(), 0);
        assertEquals(2000, subject.getAccountHistory().get(2).getBalance(), 0);
    }

    @Test
    public void canAddATransaction() {
        String date1 = "2022-06-10 12:30";
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        Clock clock1 = Clock.fixed(dateTime1.atZone(zoneId).toInstant(), zoneId);

        Transaction t1 = new Transaction(1000, TransactionType.DEPOSIT, clock1);

        Account subject = new Account();

        subject.addTransaction(t1);

        assertEquals(1, subject.getAccountHistory().size());
        assertEquals(dateTime1, subject.getAccountHistory().get(0).getDate());
        assertEquals(1000, subject.getAccountHistory().get(0).getCredit(), 0);
        assertEquals(0, subject.getAccountHistory().get(0).getDebit(), 0);
        assertEquals(1000, subject.getAccountHistory().get(0).getBalance(), 0);
    }
}
