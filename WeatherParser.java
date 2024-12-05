import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.Date;
import java.text.SimpleDateFormat;


public class WeatherParser {

    public String parse(String jsonResponse,String city_name)
    {

        // Creating JSON object to parse the json response
        JSONObject json = new JSONObject(jsonResponse);  

        // this will create a final strucurted value 
        StringBuilder response = new StringBuilder();

        response.append("Weather Forecast for next few days are "+city_name+ "\n");

        try{  // can throw Exception if jsonexception

            /*
            * code is 200 means success
            * 404 means not found
            * 401 means aunauthorized
            * 500: means internal server error 
            * 429 : means too many requests 
            */

            if(json.getInt("cod")==200){ 
            
                JSONArray jsonList = json.getJSONArray("list");  //list of values
                
                for(int i=0; i<jsonList.length(); i++){     // for each value jsonList contain repeat same 

                    JSONObject forecast = jsonList.getJSONObject(i);  //each value contain one json object

                    long timestamp = forecast.getLong("dt");    // getting date from forecast 

                    // API returns timestamp in Seconds we need to convert seconds into millieseconds since Date class 
                    // represents time in millieseconds since 1970 so timestamp*1000 will work

                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date(timestamp*1000));

                    double temp = forecast.getJSONObject("main").getDouble("temp");  //it will return temperature 
                    
                    String description = forecast.getJSONArray("weather").getJSONObject(0).getString("description");


                    response.append(date + " : "+temp+"°C : "+description+"\n");  //adding the to response message

                }

               // return response.toString();
            }

            return response.toString();  // return final string response to the user
        }catch(JSONException e){return "Not able parse data";}  // If any exception occur
    }
    
}
