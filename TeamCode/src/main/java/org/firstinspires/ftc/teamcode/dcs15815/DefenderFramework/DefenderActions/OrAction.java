package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrAction extends CompoundAction {

    OrAction() {
	   super();

    }

    public OrAction or(DefenderAction s) {
	   addAction(s);
	   return this;
    }

    public void beforeAction() {
	   isFinished = false;
    }


    @Override
    public void run() {

	   // this could be a problem. Could we have collision where one copy of the thread is still running
	   // when another starts?
	   for (DefenderAction s : actions) {
		  if (!s.hasActionSequence()) {
			 s.setActionSequence(actionSequence);
		  }
//            isFinished = isFinished || s.isFinished();
		  if (isFinished) {
			 break;
		  }
		  if (!s.isFinished()) {
			 Thread t = new Thread(() -> {
				s.run();
				if (s.isFinished() && s.hasNextAction()) {
				    setNextAction(s.nextAction);
				}
				isFinished = isFinished || s.isFinished();
			 });
			 t.start();
		  }
	   }

//        isFinished = true;
    }
}
