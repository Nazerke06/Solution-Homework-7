package airportcontrol.scheduler;
import airportcontrol.aircraft.Aircraft;
import airportcontrol.aircraft.Operation;

import java.util.Queue;

public class FuelPriorityScheduler implements RunwayScheduler {
    @Override
    public Aircraft getNextAircraft(Queue<Aircraft> landingQueue, Queue<Aircraft> takeoffQueue) {
        return !landingQueue.isEmpty() ? landingQueue.poll() : takeoffQueue.poll();
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