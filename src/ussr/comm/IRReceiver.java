package ussr.comm;

import ussr.model.Entity;
import ussr.model.Module;
import ussr.physics.PhysicsConnector;
import ussr.physics.PhysicsEntity;
import ussr.robotbuildingblocks.ReceivingDevice;
import ussr.robotbuildingblocks.RotationDescription;
import ussr.robotbuildingblocks.VectorDescription;

public class IRReceiver extends GenericReceiver {
	private float spreadAngle;
	public IRReceiver(Module module, Entity hardware, ReceivingDevice receiver) {
		super(module, hardware, receiver.getType(), receiver.getBufferSize());
		spreadAngle = (float)(30*2*Math.PI/360f);
	}
	public boolean canReceiveFrom(Transmitter transmitter) { //howto handle JME specific things?
		return true;
	}
}
