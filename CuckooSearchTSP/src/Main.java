public class Main {
    public static void main(String[] args) {
        // Load the Berlin52 dataset "C:/Users/softatt/Desktop/CoockoSearchTSP/berlin52.tsp"
        Berlin52Loader.loadCities("C:/Users/softatt/Desktop/CuckooSearchTSP/berlin52.tsp"); // Make sure the path is correct

        // Initialize Cuckoo Search with a specific number of nests
        int nestCount = 30; // You can adjust this number based on your requirements
        CuckooSearch cuckooSearch = new CuckooSearch(nestCount);

        // Define the number of iterations/generations for the Cuckoo Search algorithm
        int iterations = 10000; // Adjust based on your convergence criteria

        // Run the Cuckoo Search algorithm
        cuckooSearch.search(iterations);

        // Assuming you have a method to retrieve the best tour found by the algorithm
        Tour bestTour = cuckooSearch.getBestTour(); // This method needs to be implemented in CuckooSearch

        // Print the best tour's details
        System.out.println("Best tour distance: " + bestTour.getDistance());
        System.out.println("Best tour path: " + bestTour);
    }
}
