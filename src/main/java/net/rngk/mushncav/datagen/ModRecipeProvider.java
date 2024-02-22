package net.rngk.mushncav.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItems;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    //private static final List<ItemConvertible> NAME = List.of(ModItems., ModBlocks.,...);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        //Smelting
        //offerSmelting(exporter, NAME, RecipeCategory.MISC, ModItems., 0.7f, 200, "group");
        //offerBlasting^^

        //Recipes
        /*ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ITEM, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', Item)
                //unlocking recipe
                .criterion(hasItem(Item. ), conditionsFromItem(Item.))
                //name
                .offerTo(exporter, new Identifier(getRecipeName(Item.)));
         */
    }
}
