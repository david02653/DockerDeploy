package com.example.demo.entity.Rasa.v2.domain;

public class SessionConfig {
    private int expireTime;
    private boolean carrySlot = false;

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public void setCarrySlot(boolean carrySlot) {
        this.carrySlot = carrySlot;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public boolean isCarrySlot() {
        return carrySlot;
    }

    @Override
    public String toString() {
        return "SessionConfig{" +
                "expireTime=" + expireTime +
                ", carrySlot=" + carrySlot +
                '}';
    }
}
