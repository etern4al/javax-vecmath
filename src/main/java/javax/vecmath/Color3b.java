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
 * A three byte vector used for colors.
 * @version specification 1.2, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Color3b extends Tuple3b implements Serializable {

	private static final long serialVersionUID = 8058083586710765275L;

	/**
     * Constructs and initializes a Color3b from the specified three values.
     * @param c1 the first value
     * @param c2 the second value
     * @param c3 the third value
     */
    public Color3b(byte c1, byte c2, byte c3) {
	
    	super(c1, c2, c3);
    }

    /**
     * Constructs and initializes a Color3b from input array of length 3.
     * @param t the array of length 3 containing c1 c2 c3 in order
     */
    public Color3b(byte c[]) {
	
    	// ArrayIndexOutOfBounds is thrown if t.length < 3
    	super(c);
    }

    /**
     * Constructs and initializes a Color3b from the specified Color3b.
     * @param c the Color3b containing the initialization x y z data
     */
    public Color3b(Color3b c1) {
	
    	super(c1);
    }

    /**
     * Constructs and initializes a Color3b from the specified Tuple3b.
     * @param t1 the Tuple3b containing the initialization x y z data
     */
    public Color3b(Tuple3b t1) {
	
    	super(t1);
    }

    /**
     * Constructs and initializes a Color3b to (0,0,0).
     */
    public Color3b() {
	
    	super();
    }
}
