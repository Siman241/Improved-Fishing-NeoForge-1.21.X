package net.siman.improvedfishingmod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //raw food
    public static final FoodProperties RAWCARP = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4f).build();
    public static final FoodProperties RAWCATFISH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build();
    public static final FoodProperties RAWPIKE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build();
    //cooked food
    public static final FoodProperties COOKEDCARP = new FoodProperties.Builder().nutrition(6).saturationModifier(1.4f).build();
    public static final FoodProperties COOKEDCATFISH = new FoodProperties.Builder().nutrition(7).saturationModifier(1.2f).build();
    public static final FoodProperties COOKEDPIKE = new FoodProperties.Builder().nutrition(5).saturationModifier(1.6f).build();
    //other
    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).build();
}
