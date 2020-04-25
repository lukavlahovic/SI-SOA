package com.broker.broker.state;

public class AvailableState implements State {

    ContextAttribute contextAttribute;

    public AvailableState(ContextAttribute contextAttribute){
        this.contextAttribute =contextAttribute;
    }

    @Override
    public boolean getService(ContextAttribute contextAttribute) {
        System.out.println("Service is in available state");
        //nesto radi
        System.out.println(contextAttribute.getServis());
        return true;
    }

    @Override
    public boolean changeState(ContextAttribute contextAttribute) {
        contextAttribute.setState(contextAttribute.getUnavailableState());
        return true;
    }

    @Override
    public String toString() {
        return "Available State";
    }
}
