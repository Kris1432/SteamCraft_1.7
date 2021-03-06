package flaxbeard.steamcraft.api;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISteamTransporter {
	public float getPressure();
	public boolean canInsert(ForgeDirection face);
	public int getCapacity();
	public int getSteam();
	public void insertSteam(int amount, ForgeDirection face);
	public void decrSteam(int i);
	public boolean doesConnect(ForgeDirection face);
}
