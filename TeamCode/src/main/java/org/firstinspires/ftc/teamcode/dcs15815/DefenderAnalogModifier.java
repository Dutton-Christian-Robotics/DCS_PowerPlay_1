package org.firstinspires.ftc.teamcode.dcs15815;

public class DefenderAnalogModifier {

    private double curveValue;
    private double maxValue;

    public DefenderAnalogModifier() {
	   curveValue = 1;
	   maxValue = 1;
    }

    public DefenderAnalogModifier(double curve, double max) {
	   curveValue = curve;
	   maxValue = max;
    }

    public DefenderAnalogModifier setCurveValue(double value) {
	   curveValue = value;
	   return this;
    }

    public DefenderAnalogModifier curveDown(double value) {
	   setCurveValue(value);
	   return this;
    }

    public DefenderAnalogModifier curveUp(double value) {
	   if (value > 1) {
		  setCurveValue(1 / value);
	   } else {
		  setCurveValue(value);
	   }
	   return this;
    }

    public void setMaxValue(double value) {
	   maxValue = value;
    }

    public double modify(double value) {
	   if (value > 0) {
		  return Math.pow(value, curveValue) * maxValue;
	   } else {
		  return Math.pow(Math.abs(value), curveValue) * maxValue * -1;
	   }
    }



}
