/**
 * Copyright (C) Glitchfiend
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package terrablender.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.config.TerraBlenderConfig;
import terrablender.example.TestRegion1;
import terrablender.example.TestRegion2;
import terrablender.example.TestSurfaceRuleData;

@Mod(value = TerraBlender.MOD_ID)
public class TerraBlenderForge {
    private static final TerraBlenderConfig CONFIG = new TerraBlenderConfig(FMLPaths.CONFIGDIR.get().resolve(TerraBlender.MOD_ID + ".toml"));

    public TerraBlenderForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::loadComplete);
        TerraBlender.setConfig(CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Weights are kept intentionally low as we add minimal biomes
            Regions.register(new TestRegion1(new ResourceLocation(TerraBlender.MOD_ID, "aether_1"), 2));
            Regions.register(new TestRegion2(new ResourceLocation(TerraBlender.MOD_ID, "aether_2"), 2));

            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.AETHER, TerraBlender.MOD_ID, TestSurfaceRuleData.makeRules());
        });
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
    }
}
