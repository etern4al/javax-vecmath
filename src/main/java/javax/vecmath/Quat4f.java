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
 * A 4 element quaternion represented by single precision floating 
 * point x,y,z,w coordinates. 
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Quat4f extends Tuple4f implements Serializable {

	private static final long serialVersionUID = -7322329863794129255L;

	/**
     * Constructs and initializes a Quat4f from the specified xyzw coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param w the w scalar component
     */
    public Quat4f(float x, float y, float z, float w) {
	super(x, y, z, w);
    }
  
    /**
     * Constructs and initializes a Quat4f from the array of length 4.
     * @param v the array of length 4 containing xyzw in order
     */
    public Quat4f(float q[]) {
	
    	super(q);
    }

    /**
     * Constructs and initializes a Quat4f from the specified Quat4f.
     * @param q1 the Quat4f containing the initialization x y z w data
     */
    public Quat4f(Quat4f q1) {
	
    	super(q1);
    }

    /**
     * Constructs and initializes a Quat4f from the specified Quat4d.
     * @param q1 the Quat4d containing the initialization x y z w data
     */
    public Quat4f(Quat4d q1) {
	
    	super(q1);
    }

    /**
      * Constructs and initializes a Quat4f from the specified Tuple4d.
      * @param t1 the Tuple4d containing the initialization x y z w data
      */
    public Quat4f(Tuple4d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Quat4f from the specified Tuple4f.
     * @param t1 the Tuple4f containing the initialization x y z w data
     */
    public Quat4f(Tuple4f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Quat4f to (0,0,0,0).
     */
    public Quat4f() {
	
    	super();
    }

    /**
     * Sets the value of this quaternion to the conjugate of quaternion q1.
     * @param q1 the source vector
     */
    public final void conjugate(Quat4f q1) {
	
    	this.x = -q1.x;
    	this.y = -q1.y;
    	this.z = -q1.z;
    	this.w = q1.w;
    }

    /**
     * Negate the value of of each of this quaternion's x,y,z coordinates 
     *  in place.
     */
    public final void conjugate() {
	
    	this.x = -x;
    	this.y = -y;
    	this.z = -z;
    }

    /**
     * Sets the value of this quaternion to the quaternion product of
     * quaternions q1 and q2 (this = q1 * q2).  
     * Note that this is safe for aliasing (e.g. this can be q1 or q2).
     * @param q1 the first quaternion
     * @param q2 the second quaternion
     */
    public final void mul(Quat4f q1, Quat4f q2) {
	
    	// store on stack for aliasing-safty
    	this.set(q1.x * q2.w + q1.w * q2.x + q1.y * q2.z - q1.z * q2.y,
    			 q1.y * q2.w + q1.w * q2.y + q1.z * q2.x - q1.x * q2.z,
    			 q1.z * q2.w + q1.w * q2.z + q1.x * q2.y - q1.y * q2.x,
    			 q1.w * q2.w - q1.x * q2.x - q1.y * q2.y - q1.z * q2.z);
    }

    /**
     * Sets the value of this quaternion to the quaternion product of
     * itself and q1 (this = this * q1).
     * @param q1 the other quaternion
     */
    public final void mul(Quat4f q1) {
	
    	// store on stack for aliasing-safty
    	this.set(this.x * q1.w + this.w * q1.x + this.y * q1.z - this.z * q1.y,
    			 this.y * q1.w + this.w * q1.y + this.z * q1.x - this.x * q1.z,
    			 this.z * q1.w + this.w * q1.z + this.x * q1.y - this.y * q1.x,
    			 this.w * q1.w - this.x * q1.x - this.y * q1.y - this.z * q1.z);
    }                                     

    /**
     *
     * Multiplies quaternion q1 by the inverse of quaternion q2 and places
     * the value into this quaternion.  The value of both argument quaternions 
     * is preservered (this = q1 * q2^-1).
     * @param q1 the left quaternion
     * @param q2 the right quaternion
     */
    public final void mulInverse(Quat4f q1, Quat4f q2) {
	
    	// zero-div may occur.
    	// store on stack once for aliasing-safty
    	double n = this.norm();
    	n = (n == 0.0 ? n : 1/n);
	
    	this.set((float)((q1.x * q2.w - q1.w * q2.x - q1.y * q2.z + q1.z * q2.y) * n),
    			 (float)((q1.y * q2.w - q1.w * q2.y - q1.z * q2.x + q1.x * q2.z) * n),
    			 (float)((q1.z * q2.w - q1.w * q2.z - q1.x * q2.y + q1.y * q2.x) * n),
    			 (float)((q1.w * q2.w + q1.x * q2.x + q1.y * q2.y + q1.z * q2.z) * n));
    }

    /**
     * Multiplies this quaternion by the inverse of quaternion q1 and places
     * the value into this quaternion.  The value of the argument quaternion
     * is preserved (this = this * q^-1).
     * @param q1 the other quaternion
     */
    public final void mulInverse(Quat4f q1) {
	
    	// zero-div may occur.
    	// store on stack once for aliasing-safty
    	double n = norm();
    	n = (n == 0.0 ? n : 1/n);
	
    	this.set((float)((this.x * q1.w - this.w * q1.x - this.y * q1.z + this.z * q1.y) * n),
    			 (float)((this.y * q1.w - this.w * q1.y - this.z * q1.x + this.x * q1.z) * n),
    			 (float)((this.z * q1.w - this.w * q1.z - this.x * q1.y + this.y * q1.x) * n),
    			 (float)((this.w * q1.w + this.x * q1.x + this.y * q1.y + this.z * q1.z) * n));
    }

    private final double norm() {

    	return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    /**
     * Sets the value of this quaternion to quaternion inverse of quaternion q1.
     * @param q1 the quaternion to be inverted
     */
    public final void inverse(Quat4f q1) {
	
    	double n = q1.norm();
	
    	// zero-div may occur.
    	this.x = (float)(-q1.x / n);
    	this.y = (float)(-q1.y / n);
    	this.z = (float)(-q1.z / n);
    	this.w = (float)(q1.w / n);
    }

    /**
     * Sets the value of this quaternion to the quaternion inverse of itself.
     */
    public final void inverse() {
	
    	double n = this.norm();
	
    	// zero-div may occur.
    	this.x = (float)(-this.x / n);
    	this.y = (float)(-this.y / n);
    	this.z = (float)(-this.z / n);
    	this.w = (float)(this.w / n);
    }

    /**
     * Sets the value of this quaternion to the normalized value
     * of quaternion q1.
     * @param q1 the quaternion to be normalized.
     */
    public final void normalize(Quat4f q1) {
	
    	double n = Math.sqrt(q1.norm());
	
    	// zero-div may occur.
    	this.x = (float)(q1.x / n);
    	this.y = (float)(q1.y / n);
    	this.z = (float)(q1.z / n);
    	this.w = (float)(q1.w / n);
    }

    /**
     * Normalizes the value of this quaternion in place.
     */
    public final void normalize() {
	
    	float n = (float)Math.sqrt(this.norm());
	
    	// zero-div may occur.
		this.x /= n;
		this.y /= n;
		this.z /= n;
		this.w /= n;
    }

    /**
     * Sets the value of this quaternion to the rotational component of
     * the passed matrix.
     * @param m1 the matrix4f
     */
    public final void set(Matrix4f m1) {
	
    	this.setFromMat(m1.m00, m1.m01, m1.m02,
    					m1.m10, m1.m11, m1.m12,
    					m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the value of this quaternion to the rotational component of
     * the passed matrix.
     * @param m1 the matrix4d
     */
    public final void set(Matrix4d m1) {
	
    	this.setFromMat(m1.m00, m1.m01, m1.m02,
    			   		m1.m10, m1.m11, m1.m12,
    			   		m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the value of this quaternion to the rotational component of
     * the passed matrix.
     * @param m1 the matrix3f
     */
    public final void set(Matrix3f m1) {
	
    	this.setFromMat(m1.m00, m1.m01, m1.m02,
    					m1.m10, m1.m11, m1.m12,
    					m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the value of this quaternion to the rotational component of
     * the passed matrix.
     * @param m1 the matrix3d
     */
    public final void set(Matrix3d m1) {
	
    	this.setFromMat(m1.m00, m1.m01, m1.m02,
    					m1.m10, m1.m11, m1.m12,
    					m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the value of this quaternion to the equivalent rotation of teh
     * AxisAngle argument.
     * @param a1 the axis-angle
     */
    public final void set(AxisAngle4f a1) {
    	
		this.x = a1.x;
		this.y = a1.y;
		this.z = a1.z;
		double n = Math.sqrt(this.norm());
		
		// zero-div may occur.
		float s = (float)(Math.sin(0.5D * a1.angle) / n);
		this.x *= s;
		this.y *= s;
		this.z *= s;
		this.w = (float)Math.cos(0.5D * a1.angle);
    }

    /**
     * Sets the value of this quaternion to the equivalent rotation of teh
     * AxisAngle argument.
     * @param a1 the axis-angle
     */
    public final void set(AxisAngle4d a1) {
    	
		this.x = (float)a1.x;
		this.y = (float)a1.y;
		this.z = (float)a1.z;
		double n = Math.sqrt(this.norm());
		
		// zero-div may occur.
		float s = (float)(Math.sin(0.5D * a1.angle) / n);
		this.x *= s;
		this.y *= s;
		this.z *= s;
		this.w = (float)Math.cos(0.5D * a1.angle);
    }

    /**
     * Performs a great circle interpolation between this quaternion and the
     * quaternion parameter and places the result into this quaternion.
     * @param q1 the other quaternion
     * @param alpha the alpha interpolation parameter
     */
    public final void interpolate(Quat4f q1, double alpha) {
	
    	// From Hoggar.
    	this.normalize();
    	double n1 = Math.sqrt(q1.norm());
	
    	// zero-div may occur.
    	double x1 = q1.x / n1;
    	double y1 = q1.y / n1;
    	double z1 = q1.z / n1;
    	double w1 = q1.w / n1;

    	// t is cosine (dot product)
    	double t = this.x * x1 + this.y * y1 + this.z * z1 + this.w * w1;

    	// same quaternion (avoid domain error)
    	if(1.0 <= Math.abs(t)) {
    		
    		return;
    	}

    	// t is now theta
    	t = Math.acos(t);
    	double sin_t = Math.sin(t);

    	// same quaternion (avoid zero-div)
    	if(sin_t == 0.0) {
    		
    		return;
    	}

    	double s = Math.sin((1.0D - alpha) * t) / sin_t;
    	t = Math.sin(alpha * t) / sin_t;

    	// set values
    	this.x = (float)(s * this.x + t * x1);
    	this.y = (float)(s * this.y + t * y1);
    	this.z = (float)(s * this.z + t * z1);
    	this.w = (float)(s * this.w + t * w1);
    }

    /**
     * Performs a great circle interpolation between quaternion q1 and
     * quaternion q2 and places the result into this quaternion.
     * @param q1 the first quaternion
     * @param q2 the second quaternion
     * @param alpha the alpha interpolation parameter
     */
    public final void interpolate(Quat4f q1, Quat4f q2, double alpha) {
	
    	this.set(q1);
    	this.interpolate(q2, alpha);
    }

    private void setFromMat(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
	
    	// From Ken Shoemake
    	// (ftp://ftp.cis.upenn.edu/pub/graphics/shoemake)

    	double s;
    	double tr = m00 + m11 + m22;
    	
    	if(tr >= 0.0D) {
	    
    		s = Math.sqrt(tr + 1.0D);
    		this.w = (float)(s * 0.5D);
    		s = 0.5D / s;
    		this.x = (float)((m21 - m12) * s);
    		this.y = (float)((m02 - m20) * s);
    		this.z = (float)((m10 - m01) * s);
    		
    	} else {
    		
    		double max = Math.max(Math.max(m00, m11), m22);
	    
    		if(max == m00) {
		
    			s = Math.sqrt(m00 - (m11 + m22) + 1.0D);
    			this.x = (float)(s * 0.5D);
    			s = 0.5D / s;
    			this.y = (float)((m01 + m10) * s);
    			this.z = (float)((m20 + m02) * s);
    			this.w = (float)((m21 - m12) * s);
    			
    		} else if(max == m11) {
    			
    			s = Math.sqrt(m11 - (m22 + m00) + 1.0D);
    			this.y = (float)(s * 0.5D);
    			s = 0.5D / s;
				this.z = (float)((m12 + m21) * s);
				this.x = (float)((m01 + m10) * s);
				this.w = (float)((m02 - m20) * s);
				
    		} else {
		
    			s = Math.sqrt(m22 - (m00 + m11) + 1.0D);
	    		this.z = (float)(s * 0.5D);
	    		s = 0.5D / s;
	    		this.x = (float)((m20 + m02) * s);
	    		this.y = (float)((m12 + m21) * s);
	    		this.w = (float)((m10 - m01) * s);
    		}
    	}
    }
}
