package butterknife.transitapp.StopActivityRelated;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

import butterknife.transitapp.Model.Bus;
import butterknife.transitapp.Model.Route;
import butterknife.transitapp.Model.Stop;

/**
 * Created by Trevor on 2/28/2016.
 */
public class Feed {
    private static String API_KEY = "?apikey=3u5DrRTEp1P6bMUlApvA";
    private static Stop stopData;
    private static String stopNumber;
    private static JSONArray response;

    public static void execute(String stopNumber) {

        Feed.stopNumber = stopNumber;

        try {
            URL url = new URL("http://api.translink.ca/rttiapi/v1/stops/" + stopNumber + "/estimates" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");

            retrieveFeed(urlConnection);

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int size = response.length();
            List<Route> routeList = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                JSONObject obj = response.getJSONObject(i);
                System.out.println("HERE!");
                String routeNo = obj.getString("RouteNo");
                String routeName = obj.getString("RouteName");
                String direction = obj.getString("Direction");
                System.out.println("New Bus List");
                List<Bus> busList = new LinkedList<>();
                JSONArray schedule = obj.getJSONArray("Schedules");
                int scheduleSize = schedule.length();

                for (int j = 0; j < scheduleSize; j++) {
                    JSONObject o = schedule.getJSONObject(j);
                    String destination = o.getString("Destination");
                    String expLeave = o.getString("ExpectedLeaveTime");
                    String expCountdown = o.getString("ExpectedCountdown");
                    String scheduleStatus = o.getString("ScheduleStatus");
                    String lastUpdate = o.getString("LastUpdate");
                    Bus bus = new Bus(routeNo, routeName, direction, destination, expLeave, expCountdown,
                            scheduleStatus, lastUpdate);
                    System.out.println("Created New Schedule");
                    busList.add(bus);
                    System.out.println("Added to Bus List");
                }
                // creates new route with all schedules bus times
                Route route = new Route(routeNo, routeName, direction, busList);
                System.out.println(routeNo);

                // adds route to list of routes that service at stop
                routeList.add(route);
                System.out.println(routeList.size());
            }

            stopData = new Stop(stopNumber, routeList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void retrieveFeed(HttpURLConnection url){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            String response = stringBuilder.toString();
            Feed.response = new JSONArray(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Stop getStopData() {
        return stopData;
    }
}
