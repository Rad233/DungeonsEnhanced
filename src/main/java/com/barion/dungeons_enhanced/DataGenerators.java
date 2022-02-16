package com.barion.dungeons_enhanced;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators{
    private DataGenerators(){}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        dataGen.addProvider(new DELootTableProvider(dataGen));
        dataGen.addProvider(new DEAdvancementProvider(dataGen, exFileHelper));
    }
}