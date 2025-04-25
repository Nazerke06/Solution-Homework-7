package airportcontrol.mediator;
import airportcontrol.aircraft.Aircraft;

public interface TowerMediator {
    void broadcast(String msg, Aircraft sender);
    boolean requestRunway(Aircraft a);
}
