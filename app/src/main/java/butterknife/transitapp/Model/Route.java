package butterknife.transitapp.Model;

import java.util.List;

/**
 * Created by Trevor on 2/28/2016.
 */
public class Route {
    private String routeNo;
    private String getRouteNo;
    private String direction;
    private List<Bus> schedules;

    public Route(String routeNo, String getRouteNo, String direction, List<Bus> schedules) {
        this.routeNo = routeNo;
        this.getRouteNo = getRouteNo;
        this.direction = direction;
        this.schedules = schedules;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public String getGetRouteNo() {
        return getRouteNo;
    }

    public String getDirection() {
        return direction;
    }

    public List<Bus> getSchedules() {
        return schedules;
    }
}
