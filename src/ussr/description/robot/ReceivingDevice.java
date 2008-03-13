/**
 * 
 */
package ussr.description.robot;

import ussr.comm.TransmissionType;
import ussr.description.Description;

/**
 * Abstract description of a receiving device
 * 
 * @author Modular Robots @ MMMI
 */
public class ReceivingDevice extends Description {

    private TransmissionType type;
    private int bufferSize;
    
    public ReceivingDevice(TransmissionType _type, int _bufferSize) {
        this.type = _type; this.bufferSize = _bufferSize;
    }

    public TransmissionType getType() {
        return type;
    }

    public int getBufferSize() {
        return bufferSize;
    }

}