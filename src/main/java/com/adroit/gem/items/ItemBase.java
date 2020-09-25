package com.adroit.gem.items;

import com.adroit.gem.Gem;
import net.minecraft.item.Item;

public class ItemBase extends Item
{

    public ItemBase()
    {
        super(new Item.Properties().group(Gem.TAB));
    }
}
