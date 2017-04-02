package main;

import interfaces.IPerson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Person implements IPerson {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;
    private final int height;

    public Person(int id, String firstName, String lastName, String dateOfBirth, int height) throws ParseException {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        this.dateOfBirth = format.parse(dateOfBirth);

        this.height = height;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
