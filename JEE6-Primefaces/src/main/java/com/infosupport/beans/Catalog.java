package com.infosupport.beans;

import java.io.File;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Catalog {

    private Map<Integer, Sandwich> sandwiches = new LinkedHashMap<>();
    
    @PostConstruct
    protected void setupCatalog() {
        sandwiches.put(1, new Sandwich(1, "Egg sandwich", "Delicious eggs on wholeweat bread, healthy as ever", 4, "products/egg.jpg"));
        sandwiches.put(2, new Sandwich(2, "Burger", "Tasty burger with real beef", 5, "products/burger.jpg"));
        sandwiches.put(3, new Sandwich(3, "Cheesesteak", "Philadelphia style cheesesteak", 7, "products/cheesesteak.jpg"));
        sandwiches.put(4, new Sandwich(4, "Fluffernutter sandwich", "I have no clue what's on it, but it tastes good!", 5, "products/fluffernutter.jpg"));
        sandwiches.put(5, new Sandwich(5, "Ham/cheese sandwich", "The classic that never gets old", 5, "products/hamcheese.jpg"));
        sandwiches.put(6, new Sandwich(6, "Radish sandwich", "Feeling healthy? You should!", 5, "products/radish.jpg"));
        sandwiches.put(7, new Sandwich(7, "Roast beef", "Seriously delicious beef", 5, "products/roastbeef.jpg"));
        sandwiches.put(8, new Sandwich(8, "Wrap", "Technically not a sandwich, but, whatever...", 5, "products/wrap.jpg"));
    }
    
    public Collection<Sandwich> getSandwiches() {
       
        return new ArrayList<>(sandwiches.values());
    }
    
    public Sandwich getSandwichById(int id) {
        return sandwiches.get(id);
    }
    
}
