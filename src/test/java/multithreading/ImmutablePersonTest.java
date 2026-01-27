package multithreading;

import org.example.multithreading.immutability.Address;
import org.example.multithreading.immutability.Hobby;
import org.example.multithreading.immutability.ImmutablePerson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ImmutablePersonTest {
    @Test
    void testImmutabilityAndAliasing() {
        Date birthday = new Date(1000000000L);
        Hobby hobby = new Hobby("Chess", 5);
        Address address = new Address("Main St", 1);

        List<String> emails = new ArrayList<>();
        emails.add("alice@example.com");

        List<Date> dates = new ArrayList<>();
        dates.add(birthday);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(hobby);

        ImmutablePerson person = new ImmutablePerson(
                "Alice",
                30,
                emails,
                addresses,
                dates,
                hobbies
        );

        // --- TEST 1: original list changes don't affect ImmutablePerson ---
        emails.add("bob@example.com");
        dates.add(new Date());
        addresses.add(new Address("Second St", 2));
        hobbies.add(new Hobby("Football", 3));

        assertEquals(1, person.getEmails().size(), "Emails list should be immutable and not affected by external changes");
        assertEquals(1, person.getImportantDates().size(), "Dates list should be unaffected by external changes");
        assertEquals(1, person.addresses().size(), "Addresses list should be unaffected by external changes");
        assertEquals(1, person.getHobbies().size(), "Hobbies list should be unaffected by external changes");

        // --- TEST 2: person's lists' elements are immutable ---
        Date dateFromGetter = person.getImportantDates().getFirst();
        dateFromGetter.setTime(0);

        Hobby hobbyFromGetter = person.getHobbies().getFirst();
        hobbyFromGetter.setName("Hacking");
        hobbyFromGetter.setHoursWeekly(100);

        assertEquals(1000000000L, person.getImportantDates().getFirst().getTime(), "Date inside ImmutablePerson should not change");
        assertEquals("Chess", person.getHobbies().getFirst().getName(), "Hobby name should not change");
        assertEquals(5, person.getHobbies().getFirst().getHoursWeekly(), "Hobby hoursWeekly should not change");

        // --- TEST 3: aliasing
        assertNotSame(addresses.getFirst(), person.addresses().getFirst(), "Address objects should be different instances");
        assertNotSame(hobbies.getFirst(), person.getHobbies().getFirst(), "Hobbies objects should be different instances");
        assertNotSame(dates.getFirst(), person.getImportantDates().getFirst(), "ImportantDates objects should be different instances");
    }
}
