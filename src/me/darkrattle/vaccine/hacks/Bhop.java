package me.darkrattle.vaccine.hacks;

import org.jnativehook.keyboard.NativeKeyEvent;

import me.darkrattle.vacation.engine.hack.Hack;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.VACcine;
import me.darkrattle.vaccine.utils.GameUtils;

public class Bhop extends Hack {

	private int jump = 0x4AD805C;

	public Bhop() {
		super(VACcine.getGame(), "Bhop", -1);
	}

	@Override
	public void onEnable() {
		try {
			if (!GameUtils.getInstance().isLocalPlayerIngame()) {
				return;
			}

			if (getGame().getKeyInput().isKeyPressed(NativeKeyEvent.VC_SPACE) && canJump()) {
				jump();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jump() {
		try {
			MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), VACcine.getClient() + jump, new byte[] { 5 });
			Thread.sleep(50);
			MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), VACcine.getClient() + jump, new byte[] { 4 });
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private boolean canJump() {
		int mFlags = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), GameUtils.getInstance().getLocalPlayerAddress() + 0x100, 4).getInt(0);
		return mFlags == 257;
	}

}
