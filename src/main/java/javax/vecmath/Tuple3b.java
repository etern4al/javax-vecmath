/*
 * Copyright (C) 1997,1998,1999
 * Kenji Hiranabe, Eiwa System Management, Inc.
 *
 * This program is free software.
 * Implemented by Kenji Hiranabe(hiranabe@esm.co.jp),
 * conforming to the Java(TM) 3D API specification by Sun Microsystems.
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation. Kenji Hiranabe and Eiwa System Management,Inc.
 * makes no representations about the suitability of this software for any
 * purpose.  It is provided "AS IS" with NO WARRANTY.
 */
package javax.vecmath;

import java.io.Serializable;

/**
 * A three byte tuple.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public abstract class Tuple3b implements Serializable {

	private static final long serialVersionUID = -4749886589362392969L;

	/**
     * The first value.
     */
    public byte x;

    /**
     * The second value.
     */
    public byte y;

    /**
     * The third value.
     */
    public byte z;

    /**
     * Constructs and initializes a Tuple3b from the specified three values.
     * @param b1 the first value
     * @param b2 the second value
     * @param b3 the third value
     */
    public Tuple3b(byte b1, byte b2, byte b3) {
	
    	this.x = b1;
    	this.y = b2;
    	this.z = b3;
    }

    /**
     * 
     * Constructs and initializes a Tuple3b from input array of length 3.
     * @param t the array of length 3 containing b1 b2 b3 in order
     */
    public Tuple3b(byte t[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 3
    	this.x = t[0];
    	this.y = t[1];
    	this.z = t[2];
    }

    /**
     * Constructs and initializes a Tuple3b from the specified Tuple3b.
     * @param t1 the Tuple3b containing the initialization x y z data
     */
    public Tuple3b(Tuple3b t1) {
    	
    	this.x = t1.x;
    	this.y = t1.y;
    	this.z = t1.z;
    }

    /**
     * Constructs and initializes a Tuple3b to (0,0,0).
     */
    public Tuple3b() {}

    /**
     * Sets the value of the data members of this tuple to the value of the argument tuple t1.
     * @param t1 the source tuple for the memberwise copy
     */
    public final void set(Tuple3b t1) {
	
    	this.x = t1.x;
    	this.y = t1.y;
    	this.z = t1.z;
    }

    /**
     * Sets the value of the data members of this tuple to the value of the argument tuple t1.
     * @param t array of length 3 which is the source for the memberwise copy
     */
    public final void set(byte t[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 3
    	this.x = t[0];
    	this.y = t[1];
    	this.z = t[2];
    }

    /**
     * Places the value of the x,y,z components of this Tuple3b into the array of length 3.
     * @param t array of length 3 into which the component values are copied
     */
    public final void get(byte t[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 3
    	t[0] = this.x;
    	t[1] = this.y;
    	t[2] = this.z;
    }

    /**
     * Places the value of the x,y,z components of this tuple into the tuple t1.
     * @param t the tuple into which the values are placed
     */
    public final void get(Tuple3b t) {
	
    	t.x = this.x;
    	t.y = this.y;
    	t.z = this.z;
    }

    /**
     * Returns a hash number based on the data values in this object. 
     * Two different Tuple3b objects with identical data  values
     * (ie, returns true for equals(Tuple3b) ) will return the same hash number.
     * Two vectors with different data members may return the same hash value,
     * although this is not likely.
     */
    @Override
    public final int hashCode() {
	
    	return this.x | (this.y << 8) | (this.z << 16);
    }

    /**
     * Returns true if all of the data members of Tuple3b t1 are equal to the corresponding
     * data members in this
     * @param t1 the vector with which the comparison is made.
     */
    public final boolean equals(Tuple3b t1) {
	
    	return t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z;
    }

    /**
     * Returns true if the Object o1 is of type Tuple3b and all of the data
     * members of t1 are equal to the corresponding data members in this
     * Tuple3b.
     * @param o1 the object with which the comparison is made.
     */
    @Override
    public final boolean equals(Object o1) {
	
    	return o1 != null && (o1 instanceof Tuple3b) && this.equals((Tuple3b)o1);
    }


    /**
     * Returns a string that contains the values of this Tuple3b. The form is (x,y,z).
     * @return the String representation
     */
    @Override
    public final String toString() {
	    
    	return String.format("(%s, %s, %s)", this.x, this.y, this.z);
    }
}
