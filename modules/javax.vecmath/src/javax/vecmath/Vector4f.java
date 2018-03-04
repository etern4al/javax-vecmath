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
 * A 4 element vector that is represented by single precision floating point x,y,z,w coordinates.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Vector4f extends Tuple4f implements Serializable {

	private static final long serialVersionUID = -3531785355424765838L;

	/**
     * Constructs and initializes a Vector4f from the specified xyzw coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param w the w coordinate
     */
    public Vector4f(float x, float y, float z, float w) {
	
    	super(x, y, z, w);
    }

    /**
     * Constructs and initializes a Vector4f from the specified array of length 4.
     * @param v the array of length 4 containing xyzw in order
     */
    public Vector4f(float v[]) {
	
    	super(v);
    }

    /**
     * Constructs and initializes a Vector4f from the specified Vector4f.
     * @param v1 the Vector4f containing the initialization x y z w data
     */
    public Vector4f(Vector4f v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector4f from the specified Vector4d.
     * @param v1 the Vector4d containing the initialization x y z w data
     */
    public Vector4f(Vector4d v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector4f from the specified Tuple4d.
     * @param t1 the Tuple4d containing the initialization x y z w data
     */
    public Vector4f(Tuple4d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector4f from the specified Tuple4f.
     * @param t1 the Tuple4f containing the initialization x y z w data
     */
    public Vector4f(Tuple4f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector4f to (0,0,0,0).
     */
    public Vector4f() {
	
    	super();
    }

    /**
     * Constructs and initializes a Vector4f from the specified Tuple3f.
     * The x,y,z  components of this point are set to the corresponding
     * components
     * of tuple t1. The w component of this point is set to 0.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public Vector4f(Tuple3f t1) {
        
    	super(t1.x, t1.y, t1.z, 0);
    }

    /**
     * Sets the x,y,z components of this point to the corresponding
     * components of tuple t1. The w component of this point is set to 1.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public final void set(Tuple3f t1) {
        
    	this.set(t1.x, t1.y, t1.z, 0);
    }

    /**
     * Returns the squared length of this vector.
     * @return the squared length of this vectoras a float
     */
    public final float lengthSquared() {
	
    	return this.x * this.x +
    		   this.y * this.y +
    		   this.z * this.z +
    		   this.w * this.w;
    }

    /**
     * Returns the length of this vector.
     * @return the length of this vector as a float
     */
    public final float length() {
	  
    	return (float)Math.sqrt(lengthSquared());
    }

    /**
     * Computes the dot product of the this vector and vector v1.
     * @param  v1 the other vector
     * @return the dot product of this vector and v1
     */
    public final float dot(Vector4f v1) {
	
    	return this.x * v1.x +
    		   this.y * v1.y +
    		   this.z * v1.z +
    		   this.w * v1.w;
    }

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * @param v1 the un-normalized vector
     */
    public final void normalize(Vector4d v1) {
	
    	this.set(v1);
    	this.normalize();
    }

    /**
     * Normalizes this vector in place.
     */
    public final void normalize() {
	
    	double d = this.length();

    	// zero-div may occur.
    	this.x /= d;
    	this.y /= d;
    	this.z /= d;
    	this.w /= d;
    }

    /**
     * Returns the (4-space) angle in radians between this vector and
     * the vector parameter; the return value is constrained to the
     * range [0,PI].
     * @param v1  the other vector
     * @return the angle in radians in the range [0,PI]
     */
    public final float angle(Vector4f v1) {
	
    	// zero div may occur.
    	double d = this.dot(v1);
    	double v1Length = v1.length();
    	double vLength = this.length();

    	// numerically, domain error may occur
    	return (float)Math.acos(d / v1Length / vLength);
    }
}
