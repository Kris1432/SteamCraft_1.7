package flaxbeard.steamcraft.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ISpecialArmor;

import org.apache.commons.lang3.tuple.MutablePair;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.steamcraft.SteamcraftItems;
import flaxbeard.steamcraft.api.IEngineerable;
import flaxbeard.steamcraft.api.ISteamChargable;
import flaxbeard.steamcraft.api.exosuit.ExosuitPlate;
import flaxbeard.steamcraft.api.exosuit.UtilPlates;
import flaxbeard.steamcraft.client.render.ModelExosuit;
import flaxbeard.steamcraft.gui.GuiEngineeringTable;

public class ItemExosuitArmor extends ItemArmor implements ISpecialArmor,IEngineerable,ISteamChargable {
	private int slot;
	public ItemExosuitArmor(int i) {
		super(ItemArmor.ArmorMaterial.CHAIN, 1, i);
		slot = i;
		if (slot == 1) {
			this.setMaxDamage(6400);
		}
		else
		{
			this.setMaxDamage(0);
		}
	}
	
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }
	
	public int getPlateReqs() {
		switch (slot) {
			case 0:
				return 5;
			case 1:
				return 8;
			case 2:
				return 7;
			case 3:
				return 4;
			default:
				return 1;
		}
	}
	
	public String getString() {
		return this.iconString;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  	{
		if (stack.getItem() == SteamcraftItems.exoArmorLegs) {
			return "steamcraft:textures/models/armor/exo_2.png";
		}
		return "steamcraft:textures/models/armor/exo_1.png";
  	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		UtilPlates.registerPlatesForItem(par1IconRegister, this);
    }
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
    {
		if (!stack.hasTagCompound()) {
			return this.itemIcon;
		}
		if (stack.stackTagCompound.hasKey("plate")) {
			return pass > 0 ? UtilPlates.getIconFromPlate(stack.stackTagCompound.getString("plate"),this) : this.itemIcon;
		}
		return this.itemIcon;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel (EntityLivingBase entityLiving, ItemStack itemStack, int par2)
    {
		ModelExosuit modelbiped = new ModelExosuit(itemStack,par2);
        modelbiped.bipedHead.showModel = par2 == 0;
        modelbiped.bipedHeadwear.showModel = par2 == 0;
        modelbiped.bipedBody.showModel = par2 == 1 || par2 == 2;
        modelbiped.bipedRightArm.showModel = par2 == 1;
        modelbiped.bipedLeftArm.showModel = par2 == 1;
        modelbiped.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
        modelbiped.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
        return modelbiped;
    }

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		ItemArmor armorStack = (ItemArmor)armor.getItem();
		if (armor.hasTagCompound()) {
        	if (armor.stackTagCompound.hasKey("plate")) {
        		ExosuitPlate plate = UtilPlates.getPlate(armor.stackTagCompound.getString("plate"));
        		return new ArmorProperties(0, plate.getDamageReductionAmount(slot,source) / 25.0D, armorStack.getMaxDamage() + 1 - armor.getItemDamage());
        	}
        }
		return new ArmorProperties(0, ItemArmor.ArmorMaterial.CLOTH.getDamageReductionAmount(3-slot) / 25.0D, armorStack.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		ItemArmor armorStack = (ItemArmor)armor.getItem();
        if (armor.hasTagCompound()) {
        	if (armor.stackTagCompound.hasKey("plate")) {
        		ExosuitPlate plate = UtilPlates.getPlate(armor.stackTagCompound.getString("plate"));                
        		return plate.getDamageReductionAmount(slot, DamageSource.generic); 
            }
        }
        return ItemArmor.ArmorMaterial.CLOTH.getDamageReductionAmount(3-slot);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if (this.slot == 1) {
			if (stack.getItemDamage() < stack.getMaxDamage()-40) {
				stack.damageItem(damage*40, entity);
			}
			else
			{
				stack.setItemDamage(stack.getMaxDamage()-1);
			}
		}
	}

	@Override
	public MutablePair<Integer, Integer>[] engineerCoordinates() {
		if (this.slot == 3) {
			return new MutablePair[] { MutablePair.of(64,29),MutablePair.of(64,9),MutablePair.of(64,49) };
		}
		return new MutablePair[] { MutablePair.of(64,29) };
	}
	
	public void hasPlates(ItemStack me) {
		if (this.getStackInSlot(me, 1) != null) {
			
			if (this.getStackInSlot(me, 1).stackSize == this.getPlateReqs()) {

				if (!me.hasTagCompound()) {
					me.setTagCompound(new NBTTagCompound());
				}
				ItemStack clone = this.getStackInSlot(me, 1).copy();
				clone.stackSize = 1;
				if (UtilPlates.getPlate(clone) != null) {
					me.stackTagCompound.setString("plate", UtilPlates.getPlate(clone).getIdentifier());
				}
				else
				{
					if (me.stackTagCompound.hasKey("plate")) {
						me.stackTagCompound.removeTag("plate");
					}
				}
			}
			else
			{
				if (me.stackTagCompound.hasKey("plate")) {
					me.stackTagCompound.removeTag("plate");
				}
			}
		}
		else
		{
			if (me.stackTagCompound.hasKey("plate")) {
				me.stackTagCompound.removeTag("plate");
			}
		}
	}

	@Override
	public ItemStack getStackInSlot(ItemStack me, int var1) {
		if (me.hasTagCompound()) {
			if (me.stackTagCompound.hasKey("inv")) {
				if (me.stackTagCompound.getCompoundTag("inv").hasKey(Integer.toString(var1))) {
					return ItemStack.loadItemStackFromNBT(me.stackTagCompound.getCompoundTag("inv").getCompoundTag(Integer.toString(var1)));
				}
			}
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(ItemStack me, int var1, ItemStack stack) {
		System.out.println("fs");

		if (!me.hasTagCompound()) {
			me.setTagCompound(new NBTTagCompound());
		}
		if (!me.stackTagCompound.hasKey("inv")) {
			me.stackTagCompound.setTag("inv", new NBTTagCompound());
		}
		if (me.stackTagCompound.getCompoundTag("inv").hasKey(Integer.toString(var1))) {
			me.stackTagCompound.getCompoundTag("inv").removeTag(Integer.toString(var1));
		}
		NBTTagCompound stc = new NBTTagCompound();
		if (stack != null) {
			stack.writeToNBT(stc);
			me.stackTagCompound.getCompoundTag("inv").setTag(Integer.toString(var1), stc);
		}
		this.hasPlates(me);
	}

	@Override
	public boolean isItemValidForSlot(ItemStack me, int var1, ItemStack var2) {
		return true;
	}

	@Override
	public ItemStack decrStackSize(ItemStack me, int var1, int var2) {
		if (this.getStackInSlot(me,var1) != null)
		{
			ItemStack itemstack;	
			if (this.getStackInSlot(me,var1).stackSize <= var2)
			{
				itemstack = this.getStackInSlot(me,var1);
				this.setInventorySlotContents(me, var1, null);
				this.hasPlates(me);
				return itemstack;
			}
			else
			{
				ItemStack stack2 = this.getStackInSlot(me,var1);
				itemstack = stack2.splitStack(var2);
				this.setInventorySlotContents(me, var1, stack2);
		
				if (this.getStackInSlot(me,var1).stackSize == 0)
				{
					this.setInventorySlotContents(me, var1, null);
				}
				this.hasPlates(me);
				return itemstack;
        	}
    	}
	    else
	    {
	    	return null;
	    }
	}

	@Override
	public void drawSlot(GuiEngineeringTable guiEngineeringTable, int slotNum, int i, int j) {
		guiEngineeringTable.mc.getTextureManager().bindTexture(guiEngineeringTable.furnaceGuiTextures);
		if (this.slot == 0) {
			if (slotNum != 0) {
				guiEngineeringTable.drawTexturedModalRect(i, j, 176, 0, 18, 18);
			}
			else
			{
				guiEngineeringTable.drawTexturedModalRect(i, j, 194, 0, 18, 18);
			}
		}
		if (this.slot == 1) {
			if (slotNum != 0) {
				guiEngineeringTable.drawTexturedModalRect(i, j, 176, 0, 18, 18);
			}
			else
			{
				guiEngineeringTable.drawTexturedModalRect(i, j, 194, 0, 18, 18);
			}
		}
		if (this.slot == 2) {
			if (slotNum != 0) {
				guiEngineeringTable.drawTexturedModalRect(i, j, 176, 0, 18, 18);
			}
			else
			{
				guiEngineeringTable.drawTexturedModalRect(i, j, 194, 0, 18, 18);
			}
		}
		if (this.slot == 3) {
			switch (slotNum) {
				case 0:
					guiEngineeringTable.drawTexturedModalRect(i, j, 194, 0, 18, 18);
					break;
				case 1:
					guiEngineeringTable.drawTexturedModalRect(i, j, 230, 0, 18, 18);
					break;
				case 2:
					guiEngineeringTable.drawTexturedModalRect(i, j, 212, 0, 18, 18);
					break;
				default:
					guiEngineeringTable.drawTexturedModalRect(i, j, 176194, 0, 18, 18);
			}
		}
	}

	@Override
	public int getStackLimit(ItemStack me, int i) {
		if (this.slot == 0) {
			switch (i) {
				case 0:
					return this.getPlateReqs();
				default:
					return 64;
			}
		}
		if (this.slot == 1) {
			switch (i) {
				case 0:
					return this.getPlateReqs();
				default:
					return 64;
			}
		}
		if (this.slot == 2) {
			switch (i) {
				case 0:
					return this.getPlateReqs();
				default:
					return 64;
			}
		}
		if (this.slot == 3) {
			switch (i) {
				case 0:
					return this.getPlateReqs();
				default:
					return 1;
			}
		}
		return 64;
	}

	@Override
	public int steamPerDurability() {
		return 1;
	}

	@Override
	public boolean canCharge(ItemStack me) {
		return this == SteamcraftItems.exoArmorBody;
	}

}
