/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.fabric.init.ModClientFabric;
import biomesoplenty.init.ModBlockEntities;
import biomesoplenty.init.ModBlocks;
import biomesoplenty.init.ModClient;
import biomesoplenty.init.ModCreativeTab;
import biomesoplenty.init.ModEntities;
import biomesoplenty.init.ModFluids;
import biomesoplenty.init.ModItems;
import biomesoplenty.init.ModParticles;
import biomesoplenty.init.ModSounds;
import biomesoplenty.worldgen.carver.BOPWorldCarvers;
import biomesoplenty.worldgen.feature.BOPBaseFeatures;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import terrablender.api.TerraBlenderApi;

public class BiomesOPlentyFabric implements ModInitializer, ClientModInitializer, TerraBlenderApi
{
    @Override
    public void onInitialize()
    {
        // GlitchCore posts its registry events from its own Fabric initializer. In a
        // large mod set that initializer may run after consumers such as Dynamic
        // Trees, so register Fabric built-ins synchronously from BOP's initializer.
        BiomesOPlenty.init(false);
        ModBlocks.setup((id, value) -> Registry.register(BuiltInRegistries.BLOCK, id, value));
        ModBlockEntities.registerBlockEntities((id, value) -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, value));
        ModFluids.registerFluids((id, value) -> Registry.register(BuiltInRegistries.FLUID, id, value));
        ModItems.setup((id, value) -> Registry.register(BuiltInRegistries.ITEM, id, value));
        BOPBaseFeatures.registerFeatures((id, value) -> Registry.register(BuiltInRegistries.FEATURE, id, value));
        BOPWorldCarvers.registerCarvers((id, value) -> Registry.register(BuiltInRegistries.CARVER, id, value));
        ModEntities.registerEntities((id, value) -> Registry.register(BuiltInRegistries.ENTITY_TYPE, id, value));
        ModCreativeTab.registerCreativeTabs((id, value) -> Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, id, value));
        ModParticles.registerParticles((id, value) -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, id, value));
        ModSounds.registerSounds((id, value) -> Registry.register(BuiltInRegistries.SOUND_EVENT, id, value));
    }

    @Override
    public void onInitializeClient()
    {
        ModClient.setup();
        ModClientFabric.setup();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomesOPlenty.setupTerraBlender();
    }
}
