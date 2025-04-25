package airportcontrol;

import airportcontrol.aircraft.*;
import airportcontrol.mediator.*;
import airportcontrol.scheduler.*;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.schedule(() -> tower.setScheduler(new AlternatingScheduler()), 7, TimeUnit.SECONDS);

        List<Aircraft> aircraft = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int fuelLevel = random.nextInt(100);
            Aircraft a = switch (random.nextInt(3)) {
                case 0 -> new PassengerPlane("PP" + i, fuelLevel, tower);
                case 1 -> new CargoPlane("CP" + i, fuelLevel, tower);
                default -> new Helicopter("H" + i, fuelLevel, tower);
            };
            a.setOperation(random.nextBoolean() ? Operation.LANDING : Operation.TAKEOFF);
            aircraft.add(a);
        }

        for (Aircraft a : aircraft) {
            executor.schedule(() -> {
                System.out.println("\n" + a.getId() +
                        (a.getOperation() == Operation.LANDING ? " requesting landing." : " requesting takeoff.") +
                        " Fuel level: " + a.getFuelLevel());
                a.checkFuelStatus();
                tower.requestRunway(a);
            }, random.nextInt(10), TimeUnit.SECONDS);
        }

        executor.schedule(() -> {
            executor.shutdown();
            System.out.println("\nSimulation completed");
        }, 15, TimeUnit.SECONDS);
    }
}
