import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


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
            int seatNumber = 0;

            Path path = Paths.get(String.valueOf(this.craft.getLayoutFile()));
            String content = Files.readString(path, StandardCharsets.US_ASCII);
            String [] seatStringRows = content.split("\\n");
            String newContent = content.replace("\n" ,",");
            String [] linearSeats = newContent.split(",");
            String rowsToString = Arrays.toString(seatStringRows);
            rowsToString = rowsToString.replace("[", "");
            rowsToString = rowsToString.replace("]" , "");
            String mySeats = rowsToString;
            String [] seatStringSeats = mySeats.split(" ");


            for (int i = 0; i < seatStringRows.length; i++) {

                int size = seatStringSeats[i].length() /2;
                for (int j = 0; j < size; j++) {
                    seatNumber++;
                    Seat s = new Seat(i , j, linearSeats[seatNumber]);
                    this.seats.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int bookSeat(Passenger p) {
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getAllocatedTo() == null){
                if(p.getFlightClass().equals("first") && seats.get(i).getFlyingClass().equals("F")){
                    seats.get(i).setAllocatedTo(p);
                    return 1;
                }
                if(p.getFlightClass().equals("economy") && seats.get(i).getFlyingClass().equals("E")){
                    seats.get(i).setAllocatedTo(p);
                    return 1;
                }

                if(!checkFirstSeats() && !checkEconomySeats()){
                    return -1;
                }
            }

        }
        for (int i = 0; i < seats.size() ; i++) {
            if(seats.get(i).getAllocatedTo() == null) {
                if (p.getFlightClass().equals("first") && seats.get(i).getFlyingClass().equals("E") && !checkFirstSeats()) {
                    seats.get(i).setAllocatedTo(p);
                    return 3;
                }

                if (p.getFlightClass().equals("economy") && seats.get(i).getFlyingClass().equals("F") && !checkEconomySeats()) {
                    seats.get(i).setAllocatedTo(p);
                    return 2;
                }

                if(!checkFirstSeats() && !checkEconomySeats()){
                    return -1;
                }
            }
        }
        return 0;
    }

    public double calculateTakeOffWeight(){
        double totalWeight = 0;
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getAllocatedTo() == null){
                totalWeight = totalWeight +0;
            }else {
                totalWeight = totalWeight + seats.get(i).getAllocatedTo().calculatePersonWeight();
            }
        }
        for(int i = 0; i < crew.size(); i++){
          totalWeight = totalWeight +  crew.get(i).calculatePersonWeight();
        }
        if(totalWeight <= craft.getMaximumTakeoffWeight()){
            return totalWeight;
        }
        return -1;
    }





    public boolean checkEconomySeats() {
        boolean[] availableSeats = new boolean[seats.size()];
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getFlyingClass().equals("E")) {
                if (seats.get(i).getAllocatedTo() == null) {
                    availableSeats[i] = true;
                } else if (seats.get(i).getAllocatedTo() != null) {
                    availableSeats[i] = false;
                }

                if (availableSeats[i] == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFirstSeats() {
        boolean[] availableSeats = new boolean[seats.size()];
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getFlyingClass().equals("F")) {
                if (seats.get(i).getAllocatedTo() == null) {
                    availableSeats[i] = true;
                } else if (seats.get(i).getAllocatedTo() != null) {
                    availableSeats[i] = false;
                }

                if (availableSeats[i] == true) {
                    return true;
                }
            }
        }
        return false;
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

    @Override
    public String toString() {
        return "Flight Number: "+ flightNumber + "\n" +
                "Flying from: "+ startLocation + " to "+ endLocation + "\n" +
                "Total Distance: " + distance + "\n" +
                "Total First Class Passengers: " + firstClassPassengers() + "\n" +
                "Total Economy Class Passengers: " + economyClassPassengers() + "\n" +
                "Total Empty Seats: " + emptySeats() + "\n" +
                "Flight Crew: " + crewName() + "\n" +
                "Craft------" + craft.toString();

    }
    public String crewName(){
       String names = "";
        for (int i = 0; i < crew.size(); i++) {
          names = names + crew.get(i).name + ", ";
        }
        return names;
    }
    public int emptySeats(){
        int counter = 0;
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getAllocatedTo() == null){
                counter++;
            }
        }
        return counter;
    }
    public int firstClassPassengers(){
        int counter = 0;
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getFlyingClass().equals("F") && seats.get(i).getAllocatedTo() != null){
                counter++;
            }
        }
        return counter;
    }

    public int economyClassPassengers(){
        int counter = 0;
        for (int i = 0; i < seats.size(); i++) {
            if(seats.get(i).getFlyingClass().equals("E") && seats.get(i).getAllocatedTo() != null){
                counter++;
            }
        }
        return counter;
    }
}
