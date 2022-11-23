package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

public class DefenderEvent {
    public DefenderEventSource source;
    public Type type;
    protected DefenderActionVariable payload;

    public enum Type {
	   UP, DOWN, HELD, RELEASED, CHANGED,
	   POSITIVE, NEGATIVE, ZERO, NOTZERO,
	   VALUE
    }

    DefenderEvent(DefenderEventSource s, Type t) {
	   source = s;
	   type = t;
    }

    DefenderEvent(DefenderEventSource s, Type t, DefenderActionVariable p) {
	   source = s;
	   type = t;
	   payload = p;
    }

    public boolean matches(DefenderEvent other) {
	   boolean doTheyMatch =( this.source == other.source) && (this.type == other.type);
	   if (hasPayload()) {
		  doTheyMatch = doTheyMatch && payload.matches(other.getPayload());
	   }
	   return doTheyMatch;
    }

    public boolean hasPayload() { return payload != null; }
    public DefenderActionVariable getPayload() {
	   return payload;
    }

    public void setPayload(DefenderActionVariable payload) {
	   this.payload = payload;
    }

    public DefenderEvent copyWithPayload(DefenderActionVariable payload) {
	   DefenderEvent theCopy = new DefenderEvent(source, type, payload);
	   return theCopy;
    }

    public DefenderEvent copyWithPayload(int v) {
	   return copyWithPayload(new DefenderActionVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(double v) {
	   return copyWithPayload(new DefenderActionVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(boolean v) {
	   return copyWithPayload(new DefenderActionVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(String v) {
	   return copyWithPayload(new DefenderActionVariable("payload", v));
    }




}
