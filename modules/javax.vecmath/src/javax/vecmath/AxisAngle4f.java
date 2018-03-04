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
  * A 4 element axis angle represented by single precision floating point
  * x,y,z,angle components. An axis angle is a rotation of angle (radians) about
  * the vector (x,y,z).
  * @version specification 1.1, implementation $Revision$, $Date$
  * @author Kenji hiranabe
  */
public final class AxisAngle4f implements Serializable {

	private static final long serialVersionUID = -1528113085902377420L;

	/**
     * The x coordinate.
     */
    public float x;

    /**
     * The y coordinate.
     */
    public float y;

    /**
     * The z coordinate.
     */
    public float z;

    /**
     * The angle.
     */
    public float angle;

    /**
     * Constructs and initializes an AxisAngle4f from the specified x, y, z,
     * and angle.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param angle the angle.
     */
    public AxisAngle4f(float x, float y, float z, float angle) {
	
    	this.set(x, y, z, angle);
    }

    /**
     * Constructs and initializes an AxisAngle4f from the components contained
     * in the array.
     * @param a the array of length 4 containing x,y,z,angle in order
     */
    public AxisAngle4f(float a[]) {
	
    	this.set(a);
    }

    /**
     * Constructs and initializes a AxisAngle4f from the specified AxisAngle4f.
     * @param a1 the AxisAngle4f containing the initialization x y z angle data
     */
    public AxisAngle4f(AxisAngle4f a1) {
	
    	this.set(a1);
    }

    /**
     * Constructs and initializes a AxisAngle4f from the specified AxisAngle4f.
     * @param a1 the AxisAngle4d containing the initialization x y z angle data
     */
    public AxisAngle4f(AxisAngle4d a1) {
	
    	this.set(a1);
    }

    /**
     * Constructs and initializes a AxisAngle4f to (0,0,1,0).
     */
    public AxisAngle4f() {
	
    	this.x = 0.0F;
    	this.y = 0.0F;
    	this.z = 1.0F;
    	this.angle = 0.0F;
    }

    /**
     * Constructs and initializes an AxisAngle4f from the specified axis
     * and angle.
     *
     * @param axis the axis
     * @param angle the angle
     * @since Java 3D 1.2
     */
    public AxisAngle4f(Vector3f axis, float angle) {
        
    	this.x = axis.x;
        this.y = axis.y;
        this.z = axis.z;
        this.angle = angle;
    }

    /**
     * Sets the value of this AxisAngle4f to the specified axis and
     * angle.
     * @param axis the axis
     * @param angle the angle
     * @since Java 3D 1.2
     */
    public final void set(Vector3f axis, float angle) {
        
    	this.x = axis.x;
        this.y = axis.y;
        this.z = axis.z;
        this.angle = angle;
    }

    /**
     * Sets the value of this axis angle to the specified x,y,z,angle.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param angle the angle
     */
    public final void set(float x, float y, float z, float angle) {
	
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.angle = angle;
    }

    /**
     * Sets the value of this axis angle from the 4 values specified in the array.
     * @param a the array of length 4 containing x,y,z,angle in order
     */
    public final void set(float a[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 4
    	this.x = a[0];
    	this.y = a[1];
    	this.z = a[2];
    	this.angle = a[3];
    }

    /**
     * Sets the value of this axis angle to the value of axis angle t1.
     * @param t1 the axis angle to be copied
     */
    public final void set(AxisAngle4f a1) {
	
    	this.x = a1.x;
    	this.y = a1.y;
    	this.z = a1.z;
    	this.angle = a1.angle;
    }

    /**
     * Sets the value of this axis angle to the value of axis angle t1.
     * @param t1 the axis angle to be copied
     */
    public final void set(AxisAngle4d a1) {
	
    	this.x = (float)(a1.x);
    	this.y = (float)(a1.y);
    	this.z = (float)(a1.z);
    	this.angle = (float)(a1.angle);
    }

    /**
     * Gets the value of this axis angle into the array a of
     * length four in x,y,z,angle order.
     * @param a the array of length four
     */
    public final void get(float a[]) {
	
    	// ArrayIndexOutOfBounds is thrown if a.length < 4
    	a[0] = this.x;
    	a[1] = this.y;
    	a[2] = this.z;
    	a[3] = this.angle;
    }

    /**
     * Sets the value of this axis-angle to the rotational component of the
     * passed matrix.
     * @param m1 the matrix4f
     */
    public final void set(Matrix4f m1) {
	
    	this.setFromMat(
    		m1.m00, m1.m01, m1.m02,
    		m1.m10, m1.m11, m1.m12,
    		m1.m20, m1.m21, m1.m22
	    );
    }

    /**
     * Sets the value of this axis-angle to the rotational component of the
     * passed matrix.
     * @param m1 the matrix4d
     */
    public final void set(Matrix4d m1) {
	
    	this.setFromMat(
    		m1.m00, m1.m01, m1.m02,
    		m1.m10, m1.m11, m1.m12,
    		m1.m20, m1.m21, m1.m22
	    );
    }


    /**
     * Sets the value of this axis-angle to the rotational component of the
     * passed matrix.
     * @param m1 the matrix3f
     */
    public final void set(Matrix3f m1) {
	
    	this.setFromMat(
    		m1.m00, m1.m01, m1.m02,
    		m1.m10, m1.m11, m1.m12,
    		m1.m20, m1.m21, m1.m22
	    );
    }

    /**
     * Sets the value of this axis-angle to the rotational component of the
     * passed matrix.
     * @param m1 the matrix3d
     */
    public final void set(Matrix3d m1) {
	
    	this.setFromMat(
    		m1.m00, m1.m01, m1.m02,
    		m1.m10, m1.m11, m1.m12,
    		m1.m20, m1.m21, m1.m22
	    );
    }

    /**
     * Sets the value of this axis-angle to the rotational equivalent of the
     * passed quaternion.
     * @param q1 the Quat4f
     */
    public final void set(Quat4f q1) {
	
    	this.setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }

    /**
     * Sets the value of this axis-angle to the rotational equivalent of the
     * passed quaternion.
     * @param q1 the Quat4d
     */
    public final void set(Quat4d q1) {
	
    	this.setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }

    private final void setFromMat(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
	
    	// assuming M is normalized.
    	double cos = (m00 + m11 + m22 - 1.0D) * 0.5D;
    	this.x = (float)(m21 - m12);
    	this.y = (float)(m02 - m20);
    	this.z = (float)(m10 - m01);
    	double sin = 0.5D * Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    	this.angle = (float)Math.atan2(sin, cos);
    }

    private final void setFromQuat(double x, double y, double z, double w) {
	
    	// This logic can calculate angle without normalization.
    	// The direction of (x,y,z) and the sign of rotation cancel
    	// each other to calculate a right answer.

    	double sin_a2 = Math.sqrt(x * x + y * y + z * z);  // |sin a/2|, w = cos a/2
    	this.angle = (float)(2.0 * Math.atan2(sin_a2, w)); // 0 <= angle <= PI , because 0 < sin_a2
    	this.x = (float)x;
    	this.y = (float)y;
    	this.z = (float)z;
    }

    /**
     * Returns a string that contains the values of this AxisAngle4f. The form is (x,y,z,angle).
     * @return the String representation
     */
    @Override
    public final String toString() {
    	
	    return String.format("(%f,%f,%f,%f)", this.x, this.y, this.z, this.angle);
    }

    /**
     * Returns true if all of the data members of AxisAngle4f t1 are equal to the corresponding
     * data members in this
     * @param a1 the vector with which the comparison is made.
     */
    public final boolean equals(AxisAngle4f a1) {
	
    	return a1 != null && this.x == a1.x && this.y == a1.y && z == a1.z && this.angle == a1.angle;
    }

    /**
     * Returns true if the Object o1 is of type AxisAngle4f and all of the data
     * members of o1 are equal to the corresponding data members in this
     * AxisAngle4f.
     * @param o1 the object with which the comparison is made.
     */
    @Override
    public final boolean equals(Object o1) {
	
    	return o1 != null && (o1 instanceof AxisAngle4f) && equals((AxisAngle4f)o1);
    }

    /**
     * Returns true if the L-infinite distance between this axis-angle and axis-angle t1 is
     * less than or equal to the epsilon parameter, otherwise returns false. The L-infinite
     * distance is equal to MAX[abs(x1-x2), abs(y1-y2), abs(z1-z2), abs(angle1-angle2)].
     * @param a1 the axis-angle to be compared to this axis-angle
     * @param epsilon the threshold value
     */
    public final boolean epsilonEquals(AxisAngle4f a1, float epsilon) {
	
    	return (Math.abs(a1.x - this.x) <= epsilon) &&
    		   (Math.abs(a1.y - this.y) <= epsilon) &&
    		   (Math.abs(a1.z - this.z) <= epsilon) &&
    		   (Math.abs(a1.angle - this.angle) <= epsilon);
    }


    /**
     * Returns a hash number based on the data values in this object. 
     * Two different AxisAngle4f objects with identical data  values
     * (ie, returns true for equals(AxisAngle4f) ) will return the same hash number.
     * Two vectors with different data members may return the same hash value,
     * although this is not likely.
     */
    @Override
    public final int hashCode() {
	
    	return Float.floatToIntBits(this.x) ^ Float.floatToIntBits(this.y) ^ Float.floatToIntBits(this.z) ^ Float.floatToIntBits(this.angle);
    }
}
