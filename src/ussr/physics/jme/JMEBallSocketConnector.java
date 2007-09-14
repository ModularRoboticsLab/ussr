package ussr.physics.jme;

import org.odejava.World;

import ussr.robotbuildingblocks.RobotDescription;

import com.jme.math.Vector3f;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.Joint;
import com.jmex.physics.PhysicsSpace;
import com.jmex.physics.PhysicsUpdateCallback;
import com.jmex.physics.RotationalJointAxis;

public class JMEBallSocketConnector extends JMEMechanicalConnector {

	public JMEBallSocketConnector(Vector3f position, DynamicPhysicsNode moduleNode, String baseName, JMESimulation world, JMEModuleComponent component, RobotDescription selfDesc) {
		super(position, moduleNode, baseName, world, component, selfDesc);
		springHandler = new SpringHandler(moduleNode,50000,5000,0.09f);
		//springHandler = new SpringHandler(moduleNode,0,0,0.09f);
	}
	private RotationalJointAxis xAxis,yAxis,zAxis;
	private SpringHandler springHandler;
	private float oldDist = 0;
	protected void addAxis(Joint connection) {//Odin connector
		xAxis = connection.createRotationalAxis(); xAxis.setDirection(new Vector3f(1,0,0));
    	yAxis = connection.createRotationalAxis(); yAxis.setDirection(new Vector3f(0,0,1));
    	yAxis.setRelativeToSecondObject(true);
	}
	public synchronized void connectTo(JMEMechanicalConnector jc2) {
		super.connectTo(jc2);
		springHandler.setNode2(jc2.getNode());
		world.getPhysicsSpace().addToUpdateCallbacks(springHandler);
	}
	public synchronized void disconnect() {
		super.disconnect(); 
		world.getPhysicsSpace().removeFromUpdateCallbacks(springHandler);
	}
	public String toString() {
		String str = "Ball Socket Connector";
		if(xAxis!=null) str+= "Pos = ("+xAxis.getPosition();//+", "+yAxis.getPosition()+", "+zAxis.getPosition()+")";
		else System.out.println(" "+zAxis);
		return str;
	}
	private class SpringHandler implements PhysicsUpdateCallback {
		DynamicPhysicsNode node1, node2;
		float springConstant, restLength, dampConstant;
		public SpringHandler(DynamicPhysicsNode node1, float springConstant, float dampConstant, float restLength) {
			this.node1 = node1;
			this.dampConstant = dampConstant;
			this.springConstant = springConstant;
			this.restLength = restLength;
			oldDist = restLength;
		}
		public void setNode2(DynamicPhysicsNode node2) {
			this.node2 = node2;
		}
		public void afterStep(PhysicsSpace space, float time) {}
		Vector3f temp1;
		Vector3f temp2;
		public void beforeStep(PhysicsSpace space, float time) {
			//TODO:make damping of spring work 
			if(node1!=null&&node2!=null) {
				float dist =node1.getLocalTranslation().distance(node2.getLocalTranslation());
				float springForce = (float)(springConstant*(restLength-dist));
				if(restLength-dist>0.001f) springForce=20;
				if(restLength-dist<-0.001f) springForce=-20;
				if(springForce>100) springForce = 100;
				if(springForce<-100) springForce = -100;
				//float dampForce = -dampConstant*Math.abs(dist-oldDist);
				float dampForce = -dampConstant*(dist-oldDist);
				//if(Math.abs(springForce)>10) System.out.println("Spring force = "+springForce+" Damp Force = "+dampForce);
				float force = springForce+dampForce;
				node1.addForce(node1.getLocalTranslation().subtract(node2.getLocalTranslation()).mult(force));
				node2.addForce(node2.getLocalTranslation().subtract(node1.getLocalTranslation()).mult(force));
				oldDist = dist;
			}
		}
	}
}
