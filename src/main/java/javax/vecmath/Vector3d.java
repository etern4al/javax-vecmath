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
 * A 3 element vector that is represented by double precision floating point
 * x,y,z coordinates. If this value represents a normal, then it should be
 * normalized.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Vector3d extends Tuple3d implements Serializable {

	private static final long serialVersionUID = 1739648509421507252L;

	/**
     * Constructs and initializes a Vector3d from the specified xyz coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3d(double x, double y, double z) {
	
    	super(x, y, z);
    }

    /**
     * Constructs and initializes a Vector3d from the specified array of length 3.
     * @param v the array of length 3 containing xyz in order
     */
    public Vector3d(double v[]) {
	
    	super(v);
    }

    /**
     * Constructs and initializes a Vector3d from the specified Vector3f.
     * @param v1 the Vector3d containing the initialization x y z data
     */
    public Vector3d(Vector3f v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector3d from the specified Vector3d.
     * @param v1 the Vector3d containing the initialization x y z data
     */
    public Vector3d(Vector3d v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a Vector3d from the specified Tuple3d.
     * @param t1 the Tuple3d containing the initialization x y z data
     */
    public Vector3d(Tuple3d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector3d from the specified Tuple3f.
     * @param t1 the Tuple3f containing the initialization x y z data
     */
    public Vector3d(Tuple3f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Vector3d to (0,0,0).
     */
    public Vector3d() {
	
    	super();
    }

    /**
     * Sets this vector to be the vector cross product of vectors v1 and v2.
     * @param v1 the first vector
     * @param v2 the second vector
     */
    public final void cross(Vector3d v1, Vector3d v2) {
	
    	// store on stack once for aliasing-safty
    	// i.e. safe when a.cross(a, b)
    	this.set(v1.y * v2.z - v1.z * v2.y,
    			 v1.z * v2.x - v1.x * v2.z,
    			 v1.x * v2.y - v1.y * v2.x);
    }

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * @param v1 the un-normalized vector
     */
    public final void normalize(Vector3d v1) {
	
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
    }

    /**
     * Computes the dot product of the this vector and vector v1.
     * @param  v1 the other vector
     */
    public final double dot(Vector3d v1) {
	
    	return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }

    /**
     * Returns the squared length of this vector.
     * @return the squared length of this vector
     */
    public final double lengthSquared() {
	
    	return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * Returns the length of this vector.
     * @return the length of this vector
     */
    public final double length() {
	
    	return Math.sqrt(this.lengthSquared());
    }

    /**
     * Returns the angle in radians between this vector and
     * the vector parameter; the return value is constrained to the
     * range [0,PI].
     * @param v1  the other vector
     * @return the angle in radians in the range [0,PI]
     */
    public final double angle(Vector3d v1) {
	
    	// return (double)Math.acos(dot(v1)/v1.length()/v.length());
    	// Numerically, near 0 and PI are very bad condition for acos.
    	// In 3-space, |atan2(sin,cos)| is much stable.

    	double xx = this.y * v1.z - this.z * v1.y;
		double yy = this.z * v1.x - this.x * v1.z;
		double zz = this.x * v1.y - this.y * v1.x;
		double cross = Math.sqrt(xx * xx + yy * yy + zz * zz);

		return Math.abs(Math.atan2(cross, this.dot(v1)));
    }
}
