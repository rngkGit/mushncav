package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.rngk.mushncav.item.ModItems;

public class GlowingBlueberryBushBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<GlowingBlueberryBushBlock> CODEC = GlowingBlueberryBushBlock.createCodec(GlowingBlueberryBushBlock::new);
    private static final float MIN_MOVEMENT_FOR_DAMAGE = 0.003f;
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MapCodec<GlowingBlueberryBushBlock> getCodec() {
        return CODEC;
    }

    public GlowingBlueberryBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.GLOWING_BLUEBERRY);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) == 0) {
            return SMALL_SHAPE;
        }
        if (state.get(AGE) < 3) {
            return LARGE_SHAPE;
        }
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(AGE);
        // Sweet berry pacing, but without the >=9 light requirement so cave bushes regrow.
        if (i < 3 && random.nextInt(5) == 0) {
            BlockState blockState = state.with(AGE, i + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.FOX || entity.getType() == EntityType.BEE) {
            return;
        }

        entity.slowMovement(state, new Vec3d(0.8f, 0.75, 0.8f));
        if (world.isClient() || state.get(AGE) <= 0 || !(world instanceof ServerWorld serverWorld)) {
            return;
        }

        double deltaX = Math.abs(entity.getX() - entity.lastRenderX);
        double deltaZ = Math.abs(entity.getZ() - entity.lastRenderZ);
        if (deltaX >= MIN_MOVEMENT_FOR_DAMAGE || deltaZ >= MIN_MOVEMENT_FOR_DAMAGE) {
            entity.damage(serverWorld, serverWorld.getDamageSources().sweetBerryBush(), 1.0f);
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(AGE) != MAX_AGE && stack.isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS;
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        boolean fullyGrown = age == MAX_AGE;

        if (age > 1) {
            int count = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(ModItems.GLOWING_BLUEBERRY, count + (fullyGrown ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState pickedState = state.with(AGE, 1);
            world.setBlockState(pos, pickedState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, pickedState));
            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, i), Block.NOTIFY_LISTENERS);
    }
}
