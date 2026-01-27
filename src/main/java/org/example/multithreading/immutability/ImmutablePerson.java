package org.example.multithreading.immutability;

import java.util.Date;
import java.util.List;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> emails;
    private final List<Address> addresses;
    private final List<Date> importantDates;
    private final List<Hobby> hobbies;

    public ImmutablePerson(String name, int age, List<String> emails, List<Address> addresses, List<Date> importantDates, List<Hobby> hobbies) {
        this.name = name;
        this.age = age;
        // publicly immutable -> create unmodifiable list
        this.emails = List.copyOf(emails);
        // recommended consider immutable alternative, for this case Instant
        // publicly mutable -> deep copies in constructor and in getter, expensive
        this.importantDates = importantDates.stream().map(d -> new Date(d.getTime())).toList();
        // custom immutable class -> deep copies in constructor and in getter, expensive
        this.addresses = addresses.stream().map(Address::new).toList();
        // mutable class -> deep copies in constructor and in getter, expensive
        this.hobbies = hobbies.stream()
                .map(h -> new Hobby(h.name, h.hoursWeekly))
                .toList();
    }

    public final String getName() {
        return name;
    }

    public final int getAge() {
        return age;
    }

    public List<String> getEmails() {
        return emails;
    }

    public final List<Address> addresses() {
        return addresses.stream().map(Address::new).toList();
    }

    public final List<Date> getImportantDates() {
        return importantDates.stream()
                .map(d -> new Date(d.getTime()))
                .toList();
    }

    public List<Hobby> getHobbies() {
        return hobbies.stream()
                .map(h -> new Hobby(h.name, h.hoursWeekly))
                .toList();
    }
}

