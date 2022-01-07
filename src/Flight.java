import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Flight{
    private int flightNumber;
    private Aircraft craft;
    private String startLocation;
    private String endLocation;
    private double distance;

    public ArrayList<Seat> seats;
    public ArrayList<CrewMember> crew;

    public Flight(int flightNumber, Aircraft craft, String startLocation, String endLocation, double distance) {
        this.flightNumber = flightNumber;
        this.craft = craft;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.crew = new ArrayList<CrewMember>();

        this.seats = new ArrayList<Seat>();
        try {
            File layoutText = new File(String.valueOf(this.craft.getLayoutFile()));
            Path path = Paths.get(String.valueOf(this.craft.getLayoutFile()));
            String content = Files.readString(path, StandardCharsets.US_ASCII);
            String [] seatStringRows = content.split("\\n");
            String [] seatStringSeats = null;

            for (int i = 0; i < seatStringRows.length; i++) {
            seatStringSeats = seatStringRows[i].split(",");
            }

            for (int i = 0; i < seatStringRows.length; i++) {
                for (int j = 0; j < seatStringSeats.length; j++) {
                    Seat s = new Seat(i, j, seatStringSeats[j]);
                    this.seats.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double calculateTakeOffWeight(){
        double totalWeight = 0;
        for (int i = 0; i < seats.size(); i++) {
           totalWeight =+ seats.get(i).getAllocatedTo().calculatePersonWeight();
        }
        if(totalWeight <= craft.getMaximumTakeoffWeight()){
            return totalWeight;
        }
        return -1;
    }

    public int bookSeat(Passenger p){
        for (int i = 0; i < seats.size(); i++) {
            if(p.getFlightClass() == seats.get(i).getFlyingClass()
                    && seats.get(i).getAllocatedTo()==null){
                seats.get(i).setAllocatedTo(p);
                return 1;
            }

//           if(p.getFlightClass() == "F" &&){
//
//           }
        }
    return -1;

    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Aircraft getCraft() {
        return craft;
    }

    public void setCraft(Aircraft craft) {
        this.craft = craft;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public ArrayList<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewMember> crew) {
        this.crew = crew;
    }
}
