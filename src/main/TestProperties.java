package main;

import main.classes.Address;
import main.classes.properties.Property;
import main.classes.properties.PropertyBuilder;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.UserDataModel;

import java.util.ArrayList;
import java.util.Arrays;

public class TestProperties {
    private UserDataModel userDataModel;
    private ArrayList<Property> propertyObjectData;

    public ArrayList<Property> getTestProperties(){
        userDataModel = new UserDataModel();
        User ownerUser1 = userDataModel.loginUser("owner3", "abc123");
        User ownerUser2 = userDataModel.loginUser("owner4", "abc123");
        User ownerUser3 = userDataModel.loginUser("owner5", "abc123");
        User ownerUser4 = userDataModel.loginUser("owner6", "abc123");
        User agent1 = userDataModel.loginUser("agent007", "abc123");
        User agent2 = userDataModel.loginUser("river123", "abc123");
        User agent3 = userDataModel.loginUser("warrenbezos", "abc123");

        propertyObjectData = new ArrayList<>(Arrays.asList(
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Tamarind Suites, Cyberjaya", "Tamarind Suites")
                        .address(new Address("Persiaran Sepang", "Tamarind Suites", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(800)
                        .size("500 sqft")
                        .rentalRate(1.99)
                        .agent(agent1)
                        .description("A nice house with a good value and locate just on top of tamarind square")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(3)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Lakefront Residence, Cyberjaya", "Lakefront Residence")
                        .address(new Address("Persiaran Semarak Api", "Lakefront Residence", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1650)
                        .size("1010 sqft")
                        .rentalRate(1.19)
                        .agent(agent2)
                        .description("Nearby Amenities:\n" +
                                "- D'Pulze Shopping Mall / IOI City Mall\n" +
                                "- Putrajaya Hospital\n" +
                                "- Cyberjaya Hospital\n" +
                                "- Multimedia University (MMU)\n" +
                                "- LimKokWing University\n" +
                                "- University of Cyberjaya(Medical)\n" +
                                "- Cyberjaya Chinese School (SJKC Union)\n" +
                                "- Korean International School\n" +
                                "- King Henry VIII International School (from UK)\n" +
                                "- Elc International School\n" +
                                "\n" +
                                "Easy Access to:\n" +
                                "- XIAMEN University\n" +
                                "- Putrajaya\n" +
                                "- KLIA\n" +
                                "- KLIA 2\n" +
                                "- Kuala Lumpur\n" +
                                "- KLCC")
                        .roomInfo("1 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(3)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.WATER_HEATER)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Verdi Eco-Dominiums, Cyberjaya", "Verdi Eco-Dominiums")
                        .address(new Address("Persiaran Simfoni Off Persiaran Bestari", "Verdi Eco-Dominiums", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(3000)
                        .rentalRate(2.08)
                        .description("3 Bedrooms / 3 Bathrooms / 2 Carparks\n" +
                                "-1442 sqft\n" +
                                "-Rental : RM3, 200\n" +
                                "-Fully-Furnished\n" +
                                "-Smart Home System Provided.\n" +
                                "\n" +
                                "Another Brand New 1, 2 Bedrooms / 3+1 Bedrooms Units AVAILABLE with BEST PRICE in Verdi!\n" +
                                "-Low / High Floor\n" +
                                "-Partially Furnished / Fully Furnished\n" +
                                "1 Bedroom 700sqft : PF - FF RM1300 - RM2000\n" +
                                "2 Bedrooms 990sqft : PF - FF RM1600 - RM2500\n" +
                                "3 Bedrooms 1389sqft / 1442sqft / 1453sqft PF - FF RM2200 - RM3500\n" +
                                "Negotiable!")
                        .roomInfo("2 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(7)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.TV)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.CONDO, "Garden Plaza, Cyberjaya", "Garden Plaza")
                        .address(new Address("Persiaran Harmoni", "Garden Plaza", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .size("800 sqft")
                        .rentalFee(1100)
                        .rentalRate(2.44)
                        .agent(agent1)
                        .description("A nice house with a good value and close to the highway.")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(3)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser3, PropertyType.CONDO, "Verdi Eco-Dominiums, Cyberjaya", "Verdi Eco-Dominiums")
                        .address(new Address("Persiaran Simfoni Off Persiaran Bestari", "Verdi Eco-Dominiums", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1900)
                        .rentalRate(1.92)
                        .agent(agent3)
                        .description("2 Bedrooms / 2 Bathrooms / 2 Carparks\n" +
                                "-990sqft\n" +
                                "-Rental : RM1, 900\n" +
                                "-Fully-Furnished\n" +
                                "-Smart Home System Provided.\n" +
                                "\n" +
                                "Another Brand New 1, 2 Bedrooms / 3+1 Bedrooms Units AVAILABLE with BEST PRICE in Verdi!\n" +
                                "-Low / High Floor\n" +
                                "-Partially Furnished - Fully Furnished\n" +
                                "1 Bedroom 700sqft : PF- FF RM1300 - RM2000\n" +
                                "2 Bedrooms 990sqft : PF-FF RM1600 - RM2400\n" +
                                "3 Bedrooms 1389sqft / 1442sqft / 1453sqft PF-FF RM2200 - RM3300\n" +
                                "Negotiable!")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(4)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.CONDO, "Kenwingston Square Garden, Cyberjaya", "Kenwingston Square Garden")
                        .address(new Address("Persiaran Bestari", "Kenwingston Square Garden", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1100)
                        .rentalRate(1.99)
                        .agent(agent1)
                        .description("*BEST LONG TERM STAY FOR SMALL FAMILY AND EXPATRIATE!!!\n" +
                                "\n" +
                                "*BEST CHOICE FOR MALAYSIAN PROFESSIONAL AND BUSINESSMAN IN CYBERJAYA AND PUTRAJAYA!!!\n" +
                                "\n" +
                                "*JUST BRING YOUR CLOTHS TO MOVE IN!!!\n" +
                                "GRAB IT ASAP!!!\n")
                        .roomInfo("1 master room and 3 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.FRIDGE)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser3, PropertyType.CONDO, "Verdi Eco-Dominiums, Cyberjaya", "Verdi Eco-Dominiums")
                        .address(new Address("Persiaran Simfoni Off Persiaran Bestari", "Verdi Eco-Dominiums", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(950)
                        .size("900 sqft")
                        .rentalRate(2.05)
                        .agent(agent3)
                        .description("Property details:\n" +
                                "===========\n" +
                                "- Greenery view Studio unit comes with fully furnished\n" +
                                "- 1 queen Bedframe and mattress ( brand new)\n" +
                                "- 1 sofa set ( brand new)\n" +
                                "- 1 bar dining set ( brand new)\n" +
                                "- 1 tempered glass table\n" +
                                "- 1 TV comes with cabinet\n" +
                                "- 1 refrigerator\n" +
                                "- 1 Shoe rack ( brand new)\n" +
                                "- 1 air conditioner\n" +
                                "- Built-in kitchen cabinet with hob and hood\n" +
                                "- Ready to move in condition")
                        .roomInfo("1 master room and 3 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.CONDO, "Mutiara Ville, Cyberjaya", "Mutiara Villes")
                        .address(new Address("Persiaran Sepang Off Persiaran Multimedia", "Mutiara Ville", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1600)
                        .rentalRate(1.33)
                        .agent(agent2)
                        .description("[ SUITABLE FOR STUDENTS !! 8 SINGLE BEDS !! CAN SHARE !! ]\n" +
                                "-Tip top condition, ready to move in\n" +
                                "-Very spacious living space\n" +
                                "-Quiet and safe environment\n" +
                                "-Friendly neighborhood\n" +
                                "-Near to amenities\n" +
                                "-Very strategic and convenient location, easy access to major highways\n" +
                                "-Only @ RM1600 per month !!!\n" +
                                "-View to appreciate")
                        .roomInfo("1 master room and 3 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(4)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.TV, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.SINGLE_STORY, "D' Melor, Cyberjaya", "D' Melor")
                        .address(new Address("Persiaran Ceria", "D' Melor", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(2500)
                        .size("1400 sqft")
                        .rentalRate(1.83)
                        .agent(agent1)
                        .description("Set within a resort-styled, low-density condominium built around stunning lush landscaped gardens within the heart of the MSC city of Cyberjaya and on the doorstep to the Street Shopping Mall and the Multimedia University. Highly accessible with a mere 5 minutes drive to Putrajaya Station providing direct links to KLIA and KL Sentral as well as the Putrajaya Government Administration Complex and Kuala Lumpur via the KL-Putrajaya Highway.\n" +
                                "\n" +
                                "Located in an exclusive and resort-style living environment, this condo unit is tastefully furnished, comfortable and functional. It is situated on 2nd floor with pool view from all the bedrooms. Definitely a choice unit, viewing is strongly recommended.")
                        .roomInfo("1 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.TV, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WATER_HEATER)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.TOWNHOUSE, "Sawtelle, Cyberjaya", "Sawtelle")
                        .address(new Address("Lingkaran Cyber Point Barat", "Sawtelle", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1000)
                        .rentalRate(1.41)
                        .agent(agent1)
                        .description("Set within a resort-styled, low-density condominium built around stunning lush landscaped gardens within the heart of the MSC city of Cyberjaya and on the doorstep to the Street Shopping Mall and the Multimedia University. Highly accessible with a mere 5 minutes drive to Putrajaya Station providing direct links to KLIA and KL Sentral as well as the Putrajaya Government Administration Complex and Kuala Lumpur via the KL-Putrajaya Highway.\n" +
                                "\n" +
                                "Located in an exclusive and resort-style living environment, this condo unit is tastefully furnished, comfortable and functional. It is situated on 2nd floor with pool view from all the bedrooms. Definitely a choice unit, viewing is strongly recommended.")
                        .roomInfo("1 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.TV, FacilityType.FRIDGE, FacilityType.WATER_HEATER, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.TOWNHOUSE, "Domain 2 @ NeoCyber, Cyberjaya", "NeoCyber")
                        .address(new Address("Lingkaran Cyber Point Barat", "NeoCyber", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1000)
                        .rentalRate(222.5)
                        .agent(agent1)
                        .description("- 1 Room, 2 bathroom.\n" +
                                "- Renovated unit.\n" +
                                "- High floor.\n" +
                                "- Fully (NEW) furnitures included.\n" +
                                "- Kitchen cabinet (NEW)\n" +
                                "- Clean and tidy unit.\n" +
                                "- Suitable for small family or couple.\n" +
                                "- Convenient and walking distance to nearby amenities, shops and public transport.\n" +
                                "\n" +
                                "INTERESTED Kindly Call | Whatsapps | Text me:\n" +
                                "\n" +
                                "Candy\n" +
                                "012 - 345 6789")
                        .roomInfo("1 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.FRIDGE, FacilityType.WATER_HEATER, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.TOWNHOUSE, "The Domain (Domain 1) @ NeoCyber, Cyberjaya", "NeoCyber")
                        .address(new Address("Lingkaran Cyber Point Barat", "NeoCyber", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(700)
                        .size("1100 sqft")
                        .rentalRate(1.17)
                        .agent(agent1)
                        .description("[FOR RENT] Domain 1, Neocyber, Cyberjaya\n" +
                                "\n" +
                                "- 1 Room, 1 bathroom.\n" +
                                "- Renovated unit.\n" +
                                "- Fully furnished.\n" +
                                "- Clean and tidy unit.\n" +
                                "- Suitable for small family or couple.\n" +
                                "- Convenient and walking distance to nearby amenities, shops and public transport.\n" +
                                "\n" +
                                "INTERESTED Kindly Call | Whatsapps | Text me:\n" +
                                "\n" +
                                "Candy\n" +
                                "012 - 345 6789")
                        .roomInfo("1 master room and 4 bed room with 1 kitchen and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.FRIDGE, FacilityType.WATER_HEATER, FacilityType.AIRCOND, FacilityType.SWIMMING_POOL)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.TOWNHOUSE, "Solstice @ Pan'gaea", "Cyber 11")
                        .address(new Address("Persiaran Bestari", "Cyber 11", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(900)
                        .rentalRate(2.0)
                        .size("450 sqft")
                        .agent(agent3)
                        .description("Nice Designed 1 Bedroom Unit Ready To Move In")
                        .roomInfo("1 Bedroom+ 1 Bathroom FULLY FURNISHED")
                        .bathRoomCount(1)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.FRIDGE, FacilityType.WATER_HEATER, FacilityType.AIRCOND, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Cyberia Crescent 2", "OFF Persiaran Ceria")
                        .address(new Address("Jalan Fauna 1", "OFF Persiaran Ceria", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1450)
                        .rentalRate(1.38)
                        .size("1050 sqft")
                        .agent(agent2)
                        .description("Fully Furnished Condo Cyberia Crescent 2\n" +
                                "Cyberia Crescent 2 Condominium\n" +
                                "\n" +
                                "For Rent: RM1450 (nego)\n" +
                                "For Sell: RM315K\n" +
                                "\n" +
                                "Details:\n" +
                                "Freehold property\n" +
                                "2 covered car park lots (connected to elevator lift)\n" +
                                "Built-up size:1050sf")
                        .roomInfo("3 bedrooms (with aircond and ceiling fan), and 2 bathrooms (with water heater)")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.FRIDGE, FacilityType.WATER_HEATER, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.CONDO, "HYVE SOHO Suites Cyberjaya", "Cyber 6")
                        .address(new Address("HYVE SOHO Suites Jalan Impact", "Cyber 6", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1300)
                        .size("850 sqft")
                        .rentalRate(1.53)
                        .description("Master bedroom & 2nd rooms with\n" +
                                "* a)Wardrobe\n" +
                                "* b) Queen size Bed with Mattress\n" +
                                "* c) Curtains\n" +
                                "also got 2 car park")
                        .roomInfo("2 rooms 1 bathroom")
                        .bathRoomCount(1)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Cyberia SmartHomes", "Cyberia SmartHomes")
                        .address(new Address("Persiaran Multimedia", "Cyberia SmartHomes", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(900)
                        .rentalRate(0.90)
                        .size("900 sqft")
                        .agent(agent2)
                        .description("Best Rent Cyberia Smarthomes Condominium, The Arc,Mutiara Cyberjaya\n" +
                                "****************************************\n" +
                                "\n" +
                                "- Block A\n" +
                                "- Middle floor unit\n" +
                                "- 3R + 2B\n" +
                                "\n" +
                                "- ready to move in with newly painted house !\n" +
                                "- 1 covered parking available\n" +
                                "- 24 hour gated and guarded\n" +
                                "\n" +
                                "- Partially-furnished unit\n" +
                                "- type C, 1000 sqft\n" +
                                "- walking distance to MMU (Multimedia University)")
                        .roomInfo("1 master room, 2 rooms")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Cyberia SmartHomes", "Cyberia SmartHomes")
                        .address(new Address("Persiaran Multimedia", "Cyberia SmartHomes", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(900)
                        .rentalRate(0.90)
                        .size("1000 sqft")
                        .description("Best Rent Cyberia Smarthomes Condominium, The Arc,Mutiara Cyberjaya\n" +
                                "****************************************\n" +
                                "\n" +
                                "- Block A\n" +
                                "- Middle floor unit\n" +
                                "- 3R + 2B\n" +
                                "\n" +
                                "- ready to move in with newly painted house !\n" +
                                "- 1 covered parking available\n" +
                                "- 24 hour gated and guarded\n" +
                                "\n" +
                                "- Partially-furnished unit\n" +
                                "- type C, 1000 sqft\n" +
                                "- walking distance to MMU (Multimedia University)")
                        .roomInfo("1 master room, 2 rooms")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Mutiara Ville @ Cyberjaya", "Mutiara Ville")
                        .address(new Address("Persiaran Sepang Off Persiaran Multimedia", "Mutiara Ville", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1200)
                        .size("1000 sqft")
                        .rentalRate(1.20)
                        .agent(agent1)
                        .description("Nice Unit Near To MMU, Dpulze, Cyberjaya Hospital\n" +
                                "Mutiara Ville Condominium, Cyberjaya For rent, Fully Furnished")
                        .roomInfo("1 master room, 2 rooms")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.CONDO, "Eclipse Residence @ Pan'gaea", "Cyber 11")
                        .address(new Address("Persiaran Bestari Off Persiaran Multimedia, Pan'gaea", "Cyber 11", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1600)
                        .size("1000 sqft")
                        .rentalRate(1.60)
                        .agent(agent2)
                        .description("- Fully-furnished unit\n" +
                                "- type A, 1000 sqft\n" +
                                "- walking distance to MMU (Multimedia University)\n" +
                                "\n" +
                                "Near to :\n" +
                                "mutiara villa, OSK Pangea, Cyberia Crescent I & II, Cyberia Domain 1,2,3, D'Melor Condominium, GardenView Condominium.d")
                        .roomInfo("1 master room, 2 rooms")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.TV)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.CONDO, "Kanvas SOHO", " Off Persiaran APEC")
                        .address(new Address("Jalan Teknokrat 6", " Off Persiaran APEC", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1000)
                        .size("484 sqft")
                        .rentalRate(2.07)
                        .agent(agent2)
                        .description("Low Deposit To Rent Kanvas Soho, Build In Wardrobe. Viewing Anytime\n" +
                                "UNIT FOR RENT (Beware of Fake Agent)\n" +
                                "* Unit with Built-in Wardrobe*\n" +
                                "\n" +
                                "Property: Kanvas Soho Cyberjaya (The MOST well-kept Condo)\n" +
                                "Don't trust me, see yourself")
                        .roomInfo("1 master room, 1 rooms and 1 living room")
                        .bathRoomCount(3)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser3, PropertyType.SINGLE_STORY, "Schubert, Symphony Hills, Cyberjaya", " Symphony Hills")
                        .address(new Address("Schubert", "Symphony Hills", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(3000)
                        .size("1400 sqft")
                        .rentalRate(2.14)
                        .agent(agent2)
                        .description("• 2 storey townhouse\n" +
                                "• 3 bedrooms 3 bathrooms\n" +
                                "• Very well maintained\n" +
                                "• Walking distance to Tamarind square\n" +
                                "• Peaceful garden concept living\n" +
                                "• Fully Furnished\n" +
                                "• Rental RM3,000" +
                                "\n" +
                                "Please call 012-333 1234 for viewing.")
                        .roomInfo("1 master room, 2 rooms and 1 living room")
                        .bathRoomCount(3)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.TV)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser3, PropertyType.DOUBLE_STORY, "Cybersouth, Cyberjaya", " Cybersouth")
                        .address(new Address("Jln CB2", "Cybersouth", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(1500)
                        .size("1200 sqft")
                        .rentalRate(1.25)
                        .agent(agent2)
                        .description("For Rent\n" +
                                "Ground Level / Upper Floor\n" +
                                "Townhouse Casa Bluebell Cybersouth\n" +
                                "Rental price RM1500/mth")
                        .roomInfo("1 master room, 2 rooms and 1 living room")
                        .bathRoomCount(2)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.TV)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.TOWNHOUSE, "Isle of Kamares @ Setia Eco Glades", " Setia Eco Glades")
                        .address(new Address("Isle of Kamares, Setia Eco Glades", "Setia Eco Glades", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(3500)
                        .size("1414 sqft")
                        .rentalRate(2.48)
                        .agent(agent3)
                        .description("WhatsApp 017-123 1234 to find out more!")
                        .roomInfo("1 master room, 3 rooms and 1 living room")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.TV)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.BUNGALOW, "Schumann, Symphony Hills, Cyberjaya", " Symphony Hills")
                        .address(new Address("Schubert, Beethoven, Mozart, Beethoven Symphony Hills, Schumann", "Symphony Hills", "Cyberjaya", "Selangor", 63000, "Malaysia"))
                        .isActive(true)
                        .rentalFee(4500)
                        .size("4430 sqft")
                        .rentalRate(0.86)
                        .agent(agent3)
                        .description("Special Features:\n" +
                                "- open concept with garden\n" +
                                "- high ceiling entrance\n" +
                                "- 2 tier security check points with gated & guarded community\n" +
                                "- all bedroom come with ensuite bathroom\n" +
                                "- bathtub in master room")
                        .roomInfo("4+1 bedrooms 5 bathrooms")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.TV, FacilityType.SWIMMING_POOL)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser4, PropertyType.BUNGALOW, "Maple Residence, Laman View, Cyberjaya", "Laman View")
                        .isActive(true)
                        .rentalFee(4000)
                        .size("2,133m²")
                        .rentalRate(0.86)
                        .agent(agent3)
                        .description("INTERMEDIATE Link House* NEW UNIT* Build Up: 2133 SQ.FT.* Land Size: 22 x 70 SQ.FT* 4+1 Bedrooms + 4 Baths* Partially Furnished * Wide Car Porch (4 Cars)* Open Gate Concept * 24 Hrs Security Guard House* Exclusive 5 Star Clubhouse")
                        .roomInfo("4 bedrooms 5 bathrooms")
                        .bathRoomCount(5)
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.WIFI, FacilityType.AIRCOND, FacilityType.TV, FacilityType.SWIMMING_POOL)))
                        .buildProperty()

        ));
        System.out.println("Total Properties generated - " + propertyObjectData.size());
        return propertyObjectData;
    }
}
