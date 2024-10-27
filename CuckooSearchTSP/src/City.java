public class City {
    int number; // Location number
    int x;
    int y;

    public City(int number ,int x, int y) {
        this.number = number ;
        this.x = x;
        this.y = y;
    }


    public int getNumber() {
        return number;
    }
    public double distanceTo(City city) {
        int xDistance = Math.abs(this.x - city.x);
        int yDistance = Math.abs(this.y - city.y);
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    @Override
    public String toString() {
        return String.valueOf(number) + "(" +x +"," +y +")";
    }

}
