package me.darkrattle.vaccine.hacks;

import me.darkrattle.vacation.engine.hack.Hack;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.VACcine;
import me.darkrattle.vaccine.utils.GameUtils;

public class Triggerbot extends Hack {

	private int attack = 0x2EB1E08;

	public static int attackDelayMS = 100;

	public Triggerbot() {
		super(VACcine.getGame(), "Triggerbot", -1);
	}

	@Override
	public void onEnable() {
		try {
			if (!GameUtils.getInstance().isLocalPlayerIngame()) {
				return;
			}

			if (GameUtils.getInstance().isEnemyCrosshaired()) {
				Thread.sleep(attackDelayMS);
				attack();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void attack() {
		try {
			MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), VACcine.getClient() + attack, new byte[] { 5 });
			Thread.sleep(15);
			MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), VACcine.getClient() + attack, new byte[] { 4 });
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
