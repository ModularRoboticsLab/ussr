/**
 * Unified Simulator for Self-Reconfigurable Robots (USSR)
 * (C) University of Southern Denmark 2008
 * This software is distributed under the BSD open-source license.
 * For licensing see the file LICENCE.txt included in the root of the USSR distribution.
 */
package ussr.description.robot;

import ussr.comm.TransmissionType;
import ussr.description.Description;

/**
 * Abstract description of a transmission device
 * 
 * @author Modular Robots @ MMMI
 */
public class TransmissionDevice extends Description {

    float range;
    TransmissionType type;
    
    public TransmissionDevice(TransmissionType _type, float _range) {
        this.type = _type;
        this.range = _range;
    }

    public TransmissionType getType() { return type; }
    public float getRange() { return range; }
}
