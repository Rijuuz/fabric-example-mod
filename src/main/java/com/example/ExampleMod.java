package com.backport.mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class BackportMod implements ModInitializer {
    public static final String MOD_ID = "backportmod";

    // Items Registration (Zelite friendly, crash nahi karega)
    public static final Item WIND_CHARGE = new WindChargeItem(new Item.Properties().stacksTo(64));
    public static final Item MACE = new Item(new Item.Properties().durability(500)); 

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "wind_charge"), WIND_CHARGE);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "mace"), MACE);
    }
}
