package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

public class DefenderAction {
    protected String label;
    public DefenderAction nextAction, previousAction;
    protected boolean isFinished = false;
    public DefenderActionSequence actionSequence;

    public DefenderAction() {
    }

    public DefenderAction(DefenderActionSequence sm) {
	   setActionSequence(sm);
    }

    public DefenderActionSequence getActionSequence() {
	   return actionSequence;
    }

    public void setActionSequence(DefenderActionSequence actionSequence) {
	   this.actionSequence = actionSequence;
    }

    public boolean hasActionSequence() {
	   return actionSequence != null;
    }
    
    public void beforeStart() { }

    public void beforeStop() {  }

    public void run() { }

    public boolean isFinished() {
	   return isFinished;
    }
    public void setIsFinished(boolean b) {
	   isFinished = b;
    }

    public boolean hasNextAction() {
	   return nextAction != null;
    }

    public void setNextAction(DefenderAction ns) {
	   nextAction = ns;
	   ns.previousAction = this;
    }


    public DefenderAction then(DefenderAction ns) {
	   nextAction = ns;
	   ns.previousAction = this;
	   return this;
    }

    public DefenderAction thenAfterPause(long wait, DefenderAction ns) {
	   WaitAction waitAction = new WaitAction(wait);
	   then(waitAction);
	   waitAction.then(ns);
	   return this;
    }

    public AndAction and(DefenderAction s) {
	   AndAction newAndAction = new AndAction();
	   newAndAction.addAction(this);
	   newAndAction.addAction(s);
	   return newAndAction;
    }

    public OrAction or(DefenderAction s) {
	   OrAction newOrAction = new OrAction();
	   newOrAction.addAction(this);
	   newOrAction.addAction(s);
	   return newOrAction;
    }

    public void follows(DefenderAction... actions) {
	   for (DefenderAction s : actions) {
		  s.then(this);
	   }
    }

    public void followsAfterPause(long wait, DefenderAction... actions) {
	   for (DefenderAction s : actions) {
		  s.thenAfterPause(wait, this);
	   }
    }

}
