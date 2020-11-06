package com.adroit.gem;

import com.adroit.gem.util.RegistryHandler;
import com.adroit.gem.world.gen.RubyOreGen;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("gemmod")
@Mod.EventBusSubscriber(modid = Gem.MOD_ID)
public class Gem
{
    private static final Logger LOGGER = LogManager.getLogger(Gem.MOD_ID);
    public static final String MOD_ID = "gemmod";

    public Gem()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event)
    {
        if (event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE))
            event.addSprite(new ResourceLocation(Gem.MOD_ID, "model/ruby_shield"));

    }
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
        RubyOreGen.generateOre();
    }


    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {

    }

    public static final ItemGroup TAB = new ItemGroup("Ruby Mod")
    {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon()
        {
            return new ItemStack(RegistryHandler.RUBY.get());
        }
    };
    @SubscribeEvent
    public static void arrowParry(ProjectileImpactEvent.Arrow event)
    {

        final AbstractArrowEntity projectile = event.getArrow();
        projectile.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
        projectile.setNoGravity(true);

        if(!projectile.getEntityWorld().isRemote() && event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY)
        {
            Entity entityHit = ((EntityRayTraceResult)event.getRayTraceResult()).getEntity();
            if (event.getEntity() != null && entityHit instanceof LivingEntity)
            {
                LivingEntity entityBlocking = (LivingEntity) entityHit;
                if(entityBlocking.canBlockDamageSource(new DamageSource("parry")
                {
                    @Override
                    public Vec3d getDamageLocation()
                    {
                        return projectile.getPositionVector();
                    }
                }) && (entityBlocking.getItemInUseMaxCount() <= 20))
                {
                    Vec3d playerVec3 = entityBlocking.getLookVec();
                    projectile.setShooter(entityBlocking);
                    projectile.shoot(playerVec3.x, playerVec3.y+0.1, playerVec3.z, 1.1F, 0.1F);
                    event.setCanceled(true);
                }
            }
        }
    }
    @SubscribeEvent
    public static void throwableParry(ProjectileImpactEvent.Throwable event)
    {

        final ThrowableEntity projectile = event.getThrowable();

        if(!projectile.getEntityWorld().isRemote())
        {
            if(event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY)
            {
                Entity entityHit = ((EntityRayTraceResult)event.getRayTraceResult()).getEntity();
                if (event.getEntity() != null && entityHit instanceof LivingEntity)
                {
                    LivingEntity entityBlocking = (LivingEntity) entityHit;
                    if(entityBlocking.canBlockDamageSource(new DamageSource("parry_this")
                    {
                        @Override
                        public Vec3d getDamageLocation()
                        {
                            return projectile.getPositionVector();
                        }
                    }))
                    {
                        Vec3d playerVec3 = entityBlocking.getLookVec();
                        projectile.shoot(entityBlocking, (float)playerVec3.x, (float)playerVec3.y, (float)playerVec3.z, 1.1F, 0.1F);  // reflect faster and more accurately
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void fireballParry(ProjectileImpactEvent.Fireball event)
    {

        final DamagingProjectileEntity projectile = event.getFireball();

        if(!projectile.getEntityWorld().isRemote())
        {
            if(event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY)
            {
                Entity entityHit = ((EntityRayTraceResult)event.getRayTraceResult()).getEntity();
                if (event.getEntity() != null && entityHit instanceof LivingEntity)
                {
                    LivingEntity entityBlocking = (LivingEntity) entityHit;
                    if(entityBlocking.canBlockDamageSource(new DamageSource("parry_this")
                    {
                        @Override
                        public Vec3d getDamageLocation()
                        {
                            return projectile.getPositionVector();
                        }
                    }))
                    {
                        Vec3d playerVec3 = entityBlocking.getLookVec();

                        projectile.accelerationX = playerVec3.x * 0.1D;
                        projectile.accelerationY = playerVec3.y * 0.1D;
                        projectile.accelerationZ = playerVec3.z * 0.1D;

                        projectile.shootingEntity = entityBlocking;

                        event.setCanceled(true);
                    }
                }
            }
        }
    }

}
