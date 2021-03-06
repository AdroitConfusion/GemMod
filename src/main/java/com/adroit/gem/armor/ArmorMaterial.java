package com.adroit.gem.armor;

import com.adroit.gem.Gem;
import com.adroit.gem.util.RegistryHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ArmorMaterial implements IArmorMaterial
{
    RUBY(Gem.MOD_ID+":ruby", 30, new int[] {2,5,6,2},12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.5F, () -> { return Ingredient.fromItems(RegistryHandler.RUBY.get()); }),
    SAPPHIRE(Gem.MOD_ID+":sapphire", 30, new int[] {2,5,6,2},12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.5F, () -> { return Ingredient.fromItems(RegistryHandler.SAPPHIRE.get()); })
    ;

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {11,16,15,13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    ArmorMaterial(String name, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial)
    {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn)
    {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()]*this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn)
    {
        return this.damageReduction[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent()
    {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial()
    {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }
}
