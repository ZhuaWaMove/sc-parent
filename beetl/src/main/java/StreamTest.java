import lombok.Data;

import java.time.LocalDate;
        import java.time.Month;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;
/*w  w w .j a  v a  2s.  co m*/
public class StreamTest {
    public static void main(String[] args) {
        List<Employee> persons = Employee.persons();
        // Find any male
        Optional<Employee> anyMale = persons.stream().filter(Employee::isMale).findAny();
        if (anyMale.isPresent()) {
            System.out.println("Any male:   " + anyMale.get());
        } else {
            System.out.println("No male  found.");
        }
        // Find the first male
        Optional<Employee> firstMale = persons.stream().filter(Employee::isMale).findFirst();
        if (firstMale.isPresent()) {
            System.out.println("First male:   " + anyMale.get());
        } else {
            System.out.println("No male  found.");
        }
    }

    @Data
    static class Employee {
        public static enum Gender {
            MALE, FEMALE
        }

        private long id;
        private String name;
        private Gender gender;
        private LocalDate dob;
        private double income;

        public Employee(long id, String name, Gender gender, LocalDate dob,
                        double income) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.dob = dob;
            this.income = income;
        }
        public boolean isMale() {
            return this.gender == Gender.MALE;
        }
        public static List<Employee> persons() {
            Employee p1 = new Employee(1, "Jake", Gender.MALE, LocalDate.of(1971, Month.JANUARY, 1), 2343.0);
            Employee p2 = new Employee(2, "Jack", Gender.MALE, LocalDate.of(1972, Month.JULY, 21), 7100.0);
            Employee p3 = new Employee(3, "Jane", Gender.FEMALE, LocalDate.of(1973, Month.MAY, 29), 5455.0);
            Employee p4 = new Employee(4, "Jode", Gender.MALE, LocalDate.of(1974, Month.OCTOBER, 16), 1800.0);
            Employee p5 = new Employee(5, "Jeny", Gender.FEMALE, LocalDate.of(1975, Month.DECEMBER, 13), 1234.0);
            Employee p6 = new Employee(6, "Jason", Gender.MALE, LocalDate.of(1976, Month.JUNE, 9), 3211.0);

            List<Employee> persons = Arrays.asList(p1, p2, p3, p4, p5, p6);

            return persons;
        }
    }
}

