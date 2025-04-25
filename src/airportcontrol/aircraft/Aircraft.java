package airportcontrol.aircraft;

import airportcontrol.mediator.TowerMediator;

public abstract class Aircraft {
    protected String id;
    protected int fuelLevel;
    protected TowerMediator mediator;

    protected Operation operation = Operation.LANDING;

    public Aircraft(String id, int fuelLevel, TowerMediator mediator) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.mediator = mediator;
    }

    public void checkFuelStatus() {
        if (fuelLevel < 10) {
            System.out.println(id + " MAYDAY: Low fuel emergency!");
        }
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public abstract void receive(String msg);
}