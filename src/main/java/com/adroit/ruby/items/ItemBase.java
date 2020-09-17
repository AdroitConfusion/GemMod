package com.adroit.ruby.items;

import com.adroit.ruby.Ruby;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item
{

    public ItemBase()
    {
        super(new Item.Properties().group(Ruby.TAB));
    }
}
