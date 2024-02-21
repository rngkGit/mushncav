package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModItemGroups {
    public static final ItemGroup MUSHNCAVE_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MushroomsAndCaverns.MOD_ID, "mushncav"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mushncav"))
                    .icon(() -> new ItemStack(ModItems.MAGIC_STONE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.MAGIC_STONE);
                    }).build());
    public static void registerItemGroups(){
        MushroomsAndCaverns.LOGGER.info("Registering item groups for " + MushroomsAndCaverns.MOD_ID);
    }
}
