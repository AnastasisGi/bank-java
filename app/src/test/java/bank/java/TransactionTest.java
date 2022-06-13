package bank.java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TransactionTest {
    private static Clock clock = Clock.systemDefaultZone();
    private static ZoneId zoneId = ZoneId.systemDefault();

    @Before
    public void setupTests() {
        String str = "2022-06-10 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        clock = Clock.fixed(dateTime.atZone(zoneId).toInstant(), zoneId);
    }

    @Test
    public void canCreateTransaction() {
        Transaction subject = new Transaction(1000, TransactionType.DEPOSIT, clock);
        assertEquals(1000, subject.getAmount(), 0);
        assertEquals(TransactionType.DEPOSIT, subject.getType());
        assertEquals(LocalDateTime.now(clock), subject.getDate());
    }
}
