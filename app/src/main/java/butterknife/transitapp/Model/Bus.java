package butterknife.transitapp.Model;

/**
 * Created by Trevor on 2/28/2016.
 */
public class Bus {
    private String routeNo;
    private String routeName;
    private String direction;
    private String destination;
    private String expLeave;
    private String expCountdown;
    private String scheduleStatus;
    private String lastUpdate;

    public Bus(String routeNo,
               String routeName,
               String dir,
               String dest,
               String expLeave,
               String expCountdown,
               String scheduleStatus,
               String lastUpdate) {
        this.routeNo = routeNo;
        this.routeName = routeName;
        this.direction = dir;
        this.destination = dest;
        this.expLeave = expLeave;
        this.expCountdown = expCountdown;
        this.scheduleStatus = scheduleStatus;
        this.lastUpdate = lastUpdate;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getDirection() {
        return direction;
    }

    public String getDestination() {
        return destination;
    }

    public String getExpLeave() {
        return expLeave;
    }

    public String getExpCountdown() {
        return expCountdown;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
