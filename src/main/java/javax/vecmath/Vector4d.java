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
 * A 4 element vector that is represented by double precision floating point
 * x,y,z,w coordinates. 
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Vector4d extends Tuple4d implements Serializable {

	private static final long serialVersionUID = -8392169029347160200L;

	/**
     * Constructs and initializes a Vector4d from the specified xyzw coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param w the w coordinate
     */
    public Vector4d(double x, double y, double z, double w) {
	
    	super(x, y, z, w);
    }

    /**
     * Constructs and initializes a Vector4d from the specified array of length 4.
     * @param v the array of length 4 containing xyzw in order
     */
    public Vector4d(double v[]) {
	
    	super(v);
    }

    /**
     * Constructs and initializes a Vector4d from the specified Vector4d.
     * @param v1 the Vector4d containing the initialization x y z w data
     */
    public Vector4d(Vector4f v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector4d from the specified Vector4d.
     * @param v1 the Vector4d containing the initialization x y z w data
     */
    public Vector4d(Vector4d v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector4d from the specified Tuple4d.
     * @param t1 the Tuple4d containing the initialization x y z w data
     */
    public Vector4d(Tuple4d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector4d from the specified Tuple4f.
     * @param t1 the Tuple4f containing the initialization x y z w data
     */
    public Vector4d(Tuple4f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector4d to (0,0,0,0).
     */
    public Vector4d() {
	
    	super();
    }

    /**
     * Constructs and initializes a Vector4d from the specified Tuple3d.
     * The x,y,z  components of this point are set to the corresponding
     * components
     * of tuple t1. The w component of this point is set to 0.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public Vector4d(Tuple3d t1) {
       
    	super(t1.x, t1.y, t1.z, 0);
    }

    /**
     * Sets the x,y,z components of this point to the corresponding
     * components of tuple t1. The w component of this point is set to 1.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public final void set(Tuple3d t1) {
        
    	this.set(t1.x, t1.y, t1.z, 0);
    }

    /**
     * Returns the squared length of this vector.
     * @return the squared length of this vector
     */
    public final double lengthSquared() {
	
    	return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    /**
     * Returns the length of this vector.
     * @return the length of this vector
     */
    public final double length() {
	
    	return Math.sqrt(lengthSquared());
    }

    /**
     * Computes the dot product of the this vector and vector v1.
     * @param  v1 the other vector
     * @return the dot product of this vector and vector v1
     */
    public final double dot(Vector4d v1) {
	
    	return this.x * v1.x + this.y * v1.y + this.z * v1.z + this.w * v1.w;
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
	
    	double d = length();

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
    public final double angle(Vector4d v1) {
	
    	// zero div may occur.
    	double d = this.dot(v1);
    	double v1_length = v1.length();
    	double v_length = this.length();

    	// numerically, domain error may occur
    	return (double)Math.acos(d / v1_length / v_length);
    }
}
