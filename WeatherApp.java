import java.util.Scanner;

/*
 * this is the main class that will interact with the user 
 * user will enter city name if the city name is correct 
 * user will get result forecast for few days of that city 
 */
public class WeatherApp {
    public static void main(String args[]){

        WeatherAPI weather = new WeatherAPI(); // This will instantiate weather api class to work with 

        Scanner sc = new Scanner(System.in);    // Scanner class will get the input from user from console
        System.out.println("Enter the Name of City, you want to know the forecast:");  //ask user to enter name of the city

        String city_name = sc.nextLine().strip().toLowerCase();   // remove any extra space and convert to lower case from both ends to get correct result

        sc.close();     //closeing the input stream reader

        System.out.println(weather.getForecast(city_name));   //printing the result onto console
    }
}
