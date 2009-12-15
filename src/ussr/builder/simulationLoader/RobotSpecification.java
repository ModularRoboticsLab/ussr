package ussr.builder.simulationLoader;

import java.io.Serializable;

public class RobotSpecification implements Serializable {

  
	private String controllerLocation;
	



	private String morphologyLocation;
	



	private  int amountModules;
	
	
	
	public void setAmountModules(int amountModules) {
		this.amountModules = amountModules;
	}





	public RobotSpecification(){
		
	}
	
	
	
	
	
	public String getControllerLocation() {
		return controllerLocation;
	}

	public void setControllerLocation(String controllerLocation) {
		this.controllerLocation = controllerLocation;
	}
	
	
	public  int getAmountModules() {
		return amountModules;
	}


	public  String getMorphologyLocation() {
		return morphologyLocation;
	}
	
	public void setMorphologyLocation(String morphologyLocation) {
		this.morphologyLocation = morphologyLocation;
	}

	
	
	
}
