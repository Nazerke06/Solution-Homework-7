package airportcontrol.scheduler;
import airportcontrol.aircraft.Aircraft;

import java.util.Queue;

public interface RunwayScheduler {
    Aircraft getNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue);
    void addAircraft(Aircraft aircraft, Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue);
}