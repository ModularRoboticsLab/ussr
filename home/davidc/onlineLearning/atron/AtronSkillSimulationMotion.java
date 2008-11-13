package onlineLearning.atron;
import java.awt.Color;
import java.util.Random;

import onlineLearning.SkillFileManager;
import onlineLearning.SkillLearner;
import onlineLearning.skills.SkillQ;
import ussr.description.setup.WorldDescription;
import ussr.model.Controller;
import ussr.model.Module;
import ussr.physics.PhysicsObserver;
import ussr.physics.PhysicsSimulation;
import ussr.samples.atron.GenericATRONSimulation;

import com.jme.math.Vector3f;
public class AtronSkillSimulationMotion extends AtronSkillSimulation implements PhysicsObserver {
	float oldDist = 0;  
    float oldPrintTime=0;
    float oldSkillTime=0;
    
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<args.length;i++) {
			if(args[i].contains("TrialID=")) {
				trialID = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
			}
			else if(args[i].contains("LearningStrategy=")) {
				String strategy = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				SkillLearner.setLearningStrategy(strategy);
			}
			else if(args[i].contains("RoleSelectionStrategy=")) {
				String strategy = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				SkillQ.setRoleSelectionStrategy(strategy);
			}
			else if(args[i].contains("RewardEstimationStragegy=")) {
				String strategy = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				SkillQ.setRewardEstimationStragegy(strategy);
			}
			else if(args[i].contains("Accelerated=")) {
				String acc = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				SkillQ.setAccelerated(Boolean.parseBoolean(acc));
			}
			else if(args[i].contains("syncronized=")) {
				String syncVal = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				syncronized = Boolean.parseBoolean(syncVal);
			}
			else if(args[i].contains("primitiveRoles=")) {
				String primVal = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				primitiveRoles = Boolean.parseBoolean(primVal);
			}
			else if(args[i].contains("robot=")) {
				String robotVal = args[i].subSequence(args[i].indexOf('=')+1, args[i].length()).toString();
				try{
					robotType = ATRONRobots.valueOf(robotVal);
				} 
				catch(Exception e) {System.out.println("Error parsing "+robotVal);}
				System.out.println("Robot type set to "+robotType);
			}
			else {
				System.out.println("Unrecognized option "+args[i]);
			}
		}
		System.out.println("***** Starting Simulation Trial "+trialID+" **********");
		GenericATRONSimulation.startPaused=startPaused;
		new AtronSkillSimulationMotion().main();
		
    }
	public Vector3f getRobotCM() {
    	Vector3f cm = new Vector3f();
    	int nModulesInRobot = 0;
        for(int i=0;i<simulation.getModules().size();i++) {
    		cm = cm.addLocal((Vector3f)simulation.getHelper().getModulePos(simulation.getModules().get(i)).get());
    		nModulesInRobot++;
        }
        cm = cm.multLocal(1.0f/nModulesInRobot);
        return cm;
    }
	public void physicsTimeStepHook(PhysicsSimulation simulation) {
    	//simulation.setGravity(0.0f);
		/*for(Module m: simulation.getModules()) {
			m.addExternalForce(0f,(float)Math.sin(simulation.getTime())*10f,0f);
		}*/
		//System.out.println("{"+simulation.getTime()+","+getRobotCM().y+"},");
	/*	Random r = new Random();
		for(Module m: simulation.getModules()) {
			m.setColor(new Color(r.nextFloat(),r.nextFloat(),r.nextFloat()));
		}*/
       	if(simulation.getTime()>simulationTime) {
       		System.out.println("stopping simulation at time "+simulation.getTime());
   			simulation.stop();
       	}
    }
	protected void changeWorldHook(WorldDescription world) {
		SkillFileManager.initLogFiles("ATRON",robotType.name(),trialID);
		if(loadSkillsFromFile) SkillFileManager.loadSkills();
		//world.setPlaneTexture(WorldDescription.WHITE_TEXTURE);
		world.setPlaneTexture(WorldDescription.WHITE_GRID_TEXTURE);
		
		world.setHasBackgroundScenery(false);
		world.setFlatWorld(true);
		for(int i=0;i<ignoreConnections.size();i++) {
			for(int j=0;j<world.getConnections().size();j++) {
				if(world.getConnections().get(j).equals(ignoreConnections.get(i))) {
					System.out.println("Found one");
					world.getConnections().remove(j);
				}
			}
		}
		//PhysicsParameters.get().setResolutionFactor(2);
        /*WorldDescription.BoxDescription[] boxes = new WorldDescription.BoxDescription[5];
        VectorDescription size = new VectorDescription(10f,0.1f,0.05f);
        RotationDescription rotEastWest = new RotationDescription(new Quaternion(new float[]{0,(float)Math.PI/2,0}));
        RotationDescription rotNorthSouth= new RotationDescription(new Quaternion());
        boxes[0] = new WorldDescription.BoxDescription(new VectorDescription(10,-0.4f,0),size,rotEastWest,true);
        boxes[1] = new WorldDescription.BoxDescription(new VectorDescription(-10,-0.4f,0),size,rotEastWest,true);
        boxes[2] = new WorldDescription.BoxDescription(new VectorDescription(0,-0.4f,10),size,rotNorthSouth,true);
        boxes[3] = new WorldDescription.BoxDescription(new VectorDescription(0,-0.4f,-10),size,rotNorthSouth,true);
        
        boxes[4] = new WorldDescription.BoxDescription(new VectorDescription(0,0,0),new VectorDescription(0.05f,0.05f,0.05f),false);
        
        world.setBigObstacles(boxes);*/
    }
	protected void simulationHook(PhysicsSimulation simulation) {
		simulation.subscribePhysicsTimestep(this);
    }
	
	public Controller getController() {
		return new AtronSkillController();
	}
}
