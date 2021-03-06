/**
 * Unified Simulator for Self-Reconfigurable Robots (USSR)
 * (C) University of Southern Denmark 2008
 * This software is distributed under the BSD open-source license.
 * For licensing see the file LICENCE.txt included in the root of the USSR distribution.
 */
package ussr.physics.jme.sensors;

import ussr.description.geometry.RotationDescription;
import ussr.description.geometry.VectorDescription;
import ussr.model.Entity;
import ussr.model.PhysicsSensor;
import ussr.model.Sensor;
import ussr.physics.jme.JMESimulation;

import com.jme.math.Vector3f;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.PhysicsNode;

/**
 * Proximity sensor, works by measuring physical distance to obstacle objects (not
 * other modules).
 * 
 * @author Modular Robots @ MMMI
 * 
 */
public class JMEProximitySensor implements PhysicsSensor {

    private JMESimulation simulation;
    private String name;
    private Sensor model;
    private DynamicPhysicsNode node;
    private float range;
    private float sensitivity = 1.0f;
    
    public JMEProximitySensor(JMESimulation simulation, String name, Entity hardware, float range, DynamicPhysicsNode node) {
        this.simulation = simulation;
        this.name = name;
        this.node = node; //((JMEPhysicsEntity)hardware.getPhysics().get(0)).getNode();
        this.range = range;
    }

    /* (non-Javadoc)
     * @see ussr.model.PhysicsSensor#getName()
     */
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see ussr.model.PhysicsSensor#readValue()
     */
    public float readValue() {
        float adjustedRange = range*sensitivity;
        for(PhysicsNode obstacle: simulation.getObstacles()) {
            Vector3f t1 = node.getLocalTranslation();
            Vector3f t2 = obstacle.getLocalTranslation();
            float distance = t1.distance(t2);
            if(distance<adjustedRange) {
                return 1-distance/adjustedRange;
            }
        }
        return 0;
    }

    /* (non-Javadoc)
     * @see ussr.model.PhysicsSensor#setModel(ussr.model.Sensor)
     */
    public void setModel(Sensor sensor) {
        this.model = sensor;
    }

    /* (non-Javadoc)
     * @see ussr.physics.PhysicsEntity#getPosition()
     */
    public VectorDescription getPosition() {
        throw new Error("Method not implemented");
    }

    /* (non-Javadoc)
     * @see ussr.physics.PhysicsEntity#getRotation()
     */
    public RotationDescription getRotation() {
        throw new Error("Method not implemented");
    }

	public void reset() {
	}

	public void setPosition(VectorDescription position) {
		throw new Error("Method not implemented");
		
	}
	public void setRotation(RotationDescription rotation) {
		throw new Error("Method not implemented");		
	}
	public void moveTo(VectorDescription position, RotationDescription rotation) {
		throw new Error("Method not implemented");
	}

	public void clearDynamics() {
		node.clearDynamics();
	}
	public void addExternalForce(float forceX, float forceY, float forceZ) {
		node.addForce(new Vector3f(forceX,forceY,forceZ));		
	}

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }
}
