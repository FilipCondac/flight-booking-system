public abstract class Person {

    String name;
    int passportNumber;

    public Person(String name, int passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public abstract  double calculatePersonWeight();

}
