import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Berlin52Loader {

    public static void loadCities(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            boolean startReading = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("NODE_COORD_SECTION")) {
                    startReading = true; // Start reading coordinates after this line
                    continue;
                }
                if (startReading) {
                    if (line.isEmpty() || line.startsWith("EOF")) break; // Stop if EOF or empty line
                    String[] parts = line.trim().split("\\s+");
                    int number = Integer.parseInt(parts[0]); // Location number
                    int x = (int)Double.parseDouble(parts[1]);
                    int y = (int)Double.parseDouble(parts[2]);
                    City city = new City(number, x, y);
                    TourManager.addCity(city);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

}
