import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    private ArrayList<City> tour = new ArrayList<>();
    private double distance = 0;

    public Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList<City> tour) {
        this.tour = new ArrayList<>(tour);
    }

    public ArrayList<City> getTour() {
        return tour;
    }

    public double getDistance() {
        if (distance == 0) {
            double tourDistance = 0;
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                if (cityIndex + 1 < tourSize()) {
                    destinationCity = getCity(cityIndex + 1);
                } else {
                    destinationCity = getCity(0); // Loop back to the starting city
                }

                // Check if either city is null
                if (fromCity == null || destinationCity == null) {
                    System.err.println("Null city encountered in tour at index " + cityIndex);
                    continue; // Skip this iteration
                }

                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }


    public int tourSize() {
        return tour.size();
    }

    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        distance = 0;
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    // Shuffle the tour
    public void generateIndividual() {
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        Collections.shuffle(tour);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (City city : tour) {
            if (city != null) {
                sb.append(city).append(" -> ");
            }
        }
        // Loop back to the starting city for a complete tour representation
        if (!tour.isEmpty() && tour.get(0) != null) {
            sb.append(tour.get(0));
        }
        return sb.toString();
    }
}


     class TourManager {
    private static ArrayList<City> destinationCities = new ArrayList<>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index) {
        return destinationCities.get(index);
    }

    public static int numberOfCities() {
        return destinationCities.size();
    }
}
