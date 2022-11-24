package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import android.widget.Switch;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions.DefenderAction;

import java.util.Hashtable;

public class SwitchAction extends DefenderAction {
    private Hashtable<Integer, DefenderAction> ht;
    public int switchValue;
    public DefenderAction otherwiseAction = null;

    public SwitchAction(int v) {
	   switchValue = v;
	   ht = new Hashtable<Integer, DefenderAction>();
    }

    public SwitchAction when(int v, DefenderAction action) {
	   ht.put(v, action);
	   action.actionSequence = actionSequence;
	   return this;
    }

    public SwitchAction otherwise(DefenderAction action) {
	   otherwiseAction = action;
	   action.actionSequence = actionSequence;
	   return this;
    }

    public static SwitchAction on(int v) {
	   SwitchAction sa = new SwitchAction(v);
	   return sa;
    }

    @Override
    public void run() {
	   if (ht.containsKey(switchValue)) {
		  nextAction = ht.get(switchValue);
	   } else if (otherwiseAction != null) {
		  nextAction = otherwiseAction;
	   }
	   isFinished = true;
    }

}
