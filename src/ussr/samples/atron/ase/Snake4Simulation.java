/**
 * Unified Simulator for Self-Reconfigurable Robots (USSR)
 * (C) University of Southern Denmark 2008
 * This software is distributed under the BSD open-source license.
 * For licensing see the file LICENCE.txt included in the root of the USSR distribution.
 */
package ussr.samples.atron.ase;

import java.util.ArrayList;

import ussr.description.Robot;
import ussr.description.geometry.VectorDescription;
import ussr.description.setup.ModulePosition;
import ussr.description.setup.WorldDescription;
import ussr.model.Controller;
import ussr.physics.PhysicsFactory;
import ussr.physics.PhysicsParameters;
import ussr.physics.PhysicsSimulation;
import ussr.physics.PhysicsParameters.Material;
import ussr.samples.GenericSimulation;
import ussr.samples.ObstacleGenerator;
import ussr.samples.atron.ATRON;
import ussr.samples.atron.GenericATRONSimulation;
import ussr.samples.atron.network.ATRONReflectionController;
import ussr.samples.atron.network.ATRONReflectionEventController;
import ussr.util.supervision.CMTracker;
import ussr.util.supervision.RadioConnection;
import ussr.util.supervision.WifiCMBroadcaster;




public class Snake4Simulation extends GenericATRONSimulation {
	private boolean hasCMTracker = false;
	private boolean hasRadioConnection = true;
	RadioConnection radioConnection;
	
	
    private ObstacleGenerator.ObstacleType obstacle = ObstacleGenerator.ObstacleType.LINE;
    public static void main( String[] args ) {
		PhysicsParameters.get().setPlaneMaterial(Material.CONCRETE);
        PhysicsParameters.get().setPhysicsSimulationStepSize(0.01f);
 		PhysicsParameters.get().setRealisticCollision(true);
		PhysicsParameters.get().setWorldDampingLinearVelocity(0.5f);
		PhysicsParameters.get().setMaintainRotationalJointPositions(true); 
		new Snake4Simulation().main();
    }
	
	protected void simulationHook(PhysicsSimulation simulation) {
		super.simulationHook(simulation);
		if(hasCMTracker) {
			CMTracker tracker = new CMTracker(simulation);
			WifiCMBroadcaster broadcaster = new WifiCMBroadcaster(simulation, 7.0, tracker);
			simulation.subscribePhysicsTimestep(broadcaster);
		}	
		if(hasRadioConnection) {
			radioConnection = new RadioConnection(simulation, 9899); //allow Modular commander to communicate with USSR 
			radioConnection.setPackToASE(true);
		}
	}

	
	protected Robot getRobot() {

        ATRON robot = new ATRON() {
            public Controller createController() {
                return new ATRONReflectionEventController();
            }
        };
        
        robot.setRealistic();
        robot.setRadio();

        return robot;
    }
	
	protected ArrayList<ModulePosition> buildSnake(int length) {
    	float Yoffset = 0.4f;
    	ArrayList<ModulePosition> mPos = new ArrayList<ModulePosition>();
    	int x=0,y=0,z=0;
    	int portRC = 9900;
    	int portEvent = 9901;
    	String radioStr = "";
    	for(int i=0;i<length;i++) {
    		if(i%2==0) {
    			mPos.add(new ModulePosition(i+"", ";portRC="+portRC+";portEvent="+portEvent+""+radioStr, new VectorDescription(x*ATRON.UNIT,y*ATRON.UNIT-Yoffset,z*ATRON.UNIT), ATRON.ROTATION_EW));
    		}
    		else {
    			mPos.add(new ModulePosition(i+"", ";portRC="+portRC+";portEvent="+portEvent+""+radioStr, new VectorDescription(x*ATRON.UNIT,y*ATRON.UNIT-Yoffset,z*ATRON.UNIT), ATRON.ROTATION_NS));
    		}
    		portRC+=2;portEvent+=2;
    		x++;z++;
    		radioStr = ";radio=disabled";	//disable all but first module in snake
    	}
    	return mPos;
	}

	protected ArrayList<ModulePosition> buildRobot() {
		return buildSnake(4);
	}
    
    protected void changeWorldHook(WorldDescription world) {
    	world.setPlaneTexture(WorldDescription.WHITE_GRID_TEXTURE);
		world.setHasBackgroundScenery(false);
		PhysicsFactory.getOptions().setStartPaused(false);
    }
}
