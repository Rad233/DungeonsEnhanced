package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.function.Supplier;

public class DEFlyingStructure extends DEBaseStructure{
    public DEFlyingStructure(StructureSettings settings, DEStructurePiece[] variants, Supplier<StructureType<?>> type) {
        super(settings, variants, DEFlyingStructure::assemble, type);
    }

    public static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override
    protected boolean checkLocation(GenerationContext context) {
        return DETerrainAnalyzer.isGroundLevelBelow(context.chunkPos(), context.chunkGenerator(), 224, context.heightAccessor(), context.randomState());
    }

    @Override
    protected BlockPos getGenPos(GenerationContext context) {
        BlockPos rawPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int y;
        int minY = rawPos.getY() + 64;
        int maxY = 288;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        else {y = maxY;}
        return rawPos.atY(y);
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.FlyingDutchman.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.FlyingDutchman.getPieceType(), serializationContext, nbt);
        }
    }
}