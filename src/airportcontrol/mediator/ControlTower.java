package airportcontrol.mediator;

import airportcontrol.aircraft.*;
import airportcontrol.scheduler.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new PriorityQueue<>(Comparator.comparingInt(Aircraft::getFuelLevel));
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private Aircraft runwayOccupant = null;
    private boolean emergency = false;
    private final Logger logger = Logger.getLogger(ControlTower.class.getName());
    private final ScheduledExecutorService runwayManager = Executors.newSingleThreadScheduledExecutor();
    private RunwayScheduler scheduler = new FuelPriorityScheduler();

    public synchronized void setScheduler(RunwayScheduler scheduler) {
        this.scheduler = scheduler;
        logger.info("Switching to " + scheduler.getClass().getSimpleName());
        if (runwayOccupant == null) {
            Aircraft next = scheduler.getNextAircraft(landingQueue, takeoffQueue);
            if (next != null) grantRunway(next);
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft aircraft) {
        if (emergency && aircraft.getFuelLevel() > 10) {
            logger.info(aircraft.getId() + " request denied due to ongoing emergency");
            return false;
        }

        if (aircraft.getFuelLevel() < 10) {
            handleEmergency(aircraft);
            return true;
        }

        if (runwayOccupant == null) {
            grantRunway(aircraft);
            return true;
        }

        scheduler.addAircraft(aircraft, landingQueue, takeoffQueue);
        return false;
    }

    private void grantRunway(Aircraft aircraft) {
        runwayOccupant = aircraft;
        System.out.println("Runway granted to " + aircraft.getId() +
                (aircraft.getOperation() == Operation.LANDING ? " for landing" : " for takeoff"));
    }

    private void handleEmergency(Aircraft aircraft) {
        emergency = true;
        if (runwayOccupant != null && runwayOccupant.getOperation() == Operation.TAKEOFF) {
            System.out.println("Aborting takeoff for " + runwayOccupant.getId());
            takeoffQueue.offer(runwayOccupant);
        }
        grantRunway(aircraft);
        broadcast("EMERGENCY: " + aircraft.getId() + " requires immediate landing!", aircraft);
    }

    public void broadcast(String msg, Aircraft sender) {
        System.out.println("Broadcasting: " + msg);
    }
}