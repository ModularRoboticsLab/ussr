package ussr.aGui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

import ussr.aGui.tabs.TabsInter;
import ussr.aGui.tabs.constructionTabs.AssignBehaviorsTab;
import ussr.aGui.tabs.constructionTabs.ConstructRobotTab;
import ussr.aGui.tabs.controllers.AssignBehaviorsTabController;
import ussr.aGui.tabs.controllers.ConstructRobotTabController;
import ussr.aGui.tabs.controllers.ModuleCommunicationVisualizerController;
import ussr.remote.facade.RendererControlInter;


/**
 * Controls events generated by main GUI window. Currentlly it is MainFrameSeparate. 
 * @author Konstantinas
 */
public class MainFrameSeparateController extends GeneralController {

	/**
	 * Remote version of rendering control object.
	 */
	private static RendererControlInter rendererControl;

	/**
	 * Executes closing of main window(frame) and simulation by terminating Java Virtual Machines.
	 */
	public static void jMenuItemExitActionPerformed() {	
		try {
			if (remotePhysicsSimulation!=null){
			remotePhysicsSimulation.stop();
			}
		} catch (RemoteException e) {
			//Do not throw anything, because this means only GUI is closed and simulation was not even started			
		}
		System.exit(0);		
	} 

	/**
	 * Opens file chooser in the form of Open dialog
	 * 
	 */
	public static void openActionPerformed(FramesInter fcOpenFrame) {
		fcOpenFrame.activate();		
	}

	/**
	 * Opens file chooser in the form of Save dialog
	 * 
	 */
	public static void saveActionPerformed(FramesInter fcSaveFrame) {				
		fcSaveFrame.activate();				
	}

	/**
	 * Keeps track for how many times simulation was started, so that only first time all connectors on the modules will be connected.
	 */
	private static int timesSelected =0;

	/**
	 * Starts running remote simulation in real time.
	 */
	public static void jButtonRunRealTimeActionPerformed() {
		adaptGUI();
		
		timesSelected++;
		connectModules();
		
		try {
			if (remotePhysicsSimulation.isPaused()){// Start simulation in real time, if simulation is in paused state
				remotePhysicsSimulation.setPause(false);				
			}
			remotePhysicsSimulation.setRealtime(true);

			builderControl.setDefaultPicker();			
		} catch (RemoteException e) {
			throw new Error ("Pausing or running remote simulation in real time failed, due to remote exception");
		}
	}
	
	/**
	 * Enables and disables GUI components in relation to selection of run simulation buttons like run real time, fast and step by step.
	 */
	private static void adaptGUI(){
		ConstructRobotTab.setTabEnabled(false);
		AssignBehaviorsTab.setTabEnabled(false);
		MainFrameSeparate.getJToggleButtonVisualizer().setEnabled(true);
		ModuleCommunicationVisualizerController.setIdsModules();
	}


	/**
	 * Starts running remote simulation in fast mode.
	 */
	public static void jButtonRunFastActionPerformed() {

		adaptGUI();
		
		timesSelected++;
		connectModules();

		try {
			if (remotePhysicsSimulation.isPaused()){// Start simulation  fast, if simulation is in paused state
				remotePhysicsSimulation.setPause(false);				
			}
			remotePhysicsSimulation.setRealtime(false);

			builderControl.setDefaultPicker();
		} catch (RemoteException e) {
			throw new Error ("Pausing or running remote simulation in fast mode failed, due to remote exception");
		}
	}

	private static void connectModules(){

		if (timesSelected==1){
			try {
				builderControl.connectAllModules();
			} catch (RemoteException e) {
				throw new Error ("Failed to connect modules, due to remote exception");
			}
			
			/*Disable GUI components responsible for opening file choosers, because it is possible to load
			 *simulation from XML file only in static state of simulation.*/ 
			MainFrames.setSaveOpenEnabled(false);
		}		
	}

	/**
	 * Executes running remote simulation in step by step fashion.
	 */
	public static void jButtonRunStepByStepActionPerformed() {       	
		adaptGUI();
		
		timesSelected++;
		connectModules();

		try {
			remotePhysicsSimulation.setPause(true);
			remotePhysicsSimulation.setSingleStep(true);
		} catch (RemoteException e) {
			throw new Error ("Running remote simulation in single step mode failed, due to remote exception");
		}		
	}


	/**
	 * Pauses remote simulation.
	 */
	public static void jButtonPauseActionPerformed() {       	
		try {
			if (remotePhysicsSimulation.isPaused()==false){
				remotePhysicsSimulation.setPause(true);
			}
		} catch (RemoteException e) {
			throw new Error ("Pausing remote simulation failed, due to remote exception");
		}
	}

	/**
	 * Terminates remote simulation.
	 */
	public static void jButtonTerminateActionPerformed() {
		try {
			remotePhysicsSimulation.stop();			
		} catch (RemoteException e) {
			throw new Error ("Termination of remote simulation failed, due to remote exception");
		}
	}

	/**
	 * Renders or stops rendering the physics during remote simulation.
	 * @param jCheckBoxMenuItemPhysics, component in GUI.
	 */
	public static void jCheckBoxMenuItemPhysicsActionPerformed(JCheckBoxMenuItem jCheckBoxMenuItemPhysics) {       
       
		//FIXME
		/*try {
			rendererControl.moveDisplayTo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			if (rendererControl.isShowingPhysics() == false ){                             
				jCheckBoxMenuItemPhysics.setSelected(true);
				rendererControl.setShowPhysics(true);				
			}else {                         
				jCheckBoxMenuItemPhysics.setSelected(false);
				rendererControl.setShowPhysics(false);				
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of rendering physics on remote simulation failed, due to remote exception");
		}	 
	}	


	/**
	 * Renders or stops rendering the wire state during remote simulation.
	 * @param jCheckBoxMenuItemWireFrame, component in GUI.
	 */
	public static void jCheckBoxMenuItemWireFrameActionPerformed(JCheckBoxMenuItem jCheckBoxMenuItemWireFrame) {

		try {
			if ( rendererControl.isShowingWireFrame() == false ){        
				jCheckBoxMenuItemWireFrame.setSelected(true);
				rendererControl.setShowWireFrame(true);			
			}else {            
				jCheckBoxMenuItemWireFrame.setSelected(false);
				rendererControl.setShowWireFrame(false);		
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of rendering wire frame on remote simulation failed, due to remote exception");
		}   
	}

	/**
	 * 
	 * Renders or stops rendering the bounds during remote simulation.
	 * @param jCheckBoxMenuBounds, component in GUI.
	 */
	public static void jCheckBoxMenuBoundsActionPerformed(JCheckBoxMenuItem jCheckBoxMenuBounds) {       
		try {
			if (rendererControl.isShowingBounds() == false ){        
				jCheckBoxMenuBounds.setSelected(true);
				rendererControl.setShowBounds(true);			
			}else {            
				jCheckBoxMenuBounds.setSelected(false);
				rendererControl.setShowBounds(false);			
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of rendering bounds on remote simulation failed, due to remote exception");
		}   
	}

	/**
	 * Renders or stops rendering the normals during remote simulation.
	 * @param jCheckBoxMenuItemNormals,component in GUI.
	 */
	public static void jCheckBoxMenuItemNormalsActionPerformed(JCheckBoxMenuItem jCheckBoxMenuItemNormals) {    
		try {
			if (rendererControl.isShowingNormals() == false ){            
				jCheckBoxMenuItemNormals.setSelected(true);
				rendererControl.setShowNormals(true);			
			}else {            
				jCheckBoxMenuItemNormals.setSelected(false);
				rendererControl.setShowNormals(false);			
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of showing normals on remote simulation failed, due to remote exception");
		}      
	}

	/**
	 * Renders or stops rendering the lights during remote simulation.
	 * @param jCheckBoxMenuItemLights,component in GUI. 
	 */
	public static void jCheckBoxMenuItemLightsActionPerformed(JCheckBoxMenuItem jCheckBoxMenuItemLights) {   
		try {
			if (rendererControl.isLightStateShowing() == true ){                       
				jCheckBoxMenuItemLights.setSelected(true);
				rendererControl.setShowLights(false);			
			}else {                   
				jCheckBoxMenuItemLights.setSelected(false);
				rendererControl.setShowLights(true);			
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of showing lights on remote simulation failed, due to remote exception");
		}  
	}

	/**
	 * Renders or stops rendering the depth of the buffer during remote simulation.
	 * @param jCheckBoxMenuBufferDepth, component in GUI. 
	 */
	public static void jCheckBoxMenuBufferDepthActionPerformed(JCheckBoxMenuItem jCheckBoxMenuBufferDepth) {
		try {			
			if (rendererControl.isShowingDepth() == false ){                       
				jCheckBoxMenuBufferDepth.setSelected(true);
				rendererControl.setShowDepth(true);			
			}else {                   
				jCheckBoxMenuBufferDepth.setSelected(false);
				rendererControl.setShowDepth(false);			
			}
		} catch (RemoteException e) {
			throw new Error ("Changing the state of showing buffer depth on remote simulation failed, due to remote exception");
		}  
	}

	/**
	 * Adds or removes tabs for construction of modular robot morphology.
	 * @param jToggleButtonConstructRobot, the toggle button in main frame.
	 * @param jTabbedPaneFirst, the tabbed pane used as container for construction tabs.
	 * @param tabs, container of all tabs in main frame.
	 */
	public static void jButtonConstructRobotActionPerformed(JToggleButton jToggleButtonConstructRobot, JTabbedPane jTabbedPaneFirst, ArrayList<TabsInter> tabs ) {
		
		/*Check if tabs are defined*/
		TabsInter constructRobotTab = getTabByTitle(MainFramesInter.CONSTRUCT_ROBOT_TAB_TITLE,tabs);
		TabsInter assignBehaviorsTab = getTabByTitle(MainFramesInter.ASSIGN_BEHAVIORS_TAB_TITLE,tabs);


		if (jToggleButtonConstructRobot.isSelected()){
			/*Add tabs for construction of modular robot*/
			jTabbedPaneFirst.addTab(constructRobotTab.getTabTitle(),new javax.swing.ImageIcon(constructRobotTab.getImageIconDirectory()),constructRobotTab.getJComponent());
			jTabbedPaneFirst.addTab(assignBehaviorsTab.getTabTitle(),new javax.swing.ImageIcon(assignBehaviorsTab.getImageIconDirectory()),assignBehaviorsTab.getJComponent());

			/*Update look and feel for newly added tabs*/		
			MainFrames.changeToLookAndFeel(constructRobotTab.getJComponent());
			MainFrames.changeToLookAndFeel(assignBehaviorsTab.getJComponent());
		}else{
			/*Identify and remove tabs for construction of modular robot*/
			for (int index=0; index < jTabbedPaneFirst.getTabCount(); index++){
				String tabTitle = jTabbedPaneFirst.getTitleAt(index);
				if (tabTitle.equals(MainFramesInter.CONSTRUCT_ROBOT_TAB_TITLE)){
					jTabbedPaneFirst.removeTabAt(index);
					index--;
				}else if (tabTitle.equalsIgnoreCase(MainFramesInter.ASSIGN_BEHAVIORS_TAB_TITLE)){
					jTabbedPaneFirst.removeTabAt(index);
				}
			}
		}
		/*Adapt construction tabs to the first module in simulation environment if it exists.*/
		ConstructRobotTabController.adaptTabToModuleInSimulation();
		AssignBehaviorsTabController.adaptTabToModuleInSimulation();
	}

	/**
	 * Checks if the tab with specific title is in the container for tabs.
	 * @param tabTitle, the title of the tab to look for.
	 * @param tabs, container of all tabs in main frame.
	 * @return foundTab, the tab with the title to look for.
	 */
	private static TabsInter getTabByTitle( String tabTitle,ArrayList<TabsInter> tabs){
		TabsInter foundTab = null;
		for (int tabNr=0;tabNr<tabs.size();tabNr++){
			String currentTabTitle = tabs.get(tabNr).getTabTitle();
			if (currentTabTitle.equals(tabTitle)){
				foundTab = tabs.get(tabNr);
			}
		}
		if (foundTab == null){
			throw new Error("The tab with title ("+ tabTitle + ") was not found");
		}
		return foundTab;
	}

	/**
	 * Adds or removes tab for visualization of communication between modules.
	 * @param toggleButtonVisualizer, the toggle button in main frame.
	 * @param jTabbedPaneFirst,the tabbed pane used as container for construction tabs.
	 * @param tabs, container of all tabs in main frame.
	 */
	public static void jButtonVisualizerActionPerformed(JToggleButton toggleButtonVisualizer, JTabbedPane jTabbedPaneFirst, ArrayList<TabsInter> tabs) {
		/*Check if tab is defined*/
		TabsInter moduleCommunicationVisualizerTab = getTabByTitle(MainFramesInter.MODULE_COMMUNICATION_VISUALIZER_TAB_TITLE,tabs);

		if (toggleButtonVisualizer.isSelected()){
			/*Add tabs for visualizing module communication*/
			jTabbedPaneFirst.addTab(moduleCommunicationVisualizerTab.getTabTitle(),new javax.swing.ImageIcon(moduleCommunicationVisualizerTab.getImageIconDirectory()),moduleCommunicationVisualizerTab.getJComponent());
			/*Update look and feel for all tabs*/			
			MainFrames.changeToLookAndFeel(moduleCommunicationVisualizerTab.getJComponent());
			ModuleCommunicationVisualizerController.jButtonRunActionPerformed();
		}else{
			/*Identify and remove the tab for visualizing module communication*/
			for (int index=0; index < jTabbedPaneFirst.getTabCount(); index++){
				String tabTitle = jTabbedPaneFirst.getTitleAt(index);
				if (tabTitle.equals(MainFramesInter.MODULE_COMMUNICATION_VISUALIZER_TAB_TITLE)){
					jTabbedPaneFirst.removeTabAt(index);
				}
			}
		}		
	}



	/*Setters*/
	/**
	 * Sets renderer control of remote physics simulation for this controller.
	 * @param rendererControl, renderer control of remote physics simulation.
	 */
	public static void setRendererControl(RendererControlInter rendererControl) {
		MainFrameSeparateController.rendererControl = rendererControl;
	}
}
