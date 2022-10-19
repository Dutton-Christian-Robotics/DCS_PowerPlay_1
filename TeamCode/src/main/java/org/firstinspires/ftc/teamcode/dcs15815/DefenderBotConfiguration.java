package org.firstinspires.ftc.teamcode.dcs15815;

import java.lang.reflect.Field;
import java.util.Hashtable;

abstract class DefenderBotConfiguration {
    protected Hashtable<String, Field> fieldHashtable;

    public String DRIVETRAIN_BACKLEFT_MOTOR_NAME = null;
    public String DRIVETRAIN_FRONTLEFT_MOTOR_NAME = null;
    public String DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = null;
    public String DRIVETRAIN_BACKRIGHT_MOTOR_NAME = null;

    DefenderBotConfiguration() {
	   fieldHashtable = new Hashtable<>();
	   for (Field f : DefenderBotConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }
    }

    public Object get(String key) {
	   Field f = fieldHashtable.get(key);
	   try {
		  return f.get(this);
	   } catch (Exception e) {
		  return "";
	   }
    }

    public String getString(String key) {
	   Field f = fieldHashtable.get(key);
	   try {
		  return f.get(this).toString();
	   } catch (Exception e) {
		  return "";
	   }
    }

    public int getInt(String key) {
	   Field f = fieldHashtable.get(key);
	   try {
		  return f.getInt(this);
	   } catch (Exception e) {
		  return -1;
	   }
    }

    public double getDouble(String key) {
	   Field f = fieldHashtable.get(key);
	   try {
		  return f.getDouble(this);
	   } catch (Exception e) {
		  return -1;
	   }
    }
//
//    public double getDoubleArray(String key) {
//        Field f = fieldHashtable.get(key);
//        try {
//            return f.get(this);
//        } catch (Exception e) {
//            return -1;
//        }
//    }


    public long getLong(String key) {
	   Field f = fieldHashtable.get(key);
	   try {
		  return f.getLong(this);
	   } catch (Exception e) {
		  return -1;
	   }
    }


}