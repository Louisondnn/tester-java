package com.parkit.parkingsystem.service;
import java.util.HashMap;
import java.util.Map;


public class RecurrentUser  {
    private Map<String, Boolean> recurrentUsers; // Indique si l'utilisateur est récurrent
    private double normalTariff;
    private Map<String, Boolean> usersFromDatabase; 
    

    public RecurrentUser (double normalTariff) {
        this.recurrentUsers = new HashMap<>();
        this.normalTariff = normalTariff;
        this.usersFromDatabase = new HashMap<>(); 

    }
    public void loadRecurrentUsersFromDataBase() {
        usersFromDatabase.put("ABC123", true); //  récurrent
        usersFromDatabase.put("XYZ789", false); // New
        recurrentUsers.putAll(usersFromDatabase);
    }

    public void enterGarage(String licensePlate) {
        if (recurrentUsers.containsKey(licensePlate)) {
            System.out.println("Heureux de vous revoir ! En tant qu'utilisateur régulier de notre parking, vous allez obtenir une remise de 5%");
            recurrentUsers.put(licensePlate, true); // Marquer comme récurrent
        } else {
            recurrentUsers.put(licensePlate, false); // Premier passage
            System.out.println("Bienvenue dans notre garage !");
        }
    }

    public double calculateTariff(String licensePlate) {
        if (recurrentUsers.containsKey(licensePlate) && recurrentUsers.get(licensePlate)) {
            return normalTariff * 0.95; // 5% remise pour les utilisateurs récurrents
        } else {
            return normalTariff; // Pas de remise pour les nouveaux utilisateurs
        }
    }

    public void exitGarage(String licensePlate) {
        if (recurrentUsers.containsKey(licensePlate)) {
            if (recurrentUsers.get(licensePlate)) {
                System.out.println("Merci pour votre visite ! Vous avez obtenu une remise de 5% sur votre tarif.");
            } else {
                System.out.println("Merci pour votre visite !");
            }
        } else {
            System.out.println("Merci pour votre visite !");
        }
    }
   
    
}