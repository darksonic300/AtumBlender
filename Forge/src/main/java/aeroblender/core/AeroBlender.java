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
package aeroblender.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aeroblender.config.AeroBlenderConfig;

import static aeroblender.core.AeroBlender.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AeroBlender {
    public static final String MOD_ID = "aeroblender";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static AeroBlenderConfig CONFIG = new AeroBlenderConfig(FMLPaths.CONFIGDIR.get().resolve(MOD_ID + ".toml"));


    public AeroBlender()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::loadComplete);
        AeroBlender.setConfig(CONFIG);
    }

    public static void setConfig(AeroBlenderConfig config)
    {
        AeroBlender.CONFIG = config;
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
    }
}
