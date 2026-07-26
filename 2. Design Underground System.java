import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Helper class to store check-in details for a customer
    private static class CheckInInfo {
        String stationName;
        int checkInTime;

        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    // Helper class to aggregate travel statistics for a specific route
    private static class RouteInfo {
        double totalTime;
        int tripCount;

        RouteInfo(double totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }
    }

    // Maps customer ID -> check-in details
    private Map<Integer, CheckInInfo> checkInMap;
    // Maps route key ("Start->End") -> route travel statistics
    private Map<String, RouteInfo> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        // Record customer's current check-in location and time
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        // Retrieve and remove customer's check-in info
        CheckInInfo info = checkInMap.remove(id);

        // Form route key
        String routeKey = info.stationName + "->" + stationName;
        int travelTime = t - info.checkInTime;

        // Update aggregated route stats
        RouteInfo route = routeMap.getOrDefault(routeKey, new RouteInfo(0, 0));
        route.totalTime += travelTime;
        route.tripCount += 1;
        
        routeMap.put(routeKey, route);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteInfo route = routeMap.get(routeKey);
        
        return route.totalTime / route.tripCount;
    }
}
