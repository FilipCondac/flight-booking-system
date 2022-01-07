public class Passenger extends Person {
    private int holdBags;
    private String flightClass;


    public Passenger(String name, int passportNumber,  String flightClass,int holdBags) {
        super(name, passportNumber);
        this.flightClass = flightClass;
        this.holdBags = holdBags;
    }

    public int getHoldBags() {
        return holdBags;
    }

    public void setHoldBags(int holdBags) {
        this.holdBags = holdBags;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    @Override
    public double calculatePersonWeight() {
        if(flightClass == "first") {
            return 87.5 + (25 * holdBags);
        }else {
            return 80 + (25 * holdBags);
        }
    }
}
