/**
 * Unified Simulator for Self-Reconfigurable Robots (USSR)
 * (C) University of Southern Denmark 2008
 * This software is distributed under the BSD open-source license.
 * For licensing see the file LICENCE.txt included in the root of the USSR distribution.
 */
package atron.samples.futures;

import atron.delegate.ATRONDelegateAPI;
import atron.futures.ATRONFuturesCenterMotor;
import atron.futures.ICommand;
import ussr.model.Module;
import ussr.samples.atron.ATRONController;

/**
 * A controller for a two-wheeler ATRON robot
 * 
 * @author Modular Robots @ MMMI
 *
 */
public class ATRONSimpleFuturesController extends ATRONController {
	
	int i = 0;

	/**
     * @see ussr.model.ControllerImpl#activate()
     */
    public void activate() {
        yield();
        String name = module.getProperty("name");
        if(name=="RearRightWheel"){
        	System.out.println("Simple Futures demo");
        	ATRONDelegateAPI atronDelegateAPI = new ATRONDelegateAPI(module);
        	System.out.println("Start pos. =" + atronDelegateAPI.getAngularPositionDegrees());
            ATRONFuturesCenterMotor f = atronDelegateAPI.rotateToDegreeInDegreesFutures(90);

            // onCompletion test
            f.onCompletion(new ICommand(){
			public void execute(){
				System.out.println("onCompletion() -> execute() -> show this text!");
			}
			});
            
            // waitForCompletion test
//            f.waitForComplition();
            
            // Demo count
            while(i < 300){
            	System.out.println("Demo count = " + i++);
            	yield();
            }

        }
        
            
        while(true){
        	yield();
        }
        
    }
}
