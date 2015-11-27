package me.darkrattle.vaccine;

import javax.swing.JOptionPane;

import me.darkrattle.vacation.engine.game.Game;
import me.darkrattle.vacation.engine.hack.Hack;
import me.darkrattle.vacation.engine.hack.HackManager;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.hacks.Aimbot;
import me.darkrattle.vaccine.hacks.Bhop;
import me.darkrattle.vaccine.hacks.ESP;
import me.darkrattle.vaccine.hacks.Triggerbot;

public class CSGO extends Game {
	
	public CSGO() {
		super("Counter-Strike: Global Offensive");		
	}
	
	@Override
	public void run() {
		
		try {
			HackManager.getInstance().addHack(new Aimbot());
			HackManager.getInstance().addHack(new Bhop());
			HackManager.getInstance().addHack(new ESP());
			HackManager.getInstance().addHack(new Triggerbot());

			while (MemoryProcessor.getInstance().isProcessRunning(getName())) {
				Thread.sleep(1);

				for (Hack hack : HackManager.getInstance().getHacks()) {
					if (hack.isEnabled()) {
						hack.onEnable();
					}
				}
			}
			
			JOptionPane.showMessageDialog(null, getName() + " not found, please run the game.", "Process not found.", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
