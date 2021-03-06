package flaxbeard.steamcraft;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import flaxbeard.steamcraft.api.ICrucibleMold;
import flaxbeard.steamcraft.api.SteamcraftRegistry;
import flaxbeard.steamcraft.api.enhancement.IEnhancement;
import flaxbeard.steamcraft.api.exosuit.ExosuitPlate;
import flaxbeard.steamcraft.item.ItemEnhancementFireMusket;
import flaxbeard.steamcraft.item.ItemExosuitArmor;
import flaxbeard.steamcraft.item.ItemFirearm;
import flaxbeard.steamcraft.item.ItemIngotMold;
import flaxbeard.steamcraft.item.ItemNuggetMold;
import flaxbeard.steamcraft.item.ItemPlateMold;
import flaxbeard.steamcraft.item.ItemSpyglass;
import flaxbeard.steamcraft.item.ItemSteamcraftBook;
import flaxbeard.steamcraft.item.ItemSteamcraftIngot;
import flaxbeard.steamcraft.item.ItemSteamcraftNugget;
import flaxbeard.steamcraft.item.ItemSteamcraftPlate;
import flaxbeard.steamcraft.item.tool.ItemSteamcraftAxe;
import flaxbeard.steamcraft.item.tool.ItemSteamcraftHoe;
import flaxbeard.steamcraft.item.tool.ItemSteamcraftPickaxe;
import flaxbeard.steamcraft.item.tool.ItemSteamcraftShovel;
import flaxbeard.steamcraft.item.tool.ItemSteamcraftSword;
import flaxbeard.steamcraft.item.tool.steam.ItemSteamDrill;

public class SteamcraftItems {
	public static HashMap<String,Item> tools = new HashMap<String,Item>();
	public static Item musketCartridge;
    public static Item musket;
    public static Item pistol;
    public static Item revolver;
    public static Item blunderbuss;
    public static Item spyglass;
    
    public static Item blankMold;
    public static Item ingotMold;
    public static Item nuggetMold;
    public static Item plateMold;
    
    public static Item steamcraftIngot;
    public static Item steamcraftNugget;
    public static Item steamcraftPlate;
    
    public static Item enhancementAblaze;
    
    public static Item upgradeFlippers;
    
    public static Item book;

    public static Item exoArmorHead;
    public static Item exoArmorBody;
    public static Item exoArmorLegs;
    public static Item exoArmorFeet;
    
    public static Item steamDrill;
    
    public static void registerItems() {
		
		book = new ItemSteamcraftBook().setUnlocalizedName("steamcraft:book").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:book");
		GameRegistry.registerItem(book, "book");
		
    	musketCartridge = new Item().setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:musketCartridge").setTextureName("steamcraft:cartridge");
		GameRegistry.registerItem(musketCartridge, "musketCartridge");
		musket = new ItemFirearm(20.0F, 84,0.2F, 5.0F, false, 1).setUnlocalizedName("steamcraft:musket").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:weaponMusket");
		GameRegistry.registerItem(musket, "musket");
		pistol = new ItemFirearm(15.0F, 42,0.5F, 2.0F, false, 1).setUnlocalizedName("steamcraft:pistol").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:weaponPistol");
		GameRegistry.registerItem(pistol, "pistol");
		revolver = new ItemFirearm(12.5F, 84,0.7F, 1.0F, false, 6).setUnlocalizedName("steamcraft:revolver").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:weaponRevolver");
		GameRegistry.registerItem(revolver, "revolver");
		blunderbuss = new ItemFirearm(25.0F, 95,3.5F, 7.5F, true, 1).setUnlocalizedName("steamcraft:blunderbuss").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:weaponBlunderbuss");
		GameRegistry.registerItem(blunderbuss, "blunderbuss");
		spyglass = new ItemSpyglass().setUnlocalizedName("steamcraft:spyglass").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:spyglass");
		GameRegistry.registerItem(spyglass, "spyglass");
		SteamcraftRegistry.registerEnhancement((IEnhancement) spyglass);
		enhancementAblaze = new ItemEnhancementFireMusket().setUnlocalizedName("steamcraft:enhancementAblaze").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:enhancementAblaze");
		GameRegistry.registerItem(enhancementAblaze, "enhancementAblaze");
		SteamcraftRegistry.registerEnhancement((IEnhancement) enhancementAblaze);
		
	 	upgradeFlippers = new Item().setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:upgradeFlippers").setTextureName("steamcraft:upgradeFlippers");
		GameRegistry.registerItem(upgradeFlippers, "upgradeFlippers");
		
		exoArmorHead = new ItemExosuitArmor(0).setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:exoArmorHead").setTextureName("steamcraft:exoArmorHead");
		GameRegistry.registerItem(exoArmorHead, "exoArmorHead");
		exoArmorBody = new ItemExosuitArmor(1).setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:exoArmorBody").setTextureName("steamcraft:exoArmorBody");
		GameRegistry.registerItem(exoArmorBody, "exoArmorBody");
		exoArmorLegs = new ItemExosuitArmor(2).setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:exoArmorLegs").setTextureName("steamcraft:exoArmorLegs");
		GameRegistry.registerItem(exoArmorLegs, "exoArmorLegs");
		exoArmorFeet = new ItemExosuitArmor(3).setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:exoArmorFeet").setTextureName("steamcraft:exoArmorFeet");
		GameRegistry.registerItem(exoArmorFeet, "exoArmorFeet");
		
		steamDrill = new ItemSteamDrill().setUnlocalizedName("steamcraft:steamDrill").setCreativeTab(Steamcraft.tabTools);
		GameRegistry.registerItem(steamDrill, "steamDrill");
		
		ingotMold = new ItemIngotMold().setUnlocalizedName("steamcraft:ingotMold").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:moldIngot");
		GameRegistry.registerItem(ingotMold, "ingotMold");
		SteamcraftRegistry.addCarvableMold((ICrucibleMold) ingotMold);
		nuggetMold = new ItemNuggetMold().setUnlocalizedName("steamcraft:nuggetMold").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:moldNugget");
		GameRegistry.registerItem(nuggetMold, "nuggetMold");
		SteamcraftRegistry.addCarvableMold((ICrucibleMold) nuggetMold);
		plateMold = new ItemPlateMold().setUnlocalizedName("steamcraft:plateMold").setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:moldPlate");
		GameRegistry.registerItem(plateMold, "plateMold");
		SteamcraftRegistry.addCarvableMold((ICrucibleMold) plateMold);
		blankMold = new Item().setUnlocalizedName("steamcraft:blankMold").setMaxStackSize(1).setCreativeTab(Steamcraft.tab).setTextureName("steamcraft:moldBlank");
		GameRegistry.registerItem(blankMold, "blankMold");
		
		steamcraftIngot = new ItemSteamcraftIngot().setUnlocalizedName("steamcraft:ingot").setCreativeTab(Steamcraft.tab);
		GameRegistry.registerItem(steamcraftIngot, "steamcraftIngot");
		OreDictionary.registerOre("ingotCopper", new ItemStack(steamcraftIngot,1,0));
		OreDictionary.registerOre("ingotZinc", new ItemStack(steamcraftIngot,1,1));
		OreDictionary.registerOre("ingotBrass", new ItemStack(steamcraftIngot,1,2));
		
		ToolMaterial brass = EnumHelper.addToolMaterial("BRASS", 2, 191, 7.0F, 2.5F, 14);
		registerToolSet(brass, "Brass", "ingotBrass", true);
		registerGildedTools();
		
		steamcraftNugget = new ItemSteamcraftNugget().setUnlocalizedName("steamcraft:nugget").setCreativeTab(Steamcraft.tab);
		GameRegistry.registerItem(steamcraftNugget, "steamcraftNugget");
		OreDictionary.registerOre("nuggetCopper", new ItemStack(steamcraftNugget,1,0));
		OreDictionary.registerOre("nuggetZinc", new ItemStack(steamcraftNugget,1,1));
		OreDictionary.registerOre("nuggetIron", new ItemStack(steamcraftNugget,1,2));
		OreDictionary.registerOre("nuggetBrass", new ItemStack(steamcraftNugget,1,3));
		
		steamcraftPlate = new ItemSteamcraftPlate().setUnlocalizedName("steamcraft:plate").setCreativeTab(Steamcraft.tab);
		GameRegistry.registerItem(steamcraftPlate, "steamcraftPlate");
		OreDictionary.registerOre("plateCopper", new ItemStack(steamcraftPlate,1,0));
		OreDictionary.registerOre("plateZinc", new ItemStack(steamcraftPlate,1,1));
		OreDictionary.registerOre("plateIron", new ItemStack(steamcraftPlate,1,2));
		OreDictionary.registerOre("plateGold", new ItemStack(steamcraftPlate,1,3));
		OreDictionary.registerOre("plateBrass", new ItemStack(steamcraftPlate,1,4));
		OreDictionary.registerOre("plateThaumium", new ItemStack(steamcraftPlate,1,5));
		SteamcraftRegistry.addExosuitPlate(new ExosuitPlate("Copper","plateCopper","Copper","Copper"));
		SteamcraftRegistry.addExosuitPlate(new ExosuitPlate("Iron","plateIron","Iron","Iron"));
		SteamcraftRegistry.addExosuitPlate(new ExosuitPlate("Gold","plateGold","Gold","Gold"));

    }
    
    public static void registerToolSet(ToolMaterial tool, String string, Object repair, boolean addRecipes) {
    	Item pick = new ItemSteamcraftPickaxe(tool, repair).setUnlocalizedName("steamcraft:pick"+string).setCreativeTab(Steamcraft.tabTools).setTextureName("steamcraft:pick"+string);
 		GameRegistry.registerItem(pick, "pick"+string);
 		tools.put("pick"+string,pick);
 		
    	Item axe = new ItemSteamcraftAxe(tool, repair).setUnlocalizedName("steamcraft:axe"+string).setCreativeTab(Steamcraft.tabTools).setTextureName("steamcraft:axe"+string);
 		GameRegistry.registerItem(axe, "axe"+string);
 		tools.put("axe"+string,axe);
 		
    	Item shovel = new ItemSteamcraftShovel(tool, repair).setUnlocalizedName("steamcraft:shovel"+string).setCreativeTab(Steamcraft.tabTools).setTextureName("steamcraft:shovel"+string);
 		GameRegistry.registerItem(shovel, "shovel"+string);
 		tools.put("shovel"+string,shovel);
 		
 	   	Item hoe = new ItemSteamcraftHoe(tool, repair).setUnlocalizedName("steamcraft:hoe"+string).setCreativeTab(Steamcraft.tabTools).setTextureName("steamcraft:hoe"+string);
 		GameRegistry.registerItem(hoe, "hoe"+string);
 		tools.put("hoe"+string,hoe);
 		
 	   	Item sword = new ItemSteamcraftSword(tool, repair).setUnlocalizedName("steamcraft:sword"+string).setCreativeTab(Steamcraft.tabTools).setTextureName("steamcraft:sword"+string);
 		GameRegistry.registerItem(sword, "sword"+string);
 		tools.put("sword"+string,sword);
 		if (addRecipes) {
 			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pick), "xxx", " s ", " s ", 
 			        'x', repair, 's', "stickWood"));
 			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(axe), "xx", "xs", " s", 
 			        'x', repair, 's', "stickWood"));
 			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(shovel), "x", "s", "s", 
 			        'x', repair, 's', "stickWood"));
 			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hoe), "xx", " s", " s", 
 			        'x', repair, 's', "stickWood"));
 			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sword), "x", "x", "s", 
 			        'x', repair, 's', "stickWood"));
 		}
    }
    
    public static void registerGildedTools() {
		ToolMaterial tool = EnumHelper.addToolMaterial("GILDEDGOLD", 2, 250, 6.0F, 2.0F, 22);
		
    	Item pick = new ItemSteamcraftPickaxe(tool, new ItemStack(steamcraftIngot,1,3)).setUnlocalizedName("steamcraft:pickGildedGold").setCreativeTab(Steamcraft.tabTools).setTextureName("gold_pickaxe");
 		GameRegistry.registerItem(pick, "pickGildedGold");
 		tools.put("pickGildedGold",pick);
 		
    	Item axe = new ItemSteamcraftAxe(tool, new ItemStack(steamcraftIngot,1,3)).setUnlocalizedName("steamcraft:axeGildedGold").setCreativeTab(Steamcraft.tabTools).setTextureName("gold_axe");
 		GameRegistry.registerItem(axe, "axeGildedGold");
 		tools.put("axeGildedGold",axe);
 		
    	Item shovel = new ItemSteamcraftShovel(tool, new ItemStack(steamcraftIngot,1,3)).setUnlocalizedName("steamcraft:shovelGildedGold").setCreativeTab(Steamcraft.tabTools).setTextureName("gold_shovel");
 		GameRegistry.registerItem(shovel, "shovelGildedGold");
 		tools.put("shovelGildedGold",shovel);
 		
 	   	Item hoe = new ItemSteamcraftHoe(tool, new ItemStack(steamcraftIngot,1,3)).setUnlocalizedName("steamcraft:hoeGildedGold").setCreativeTab(Steamcraft.tabTools).setTextureName("gold_hoe");
 		GameRegistry.registerItem(hoe, "hoeGildedGold");
 		tools.put("hoeGildedGold",hoe);
 		
 	   	Item sword = new ItemSteamcraftSword(tool, new ItemStack(steamcraftIngot,1,3)).setUnlocalizedName("steamcraft:swordGildedGold").setCreativeTab(Steamcraft.tabTools).setTextureName("gold_sword");
 		GameRegistry.registerItem(sword, "swordGildedGold");
 		tools.put("swordGildedGold",sword);
 		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pick), "xxx", " s ", " s ", 
		        'x', new ItemStack(steamcraftIngot,1,3), 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(axe), "xx", "xs", " s", 
		        'x', new ItemStack(steamcraftIngot,1,3), 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(shovel), "x", "s", "s", 
		        'x', new ItemStack(steamcraftIngot,1,3), 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hoe), "xx", " s", " s", 
		        'x', new ItemStack(steamcraftIngot,1,3), 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sword), "x", "x", "s", 
		        'x', new ItemStack(steamcraftIngot,1,3), 's', "stickWood"));
    }
    
    public static Item pick(String string) {
    	return tools.get("pick"+string);
    } 
    public static Item axe(String string) {
    	return tools.get("axe"+string);
    }
    public static Item shovel(String string) {
    	return tools.get("shovel"+string);
    }
    public static Item hoe(String string) {
    	return tools.get("hoe"+string);
    }
    public static Item sword(String string) {
    	return tools.get("sword"+string);
    }
}
