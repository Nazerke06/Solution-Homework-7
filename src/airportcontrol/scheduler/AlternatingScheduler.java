package airportcontrol.scheduler;
import airportcontrol.aircraft.Aircraft;
import airportcontrol.aircraft.Operation;

import java.util.Queue;

public class AlternatingScheduler implements RunwayScheduler {
    private boolean preferLanding = true;

    @Override
    public Aircraft getNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue) {
        Aircraft next = null;
        if (preferLanding && !landingQueue.isEmpty()) {
            next = landingQueue.poll();
        } else if (!takeoffQueue.isEmpty()) {
            next = takeoffQueue.poll();
        } else if (!landingQueue.isEmpty()) {
            next = landingQueue.poll();
        }
        preferLanding = !preferLanding;
        return next;
    }

    @Override
    public void addAircraft(Aircraft aircraft, Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue) {
        if (aircraft.getOperation() == Operation.LANDING) {
            landingQueue.offer(aircraft);
        } else {
            takeoffQueue.offer(aircraft);
        }
    }
}