package com.abn.recipeapi_v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeIDGenerator {
    private final List<Integer> usedIds = new ArrayList<>();
    private int min = 0;
    private int max = 1000;
    public Integer generateID() {

        if (usedIds.size() == max) {
            min = max;
            max += 1000;
        }
        Random r = new Random();
        int randomNumber = r.nextInt(min,max);

        while (usedIds.contains(randomNumber)) {
            randomNumber = r.nextInt(min,max);
        }

        usedIds.add(randomNumber);

        return randomNumber;
    }
}
