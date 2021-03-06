/* DynaRole Java code generated for carsnakeSimple */
/* DynaRole Java statemachine generated for carsnakeSimple */
package robustReversible.gen;

import robustReversible.*;

public class snakeGen_seq extends StateMachine {

  private static final int CENTER_ERROR_TOLERANCE = 5;
  private int token = 255;
  private int myID=-1;
  private boolean isDone = false; 
  private static final boolean TRUE = true;
  private static final boolean FALSE = false;
  
  private boolean doneRotatingTo(int goal) { 
	
	if(api.isRotating()) {
		return false; 
	}

	return true;
  }

  private void rotateDirTo(int to, boolean direction) {
	api.rotateDirToInDegrees(to, direction);
}


  private void connect(int connector) {
	api.connect(connector);
  }

  private void disconnect(int connector) {
	api.disconnect(connector);
  }

  private boolean notDoneConnecting(int connector) {
	return !api.isConnected(connector);
} 
 
  private boolean notDoneDisconnecting(int connector) {
	return api.isConnected(connector);
  }

  protected void stateMachine() { 
    api.yield();
    if(token == 255) { /* try to see if there's a new state for me */
      token = stateManager.getMyNewState();
	  if(token!=255) {
		System.out.println(myID+": Now performing state "+token);
	  }
    }
	
	switch(token) {
	case 0: token = 1; /* fall-through */
    case 1: /* Module M__3 */
      disconnect(0);
      token = 2;
      break;
    case 2:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(0)) break;
      token = 3; /* fall-through */
    case 3: /* Module M__3 */
      disconnect(6);
      token = 4;
      break;
    case 4:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(6)) break;
      stateManager.sendState(5,0);
      token = 255;
      break;
    case 5: /* Module M__0 */
      rotateDirTo(216,TRUE);
      token = 6;
      break;
    case 6:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      stateManager.sendState(7,6);
      token = 255;
      break;
    case 7: /* Module M__6 */
      rotateDirTo(216,TRUE);
      token = 8;
      break;
    case 8:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      token = 9; /* fall-through */
    case 9: /* Module M__6 */
      rotateDirTo(0,FALSE);
      token = 10;
      break;
    case 10:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(11,0);
      token = 255;
      break;
    case 11: /* Module M__0 */
      rotateDirTo(0,FALSE);
      token = 12;
      break;
    case 12:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(13,3);
      token = 255;
      break;
    case 13: /* Module M__3 */
      connect(6);
      token = 14;
      break;
    case 14:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(6)) break;
      token = 15; /* fall-through */
    case 15: /* Module M__3 */
      connect(0);
      token = 16;
      break;
    case 16:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(0)) break;
      token = 17; /* fall-through */
    case 17: /* Module M__3 */
      disconnect(0);
      token = 18;
      break;
    case 18:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(0)) break;
      token = 19; /* fall-through */
    case 19: /* Module M__3 */
      disconnect(6);
      token = 20;
      break;
    case 20:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(6)) break;
      stateManager.sendState(21,0);
      token = 255;
      break;
    case 21: /* Module M__0 */
      rotateDirTo(216,TRUE);
      token = 22;
      break;
    case 22:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      stateManager.sendState(23,6);
      token = 255;
      break;
    case 23: /* Module M__6 */
      rotateDirTo(216,TRUE);
      token = 24;
      break;
    case 24:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      token = 25; /* fall-through */
    case 25: /* Module M__6 */
      rotateDirTo(0,FALSE);
      token = 26;
      break;
    case 26:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(27,0);
      token = 255;
      break;
    case 27: /* Module M__0 */
      rotateDirTo(0,FALSE);
      token = 28;
      break;
    case 28:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(29,3);
      token = 255;
      break;
    case 29: /* Module M__3 */
      connect(6);
      token = 30;
      break;
    case 30:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(6)) break;
      token = 31; /* fall-through */
    case 31: /* Module M__3 */
      connect(0);
      token = 32;
      break;
    case 32:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(0)) break;
      token = 33; /* fall-through */
    case 33: /* Module M__3 */
      disconnect(0);
      token = 34;
      break;
    case 34:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(0)) break;
      token = 35; /* fall-through */
    case 35: /* Module M__3 */
      disconnect(6);
      token = 36;
      break;
    case 36:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneDisconnecting(6)) break;
      stateManager.sendState(37,0);
      token = 255;
      break;
    case 37: /* Module M__0 */
      rotateDirTo(216,TRUE);
      token = 38;
      break;
    case 38:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      stateManager.sendState(39,6);
      token = 255;
      break;
    case 39: /* Module M__6 */
      rotateDirTo(216,TRUE);
      token = 40;
      break;
    case 40:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(216)) break;
      token = 41; /* fall-through */
    case 41: /* Module M__6 */
      rotateDirTo(0,FALSE);
      token = 42;
      break;
    case 42:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(43,0);
      token = 255;
      break;
    case 43: /* Module M__0 */
      rotateDirTo(0,FALSE);
      token = 44;
      break;
    case 44:
      if(stateManager.pendingStatesPresent()) break;
      if(!doneRotatingTo(0)) break;
      stateManager.sendState(45,3);
      token = 255;
      break;
    case 45: /* Module M__3 */
      connect(6);
      token = 46;
      break;
    case 46:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(6)) break;
      token = 47; /* fall-through */
    case 47: /* Module M__3 */
      connect(0);
      token = 48;
      break;
    case 48:
      if(stateManager.pendingStatesPresent()) break;
      if(notDoneConnecting(0)) break;

	
	stateManager.sendState(254,-1);
	token=255;
	break;
	
	case 255:
		if(stateManager.getGlobalState()==254) {
			if(!isDone) {
				System.out.println(myID+": i am done...");
				api.reportResult(true);
			}
			isDone = true;
		}
		break;
	}
}

  public void init(int self_id) {
    int address;
    if((self_id==0)) address = 0;
    else if((self_id==1)) address = 1;
    else if((self_id==2)) address = 2;
    else if((self_id==3)) address = 3;
    else if((self_id==4)) address = 4;
    else if((self_id==5)) address = 5;
    else if((self_id==6)) address = 6;

    else address = 127;
	myID = address;
	token = 255;
	
    api.setLeds(myID);
	reset_state();
	stateManager.init(myID,3);
	isDone = false;
}

  public void reset_sequence() {
    stateManager.reset_sequence();
}

  public void reset_state() {
    stateManager.reset_state();
}

  public boolean checkPendingStateResponsibility(int address, int pendingState) {
    return false;
  }

  int getLastState(int address) {
    if(address==0) return 43;
    if(address==3) return 45;
    if(address==6) return 39;
    return 255;
  }
  
  int getLastStateLowerBound(int address) {
    if(address==0) return 29;
    if(address==3) return 39;
    if(address==6) return 29;
    return 255;
  }


}