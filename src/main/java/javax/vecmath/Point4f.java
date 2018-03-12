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
 * A 4 element point that is represented by single precision
 * floating point x,y,z,w coordinates.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Point4f extends Tuple4f implements Serializable {

	private static final long serialVersionUID = -8509269322697833837L;

	/**
     * Constructs and initializes a Point4f from the specified xyzw coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param w the w coordinate
     */
    public Point4f(float x, float y, float z, float w) {
	
    	super(x, y, z, w);
    }

    /**
     * Constructs and initializes a Point4f from the specified array.
     * @param p the array of length 4 containing xyzw in order
     */
    public Point4f(float p[]) {
	
    	super(p);
    }

    /**
     * Constructs and initializes a Point4f from the specified Point4f.
     * @param p1 the Point4f containing the initialization x y z w data
     */
    public Point4f(Point4f p1) {
	
    	super(p1);
    }

    /**
     * Constructs and initializes a Point4f from the specified Point4d.
     * @param p1 the Point4d containing the initialization x y z w data
     */
    public Point4f(Point4d p1) {
	
    	super(p1);
    }

    /**
     * Constructs and initializes a Point4f from the specified Tuple4d.
     * @param t1 the Tuple4d containing the initialization x y z w data
     */
    public Point4f(Tuple4d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Point4f from the specified Tuple4f.
     * @param t1 the Tuple4f containing the initialization x y z w data
     */
    public Point4f(Tuple4f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Point4f to (0,0,0,0).
     */
    public Point4f() {
	
    	super();
    }

    /**
     * Constructs and initializes a Point4d from the specified Tuple3f.
     * The x,y,z  components of this point are set to the corresponding
     * components
     * of tuple t1. The w component of this point is set to 1.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public Point4f(Tuple3f t1) {
        
    	super(t1.x, t1.y, t1.z, 1.0F);
    }

    /**
     * Sets the x,y,z components of this point to the corresponding
     * components of tuple t1. The w component of this point is set to 1.
     *
     * @param t1 the tuple to be copied
     * @since Java3D 1.2
     */
    public final void set(Tuple3f t1) {
        
    	this.set(t1.x, t1.y, t1.z, 1F);
    }

    /**
     * Computes the square of the distance between this point and point p1.
     * @param  p1 the other point
     * @return the square of distance between these two points as a float
     */
    public final float distanceSquared(Point4f p1) {
	
    	double dx = this.x - p1.x;
    	double dy = this.y - p1.y;
    	double dz = this.z - p1.z;
    	double dw = this.w - p1.w;
	
    	return (float)(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    /**
     * Returns the distance between this point and point p1.
     * @param p1 the other point
     * @return the distance between these two points
     */
    public final float distance(Point4f p1) {
	
    	return (float)Math.sqrt(this.distanceSquared(p1));
    }

    /**
     * Computes the L-1 (Manhattan) distance between this point and point p1.
     * The L-1 distance is equal to abs(x1-x2) + abs(y1-y2)
     * + abs(z1-z2) + abs(w1-w2).
     * @param p1 the other point
     * @return L-1 distance
     */
    public final float distanceL1(Point4f p1) {
	
    	return Math.abs(this.x - p1.x) +
    		   Math.abs(this.y - p1.y) +
    		   Math.abs(this.z - p1.z) +
    		   Math.abs(this.w - p1.w);
    }

    /**
     * Computes the L-infinite distance between this point and point p1.
     * The L-infinite distance is equal to MAX[abs(x1-x2), abs(y1-y2), abs(z1-z2), abs(w1-w2)].
     * @param p1 the other point
     * @return L-infinite distance
     */
    public final float distanceLinf(Point4f p1) {
	
    	return Math.max(Math.max(Math.abs(this.x - p1.x), Math.abs(this.y - p1.y)), Math.max(Math.abs(this.z - p1.z), Math.abs(this.w - p1.w)));
    }

    /**
     * Multiplies each of the x,y,z components of the Point4f parameter by 1/w,
     * places the projected values into this point, and places a 1 as the w
     * parameter of this point.
     * @param p1 the source Point4d, which is not modified
     */
     public final void project(Point4f p1) {
	 
    	 // zero div may occur.
    	 this.x = p1.x / p1.w;
    	 this.y = p1.y / p1.w;
    	 this.z = p1.z / p1.w;
    	 this.w = 1.0F;
     }
}
