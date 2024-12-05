import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class WeatherAPI
{
    private static final String API_KEY = "API_KEY_WILL_BE_HERE";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    public String getForecast(String city)
    {
        try{
            String city_url = BASE_URL + "?q=" +city+ "&appid="+ API_KEY+ "&units=metric";
            URI uri = new URI(city_url);
            URL url = uri.toURL();

            HttpURLConnection connection  = (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder res = new StringBuilder();

            String line;

            while((line = reader.readLine())!=null){
                res.append(line);
            }

            reader.close();

            //
            WeatherParser parse = new WeatherParser();
            
            return parse.parse(res.toString(),city);

        }catch(Exception e){return "Invalid City Name";}
    }
}
