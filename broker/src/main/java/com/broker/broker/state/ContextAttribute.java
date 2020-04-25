package com.broker.broker.state;

public class ContextAttribute {

    private State availableState;
    private State unavailableState;
    private State state;
    private String servis;

    public ContextAttribute(String servis){
        this.availableState = new AvailableState(this);
        this.unavailableState = new UnavailableState(this);
        this.servis = servis;
        this.state = availableState;
    }

    public void setState(State state){
        this.state = state;
    }

    public boolean getService(){
        return state.getService(this);
    }

    public State getState(){
        return state;
    }

    public boolean changeState(){
        return this.state.changeState(this);
    }

    public State getAvailableState() {
        return availableState;
    }

    public State getUnavailableState() {
        return unavailableState;
    }

    public String getServis() {
        return servis;
    }
}
