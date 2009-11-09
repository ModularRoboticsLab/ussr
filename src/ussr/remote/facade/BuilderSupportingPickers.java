package ussr.remote.facade;

import ussr.aGui.tabs.controllers.ConstructRobotTabController;
import ussr.builder.SupportedModularRobots;
import ussr.builder.constructionTools.ConstructionToolSpecification;
import ussr.builder.constructionTools.ConstructionTools;
import ussr.builder.genericTools.ColorConnectors;
import ussr.builder.genericTools.RemoveModule;
import ussr.physics.jme.JMESimulation;
import ussr.physics.jme.pickers.PhysicsPicker;
import ussr.physics.jme.pickers.Picker;

/**
 * Pickers (left side of the mouse selectors) supported by builder.
 * @author Konstantinas
 *
 */
public enum BuilderSupportingPickers {

	
	/**
	 * Move modules with the mouse in simulation environment during simulation runtime.
	 */
	DEFAULT(new PhysicsPicker()),
	
	/**
	 * Remove module in static state of simulation (also works during simulation runtime, however that is not recommended).
	 */
	REMOVE_MODULE(new RemoveModule()),
	
	/**
	 * Move module with movement of the mouse in static state of simulation (hold left side of the mouse).
	 */
	MOVE_MODULE(new PhysicsPicker(true,true)),	
	
	/**
	 * Rotates the module with opposite rotation to current one.
	 */
	ROTATE_MODULE_OPPOSITE(new ConstructionToolSpecification(ConstructRobotTabController.getChosenMRname(),ConstructionTools.OPPOSITE_ROTATION)),

	/**
	 * Colors connectors on the module with color coding.
	 */
	COLOR_CONNECTORS(new ColorConnectors()),
	
	/**
	 * Rotates the module with standard rotation.
	 */
	//TEST ME
	ROTATE_MODULE_STANDARD (new ConstructionToolSpecification(ConstructRobotTabController.getChosenMRname(),ConstructionTools.STANDARD_ROTATIONS,ConstructRobotTabController.getChosenStandardRotation())),
	
	/**
	 * Adds new module on connector selected by user.
	 */
	ON_SELECTED_CONNECTOR(new ConstructionToolSpecification(ConstructRobotTabController.getChosenMRname(),ConstructionTools.ON_SELECTED_CONNECTOR)),
	
	/**
	 * 
	 */
	//TEST ME
	ON_CHOSEN_CONNECTOR_NR(new ConstructionToolSpecification(ConstructRobotTabController.getChosenMRname(),ConstructionTools.ON_CHOSEN_CONNECTOR,ConstructRobotTabController.getChosenConnectorNr())),
	
	/**
	 * 
	 */
	//TEST ME
	ON_ALL_CONNECTORS(new ConstructionToolSpecification(ConstructRobotTabController.getChosenMRname(),ConstructionTools.ON_ALL_CONNECTORS));
	
	
	;
	
	
	
	/**
	 * The picker supported by builder.
	 */
	private Picker picker;
	
	/**
	 * Contains pickers (left side of the mouse selectors) supported by builder.
	 * @param picker, the picker supported by builder.
	 */
	BuilderSupportingPickers(Picker picker){
		this.picker = picker;
	}
	/**
	 * Returns the picker supported by builder.
	 * @return, the picker supported by builder.
	 */
	public Picker getPicker(){
		return this.picker;
	}
	

		
	
	
}
