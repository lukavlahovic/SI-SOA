package com.broker.broker.state;

public class UnavailableState implements State {

    ContextAttribute contextAttribute;

    public UnavailableState(ContextAttribute contextAttribute){
        this.contextAttribute =contextAttribute;
    }

    @Override
    public boolean getService(ContextAttribute contextAttribute) {
        System.out.println("Service is in unavailable state");
        return false;
        //contextAttribute.setState(this);
    }

    @Override
    public boolean changeState(ContextAttribute contextAttribute) {
        contextAttribute.setState(contextAttribute.getAvailableState());
        return true;
    }

    public String toString(){
        return "Unavailable State";
    }
}
