package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import java.util.ArrayList;
import java.util.Hashtable;

public class AndAction extends CompoundAction {

    AndAction() {
	   super();

    }

    public AndAction and(DefenderAction s) {
	   addAction(s);
	   return this;
    }

    @Override
    public void run() {
	   // not sure what goes in here! Do we KEEP creating a thread for each action
	   // or is it created and run once? I think it needs to be the former, for example,
	   // to make sure that event actions get updated lists of events...
	   isFinished = true;

	   // this could be a problem. Could we have collision where one copy of the thread is still running
	   // when another starts?
	   for (DefenderAction s : actions) {
		  if (!s.hasActionSequence()) {
			 s.setActionSequence(actionSequence);
		  }
		  isFinished = isFinished && s.isFinished();
		  if (!isFinished && !s.isFinished()) {
			 Thread t = new Thread(() -> s.run());
			 t.start();
		  }

	   }

//        isFinished = true;
    }
}
