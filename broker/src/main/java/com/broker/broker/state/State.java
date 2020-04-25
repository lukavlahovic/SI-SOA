package com.broker.broker.state;

public interface State {

    public boolean getService(ContextAttribute contextAttribute);
    public boolean changeState(ContextAttribute contextAttribute);
}
