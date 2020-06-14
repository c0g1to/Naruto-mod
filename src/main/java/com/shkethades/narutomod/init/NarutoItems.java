package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.item.NarutoArmorItem;
import com.shkethades.narutomod.item.FoodItem;
import com.shkethades.narutomod.item.MeleeItem;
import com.shkethades.narutomod.item.ShurikenItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NarutoItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NarutoMod.MOD_ID);

    public static final RegistryObject<Item> SHURIKEN = ITEMS.register("shuriken", ShurikenItem::new);

    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai", () -> new SwordItem(ItemTier.IRON, 1, -0.8F, new MeleeItem.Properties().group(NarutoMod.GROUP)));
    public static final RegistryObject<Item> KATANA = ITEMS.register("katana", () -> new SwordItem(MeleeItem.Tiers.STEEL, 3, -1.6F, new MeleeItem.Properties().group(NarutoMod.GROUP)));

    public static final RegistryObject<Item> HEADBAND = ITEMS.register("headband", () -> new ArmorItem(NarutoArmorItem.MaterialTiers.SHINOBI, EquipmentSlotType.HEAD, new NarutoArmorItem.Properties().group(NarutoMod.GROUP)));
    public static final RegistryObject<Item> FLAK_JACKET = ITEMS.register("flak_jacket", () -> new ArmorItem(NarutoArmorItem.MaterialTiers.SHINOBI, EquipmentSlotType.CHEST, new NarutoArmorItem.Properties().group(NarutoMod.GROUP)));
    public static final RegistryObject<Item> ANBU_MASK = ITEMS.register("anbu_mask", () -> new ArmorItem(NarutoArmorItem.MaterialTiers.ANBU, EquipmentSlotType.HEAD, new NarutoArmorItem.Properties().group(NarutoMod.GROUP)));
    public static final RegistryObject<Item> ANBU_VEST = ITEMS.register("anbu_vest", () -> new ArmorItem(NarutoArmorItem.MaterialTiers.ANBU, EquipmentSlotType.CHEST, new NarutoArmorItem.Properties().group(NarutoMod.GROUP)));

    public static final RegistryObject<Item> RAMEN = ITEMS.register("ramen", () -> FoodItem.RAMEN);
    public static final RegistryObject<Item> BATTLE_PILL = ITEMS.register("battle_pill", () -> FoodItem.BATTLE_PILL);

    public static final RegistryObject<BlockItem> EXPLOSIVE_TAG = ITEMS.register("explosive_tag", () -> new BlockItem(NarutoBlocks.EXPLOSIVE_TAG.get(), new Item.Properties().maxStackSize(16).group(NarutoMod.GROUP)));
}