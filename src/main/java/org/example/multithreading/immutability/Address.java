package org.example.multithreading.immutability;

// Make the element immutable (recommended)
public final class Address {
    private final String street;
    private final int building;

    public Address(String street, int building) {
        this.street = street;
        this.building = building;
    }

    public Address(Address other) {
        this.street = other.street;
        this.building = other.building;
    }

    public final String getStreet() {
        return street;
    }

    public final int getBuilding() {
        return building;
    }
}
