/**
 * Unified Simulator for Self-Reconfigurable Robots (USSR)
 * (C) University of Southern Denmark 2008
 * This software is distributed under the BSD open-source license.
 * For licensing see the file LICENCE.txt included in the root of the USSR distribution.
 */
package ussr.samples.odin.modules;

import java.awt.Color;

import ussr.description.geometry.ConeShape;
import ussr.description.geometry.CylinderShape;
import ussr.description.geometry.GeometryDescription;
import ussr.description.geometry.RotationDescription;
import ussr.description.geometry.SphereShape;
import ussr.description.geometry.VectorDescription;
import ussr.description.robot.ConnectorDescription;
import ussr.description.robot.ModuleComponentDescription;
import ussr.description.robot.RobotDescription;

/**
 * Odin wheel module
 * 
 * @author Modular Robots @ MMMI
 */
public abstract class OdinWheel extends Odin {

	/**
	 * A contractive rod with a connector in each end
	 */
	public RobotDescription getDescription() {
		RobotDescription description = new RobotDescription("OdinWheel");
		float pi = (float)Math.PI;
        CylinderShape cylinder = new CylinderShape(0.035f/2f,0.06f,new VectorDescription(0,0,0), new RotationDescription(0,-pi/2,0));
        SphereShape wheel = new SphereShape(0.035f,new VectorDescription(0,0,0), new RotationDescription(pi/2,-pi/2,0));
        ConeShape coneCap1 = new ConeShape(0.035f/2f,0.035f, new VectorDescription(-0.03f-0.035f/2f,0,0), new RotationDescription(pi,-pi/2,0));
        ConeShape coneCap2 = new ConeShape(0.032f/2f,0.035f, new VectorDescription(0.03f+0.035f/2f,0,0), new RotationDescription(0,-pi/2,0));
        
        cylinder.setColor(Color.WHITE);
        wheel.setColor(Color.BLUE);
        coneCap1.setColor(Color.WHITE);
        coneCap2.setColor(Color.WHITE);
        
        cylinder.setAccurateCollisionDetection(false);
        wheel.setAccurateCollisionDetection(false);
        coneCap1.setAccurateCollisionDetection(false);
        coneCap2.setAccurateCollisionDetection(false);
        
        ModuleComponentDescription axleComponent = new ModuleComponentDescription(new GeometryDescription[] {cylinder,coneCap1,coneCap2});
        ModuleComponentDescription wheelComponent = new ModuleComponentDescription(new GeometryDescription[] {wheel});

        ConnectorDescription.Common common = new ConnectorDescription.Common();
        GeometryDescription connector = super.makeConnectorShape();
        common.setGeometry(new GeometryDescription[] { connector });
        common.setType( ConnectorDescription.Type.MECHANICAL_CONNECTOR_BALL_SOCKET );
        float unit = 0.06f/2+0.035f; 
        axleComponent.setConnectors(new ConnectorDescription[] {
        		new ConnectorDescription(common,new VectorDescription(-unit, 0, 0)),
        		new ConnectorDescription(common,new VectorDescription(unit, 0, 0))
        });
        //description.setMaxConnectionDistance(6);
        description.setModuleComponents(new ModuleComponentDescription[] { axleComponent, wheelComponent });
        return description;
	}
}
