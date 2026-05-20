package entity;

import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact() {
    }

    public Contact(int id, String phoneNumber, String name) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact other = (Contact) obj;
        return Objects.equals(this.phoneNumber, other.phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Tên: %-18s | Số điện thoại: %s", id, name, phoneNumber);
    }
}
