package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

import java.util.ArrayList;
import java.util.Hashtable;

public class EventAction extends DefenderAction {
    protected Hashtable<DefenderEvent, Runnable> eventResponses;

    EventAction() {
	   super();
	   eventResponses = new Hashtable<>();
    }

    public void when(DefenderEventSource s, DefenderEvent.Type type, Runnable r) {
	   eventResponses.put(new DefenderEvent(s, type), r);
    }

    public void when(DefenderEvent e, Runnable r) {
	   eventResponses.put(e, r);
    }


    public void when(DefenderEventSource s, DefenderEvent.Type type, Runnable r, long debounceTimeout) {
	   DefenderDebouncer debouncer = new DefenderDebouncer(debounceTimeout, r);
	   eventResponses.put(new DefenderEvent(s, type), debouncer);
    }

    public void when(DefenderEvent e, Runnable r, long debounceTimeout) {
	   DefenderDebouncer debouncer = new DefenderDebouncer(debounceTimeout, r);
	   eventResponses.put(e, debouncer);
    }

    public void andThenWhen(DefenderEvent e, DefenderAction ns) {
	   nextAction = ns;
	   when(e, () -> {
		  nextAction = ns;
		  isFinished = true;
	   });
    }


    @Override
    public void run() {
	   ArrayList<DefenderEvent> currentEvents = actionSequence.getCurrentEvents();
	   eventResponses.forEach((eventPattern, response) -> {
		  for (DefenderEvent event : currentEvents)
			 if (eventPattern.matches(event)) {
				if (event.hasPayload()) {
				    actionSequence.setVariable(event.getPayload().key, event.getPayload());
				}
				response.run();
			 }

	   });
    }
}
