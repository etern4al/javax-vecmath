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
 * A 3 element color represented by single precision floating point x,y,z
 * coordinates. Color components should be in the range of zero to one.
 * @version specification 1.2, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Color3f extends Tuple3f implements Serializable {

	private static final long serialVersionUID = -578322095534561507L;

	/**
     * Constructs and initializes a Color3f from the specified xyz
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Color3f(float x, float y, float z) {
	
    	super(x, y, z);
    }

    /**
     * Constructs and initializes a Color3f from input array of length 3.
     * @param c the array of length 3 containing xyz in order
     */
    public Color3f(float c[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 3
    	super(c);
    }

    /**
     * Constructs and initializes a Color3f from the specified Color3f.
     * @param c the Color3f containing the initialization x y z data
     */
    public Color3f(Color3f c1) {
	
    	super(c1);
    }

    /**
     * Constructs and initializes a Color3f from the specified Tuple3d.
     * @param t1 the Tuple3d containing the initialization x y z data
     */
    public Color3f(Tuple3d t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Color3f from the specified Tuple3f.
     * @param t1 the Tuple3f containing the initialization x y z data
     */
    public Color3f(Tuple3f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Color3f to (0,0,0).
     */
    public Color3f() {
	
    	super();
    }
}
