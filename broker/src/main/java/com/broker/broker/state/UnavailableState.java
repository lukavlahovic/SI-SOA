package com.broker.broker.state;

public class UnavailableState implements State {

    ContextAttribute contextAttribute;

    public UnavailableState(ContextAttribute contextAttribute){
        this.contextAttribute =contextAttribute;
    }

    @Override
    public boolean getService(ContextAttribute contextAttribute) {
        System.out.println("Service " + contextAttribute.getServis() + " is in unavailable state");
        return false;
    }

    @Override
    public boolean changeState(ContextAttribute contextAttribute) {
        contextAttribute.setState(contextAttribute.getAvailableState());
        System.out.println("SERVIS " + contextAttribute.getServis() + " IS NOW AVAILABLE");
        return true;
    }

    public String toString(){
        return "Unavailable State";
    }
}
