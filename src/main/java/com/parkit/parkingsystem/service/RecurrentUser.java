package com.parkit.parkingsystem.service;
import java.util.HashMap;
import java.util.Map;

public class ParkingSystem {
    private Map<String, Boolean> recurrentUsers;
    private double normalTariff;

    public ParkingSystem(double normalTariff) {
        this.recurrentUsers = new HashMap<>();
        this.normalTariff = normalTariff;
    }

    public void enterGarage(String licensePlate) {
        if (recurrentUsers.containsKey(licensePlate)) {
            System.out.println("Heureux de vous revoir ! En tant qu'utilisateur régulier de notre parking, vous allez obtenir une remise de 5%");
            recurrentUsers.put(licensePlate, true);
        } else {
            recurrentUsers.put(licensePlate, false);
        }
    }

    public double calculateTariff(String licensePlate) {
        if (recurrentUsers.get(licensePlate)) {
            return normalTariff * 0.95; // 5% discount
        } else {
            return normalTariff;
        }
    }
}