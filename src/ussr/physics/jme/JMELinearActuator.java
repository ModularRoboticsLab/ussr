/**
 * 
 */
package ussr.physics.jme;

import ussr.model.Actuator;
import ussr.model.PhysicsActuator;

import com.jme.math.Vector3f;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.Joint;
import com.jmex.physics.JointAxis;


/**
 * @author david
 *
 */
public class JMELinearActuator implements PhysicsActuator {

//	private Actuator model;
    private DynamicPhysicsNode node1;
    private DynamicPhysicsNode node2;
    private JMESimulation world;
//    private JMEModuleComponent module;
    private Joint joint;
    private String name;
    private float epsilon = 0.001f;
	private float maxVelocity = 0.01f;
	private JointAxis axis;
	
    public JMELinearActuator(JMESimulation world, String baseName) {
        this.world = world;
        this.name = baseName;
    }
    /**
     * Set the control parameters of the actuator
     * @param maxAcceleration (m/s^2)
     * @param maxVelocity (m/s)
     * @param lowerLimit (m)
     * @param upperLimit (m)
     */
    public void setControlParameters(float maxAcceleration, float maxVelocity, float lowerLimit, float upperLimit) {
    	if(joint==null) throw new RuntimeException("You must attach dynamics nodes first");
    	this.maxVelocity = maxVelocity;
    	axis.setAvailableAcceleration(maxAcceleration);
    	axis.setPositionMaximum(upperLimit);
    	axis.setPositionMinimum(lowerLimit);
    }
    /**
     * Attach the linear acutator between two DynamicPhysicsNode 
     * @param DynamicPhysicsNode d1
     * @param DynamicPhysicsNode d2
     */
    public void attach(DynamicPhysicsNode d1, DynamicPhysicsNode d2) {
    	node1=d1; node2=d2;
    	//node1.setAffectedByGravity(true);
    	//node2.setAffectedByGravity(true);
    	if(joint==null)  {
    		joint = world.getPhysicsSpace().createJoint();
    		joint.attach(node1,node2);
    		axis = joint.createTranslationalAxis();
    		axis.setDirection(new Vector3f(-1,0,0));
    		axis.setDesiredVelocity(0);
    		setControlParameters(0.1f,0.05f,0f,0.11f); //default parameters
    	}
    }
    
    /**
     * encoder value in percent
     * @return encoder value in percent
     */
    public float getEncoderValue() {
    	return 1f-(axis.getPositionMaximum()-axis.getPosition())/(axis.getPositionMaximum()-axis.getPositionMinimum());
    }

	/**
	 * Make the actuator rotate towards a goal [0-1] percent of fully expanded 
	 * @see ussr.model.PhysicsActuator#activate(float)
	 */
	public boolean activate(float goal) {
		if(Float.isNaN(axis.getPosition())) {
			//System.out.println("Actuator is not yet setup!");
			return false;
		}
		float error = goal-getEncoderValue();
		//System.out.println("error = "+error+" goal="+goal);
		axis.setDesiredVelocity(maxVelocity*error);
		//System.out.println("acc = "+joint.getAxes().get(0).getAvailableAcceleration());
		//System.out.println("goal = "+goal+" pos = "+getEncoderValue()+" error "+error);
		return false;
	}

	/** 
	 * Relax the linear actuator - can this be done always?
	 * @see ussr.model.PhysicsActuator#disactivate()
	 */
	public void disactivate() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see ussr.model.PhysicsActuator#isActive()
	 */
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see ussr.model.PhysicsActuator#setModel(ussr.model.Actuator)
	 */
	public void setModel(Actuator actuator) {
		// TODO Auto-generated method stub

	}
	public String toString() {
		return "JMELinearActuator: "+name;
	}

}