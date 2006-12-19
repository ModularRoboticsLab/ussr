/*Copyright*/
package com.jmetest.physics;

import java.util.logging.Level;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingSphere;
import com.jme.input.InputHandler;
import com.jme.input.KeyInput;
import com.jme.input.MouseInput;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.TriMesh;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Sphere;
import com.jme.util.LoggingSystem;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.StaticPhysicsNode;
import com.jmex.physics.util.PhysicsPicker;
import com.jmex.physics.util.SimplePhysicsGame;

/**
 * @author Irrisor
 */
public class TestGenerateGeometry extends SimplePhysicsGame {

    protected void simpleInitGame() {
//        rootNode.getLocalRotation().fromAngleNormalAxis( -0.1f, new Vector3f( 0, 0, 1 ) );

        final StaticPhysicsNode staticNode = getPhysicsSpace().createStaticNode();

        TriMesh trimesh = new Box( "trimesh", new Vector3f(), 15, 0.5f, 15 );
        staticNode.attachChild( trimesh );
        trimesh.setModelBound( new BoundingBox() );
        trimesh.updateModelBound();

        staticNode.getLocalTranslation().set( 0, -5, 0 );

        rootNode.attachChild( staticNode );
        staticNode.generatePhysicsGeometry();

        final DynamicPhysicsNode dynamicNode = getPhysicsSpace().createDynamicNode();

        Sphere meshSphere = new Sphere( "meshsphere", 9, 9, 2 );
        meshSphere.getLocalTranslation().set( -1, 0, 0 );
        meshSphere.setModelBound( new BoundingSphere() );
        meshSphere.updateModelBound();
        dynamicNode.attachChild( meshSphere );

        Node sphere2Node = new Node( "2" );
        sphere2Node.getLocalTranslation().set( 0.25f, 0, 0 );
        sphere2Node.getLocalRotation().fromAngleNormalAxis( -FastMath.PI / 2, new Vector3f( 0, 1, 0 ) );
        Sphere meshSphere2 = new Sphere( "meshsphere2", 9, 9, 1 );
        meshSphere2.getLocalTranslation().set( 0.5f, 0, 0 );
        meshSphere2.setModelBound( new BoundingSphere() );
        meshSphere2.updateModelBound();
        sphere2Node.attachChild( meshSphere2 );
        dynamicNode.attachChild( sphere2Node );

        dynamicNode.generatePhysicsGeometry();

        rootNode.attachChild( dynamicNode );
        dynamicNode.computeMass();

        final DynamicPhysicsNode dynamicNode3 = getPhysicsSpace().createDynamicNode();

        Box meshBox3 = new Box( "meshbox3", new Vector3f(), 2, 2, 2 );
        meshBox3.setModelBound( new BoundingBox() );
        meshBox3.updateModelBound();
        dynamicNode3.attachChild( meshBox3 );
        dynamicNode3.generatePhysicsGeometry();

        rootNode.attachChild( dynamicNode3 );
        dynamicNode3.computeMass();

        showPhysics = true;

        final InputAction resetAction = new InputAction() {
            public void performAction( InputActionEvent evt ) {
                dynamicNode.getLocalTranslation().set( 0, 3, 0 );
                dynamicNode.getLocalRotation().set( 0, 0, 0, 1 );
                dynamicNode.clearDynamics();

                dynamicNode3.getLocalTranslation().set( 0, -2.5f, 0 );
                dynamicNode3.getLocalRotation().set( 0, 0, 0, 1 );
                dynamicNode3.clearDynamics();
            }
        };
        input.addAction( resetAction, InputHandler.DEVICE_KEYBOARD, KeyInput.KEY_R, InputHandler.AXIS_NONE, false );
        resetAction.performAction( null );

        InputAction removeAction = new InputAction() {
            public void performAction( InputActionEvent evt ) {
                staticNode.setActive( !staticNode.isActive() );
            }
        };
        input.addAction( removeAction, InputHandler.DEVICE_KEYBOARD, KeyInput.KEY_DELETE, InputHandler.AXIS_NONE, false );

        MouseInput.get().setCursorVisible( true );
        new PhysicsPicker( input, rootNode, getPhysicsSpace() );
    }

    @Override
    protected void simpleUpdate() {
        cameraInputHandler.setEnabled( MouseInput.get().isButtonDown( 1 ) );
    }

    public static void main( String[] args ) {
        System.out.println("java.library.path="+System.getProperty("java.library.path"));
        LoggingSystem.getLogger().setLevel( Level.WARNING );
        new TestGenerateGeometry().start();
    }
}

/*
 * $log$
 */
