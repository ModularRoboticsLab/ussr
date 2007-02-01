/**
 * Uniform Simulator for Self-reconfigurable (modular) Robots
 * 
 * (C) 2006 University of Southern Denmark
 */
package ussr.robotbuildingblocks;
/**
 * A description of a cone geometry
 * @author david
 *
 */
public class ConeShape extends CylinderShape {

	public ConeShape(float radius, float height) {
		super(radius, height);
	}
    public ConeShape(float radius, float height, VectorDescription translation) { 
		super(radius,height,translation);
    }
    public ConeShape(float radius, float height, VectorDescription translation, RotationDescription rotation) { 
		super(radius,height,translation,rotation);
    }
}