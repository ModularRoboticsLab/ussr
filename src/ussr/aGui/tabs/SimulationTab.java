package ussr.aGui.tabs;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import ussr.aGui.enumerations.TextureDescriptions;
import ussr.aGui.enumerations.TreeElements;
import ussr.aGui.tabs.additionalResources.CheckBoxEditor;
import ussr.aGui.tabs.additionalResources.SpinnerEditor;
import ussr.aGui.tabs.controllers.SimulationTabController;
import ussr.description.setup.WorldDescription;


/**

 * @author Konstantinas
 */
public class SimulationTab extends Tabs {


	/**
	 * The constrains of grid bag layout used during design of the tab.
	 */
	public  GridBagConstraints gridBagConstraints = new GridBagConstraints();

	/**
	 * @param initiallyVisible
	 * @param firstTabbedPane
	 * @param tabTitle
	 * @param imageIconDirectory
	 */
	public SimulationTab(boolean initiallyVisible, boolean firstTabbedPane, String tabTitle, String imageIconDirectory){
		super(initiallyVisible,firstTabbedPane,tabTitle,imageIconDirectory);

		/*instantiate new panel, which will be the container for all components situated in the tab*/		
		super.jComponent = new javax.swing.JPanel(new GridBagLayout());
		initComponents();
	}

	String[][] treeElements  = { { "WorldDescription", "Red", "Blue", "Green" },
			{ "PhysicsParameters", "Tart", "Sweet", "Bland" },
	};
	/**
	 * Initializes the visual appearance of all components in the tab.
	 * Follows Strategy  pattern.
	 */
	public void initComponents() {
		//jScrollPane3 = new javax.swing.JScrollPane();
		//jScrollPane4 = new javax.swing.JScrollPane();

		jScrollPaneTree = new javax.swing.JScrollPane();



		DefaultMutableTreeNode firstNodeHierarchy = new DefaultMutableTreeNode(TreeElements.Simulation.toString());
		
		DefaultMutableTreeNode secondNodeHierarchyRobot =  new DefaultMutableTreeNode(TreeElements.Robot.toString());
		firstNodeHierarchy.add(secondNodeHierarchyRobot);
		
		
		
		
		DefaultMutableTreeNode secondNodeHierarchyWorldDescription = new DefaultMutableTreeNode(TreeElements.World_Description.toString().replace("_", " "));
		firstNodeHierarchy.add(secondNodeHierarchyWorldDescription);
		
		DefaultMutableTreeNode thirdNodeHierarchyRobot =  new DefaultMutableTreeNode(TreeElements.Plane_Size.toString().replace("_", ""));
		secondNodeHierarchyWorldDescription.add(thirdNodeHierarchyRobot);

		DefaultMutableTreeNode secondNodeHierarchyPhysicsParameters = new DefaultMutableTreeNode(TreeElements.Physics_Parameters.toString().replace("_", ""));
		firstNodeHierarchy.add(secondNodeHierarchyPhysicsParameters);
		
		


		jTree1 = new javax.swing.JTree(firstNodeHierarchy);
		
		ImageIcon closedIcon = new ImageIcon(DIRECTORY_ICONS + EXPANSION_CLOSED);
		ImageIcon openIcon = new ImageIcon(DIRECTORY_ICONS + EXPANSION_OPENED);
		ImageIcon leafIcon = new ImageIcon(DIRECTORY_ICONS + FINAL_LEAF);
		
		if (openIcon != null) {
		    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();		    
		    renderer.setClosedIcon(closedIcon);
		    renderer.setOpenIcon(openIcon);
		    renderer.setLeafIcon(leafIcon);
		    jTree1.setCellRenderer(renderer);
		}

		jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				jTree1.getLastSelectedPathComponent();

				if (node == null)
					//Nothing is selected.	
					return;

				Object nodeInfo = node.getUserObject();
				if (node.isLeaf()) {
					System.out.println(node.getPath()[node.getPath().length-1].toString());
					
					
				/*	BookInfo book = (BookInfo)nodeInfo;
					displayURL(book.bookURL);*/
				} /*else {
					displayURL(helpURL); 
				}*/

			}
		});


		jScrollPaneTree.setViewportView(jTree1);
		jScrollPaneTree.setPreferredSize(new Dimension(300,300));
		super.jComponent.add(jScrollPaneTree);
		/*final ArrayList<AbstractCellEditor> editors = new ArrayList<AbstractCellEditor>();

		editors.add(new SpinnerEditor());

		JComboBox comboBox1 = new JComboBox( TextureDescriptions.values() );        
		DefaultCellEditor dce1 = new DefaultCellEditor( comboBox1 );
		editors.add(dce1);

		comboBox2 = new JComboBox( WorldDescription.CameraPosition.values() );        
		DefaultCellEditor dce2 = new DefaultCellEditor( comboBox2 );
		editors.add(dce2);

		editors.add(new CheckBoxEditor());//the world is flat
		editors.add(new CheckBoxEditor());//has background scenery
		editors.add(new CheckBoxEditor());//has heavy obstacles

		//JComboBox comboBox3 = new JComboBox( new String[]{"yes","no"}); //the world is flat       
		//DefaultCellEditor dce3 = new DefaultCellEditor( comboBox3 );
		//editors.add(dce3);



		//jTableSimulationDescription =  new  javax.swing.JTable();


		   Object[][] defaultData = {                
				   {"Plane Size", new Integer(0)},
				   {"The world is flat", new Boolean(false)},
		   };


		String[] columnNames = {"World Description","Values"};

		final String[] worldDescriptionParameters = {"Plane Size","Plane Texture","Camera Position",
				                                      "The world is flat","Has background scenery",
				                                      "Has heavy obstacles","Is frame grabbing active" 
				                                     };


		DefaultTableModel model = new DefaultTableModel(columnNames,worldDescriptionParameters.length){
			public Object getValueAt(int row, int col)
            {
                if (col==0){
                   return worldDescriptionParameters[row];
                }
                return super.getValueAt(row,col);
            }
            public boolean isCellEditable(int row, int col)
            {
                if (col==0)
                    return false;	                
                return true;
            }
		};       
		jTableWorldDescription = new JTable(model){               
			//  Determine editor to be used by row             
			public TableCellEditor getCellEditor(int row, int column){                       
				int modelColumn = convertColumnIndexToModel( column );                       
				//if (modelColumn < editors.size()+1)
				//if(modelColumn <= 1)
					return (TableCellEditor)editors.get(row);                     
				else                               
					return super.getCellEditor(row, column);                
				}       
			};
			jTableWorldDescription.setVisible(false);

			jTableWorldDescription.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseReleased(java.awt.event.MouseEvent evt) {
	            	//jTable1MouseReleased(evt);
	            }
	        });


			jScrollPane3.setViewportView(jTableWorldDescription);
			jScrollPane3.setPreferredSize(new Dimension(300,155));
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			super.jComponent.add(jScrollPane3,gridBagConstraints);





			final ArrayList<AbstractCellEditor> editors1 = new ArrayList<AbstractCellEditor>();
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());
			editors1.add(new SpinnerEditor());


			String[] columnNames1 = {"Physics Parameters","Values"};

			final String[] worldDescriptionParameters1 = {"Damping linear velocity","Damping angular velocity",
					                                      "Physics Simulation Step Size",
					                                      "Realistic collision","Gravity",
					                                      "Plane material","Maintain rotational joint positiosn", 
					                                      		"Big obstacles","Constraint force fix", "Resolution factor",
					                                      		"Use mouse event queue","Synchronize with controllers", 
					                                      		"Physics simulation controller step factor"};


			DefaultTableModel model1 = new DefaultTableModel(columnNames1,worldDescriptionParameters1.length){
				public Object getValueAt(int row, int col)
	            {
	                if (col==0){
	                   return worldDescriptionParameters1[row];
	                }
	                return super.getValueAt(row,col);
	            }
	            public boolean isCellEditable(int row, int col)
	            {
	                if (col==0)
	                    return false;	                
	                return true;
	            }
			};       
			jTablePhysicsParameters = new JTable(model1){               
				//  Determine editor to be used by row             
				public TableCellEditor getCellEditor(int row, int column){                       
					int modelColumn = convertColumnIndexToModel( column );                       
					//if (modelColumn < editors.size()+1)
					if(modelColumn <= 3)
						return (TableCellEditor)editors1.get(row);                     
					else                               
						return super.getCellEditor(row, column);                
					}       
				};

				jTablePhysicsParameters.setVisible(false);

				jTablePhysicsParameters.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseReleased(java.awt.event.MouseEvent evt) {
		            	SimulationTabController.jTablePhysicsParametersMouseReleasedActionPerformed(evt);
		            }
		        });


				jScrollPane4.setViewportView(jTablePhysicsParameters);
				jScrollPane4.setPreferredSize(new Dimension(390,240));
				gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 1;
				super.jComponent.add(jScrollPane4,gridBagConstraints);		*/	

		//jTableSimulationDescription.setEnabled(false);

		/*jTableSimulationTab = new JTableSimulationTab();        

		String[] namesColums = {"World Description","Values","Physics parameters","Values"};
		final String[] worldDescriptionParameters = {"Plane Size","Some"};
		DefaultTableModel model =new DefaultTableModel(namesColums,2){




			 public Object getValueAt(int row, int col)
	            {
	                if (col==0){
	                   return worldDescriptionParameters[row];
	                }
	                return super.getValueAt(row,col);
	            }
	            public boolean isCellEditable(int row, int col)
	            {
	                if (col==0||col==2)
	                    return false;	                
	                return true;
	            }

		};
		jTableSimulationTab.setModel(model);
		jTableSimulationTab.setRowSelectionAllowed(false);
		jTableSimulationTab.setColumnSelectionAllowed(false);
		RowEditorModel rowEditorModel = new RowEditorModel();
		jTableSimulationTab.setRowEditorModel(rowEditorModel);


		SpinnerEditor spinner = new SpinnerEditor();
		rowEditorModel.addEditorForRow(0, spinner);*/


		//spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 5));
		//AbstractCellEditor ed = new AbstractCellEditor(spinner);

		/*	jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {//rows

				},
				new String [] {
						"World Description", "Value", "PhysicsParameters", "Value"
				}


		)*//*{
			@SuppressWarnings("unchecked")
			Class[] types = new Class [] {
				java.lang.String.class, java.lang.Object.class,java.lang.String.class,java.lang.Object.class
			};
			boolean[] canEdit = new boolean [] {
					false, true, false, true
			};

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}

			  public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
		});*/





	}





	/*	public static javax.swing.JTable getJTablePhysicsParameters() {
		return jTablePhysicsParameters;
	}

	public static void setJTablesVisible(boolean visible) {
		jTableWorldDescription.setVisible(visible);
		jTablePhysicsParameters.setVisible(visible);
	}

	public static JComboBox getComboBox2() {
		return comboBox2;
	}

	public static javax.swing.JTable getJTableWorldDescription() {
		return jTableWorldDescription;
	}*/


	private static javax.swing.JTree jTree1;
	private javax.swing.JScrollPane jScrollPaneTree;

	/*private javax.swing.JLabel jLabel1;
	private javax.swing.JSpinner jSpinner1;

	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;


	private static javax.swing.JTable jTableWorldDescription ;
	private static javax.swing.JTable jTablePhysicsParameters ;

	private static JCheckBox jCheckBox3;
	private static JComboBox comboBox2;

	public static JCheckBox getComboBox3() {
		return jCheckBox3;
	}

   public static void setTabVisible(){

   }*/

}
