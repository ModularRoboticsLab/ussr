package ussr.physics.jme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jme.bounding.BoundingSphere;
import com.jme.input.InputHandler;
import com.jme.input.action.InputActionEvent;
import com.jme.input.action.InputActionInterface;
import com.jme.input.util.SyntheticButton;
import com.jme.math.Vector3f;
import com.jme.scene.TriMesh;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.Joint;
import com.jmex.physics.contact.ContactInfo;

import ussr.description.GeometryDescription;
import ussr.model.Connector;
import ussr.physics.PhysicsConnector;
import ussr.physics.PhysicsLogger;

public class JMEStickyConnector implements JMEConnector {
    /**
     * The abstract connector represented by this jme entity
     */
    private Connector model;
    private DynamicPhysicsNode node;
    private JMESimulation world;
    private JMEConnector connectedConnector = null;
    private JMEStickyConnector lastProximityConnector = null;
    private JMEModule module;
    private float maxConnectDistance;

    public JMEStickyConnector(Vector3f position, DynamicPhysicsNode moduleNode, String baseName, List<GeometryDescription> geometry,JMESimulation world, JMEModule module, float maxConnectionDistance) {
        this.world = world;
        this.module = module;
        this.maxConnectDistance = maxConnectionDistance;
        // Create connector node
        DynamicPhysicsNode connector = world.getPhysicsSpace().createDynamicNode();
        module.getNodes().add(connector);
        // Create visual appearance
        assert geometry.size()==1; // Only tested with size 1 geometry
        for(GeometryDescription element: geometry) {
            TriMesh mesh = JMEDescriptionHelper.createShape(connector, baseName+position.toString(), element);
            world.connectorRegistry.put(mesh.getName(),this);
            mesh.getLocalTranslation().set( mesh.getLocalTranslation().add(position) );
            mesh.setModelBound( new BoundingSphere() );
            mesh.updateModelBound();
            connector.attachChild( mesh );
        }
        // Finalize
        connector.generatePhysicsGeometry();
        world.getRootNode().attachChild( connector );
        connector.computeMass();
        // Joint
        Joint connect = world.getPhysicsSpace().createJoint();
        connect.attach(moduleNode, connector);
        // Collision handler
        final SyntheticButton collisionEventHandler = connector.getCollisionEventHandler();
        world.getInput().addAction( new ModuleCollisionAction(),
                collisionEventHandler.getDeviceName(), collisionEventHandler.getIndex(),
                InputHandler.AXIS_NONE, false );
        this.node = connector;
    }
    
    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#getNode()
     */
    public DynamicPhysicsNode getNode() { return node; }
    
    public class ModuleCollisionAction implements InputActionInterface {

        public void performAction(InputActionEvent evt) {
            //if(!world.getConnectorsAreActive()) return;
            ContactInfo contactInfo = ( (ContactInfo) evt.getTriggerData() );
            String g1 = contactInfo.getGeometry1().getName();
            String g2 = contactInfo.getGeometry2().getName();
            if(world.connectorRegistry.containsKey(g1) && world.connectorRegistry.containsKey(g2)) {
                JMEStickyConnector c1 = (JMEStickyConnector)world.connectorRegistry.get(g1); 
                JMEStickyConnector c2 = (JMEStickyConnector)world.connectorRegistry.get(g2);
                c1.setProximityConnector(c2);
                c2.setProximityConnector(c1);
                c1.module.changeNotify();
                c2.module.changeNotify();
                //if(c1.isConnected()||c2.isConnected()) return;
                //c1.connectTo(c2);
            }
        }

    }

    private void setProximityConnector(JMEStickyConnector other) {
        this.lastProximityConnector=other;
    }

    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#otherConnectorAvailable()
     */
    public boolean otherConnectorAvailable() {
        return this.lastProximityConnector!=null;
    }
    
    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#getAvailableConnectors()
     */
    public synchronized List<Connector> getAvailableConnectors() {
        if(this.lastProximityConnector==null) return Collections.emptyList();
        if(node.getLocalTranslation().distance(lastProximityConnector.node.getLocalTranslation())>maxConnectDistance) {
            lastProximityConnector = null;
            return Collections.emptyList();
        }
        return Arrays.asList(new Connector[] { this.lastProximityConnector.model });
    }
    
    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#isConnected()
     */
    public synchronized boolean isConnected() {
        return connectedConnector!=null;
    }

    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#connectTo(ussr.physics.PhysicsConnector)
     */
    public synchronized void connectTo(PhysicsConnector otherConnector) {
        if(!(otherConnector instanceof JMEStickyConnector)) throw new Error("Mixed connector types not supported: "+otherConnector);
        JMEStickyConnector other = (JMEStickyConnector)otherConnector;
        if(this.isConnected()||other.isConnected()) { 
            PhysicsLogger.logNonCritical("Attempted connecting two connectors of which at least one was already connected.");
            return;
        }
        Joint join = world.getPhysicsSpace().createJoint();
        join.attach(this.getNode(),other.getNode());
        world.dynamicJoints.add(join);
        this.connectedConnector = other;
        other.connectedConnector = this;
    }

    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#reset()
     */
    public void reset() {
        connectedConnector = null;        
    }
    
    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#toString()
     */
    public String toString() {
        return "JMEConnector<"+node.hashCode()+">";
    }

    /* (non-Javadoc)
     * @see ussr.physics.jme.JMEConnector#setModel(ussr.model.Connector)
     */
    public void setModel(Connector connector) {
        this.model = connector;        
    }
}