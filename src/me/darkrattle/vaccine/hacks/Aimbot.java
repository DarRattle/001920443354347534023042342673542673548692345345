package me.darkrattle.vaccine.hacks;

import java.nio.ByteBuffer;

import com.sun.jna.Pointer;

import me.darkrattle.vacation.engine.hack.Hack;
import me.darkrattle.vacation.engine.memory.MemoryProcessor;
import me.darkrattle.vaccine.VACcine;
import me.darkrattle.vaccine.utils.GameUtils;

public class Aimbot extends Hack {

	private int boneMatrix = 0x267C;
	private int vecOrigin = 0x134;
	private int viewAngles = 0x4CE0;

	public double DEGREES_TO_RADIANS = 180D / Math.PI;

	public Aimbot() {
		super(VACcine.getGame(), "Aimbot", -1);
	}

	@Override
	public void onEnable() {
		try {
			float[] playerBones = getBones(GameUtils.getInstance().getLocalPlayerAddress());
			float[] enemyBones = getBones(GameUtils.getInstance().getCrosshairedEnemyAddress());
			float[] playerPosition = getPosition(GameUtils.getInstance().getLocalPlayerAddress());
			float[] aimAngle = calculateAngle(playerPosition, enemyBones);
			setAngles(aimAngle);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private float[] calculateAngle(float[] source, float[] distance) {
		float[] angles = new float[3];
		float[] delta = { (source[0] - distance[0]), (source[1] - distance[1]), (source[2] - distance[2]) };
		float hyp = (float) Math.sqrt(delta[0] * delta[0] + delta[1] * delta[1]);
		angles[0] = (float) (Math.asin(delta[2] / hyp) * DEGREES_TO_RADIANS);
		angles[1] = (float) (Math.atan(delta[1] / delta[0]) * DEGREES_TO_RADIANS);
		if (delta[0] >= 0F) {
			angles[1] += 180F;
		}
		angles[2] = 0F;
		return angles;
	}

	private void setAngles(float[] angles) {

		byte[] _angles = ByteBuffer.allocate(12).putFloat(1).putFloat(2).putFloat(3).array();

		MemoryProcessor.getInstance().writeMemory(getGame().getProcess(), GameUtils.getInstance().getClientState() + viewAngles, _angles);
	}

	private float[] getBones(int playerAddress) {
		float[] bones = new float[3];

		int _boneMatrix = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), playerAddress + boneMatrix, 4).getInt(0);

		bones[0] = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), _boneMatrix + 0x30 * 6 + 0x0C, 4).getFloat(0);
		bones[1] = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), _boneMatrix + 0x30 * 6 + 0x1C, 4).getFloat(0);
		bones[2] = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), _boneMatrix + 0x30 * 6 + 0x2C, 4).getFloat(0);

		return bones;
	}

	private float[] getPosition(int playerAddress) {
		float[] position = new float[3];

		Pointer positionPointer = MemoryProcessor.getInstance().readMemory(getGame().getProcess(), playerAddress + vecOrigin, 12);

		position[0] = positionPointer.getFloat(0);
		position[1] = positionPointer.getFloat(4);
		position[2] = positionPointer.getFloat(8);

		return position;
	}

}
