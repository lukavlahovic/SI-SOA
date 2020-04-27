package com.broker.broker.state;

public class AvailableState implements State {

    ContextAttribute contextAttribute;

    public AvailableState(ContextAttribute contextAttribute){
        this.contextAttribute =contextAttribute;
    }

    @Override
    public boolean getService(ContextAttribute contextAttribute) {
        System.out.println("Service " + contextAttribute.getServis() + " is in available state");
        return true;
    }

    @Override
    public boolean changeState(ContextAttribute contextAttribute) {
        contextAttribute.setState(contextAttribute.getUnavailableState());
        System.out.println("SERVIS " + contextAttribute.getServis() + " IS NOW UNAVAILABLE");
        return true;
    }

    @Override
    public String toString() {
        return "Available State";
    }
}
