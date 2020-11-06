package com.adroit.gem.items.ruby_shield;

import com.adroit.gem.items.ruby_shield.RubyShieldModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.*;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;

import java.util.List;

public class RubyShieldTileEntityRenderer extends ItemStackTileEntityRenderer
{
    private final BannerTileEntity banner = new BannerTileEntity();
    private final ShieldModel modelShield = new RubyShieldModel();

    @Override
    public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Item item = itemStackIn.getItem();
        if (item instanceof BlockItem && item == Items.SHIELD)
        {
            boolean flag = itemStackIn.getChildTag("BlockEntityTag") != null;
            matrixStackIn.push();
            matrixStackIn.scale(1.0F, -1.0F, -1.0F);
            Material material = flag ? ModelBakery.LOCATION_SHIELD_BASE : ModelBakery.LOCATION_SHIELD_NO_PATTERN;
            IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(bufferIn, this.modelShield.getRenderType(material.getAtlasLocation()), false, itemStackIn.hasEffect()));
            this.modelShield.func_228294_b_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            if (flag) {
                List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.func_230138_a_(ShieldItem.getColor(itemStackIn), BannerTileEntity.func_230139_a_(itemStackIn));
                BannerTileEntityRenderer.func_230180_a_(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, this.modelShield.func_228293_a_(), material, false, list);
            } else {
                this.modelShield.func_228293_a_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            }

            matrixStackIn.pop();
        }
    }
}