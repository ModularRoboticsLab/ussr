/**
 * 
 */
package ussr.samples.atron.natives;

import ussr.model.Controller;
import ussr.robotbuildingblocks.Robot;
import ussr.samples.atron.ATRON;
import ussr.samples.atron.ATRONCarSimulation;

/**
 * @author ups
 *
 * TODO Write a nice and user-friendly comment here
 * 
 */
public class ATRONNativeCarSimulation extends ATRONCarSimulation {
    public static void main(String argv[]) {
        new ATRONNativeCarSimulation().main();
    }
    
    @Override
    protected Robot getRobot() {
        return new ATRON() {
            public Controller createController() {
                return new ATRONNativeController("carController");
            }
        };
    }
}