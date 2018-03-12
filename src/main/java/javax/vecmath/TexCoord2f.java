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
 * A 2 element texture coordinate that is represented by single precision
 * floating point x,y coordinates.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class TexCoord2f extends Tuple2f implements Serializable {

	private static final long serialVersionUID = -8699842841944018270L;

	/**
     * Constructs and initializes a TexCoord2f from the specified xy coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public TexCoord2f(float x, float y) {
	
    	super(x, y);
    }

    /**
     * Constructs and initializes a TexCoord2f from the specified array.
     * @param p the array of length 2 containing xy in order
     */
    public TexCoord2f(float v[]) {
	
    	super(v);
    }

    /**
     * Constructs and initializes a TexCoord2f from the specified TexCoord2f.
     * @param v1 the TexCoord2f containing the initialization x y data
     */
    public TexCoord2f(TexCoord2f v1) {
	
    	super(v1);
    }

    /**
     * Constructs and initializes a TexCoord2f from the specified Tuple2f.
     * @param t1 the Tuple2f containing the initialization x y data
     */
    public TexCoord2f(Tuple2f t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a TexCoord2f to (0,0).
     */
    public TexCoord2f() {
	
    	super();
    }
}
