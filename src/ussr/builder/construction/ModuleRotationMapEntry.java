package ussr.builder.construction;

import ussr.description.geometry.RotationDescription;

import com.jme.math.Vector3f;

/**
 * The main responsibility of this class is to act as a helper class (for Strategy pattern) for storing the 
 * information about  all available specific rotations of the module.
 * @author Konstantinas 
 */
//FIXME 1) UPDATE COMMENTS ESSPECIALLY FOR CONSTRUCTORS
//2) MAYBE USE QUATERNIONS INSTEAD  
public class ModuleRotationMapEntry {
	
	/**
	 * The name of rotation a module
	 */
	private String rotationName;
	
	/**
	 * The rotation of the module
	 */
	private RotationDescription rotation;
	
	/**
	 * Opposite rotation of the module
	 */
	private RotationDescription rotationOpposite;
	
	/**
	 * Additional specific rotation of the module
	 */
	private RotationDescription rotationSpecific;
	
	/**
	 * The component number of the module
	 */
	private int componentNr;
	
	/**
	 * The new position of component of the module
	 */
	private Vector3f componentNewPosition;
	
	
	/**
	 * Constructor
	 * @param rotationName, the name of rotation of the module
	 * @param rotation,the rotation of the module
	 * @param rotationOpposite,opposite rotation of the module
	 */
	public ModuleRotationMapEntry(String rotationName,RotationDescription rotation, RotationDescription rotationOpposite){
		this.rotationName = rotationName;
		this.rotation = rotation;
		this.rotationOpposite = rotationOpposite; 
	}
	
	/**
	 * Constructor
	 * @param rotationName, the name of rotation of the module
	 * @param rotation,the rotation of the module
	 * @param rotationOpposite,opposite rotation of the module
	 * @param rotationSpecific, additional specific rotation of the module
	 */
	public ModuleRotationMapEntry(String rotationName,RotationDescription rotation, RotationDescription rotationOpposite, RotationDescription rotationSpecific){
		this.rotationName = rotationName;
		this.rotation = rotation;
		this.rotationOpposite = rotationOpposite;
		this.rotationSpecific = rotationSpecific; 
	}
	
	/**
	 * Constructor
	 * @param rotationName, the name of rotation of the module
	 * @param componentNr,the component number of the module
	 * @param rotation, new rotation of component
	 * @param componentNewPosition,new position of component of the module 
	 */
	public ModuleRotationMapEntry(String rotationName,int componentNr,RotationDescription rotation, Vector3f componentNewPosition){
		this.rotationName = rotationName;
		this.componentNr = componentNr;
		this.rotation = rotation;		
		this.componentNewPosition = componentNewPosition; 
	}

	/**
	 * Returns the name of rotation of the module
	 * @return rotationName, the name of rotation of the module
	 */
	public String getRotationName() {
		return rotationName;
	}

	/**
	 * Returns the rotation of the module
	 * @return rotation, the rotation of the module
	 */
	public RotationDescription getRotation() {
		return rotation;
	}

	/**
	 * Returns the opposite rotation
	 * @return rotationOpposite, the opposite rotation
	 */
	public RotationDescription getRotationOppositeValue() {
		return rotationOpposite;
	}

	/**
	 * Returns additional specific rotation of the module
	 * @return rotationSpecific, specific rotation of the module
	 */
	public RotationDescription getRotationAroundAxisValue() {
		return rotationSpecific;
	}

	/**
	 * Returns the component number of the module
	 * @return componentNr,the component number of the module  
	 */
	public int getComponentNr() {
		return componentNr;
	}

	/**
	 * Returns new  position of component of the module 
	 * @return componentNewPosition,new  position of component of the module
	 */
	public Vector3f getComponentNewPosition() {
		return componentNewPosition;
	}

}
