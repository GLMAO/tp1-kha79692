package com.example.lookup;

import java.util.HashMap;
import java.util.Map;

public class Lookup {

    // Singleton
    private static Lookup instance = null;

    private Map<Class<?>, Object> services = new HashMap<>();

    private Lookup() {
        // constructeur privé pour le Singleton
    }

    public static synchronized Lookup getInstance() {
        if (instance == null) {
            instance = new Lookup();
        }
        return instance;
    }

    // méthode pour enregistrer un service
    public <T> void subscribeService(Class<? super T> service, T instance) {
        if (service == null || instance == null) {
            throw new IllegalArgumentException("Service et instance ne peuvent pas être nuls");
        }
        // Vérification que l'instance est compatible avec la classe
        if (!service.isInstance(instance)) {
            throw new IllegalArgumentException("L'instance n'est pas compatible avec la classe " + service.getName());
        }
        services.put(service, instance);
    }

    // méthode pour récupérer un service
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> service) {
        Object instance = services.get(service);
        if (instance == null) {
            return null;
        }
        // Cast sûr grâce à la vérification lors de l'enregistrement
        return (T) instance;
    }
}
