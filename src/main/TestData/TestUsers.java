package main.TestData;

import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.enums.UserType;

import java.util.ArrayList;
import java.util.Arrays;

public class TestUsers {
    public ArrayList<User> getTestUsers(User superAdmin) {

        return new ArrayList<>(Arrays.asList(
                //admin
                superAdmin,
                new UserBuilder("lime0101", "lemon@abc.com", "abc123")
                        .firstName("Lemon")
                        .lastName("Lime")
                        .address("69, Taman Good, 69420")
                        .buildUser(UserType.ADMIN),
                //owner
                new UserBuilder("owner3", "cardi@abc.com", "abc123")
                        .firstName("Cardi")
                        .lastName("C")
                        .address("8 Jln Hulu Batu 7 Hulu Ampang")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner4", "cola@abc.com", "abc123")
                        .firstName("Cola")
                        .lastName("Sprite")
                        .address("36 Tingkat 3 Persiaran 65C Off Jalan Pahang Barat")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner5", "selwyn@abc.com", "abc123")
                        .firstName("Selwyn")
                        .lastName("Ping")
                        .address("9 Jln Hulu Batu 10 Hulu Ampang")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner6", "affendi@abc.com", "abc123")
                        .firstName("Nafis")
                        .lastName("Affendi")
                        .address("37 Tingkat 4 Persiaran 65C Off Jalan Pahang Barat")
                        .buildUser(UserType.OWNER),
                //regular user
                new UserBuilder("milo1234", "pepsi@abc.com", "abc123")
                        .firstName("Pepsi")
                        .lastName("Milo")
                        .address("Jalan Pjs 11/20, Bandar Sunway,")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("mustelon", "abcg4@abc.com", "abc123")
                        .firstName("Elon")
                        .lastName("Must")
                        .address("11, Taman Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                new UserBuilder( "adam0101", "adam@abc.com", "abc123")
                        .firstName("Adam")
                        .lastName("Tan")
                        .address("11 Jalan Juru, Bukit Mertajam,")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("markkepen", "mark123@abc.com", "abc123")
                        .firstName("Mark")
                        .lastName("Kepen")
                        .address("55, Taman Very Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                new UserBuilder( "peterspark0101", "ironspider@abc.com", "abc123")
                        .firstName("Peter")
                        .lastName("Spark")
                        .address("22, Taman Very Very Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                //agent
                new UserBuilder("agent007", "agent007@abc.com", "abc123")
                        .firstName("Jamie")
                        .lastName("Bonds")
                        .address("19-01, Jln Padi Emas 3/1, Bahru")
                        .buildUser(UserType.AGENT),
                new UserBuilder("river123", "dog4life@abc.com", "abc123")
                        .firstName("Keanu")
                        .lastName("River")
                        .address("19-01, Jln Padi Silver 4/5")
                        .buildUser(UserType.AGENT),
                new UserBuilder("warrenbezos", "agent007@abc.com", "abc123")
                        .firstName("Jeff")
                        .lastName("Buffet")
                        .address("19-01, Jln Padi Emas 3/1, Bahru")
                        .buildUser(UserType.AGENT)

        ));
    }
}
