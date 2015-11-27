package me.darkrattle.vaccine.hacks;

import me.darkrattle.vacation.engine.hack.Hack;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.VACcine;

public class ESP extends Hack {

	private int glow = 0x0;
	
	public ESP() {
		super(VACcine.getGame(), "ESP", -1);
	}

	@Override
	public void onEnable() {
		try {
			MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), VACcine.getClient() + glow, new byte[] { 5 } );
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
