package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import java.util.ArrayList;
import java.util.Hashtable;

public class DefenderActionSequence {

    private DefenderAction currentAction;
    public DefenderBot bot;
    private boolean isFinished;
    private ArrayList<DefenderEventSource> eventSources;
    protected ArrayList<DefenderEvent> currentEvents;
    protected Hashtable<String,DefenderActionVariable> variables = new Hashtable<>();


    DefenderActionSequence(DefenderBot b) {
	   eventSources = new ArrayList<>();
	   bot = b;
	   isFinished = false;
	   currentEvents = new ArrayList<>();
    }

    public void addEventSource(DefenderEventSource s) {
	   eventSources.add(s);
    }

    public boolean isFinished() {
	   return isFinished;
    }

    public ArrayList<DefenderEvent> getCurrentEvents() {
	   return currentEvents;
    }


    public void setAction(DefenderAction s) {
	   if (currentAction != null) {
		  currentAction.beforeStop();
	   }
	   currentAction = s;
	   currentAction.beforeStart();
	   currentAction.setActionSequence(this);
    }

    public void setNextAction(DefenderAction s) {
	   currentAction.nextAction = s;
    }

    public void setVariable(String key, DefenderActionVariable v) {
	   variables.put(key, v);
    }

    public DefenderActionVariable getVariable(String key) {
	   return variables.get(key);
    }

    public DefenderAction getCurrentAction() {
	   return currentAction;
    }

    public void run() {
	   ArrayList<DefenderEvent> events = new ArrayList<>();

	   if (isFinished) {
		  return;
	   }
	   if (currentAction == null) {
		  throw new RuntimeException("ActionSequence has no current action. Did you forget to set a first action?");
	   }

	   for (DefenderEventSource s : eventSources) {
		  events.addAll(s.gatherEvents());
	   }
	   currentEvents.clear();
	   currentEvents.addAll(events);
	   currentAction.run();

	   if (currentAction.isFinished()) {
		  if (currentAction.hasNextAction()) {
			 setAction(currentAction.nextAction);
		  } else {
			 isFinished = true;
		  }
	   }
    }

}