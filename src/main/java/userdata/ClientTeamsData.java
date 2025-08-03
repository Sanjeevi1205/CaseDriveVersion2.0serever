package userdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientTeamsData {
    private static final AtomicInteger scenarioCounter = new AtomicInteger(0);

    @Log
    @Getter
    @RequiredArgsConstructor
    public enum MemberData {
        MEMBER_DATA(
                generateDynamicFirstName(),
                "Test",
                "34342442423",
                "Project Staff",
                "pqRsVuI@1234",
                "pqRsVuI@1234",
                "invalid_Email.com",
                "mail@lezdotechmed.com",
                "Test Description",
                generateTeamName()
        );

        private final String firstname;
        private final String lastname;
        private final String phonenumber;
        private final String jobrole;
        private final String password;
        private final String confirmpassword;
        private final String invalidEmail;
        private final String existingMail;
        private final String descriptionValue;
        private final String dynamicTeamName;

        public String getEmployeeID() {
            return generateEmployeeID(scenarioCounter.getAndIncrement());
        }

        private static String generateDynamicFirstName() {
            String[] firstNames = {"John", "Jane", "Michael", "Emily", "Chris", "Sarah", "David", "Anna", "Robert"};
            Random random = new Random();
            return firstNames[random.nextInt(firstNames.length)];
        }

        public static String generateTestTemplateName() {
            Random random = new Random();
            int randomNumber = 10000 + random.nextInt(90000);
            int currentCount = scenarioCounter.incrementAndGet();
            return "Test Template " + randomNumber + " " + currentCount;
        }

        private static String generateEmployeeID(int scenarioNumber) {
            int randomNum = new Random().nextInt(10000) + 1;
            return "EMP" + scenarioNumber + randomNum;
        }

        public static String generateTeamName() {
            String[] themes = {"Alpha", "Beta", "Gamma", "Delta", "Omega", "Zeta", "Nova"};
            String[] vibes = {"Squad", "Force", "Crew", "Gang", "Pack"};

            Random random = new Random();
            String theme = themes[random.nextInt(themes.length)];
            String vibe = vibes[random.nextInt(vibes.length)];

            int scenarioNum = scenarioCounter.incrementAndGet(); // Increments for each call
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")); // seconds only

            return theme + vibe + scenarioNum + time;
        }


        public static String generateCompanyName() {
            String[] adjectives = {"Innovative","Tech", "Creative", "Dynamic", "Future", "Smart", "Advanced", "Prime"};
            String[] industries = {"Solutions", "Technologies", "Group", "Systems", "Corporation", "Enterprises", "Consulting"};
            String[] regions = {"USA", "Global", "Europe", "Asia", "Canada", "Worldwide"};

            Random random = new Random();
            String adjective = adjectives[random.nextInt(adjectives.length)];
            String industry = industries[random.nextInt(industries.length)];
            String region = regions[random.nextInt(regions.length)];

            int uniqueId = scenarioCounter.incrementAndGet();

            return "LezDoTechMed " + adjective + " " + industry + " " + region + " " + uniqueId;
        }

        public static String generateCompanyNameCRM() {
            String[] adjectives = {"Trusted", "Elite", "Premier", "Strategic", "Legacy", "Noble", "Leading", "Prestige", "United"};
            String[] industries = {"Law", "Legal", "Associates", "Partners", "Attorneys", "Group", "Counsel", "Chambers"};
            String[] regions = {"USA", "Global", "LLP", "PLC", "International", "Nationwide", "Firm", "Worldwide"};


            Random random = new Random();
            String adjective = adjectives[random.nextInt(adjectives.length)];
            String industry = industries[random.nextInt(industries.length)];
            String region = regions[random.nextInt(regions.length)];

            int uniqueId = scenarioCounter.incrementAndGet();

            return adjective + " " + industry + " " + region + " " + uniqueId;
        }

        public static String generateBankName() {
            String[] bankNames = {
                    "LezDoBank", "GlobalTech Bank", "Premier Finance Bank", "Quantum Bank",
                    "Horizon Banking Corp", "RapidFunds Bank", "SecureTrust Bank",
                    "Unity Financial Bank", "CrestPoint Bank", "Vertex Bank"
            };

            Random random = new Random();

            return bankNames[random.nextInt(bankNames.length)];
        }

        public static String generateAccountNumber() {
            Random random = new Random();

            long accountNumber = 1000000000L + (random.nextLong() % 9000000000L);

            if (accountNumber < 0) {
                accountNumber = -accountNumber;
            }

            return String.valueOf(accountNumber);
        }

        public static void main(String[] args) {
            String accountNumber = generateAccountNumber();
            log.info("Generated Account Number: " + accountNumber);
        }

        public static String generateState() {
            String[] states = {
                    "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
                    "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
                    "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                    "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
                    "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
                    "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                    "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
            };

            Random random = new Random();
            return states[random.nextInt(states.length)];
        }


        public static String generateCity() {
            String[] cities = {
                    "Los Angeles", "Houston", "Miami", "New York City", "Chicago", "Philadelphia", "Phoenix",
                    "San Antonio", "San Diego", "Dallas"
            };

            Random random = new Random();
            return cities[random.nextInt(cities.length)];
        }

        public static String generateZipcode() {
            Random random = new Random();
            int zipcode = 10000 + random.nextInt(90000);
            return String.valueOf(zipcode);
        }

        public static String generateAddress() {
            String state = generateState();
            String city = generateCity();
            String zipcode = generateZipcode();

            return city + ", " + state + ", " + zipcode;
        }

        public static String generateStreet() {
            String[] streetNames = {
                    "Main Street", "Oak Avenue", "Pine Road", "Maple Boulevard", "Elm Drive", "Cedar Lane", "Sunset Way",
                    "Greenfield Street", "Riverdale Drive", "Broadway", "Chestnut Street", "Willow Way", "Beechwood Street",
                    "Silver Birch Drive", "Rosewood Drive", "Hilltop Avenue", "Forest Lane", "Lakeside Drive", "Hillside Road",
                    "Victoria Street", "Park Avenue", "Regency Court", "Shady Grove Lane", "Kings Road", "Kingfisher Street"
            };
            Random random = new Random();
            return streetNames[random.nextInt(streetNames.length)];
        }


    }
}
