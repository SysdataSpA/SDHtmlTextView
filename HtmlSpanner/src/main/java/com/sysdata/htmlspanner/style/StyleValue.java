package com.sysdata.htmlspanner.style;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/29/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class StyleValue {

    public static enum Unit { PX, EM, PERCENTAGE };

    private Integer intValue;
    private Float floatValue;

    private Unit unit;

    public static StyleValue parse( String value ) {

        if ( value.equals("0") ) {
            return new StyleValue(0f, Unit.EM);
        }

        // removing extra spaces
        value = value.replaceAll("\\s+","");

        if ( value.endsWith("px") ) {

            try {
                final int intValue = Integer.parseInt( value.substring(0, value.length() -2) );
                return new StyleValue(intValue);
            } catch (NumberFormatException nfe ) {
                Log.e("StyleValue", "Can't parse value: " + value );
                return null;
            }
        }

        if ( value.endsWith("%") ) {
            Log.d("StyleValue", "translating percentage " + value );
            try {
                final int percentage = Integer.parseInt( value.substring(0, value.length() -1 ) );
                final float floatValue = percentage / 100f;

                return new StyleValue(floatValue, Unit.PERCENTAGE);
            } catch ( NumberFormatException nfe ) {
                Log.e("StyleValue", "Can't parse font-size: " + value );
                return null;
            }
        }

        if ( value.endsWith("em") ) {
            try {
                final Float number = Float.parseFloat(value.substring(0, value.length() - 2));
                return new StyleValue(number, Unit.EM);
            } catch ( NumberFormatException nfe ) {
                Log.e("CSSCompiler", "Can't parse value: " + value );
                return null;
            }
        }

        return null;
    }

    public StyleValue( int intValue ) {
        this.unit = Unit.PX;
        this.intValue = intValue;
    }

    public StyleValue( float floatValue, Unit unit ) {
        this.floatValue = floatValue;
        this.unit = unit;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public Unit getUnit() {
        return this.unit;
    }

    @Override
    public String toString() {
        if ( intValue != null ) {
            return "" + intValue + this.unit;
        } else {
            return "" + floatValue + this.unit;
        }
    }
}
