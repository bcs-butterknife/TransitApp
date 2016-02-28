package butterknife.transitapp.Model;

import java.util.List;

/**
 * Created by Trevor on 2/28/2016.
 */
public class Stop {

    private String stopNum;
    private String stopName;
    private String bayNum;
    private String city;
    private String onStreet;
    private String atStreet;
    private double longitude;
    private double latitude;
    private double wheelchairAccess;
    private String distance;
    private String routes;

    private List<Route> buses;

    public Stop(String stopNum, String stopName, String city, String onStreet,
                String atStreet, double wheelchairAccess, String routes) {
        this.stopNum = stopNum;
        this.stopName = stopName;
        this.city = city;
        this.onStreet = onStreet;
        this.atStreet = atStreet;
        this.wheelchairAccess = wheelchairAccess;
        this.routes = routes;
    }

    public Stop(String stopNum,
                String stopName,
                String bayNum,
                String city,
                String onStreet,
                String atStreet,
                double longitude,
                double latitude,
                double wheelchairAccess,
                String distance,
                String routes,
                List<Route> route) {
        this.stopNum = stopNum;
        this.stopName = stopName;
        this.bayNum = bayNum;
        this.city = city;
        this.onStreet = onStreet;
        this.atStreet = atStreet;
        this.longitude = longitude;
        this.latitude = latitude;
        this.wheelchairAccess = wheelchairAccess;
        this.distance = distance;
        this.routes = routes;
        this.buses = route;
    }

    public Stop(String stopNum, List<Route> buses) {
        this.stopNum = stopNum;
        this.buses = buses;
    }

    public String getStopNum() {
        return stopNum;
    }

    public List<Route> getBuses() {
        return buses;
    }

    public String getNextTimes(String routeNum) {
        String nextTimes = "";
        List<Bus> schedule = null;
        for (int i = 0; i < buses.size(); i++) {
            if (buses.get(i).getRouteNo() == routeNum) {
                schedule = buses.get(i).getSchedules();
                break;
            }
        }

        if (schedule != null) {
            for (Bus b : schedule) {
                nextTimes = nextTimes + b.getExpLeave() + " ";
            }
            return nextTimes;
        } else {
            return "";
        }
    }
}
