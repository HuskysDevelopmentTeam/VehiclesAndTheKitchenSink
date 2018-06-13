package net.hdt.vks.init;

import net.hdt.vks.recipes.RecipeColorChromalux;
import net.hdt.vks.recipes.RecipeRefillChromalux;

public class ModRecipes {
    public static void register() {
        RegistrationHandler.Recipes.add(new RecipeColorChromalux());
        RegistrationHandler.Recipes.add(new RecipeRefillChromalux());
    }
}
