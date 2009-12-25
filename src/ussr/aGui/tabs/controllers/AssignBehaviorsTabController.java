package ussr.aGui.tabs.controllers;

import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;

import ussr.aGui.helpers.hintPanel.HintPanelInter;
import ussr.aGui.helpers.hintPanel.HintPanelTypes;
import ussr.aGui.tabs.constructionTabs.AssignBehaviorsTab;

import ussr.builder.enumerations.LabeledEntities;
import ussr.builder.enumerations.LabelingTools;
import ussr.builder.enumerations.SupportedModularRobots;

import ussr.builder.helpers.FileDirectoryHelper;
import ussr.builder.labelingTools.LabelingTemplate;

public class AssignBehaviorsTabController extends TabsControllers {

	/**
	 * Container for keeping all classes of controllers extracted from package "ussr.builder.controllerAdjustmentTool";
	 */
	private static  Vector<String> classesOfControllers ;

	/**
	 * Temporary container for keeping classes of controllers filtered out for specific modular robot.
	 */
	private static  Vector<String> tempClassesOfControllers =  new Vector<String> ()  ;

	/**
	 * The name of the package where all behaviors are stored for interactive adjustment of controller.
	 */
	private static final String packageName = "ussr.builder.controllerAdjustmentTool";


	/**
	 * Loads all existing names of controllers from package ussr.builder.controllerAdjustmentTool and filters
	 * out the ones for selected button (modular robot name).
	 * @param radionButton, the radio button representing modular robot name.
	 */
	public static void jButtonGroupActionPerformed(javax.swing.AbstractButton radionButton){
		loadExistingControllers(AssignBehaviorsTab.getJListAvailableControllers());

		boolean modularRobotNameExists = false;
		SupportedModularRobots[] supportedModularRobots = SupportedModularRobots.values();
		for (int buttonTextItem=0;buttonTextItem<supportedModularRobots.length;buttonTextItem++){
			
			if (radionButton.getText().equals(supportedModularRobots[buttonTextItem].getUserFriendlyName())){
				String modularRobotName= SupportedModularRobots.getModularRobotSystemName(supportedModularRobots[buttonTextItem].getUserFriendlyName()).toString();
				updateList(AssignBehaviorsTab.getJListAvailableControllers(),filterOut(modularRobotName));
				modularRobotNameExists =true;
			}
		}

		if (modularRobotNameExists==false){
			throw new Error ("Not supported modulal robot name: "+ radionButton.getText());
		}
		/*Informing user*/
		AssignBehaviorsTab.getHintPanel().setType(HintPanelTypes.INFORMATION);
		AssignBehaviorsTab.getHintPanel().setText(HintPanelInter.builInHintsAssignBehaviorTab[1]);
	}

	/**
	 * Extracts and loads the names of controllers existing in the package "ussr.builder.controllerAdjustmentTool" into jList
	 * @param jList1, the component in GUI.
	 */
	public static void loadExistingControllers(javax.swing.JList jList1){

		Class[] classes = null;
		try {
			classes = FileDirectoryHelper.getClasses(packageName);
		} catch (ClassNotFoundException e) {
			throw new Error ("The package named as: "+ packageName + "was not found in the directory ussr.builder.controllerAdjustmentTool");			
		}		

		/*Loop through the classes and take only controllers, but not the classes defining the tool*/
		classesOfControllers = new Vector<String>();
		for (int i=0; i<classes.length;i++){
			if (classes[i].toString().contains("AssignControllerTool")||classes[i].toString().contains("ControllerStrategy")){
				//do nothing	
			}else{
				classesOfControllers.add(classes[i].toString().replace("class "+packageName+".", ""));
			}
		}			
		updateList(jList1,classesOfControllers);
		/*Update the list with newly loaded names of controllers*/
	}


	/**
	 * Updates the list with the names of controllers.
	 * @param jList1,the component in GUI. 
	 * @param controllers, vector of controllers.
	 */
	@SuppressWarnings("serial")
	public static void updateList(javax.swing.JList jList1,final Vector<String> controllers ){
		jList1.setModel(new javax.swing.AbstractListModel() {
			Object[] strings =  controllers.toArray();
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});		
	}


	/**
	 * Filters out the names of controller for specific modular robot name.
	 * @param modularRobotName, modular robot name.
	 * @return tempClassesOfControllers, array of controllers for specific modular robot name.
	 */
	public static Vector<String> filterOut(String modularRobotName){
		tempClassesOfControllers.removeAllElements();
		for (int index=0; index<classesOfControllers.size();index++){
			if (classesOfControllers.get(index).contains(modularRobotName)){
				tempClassesOfControllers.add(classesOfControllers.get(index));
			}
		}
		return tempClassesOfControllers;
	}

	/**
	 * Initializes the tool for assigning controller chosen by user in GUI component. 
	 * @param jList1,the component in GUI. 
	 */
	public static void jListAvailableControllersMouseReleased(javax.swing.JList jList1) {
		try {
			builderControl.setAdjustControllerPicker(packageName+"."+jList1.getSelectedValue());			
		} catch (RemoteException e) {
			throw new Error("Failed to initate picker called "+ "AssignControllerTool" + ", due to remote exception");
		}		
	}

	/**
	 * Adapts Assign Behaviors Tab to the the type of first module in simulation environment.
	 * TODO MAKE IT MORE GENERIC BY MEANS OF IDENTIFYING THE LAST TYPE OF MODULE IN XML FILE
	 * OR SOMETHING SIMILLAR.
	 */
	public static void adaptTabToModuleInSimulation(){
		int amountModules =0;		
		try {
			amountModules =  builderControl.getIDsModules().size();
		} catch (RemoteException e) {
			throw new Error("Failed to identify amount of modules in simulation environment, due to remote exception");
		}

		if (amountModules>0){
		/*	Adapt to first module*/
			String modularRobotName ="";
			try {
				modularRobotName = builderControl.getModuleType(0);
			} catch (RemoteException e) {
				throw new Error ("Failed to identify the type of the first module in simulation environment, due to remote exception.");
			}
			
			if (modularRobotName.toUpperCase().contains(SupportedModularRobots.ATRON.toString())){
				jButtonGroupActionPerformed(AssignBehaviorsTab.getRadionButtonATRON());
				AssignBehaviorsTab.getRadionButtonATRON().setSelected(true);
			} else if (modularRobotName.toUpperCase().contains(SupportedModularRobots.ODIN.toString())){
				jButtonGroupActionPerformed(AssignBehaviorsTab.getRadioButtonODIN());
				AssignBehaviorsTab.getRadioButtonODIN().setSelected(true);
			} else if (modularRobotName.toUpperCase().contains(SupportedModularRobots.MTRAN.toString())){
				jButtonGroupActionPerformed(AssignBehaviorsTab.getRadioButtonMTRAN());
				AssignBehaviorsTab.getRadioButtonMTRAN().setSelected(true);
			}else if(modularRobotName.toUpperCase().contains(SupportedModularRobots.CKBOT_STANDARD.toString())){
				jButtonGroupActionPerformed(AssignBehaviorsTab.getRadionButtonCKBOTSTANDARD());
				AssignBehaviorsTab.getRadionButtonCKBOTSTANDARD().setSelected(true);
			}		
		}
	}
	
	
	public static void updateHintPanel(HintPanelTypes hintPanelTypes,String text){
		AssignBehaviorsTab.getHintPanel().setType(hintPanelTypes);
		AssignBehaviorsTab.getHintPanel().setText(text);
	}
	
	
}

