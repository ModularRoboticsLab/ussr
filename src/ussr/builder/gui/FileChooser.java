package ussr.builder.gui;

import ussr.builder.saveLoadXML.SaveLoadXMLBuilderTemplate;
import ussr.builder.saveLoadXML.SaveLoadXMLFileTemplate;
import ussr.physics.jme.JMESimulation;

/**
 * The main responsibility of this class is to display file chooser in two types(forms):
 * 1) open dialog and 2) save dialog.
 * @author  Konstantinas
 */
public class FileChooser extends javax.swing.JFrame {
    
 /**
 * The physical simulation
 */
 private JMESimulation simulation;
 
 private SaveLoadXMLFileTemplate saveLoadXML ;

 /**
 * The type of FileChooser. For example true if it is open dialog and false if it is save dialog 
 */
private boolean type;
    
    
    private final GuiUtilities guiUtil = new GuiUtilities();

/** Creates new form FileChooser */
public FileChooser(JMESimulation simulation, boolean type) {
     this.simulation = simulation;
     this.type = type;
      initComponents();        
      changeOpenSaveDialog(type);
     guiUtil.changeToSetLookAndFeel(this);
     this.saveLoadXML =  new SaveLoadXMLBuilderTemplate(this.simulation); 
}
  
/** This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

    jFileChooser1 = new javax.swing.JFileChooser();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Choose the file");
    getContentPane().setLayout(new java.awt.FlowLayout());

    jFileChooser1.setDialogTitle("Choose the file to save or open");
    jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        
				jFileChooser1ActionPerformed(evt);
			
        }
    });
    getContentPane().add(jFileChooser1);
    jFileChooser1.setAcceptAllFileFilterUsed(false);
    jFileChooser1.setFileFilter(new FileFilter (".xml"));

    pack();
}// </editor-fold>
  
/**
 * Changes the type of dialog window to Open or Save
    * @param type, true if Open  and false if Save dialog
 */
private void changeOpenSaveDialog(boolean type){
	if (type){
		jFileChooser1.setDialogType(javax.swing.JFileChooser.OPEN_DIALOG);
                    jFileChooser1.setDialogTitle("Open file");
	}else {
		jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
                    jFileChooser1.setDialogTitle("Save as");
	}
}


private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    String command = evt.getActionCommand();//Selected button command
	if(command.equalsIgnoreCase("ApproveSelection") && this.type == false){//Save dialog
		System.out.println("Save pressed"); //For debugging        
		String fileDirectoryName = jFileChooser1.getSelectedFile().toString();
		System.out.println("fileDirectoryName:"+fileDirectoryName); //For debugging     
		//SaveLoad saveLoad = new SaveLoad(simulation);
		//saveLoad.saveXMLfile(fileDiretoryName);
		saveLoadXML.saveXMLfile(fileDirectoryName);	
		
		this.dispose();
	}else if(command.equalsIgnoreCase("ApproveSelection") && this.type == true ){//Open dialog
		System.out.println("Open pressed");//For debugging        
		String fileDirectoryName = jFileChooser1.getSelectedFile().toString();
		System.out.println("fileDiretoryName:"+fileDirectoryName); //For debugging     
        //SaveLoad saveLoad = new SaveLoad(simulation);
		//saveLoad.loadXMLfile(fileDiretoryName);
		saveLoadXML.loadXMLfile(fileDirectoryName);	
		this.dispose();       
	}else if (command.equalsIgnoreCase("CancelSelection")){
		System.out.println("Cancel pressed");//For debugging
		this.dispose();
	}     
    
}                                             

public void activate(){
	java.awt.EventQueue.invokeLater(new Runnable(){
		public void run() {           
            FileChooser fileChooser = new FileChooser(simulation, type);
			fileChooser.setSize(580, 450);
			fileChooser.setVisible(true);
        }
	});    	
}

// Variables declaration - do not modify
private javax.swing.JFileChooser jFileChooser1;
// End of variables declaration

}
