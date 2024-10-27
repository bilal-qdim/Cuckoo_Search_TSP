import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CuckooSearch {
    private ArrayList<Tour> nests = new ArrayList<>();
    private double discoveryRate = 0.25; // Example value

    public CuckooSearch(int nestCount) {
        for (int i = 0; i < nestCount; i++) {
            Tour newTour = new Tour();
            newTour.generateIndividual();
            nests.add(newTour);
        }
    }

    // Perform the search
    public void search(int iterations) {
        for (int i = 0; i < iterations; i++) {
            // Get a new solution using Levy flight
            Tour bestSolution = getBestSolution(); // Assuming getBestSolution() method exists
            Tour newSolution = getNewSolutionByLevyFlights(bestSolution);

            // Get the worst solution
            Tour worst = getWorstSolution();

            // Replace the worst solution with the new solution if it's better
            if (newSolution.getDistance() < worst.getDistance()) {
                nests.remove(worst);
                nests.add(newSolution);
            }

            // Discover new solutions based on the discovery rate
            discoverNewSolutions();
        }
    }

    private Tour getNewSolutionByLevyFlights(Tour bestSolution) {
        // Clone the best solution to start with
        Tour newSolution = new Tour(new ArrayList<>(bestSolution.getTour()));

        // Perform a series of swaps based on Levy flights to generate a new solution
        for (int i = 0; i < newSolution.tourSize(); i++) {
            // Perform Levy Flight to determine the swap index
            int swapIndex = (i + (int)levyFlight()) % newSolution.tourSize();
            if (swapIndex < 0) {
                swapIndex += newSolution.tourSize(); // Ensure swapIndex is positive
            }

            // Swap the current city with the city at swapIndex
            Collections.swap(newSolution.getTour(), i, swapIndex);
        }

        return newSolution;
    }


    // Levy flight step generator
    private double levyFlight() {
        double beta = 1.5;
        double sigma = Math.pow((gamma(1 + beta) * Math.sin(Math.PI * beta / 2)) / (gamma((1 + beta) / 2) * beta * Math.pow(2, (beta - 1) / 2)), 1 / beta);
        double u = Math.random() * sigma;
        double v = Math.random();
        return u / Math.pow(Math.abs(v), 1 / beta);
    }

    // Gamma function approximation
    private double gamma(double x) {
        return Math.sqrt(2 * Math.PI / x) * Math.pow((x / Math.E), x);
    }

    private Tour getWorstSolution() {
        return Collections.max(nests, (tour1, tour2) -> {
            if (tour1.getDistance() < tour2.getDistance()) return -1;
            else if (tour1.getDistance() > tour2.getDistance()) return 1;
            return 0;
        });
    }

    private void discoverNewSolutions() {
        // Randomly replace some solutions based on the discovery rate
        for (int i = 0; i < nests.size(); i++) {
            if (Math.random() < discoveryRate) {
                nests.get(i).generateIndividual();
            }
        }
    }

    // Assuming a getBestSolution method exists to find the best current solution
    private Tour getBestSolution() {
        return Collections.min(nests, Comparator.comparingDouble(Tour::getDistance));
    }

    public Tour getBestTour() {
        if (nests.isEmpty()) {
            return null; // or throw an exception based on your preference
        }

        Tour bestTour = nests.get(0);
        for (Tour currentTour : nests) {
            if (currentTour.getDistance() < bestTour.getDistance()) {
                bestTour = currentTour;
            }
        }

        return bestTour;
    }
}
