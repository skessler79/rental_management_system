package main.TestData;

import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.enums.UserType;

import java.util.ArrayList;
import java.util.Arrays;

public class TestPendingUsers {
    public ArrayList<User> getTestPendingUsers() {
        return new ArrayList<>(Arrays.asList(
                //admin
                new UserBuilder(  "aqel0101", "abc2@abc.com", "abc123")
                        .firstName("Aqel")
                        .lastName("Kumar")
                        .address("Jalan Permas 10, Bandar Baru Permas Jaya")
                        .buildUser(UserType.ADMIN),
                //owner
                new UserBuilder("ali0101", "ali@abc.com", "abc123")
                        .firstName("Ali")
                        .lastName("Tan")
                        .address("19-01, Jln Padi Emas 3/1, Taman Abc, Johor Bahru")
                        .buildUser(UserType.OWNER),
                new UserBuilder(  "ee0101", "ee@abc.com", "abc123")
                        .firstName("Ee")
                        .lastName("Ming")
                        .address(" Lorong Kota Permail 11, Taman kota Permai,")
                        .buildUser(UserType.OWNER),
                //agent
                new UserBuilder("shallow123", "johny@abc.com", "abc123")
                        .firstName("Johny")
                        .lastName("Shallow")
                        .address("273B Kampar Road")
                        .buildUser(UserType.AGENT),
                //regular
                new UserBuilder("abu0202", "abu@abc.com", "abc123")
                        .firstName("Abu")
                        .lastName("Lim")
                        .address("No. 11 Jln Puah Jaya Taman Setapak Indah Jaya")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("michael4321", "bilebile@abc.com", "abc123")
                        .firstName("Michael")
                        .lastName("Nickson")
                        .address("2 Jln Siu Nam, Johor Bahru")
                        .buildUser(UserType.REGULAR)

        ));
    }
}
