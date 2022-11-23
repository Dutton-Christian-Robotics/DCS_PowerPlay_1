package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

public class DefenderBotPosition {
    private double x;
    private double y;
    private double heading;
    private PositionType type;

    // Currently, PositionType doesn't do anything
    public enum PositionType {
	   ABSOLUTE, RELATIVE
    }

    DefenderBotPosition(double x, double y, double h, PositionType t) {
	   this.x = x;
	   this.y = y;
	   this.heading = h;
	   this.type = t;
    }

    DefenderBotPosition(double x, double y, double h) {
	   this.x = x;
	   this.y = y;
	   this.heading = h;
	   this.type = PositionType.ABSOLUTE;
    }

    DefenderBotPosition(double x, double y) {
	   this.x = x;
	   this.y = y;
	   this.heading = 0;
	   this.type = PositionType.ABSOLUTE;
    }

    public double getX() {
	   return x;
    }

    public void setX(double x) {
	   this.x = x;
    }

    public double getY() {
	   return y;
    }

    public void setY(double y) {
	   this.y = y;
    }

    public double getHeading() {
	   return heading;
    }

    public void setHeading(double heading) {
	   this.heading = heading;
    }

    public void shift(double dX, double dY) {
	   setX(x + dX);
	   setY(y + dY);
    }

    public void add(double dX, double dY) {
	   shift(dX, dY);
    }

    public DefenderBotPosition relativePosition(double dX, double dY) {
	   return new DefenderBotPosition(x + dX, y + dY);
    }
}