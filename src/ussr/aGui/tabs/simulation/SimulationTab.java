package ussr.aGui.tabs.simulation;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import javax.swing.ImageIcon;

import javax.swing.border.TitledBorder;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ussr.aGui.MainFrames;
import ussr.aGui.enumerations.hintpanel.HintsSimulationTab;
import ussr.aGui.enumerations.tabs.TabsComponentsText;
import ussr.aGui.enumerations.tabs.TabsIcons;
import ussr.aGui.helpers.hintPanel.HintPanel;

import ussr.aGui.tabs.Tabs;
import ussr.aGui.tabs.controllers.SimulationTabController;
import ussr.builder.simulationLoader.SimulationSpecification;

/**
 * Defines visual appearance of the tab called Simulation.
 * @author Konstantinas
 */
public class SimulationTab extends Tabs {

	/**
	 * The constrains of grid bag layout used during design of the tab.
	 */
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();
	
	private static String selectedNodeName;

	public static String getSelectedNodeName() {
		return selectedNodeName;
	}

	/**
	 * Defines visual appearance of the tab called "Simulation".
	 * @param initiallyVisible, true if the tab is visible after activation of main GUI window. 
	 * @param firstTabbedPane, location of the tab in the main GUI frame. True if it is the first tabbed pane from the top.
	 * @param tabTitle, the title of the tab.
	 * @param imageIconDirectory,the directory for icon displayed in the top-left corner of the tab.
	 */
	public SimulationTab(boolean initiallyVisible, boolean firstTabbedPane, String tabTitle, String imageIconDirectory){
		super(initiallyVisible,firstTabbedPane,tabTitle,imageIconDirectory);

		/*instantiate new panel, which will be the container for all components situated in the tab*/		
		super.jComponent = new javax.swing.JPanel(new GridBagLayout());
		//super.jComponent.setPreferredSize(new Dimension(MainFrames.getMainFrameViableWidth(),300));
		initComponents();
	}

	/**
	 * Initializes the visual appearance of all components in the tab.
	 * Follows Strategy  pattern.
	 */
	public void initComponents() {
		/*Instantiate components*/
		jScrollPaneTreeSimulation = new javax.swing.JScrollPane();
		jPanelEditor = new javax.swing.JPanel(new GridBagLayout());
		jTreeSimulation = new javax.swing.JTree();
		jSplitPaneSimulationTreeAndEditor = new javax.swing.JSplitPane();
		
		
		/*Define components*/
		DefaultTreeModel treeModel = new DefaultTreeModel(initializeSimulationRootNode());
		jTreeSimulation.setModel(treeModel);
		jTreeSimulation.setExpandsSelectedPaths(true);
		
		ImageIcon closedIcon = TabsIcons.EXPANSION_CLOSED_SMALL.getImageIcon();
		ImageIcon openIcon = TabsIcons.EXPANSION_OPENED_SMALL.getImageIcon();
		ImageIcon leafIcon = TabsIcons.FINAL_LEAF_SMALL.getImageIcon();
		if (openIcon != null) {
			DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();		    
			renderer.setClosedIcon(closedIcon);
			renderer.setOpenIcon(openIcon);
			renderer.setLeafIcon(leafIcon);
			jTreeSimulation.setCellRenderer(renderer);
		}
		jTreeSimulation.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTreeSimulation.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				jTreeSimulation.getLastSelectedPathComponent();
				if (node == null)
					/*Nothing is selected.*/	
					return;
				Object nodeInfo = node.getUserObject();
				if (node.isLeaf()||node.isNodeRelated(firstNodeHierarchySimulation)) {
					selectedNodeName = node.getPath()[node.getPath().length-1].toString();
					//System.out.println(selectedNodeName);
					SimulationTabController.jTreeItemSelectedActionPerformed(selectedNodeName);
				} 
			}
		});

		jScrollPaneTreeSimulation.setViewportView(jTreeSimulation);
		jScrollPaneTreeSimulation.setPreferredSize(new Dimension(300,300));
	

		TitledBorder displayTitle;
		displayTitle = BorderFactory.createTitledBorder(TabsComponentsText.EDIT_VALUE.getUserFriendlyName());
		displayTitle.setTitleJustification(TitledBorder.CENTER);
		jPanelEditor.setBorder(displayTitle);
		jPanelEditor.setPreferredSize(new Dimension(300,300));
		
		jSplitPaneSimulationTreeAndEditor.setOrientation(javax.swing.JSplitPane.HORIZONTAL_SPLIT);
		
		jSplitPaneSimulationTreeAndEditor.setLeftComponent(jScrollPaneTreeSimulation);
		jSplitPaneSimulationTreeAndEditor.setRightComponent(jPanelEditor);
		jSplitPaneSimulationTreeAndEditor.setPreferredSize(new Dimension(300,300));
	
		
		jSplitPaneSimulationTreeAndEditor.setDividerSize(5);
		jSplitPaneSimulationTreeAndEditor.setDividerLocation(280);
		jSplitPaneSimulationTreeAndEditor.setVisible(false);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		
		super.jComponent.add(jSplitPaneSimulationTreeAndEditor,gridBagConstraints);
		
		hintPanel = new HintPanel(600, HINT_PANEL_HEIGHT);
		hintPanel.setBorderTitle("Display for hints");
		hintPanel.setText(HintsSimulationTab.DEFAULT.getHintText());
		hintPanel.setVisible(false);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;		
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;

		super.jComponent.add(hintPanel,gridBagConstraints);
	}

	public static javax.swing.JTree getJTreeSimulation() {
		return jTreeSimulation;
	}

	/**
	 * Initializes simulation root tree node including all sub nodes.
	 * @return simulation root tree node including all sub nodes.
	 */
	private DefaultMutableTreeNode initializeSimulationRootNode(){
		
		for(int index =0;index<SimulationTabTreeNodes.values().length;index++){
			SimulationTabTreeNodes currentNode = SimulationTabTreeNodes.values()[index];
			switch(currentNode.getPlaceInHierarchy()){
			case FIRST:
				firstNodeHierarchySimulation = new DefaultMutableTreeNode(currentNode.getUserFriendlyName());
				//set default mutable tree node for future easier reference.
				currentNode.setDefaultMutableTreeNode(firstNodeHierarchySimulation);
			break;
			case SECOND:
				secondNodeHierarchy = new DefaultMutableTreeNode(currentNode.getUserFriendlyName());
				currentNode.setDefaultMutableTreeNode(secondNodeHierarchy);
				firstNodeHierarchySimulation.add(secondNodeHierarchy);
				break;
			case THIRD:
				thirdNodeHierarchy = new DefaultMutableTreeNode(currentNode.getUserFriendlyName());
				currentNode.setDefaultMutableTreeNode(thirdNodeHierarchy);
				secondNodeHierarchy.add(thirdNodeHierarchy);
				break;
			case NOT_USED://do nothing
				break;
			default: throw new Error("Place in hierarchy "+ currentNode.getPlaceInHierarchy()+ " is not supported yet.");
			}
		}
		return firstNodeHierarchySimulation;
	}

	/**
	 * 
	 */
	private static boolean firstTime = true;
	
	private static int robotNumber=0;
	
	/**
	 * @param simulationSpecification
	 */
	public static void addRobotNode(SimulationSpecification simulationSpecification, boolean fromSimulationXMLFile){
		
		
		DefaultTreeModel model = (DefaultTreeModel)jTreeSimulation.getModel();
		DefaultMutableTreeNode robotsNode = (DefaultMutableTreeNode) model.getChild(model.getRoot(),2);
		
		if (fromSimulationXMLFile){
			robotNumber =0;//reset
			
			robotsNode.removeAllChildren();			
			int nrRobotsInSimulation = simulationSpecification.getRobotsInSimulation().size();
			for (int robotNr=1;robotNr<nrRobotsInSimulation+1;robotNr++){
				DefaultMutableTreeNode thirdNodeHierarchyRobot =  new DefaultMutableTreeNode(SimulationTabTreeNodes.ROBOT_NR.getUserFriendlyName()+"."+robotNr);
				robotsNode.add(thirdNodeHierarchyRobot);
				//secondNodeHierarchyRobots.add(thirdNodeHierarchyRobot);
				robotNumber =robotNr;
			}
			
		}else{
			robotNumber++;
			DefaultMutableTreeNode thirdNodeHierarchyRobot =  new DefaultMutableTreeNode(SimulationTabTreeNodes.ROBOT_NR.getUserFriendlyName()+"."+robotNumber);
        	//DefaultTreeModel model = (DefaultTreeModel)jTreeSimulation.getModel();
        	model.insertNodeInto(thirdNodeHierarchyRobot, robotsNode, robotsNode.getChildCount());	
		}
		model.reload();		
	
		jTreeSimulationExpandAllNodes();
	
		//jTreeSimulation.validate();
		//jTreeSimulation.repaint();
		jScrollPaneTreeSimulation.setViewportView(jTreeSimulation);
		
	} 
	
	public static void jTreeSimulationExpandAllNodes(){
		for (int rowNr = 0; rowNr < jTreeSimulation.getRowCount(); rowNr++) {
			jTreeSimulation.expandRow(rowNr);
		}
	}

	public static javax.swing.JPanel getJPanelEditor() {

		return jPanelEditor;
	}
	
	public static void setTabEnabled(boolean enabled){
		
		jSplitPaneSimulationTreeAndEditor.setEnabled(enabled);
		hintPanel.setEnabled(enabled);
		
	};

	public static void setTabVisible(boolean visible) {		
		jScrollPaneTreeSimulation.setVisible(visible);
		jPanelEditor.setVisible(visible);
		jSplitPaneSimulationTreeAndEditor.setVisible(visible);
		
		hintPanel.setVisible(visible);	
		hintPanel.setText(HintsSimulationTab.DEFAULT.getHintText());
		
	}
	
	public static void resizeComponents(){
		int simulationTabIndex = 0;
		int heightIcon = MainFrames.getJTabbedPaneFirst().getIconAt(simulationTabIndex).getIconHeight();
		int height = MainFrames.getJTabbedPaneFirst().getHeight()-(3*heightIcon)- HINT_PANEL_HEIGHT ;
		//int width = (int)MainFrames.getJTabbedPaneFirst().getWidth()/2;
		
		jSplitPaneSimulationTreeAndEditor.setPreferredSize(new Dimension(600,height));		
		jSplitPaneSimulationTreeAndEditor.validate();	
	}

/*	public static void addMorphologyEditor() {
		Map<String,String> fileDescriptionsAndExtensions= new HashMap<String,String>();
		fileDescriptionsAndExtensions.put(FileChooserFrameInter.ROBOT_FILE_DESCRIPTION, FileChooserFrameInter.DEFAULT_FILE_EXTENSION);
		//System.out.println("BOOO"+RobotSpecification.getMorphologyLocation());

		//FileChooserFrameInter fcOpenFrame = new FileChooserOpenFrame(fileDescriptionsAndExtensions,FileChooserFrameInter.FC_XML_CONTROLLER,TemporaryRobotSpecification.getMorphologyLocation());
		//fcOpenFrame.setSelectedFile(new File("some.xml"));

		//jPanelEditor.add(MainFrames.initOpenButton(fcOpenFrame));
	}*/

	//private static String robotMorphologyLocation;



	/*public static void setRobotMorphologyLocation(String robotMorphologyLocation) {
		SimulationTab.robotMorphologyLocation = robotMorphologyLocation;
	}*/


	private static HintPanel  hintPanel;

	public static HintPanel getHintPanel() {
		return hintPanel;
	}


	public static javax.swing.JSpinner getJSpinnerCoordinateValue() {
		return jSpinnerCoordinateValue;
	}

	private static javax.swing.JTree jTreeSimulation;
	private static javax.swing.JScrollPane jScrollPaneTreeSimulation;

	private static javax.swing.JPanel jPanelEditor;
	private static javax.swing.JSpinner jSpinnerCoordinateValue;

	private static  DefaultMutableTreeNode firstNodeHierarchySimulation,secondNodeHierarchy,thirdNodeHierarchy;
	
	private static javax.swing.JSplitPane jSplitPaneSimulationTreeAndEditor;



}
