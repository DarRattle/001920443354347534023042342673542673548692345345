package me.darkrattle.vaccine.utils;

import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.VACcine;

public class GameUtils {

	private int EntityList = 0x4A43844;
	private int LocalPlayer = 0xA9D44C;
	private int ClientState = 0x5D3234;

	private int LifeState = 0x25B;
	private int GameState = 0xE8;
	private int Team = 0xF0;
	private int CrosshairId = 0xA904;
	private int EntitySize = 0x10;

	private static GameUtils instance;

	public int getLocalPlayerAddress() {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), VACcine.getClient() + LocalPlayer, 4).getInt(0);
	}

	public int getCrosshairedEnemyAddress() {
		int playerId = getCrosshairId();

		if (playerId < 64 && playerId >= 0 && getPlayerTeam(playerId) != getLocalPlayerTeam() && isPlayerDead(playerId) == false) {
			return getBaseEntity(playerId);
		}

		return -1;
	}

	public boolean isEnemyCrosshaired() {
		int playerId = getCrosshairId();

		if (playerId < 64 && playerId >= 0 && getPlayerTeam(playerId) != getLocalPlayerTeam() && isPlayerDead(playerId) == false) {
			return true;
		}

		return false;
	}

	public boolean isLocalPlayerIngame() {
		int enginePointer = MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), VACcine.getEngine() + ClientState, 4).getInt(0);
		int inGame = MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), enginePointer + GameState, 4).getInt(0);

		return inGame == 6;
	}

	public boolean isPlayerDead(int playerId) {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), getBaseEntity(playerId) + LifeState, 4).getInt(0) != 0;
	}

	public int getPlayerTeam(int playerId) {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), getBaseEntity(playerId) + Team, 4).getInt(0);
	}

	public int getLocalPlayerTeam() {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), getLocalPlayerAddress() + Team, 4).getInt(0);
	}

	public int getCrosshairId() {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), getLocalPlayerAddress() + CrosshairId, 4).getInt(0) - 1;
	}

	public int getBaseEntity(int playerId) {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), VACcine.getClient() + EntityList + (EntitySize * playerId), 4).getInt(0);
	}

	public int getClientState() {
		return MemoryProcessor.getInstance().readMemory(VACcine.getGame().getProcess(), VACcine.getEngine() + ClientState, 4).getInt(0);
	}

	public static GameUtils getInstance() {
		if (instance == null) {
			instance = new GameUtils();
		}

		return instance;
	}

}
