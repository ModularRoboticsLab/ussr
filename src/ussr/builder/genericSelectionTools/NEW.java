package ussr.builder.genericSelectionTools;

import com.jme.math.Matrix3f;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jme.scene.TriMesh;
import com.jmex.physics.DynamicPhysicsNode;

import ussr.description.geometry.RotationDescription;
import ussr.description.geometry.VectorDescription;
import ussr.physics.jme.JMEModuleComponent;
import ussr.physics.jme.JMESimulation;
import ussr.physics.jme.pickers.CustomizedPicker;

public class NEW extends CustomizedPicker{

	/**
	 * The physical simulation
	 */
	private JMESimulation simulation;
	
	private float degrees;
	
	private int selectedConnectorNr;
	
	public NEW(JMESimulation simulation, float degrees){
		this.simulation = simulation;
		this.degrees = degrees;
	}
	
	@Override
	protected void pickModuleComponent(JMEModuleComponent component) {
		
		VectorDescription vd1 = component.getLocalPosition();		
		VectorDescription vd2 = component.getModel().getConnectors().get(0).getPhysics().get(0).getPosition();
		
			
		 Quaternion rotationQ = component.getRotation().getRotation();
		 Matrix3f matrix = new Matrix3f();
		matrix.set(rotationQ);		
		
		
		Vector3f vd1f= new Vector3f(vd1.getX(),vd1.getY(),vd1.getZ()); 
		Vector3f vd2f= new Vector3f(vd2.getX(),vd2.getY(),vd2.getZ());
		
		//float x= vd2.getX() - vd1.getX();
		//float y= vd2.getY()- vd1.getY();
		//float z= vd2.getZ()- vd1.getZ();
		
		//matrix.mult(new Vector3f(x,y,z));
		
		
		 //Vector3f d1 = new Vector3f(x,y,z);
		 
		 Vector3f d2 = vd2f.subtract(vd1f);
		 System.out.println("Lenght:"+d2.length());
		 //d2.normalize();
		 Vector3f d3 = new Vector3f(d2.x,d2.y,d2.z);
		 //System.out.println("Lenght:"+d3.length());
		 //d1= d1.mult(-0.01f);
		
		component.setPosition(new VectorDescription(d3.x,d3.y,d3.z));
		
		
		
		/*ROTATION*/
	/*	Quaternion rotationQuaternion = component.getRotation().getRotation();
		Matrix3f rotationComponent = new Matrix3f();
		rotationComponent.set(rotationQuaternion);
		
		
		Matrix3f finalRotation = rotateAround(rotationComponent,"Z", this.degrees); 
		
		Quaternion newQaut = new Quaternion();
		newQaut.fromRotationMatrix(finalRotation);
		
		RotationDescription rd = new RotationDescription();
		rd.setRotation(newQaut);

		component.setRotation(rd);		*/
	}

	@Override
	protected void pickTarget(Spatial target) {
		if(target instanceof TriMesh) {			
			String name = simulation.getGeometryName((TriMesh)target);
			if(name!=null && name.contains("Connector")){ 
				//System.out.println("Connector: "+name);//For debugging				
				String [] temp = null;	         
				temp = name.split("#");// Split by #, into two parts, line describing the connector. For example "Connector 1 #1"
				this.selectedConnectorNr= Integer.parseInt(temp[1].toString());// Take only the connector number, in above example "1" (at the end)							
			}
		}	
		
	}
	
     private Matrix3f rotateAround(Matrix3f matrix,String coordinate, float degrees){
		
		float cos =(float)Math.cos(degrees);
		float sin =(float)Math.sin(degrees);
		
		Matrix3f rotationMatrix = null;
		if (coordinate.equalsIgnoreCase("X")){
			System.out.println("Degrees:"+degrees);
			rotationMatrix = new Matrix3f(1f,0f,0f,0f,cos,-sin,0f,sin,cos);
		}else if(coordinate.equalsIgnoreCase("Y")){
			System.out.println("Degrees:"+degrees);
			rotationMatrix = new Matrix3f(cos,0f,sin,0f,1f,0f,-sin,0f,cos);
			
		}else if(coordinate.equalsIgnoreCase("Z")){
			System.out.println("Degrees:"+degrees);
			rotationMatrix = new Matrix3f(cos,-sin,0f,sin,cos,0f,0f,0f,1);					
		}	
		
		return rotationMatrix.mult(matrix) ;
	}  

}
