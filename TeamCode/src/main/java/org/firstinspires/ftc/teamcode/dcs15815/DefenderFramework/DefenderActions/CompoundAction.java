package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import java.util.ArrayList;
import java.util.Hashtable;

public class CompoundAction extends DefenderAction {
    protected ArrayList<DefenderAction> actions;

    CompoundAction() {
	   super();
	   actions = new ArrayList<>();
    }

    public void addAction(DefenderAction s) {
	   actions.add(s);
	   s.setActionSequence(actionSequence);
    }

    @Override
    public void beforeStart() {
	   for (DefenderAction s : actions) {
		  s.beforeStart();
	   }
    }

    @Override
    public void beforeStop() {
	   for (DefenderAction s : actions) {
		  s.beforeStop();
	   }

    }




    @Override
    public void run() {
	   isFinished = true;
    }
}
