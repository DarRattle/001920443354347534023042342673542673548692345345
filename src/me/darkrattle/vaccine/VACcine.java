package me.darkrattle.vaccine;

import java.awt.EventQueue;

import me.darkrattle.vacation.engine.game.Game;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.window.UI;

public class VACcine {

	public static final String NAME = "VACcine";
	public static final String VERSION = "v1.0";
	public static final String[] AUTHORS = { "DarkRattle" };

	private static int client;
	private static int engine;

	private static CSGO game;

	public static int getClient() {
		return client;
	}

	public static int getEngine() {
		return engine;
	}

	public static Game getGame() {
		if (game == null) {
			game = new CSGO();
		}

		return game;
	}

	public static void main(String[] args) {
		getGame();

		client = MemoryProcessor.getInstance().getBaseAddress(game.getProcess(), "client.dll");
		engine = MemoryProcessor.getInstance().getBaseAddress(game.getProcess(), "engine.dll");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
