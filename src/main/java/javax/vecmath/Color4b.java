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
 * A four byte colors  (mostly used for colors with alpha).
 * @version specification 1.2, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Color4b extends Tuple4b implements Serializable {

	private static final long serialVersionUID = -1419027741805583467L;

	/**
     * Constructs and initializes a Color4b from the specified four values.
     * @param c1 the first value
     * @param c2 the second value
     * @param c3 the third value
     * @param c4 the fourth value
     */
    public Color4b(byte c1, byte c2, byte c3, byte c4) {
	
    	super(c1, c2, c3, c4);
    }

    /**
     * Constructs and initializes a Color4b from input array of length 4.
     * @param c the array of length 4 containing c1 c2 c3 c4 in order
     */
    public Color4b(byte c[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 4
    	super(c);
    }

    /**
     * Constructs and initializes a Color4b from the specified Color4b.
     * @param c the Color4b containing the initialization x y z w data
     */
    public Color4b(Color4b c1) {
	
    	super(c1);
    }

    /**
     * Constructs and initializes a Color4b from the specified Tuple4b.
     * @param t1 the Tuple4b containing the initialization x y z w data
     */
    public Color4b(Tuple4b t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Color4b to (0,0,0,0).
     */
    public Color4b() {
	
    	super();
    }
}
