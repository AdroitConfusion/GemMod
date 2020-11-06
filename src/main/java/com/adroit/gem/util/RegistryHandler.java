package com.adroit.gem.util;

import com.adroit.gem.Gem;
import com.adroit.gem.armor.ModArmorMaterial;
import com.adroit.gem.blocks.BlockItemBase;
import com.adroit.gem.blocks.RubyBlock;
import com.adroit.gem.blocks.RubyOre;
import com.adroit.gem.items.ItemBase;
import com.adroit.gem.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class RegistryHandler
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Gem.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Gem.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Gem.MOD_ID);


    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", ItemBase::new);

    //Tools
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () ->
        new SwordItem(ModItemTier.RUBY, 5, -2.4F, new Item.Properties().group(Gem.TAB))
    );
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () ->
            new PickaxeItem(ModItemTier.RUBY, 3, -2.8F, new Item.Properties().group(Gem.TAB))
    );
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () ->
            new ShovelItem(ModItemTier.RUBY, 3.5F, -3.0F, new Item.Properties().group(Gem.TAB))
    );
    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () ->
            new AxeItem(ModItemTier.RUBY, 7, -3.0F, new Item.Properties().group(Gem.TAB))
    );
    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () ->
            new HoeItem(ModItemTier.RUBY, 0.0F, new Item.Properties().group(Gem.TAB))
    );

    //Armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(Gem.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(Gem.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(Gem.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(Gem.TAB)));

    //Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", RubyOre::new);

    //Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItemBase(RUBY_BLOCK.get()));
    public static final RegistryObject<Item> RUBY_ORE_ITEM = ITEMS.register("ruby_ore", () -> new BlockItemBase(RUBY_ORE.get()));

    //Entities
    //public static final RegistryObject<EntityType<>> = ENTITIES_TYPES.register("ruby_shield", )

    //Entity Items
    public static final RegistryObject<ShieldItem> RUBY_SHIELD = ITEMS.register("ruby_shield", () ->
            new ShieldItem(new Item.Properties().maxDamage(500).group(Gem.TAB)));


}
