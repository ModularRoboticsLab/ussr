package ussr.physics.jme.robots;

import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.material.Material;

import ussr.model.Module;
import ussr.physics.ModuleFactory;
import ussr.physics.PhysicsLogger;
import ussr.physics.PhysicsSimulation;
import ussr.physics.jme.JMEModuleComponent;
import ussr.physics.jme.JMESimulation;
import ussr.robotbuildingblocks.GeometryDescription;
import ussr.robotbuildingblocks.Robot;

public class JMEDefaultFactory implements ModuleFactory {

    private JMESimulation simulation;
    private String prefix;
    
    public JMEDefaultFactory(String prefix) {
        this.prefix = prefix;
    }
    
    public void createModule(int module_id, Module module, Robot robot, String module_name) {
        if(robot.getDescription().getModuleGeometry().size()==1) {
            // Create central module node
            DynamicPhysicsNode moduleNode = simulation.getPhysicsSpace().createDynamicNode();            
            int j=0;
            for(GeometryDescription geometry: robot.getDescription().getModuleGeometry()) {
                JMEModuleComponent physicsModule = new JMEModuleComponent(simulation,robot,geometry,"module#"+Integer.toString(module_id)+"."+(j++),module,moduleNode);
                module.addComponent(physicsModule);
                simulation.getModuleComponents().add(physicsModule);
            }
            moduleNode.setMaterial(Material.GRANITE);
            moduleNode.computeMass();
        } else {
            throw new RuntimeException("Module type can not be constructed");
        }
    }

    public String getModulePrefix() {
        return prefix;
    }

    public void setSimulation(PhysicsSimulation simulation) {
        this.simulation = (JMESimulation)simulation;
    }

}