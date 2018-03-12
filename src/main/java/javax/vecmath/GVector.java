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
 * A double precision, general, and dynamically resizeable one 
 * dimensional vector class. Index numbering begins with zero.
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class GVector implements Serializable {

	private static final long serialVersionUID = -8218568774945037304L;
	
	// note: only elementCount data in elementData are valid.
    // elementData.length is the allocated size.
    // invariant: elementData.length >= elementCount.
    private int elementCount;
    private double elementData[];

    /**
     * Constructs a new generalized mathematic Vector with zero
     * elements; length reprents the number of elements in the vector.
     * @param length number of elements in this vector. 
     */
    public GVector(int length) {
	
    	this.elementCount = length;
        this.elementData = new double[length];  // will be initialized to 0.0
    }

    /**
     * Constructs a new generalized mathematic Vector with zero 
     * elements; length represents the number of elements in the 
     * vector. !! this comment is a bug in Sun's API !!
     * @param vector the values for the new vector. 
     */
    public GVector(double vector[]) {
	
    	this(vector.length);
        System.arraycopy(vector, 0, this.elementData, 0, this.elementCount);
    }

    /**
     * Constructs a new GVector and copies the initial values from the parameter vector.  
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(GVector vector) {
	
    	this(vector.elementCount);
        System.arraycopy(vector.elementData, 0, this.elementData, 0, this.elementCount);
    }

    /**
     * Constructs a new GVector and copies the initial values from the Tuple 
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(Tuple2f tuple) {
	
    	this(2);
    	this.set(tuple);
    }

    /**
     * Constructs a new GVector and copies the initial values from the Tuple 
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(Tuple3f tuple) {
	
    	this(3);
    	this.set(tuple);
    }

    /**
     * Constructs a new GVector and copies the initial values from the Tuple 
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(Tuple3d tuple)  {
	
    	this(3);
    	this.set(tuple);
    }

    /**
     * Constructs a new GVector and copies the initial values from the Tuple 
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(Tuple4f tuple) {
	
    	this(4);
    	this.set(tuple);
    }
    
    /**
     * Constructs a new GVector and copies the initial values from the Tuple 
     * @param vector the source for the new GVector's initial values 
     */
    public GVector(Tuple4d tuple) {
	
    	this(4);
    	this.set(tuple);
    }

    /**
     * Constructs a new GVector by copying length elements from the 
     * array parameter.  The parameter length must be less than or 
     * equal to vector.length.
     * @param vector The array from which the values will be copied.
     * @param length The number of values copied from the array.
     */
    public GVector(double vector[], int length) {
	
    	// ArrayIndexOutOfBounds occur if length > vector.legnth
    	this(length);
        System.arraycopy(vector, 0, this.elementData, 0, this.elementCount);
    }

    /**
     * Returns the square root of the sum of the squares of this vector (its length in n-dimensional space).
     * @return length of this vector 
     */
    public final double norm() {
	
    	return Math.sqrt(this.normSquared());
    }

    /**
     * Returns the sum of the squares of this vector (its length squared in n-dimensional space).
     * @return length squared of this vector 
     */
    public final double normSquared() {
        
    	double s = 0.0D;
    	
        for(int i = 0; i < this.elementCount; i++) {
           
        	s += this.elementData[i] * this.elementData[i];
        }
        
        return s;
    }

    /**
     * Sets the value of this vector to the normalization of vector v1. 
     * @param v1 the un-normalized vector 
     */
    public final void normalize(GVector v1) {
	
    	this.set(v1);
    	this.normalize();
    }

    /**
     * Normalizes this vector in place. 
     */
    public final void normalize() {
        
    	double len = this.norm();
	
        // zero-div may happen.
        for (int i = 0; i < elementCount; i++) {
	    
        	this.elementData[i] /= len;
        }
    }

    /**
     * Sets the value of this vector to the scalar multiplication of 
     * the scale factor with the vector v1.
     * @param s the scalar value 
     * @param v1 the source vector 
     */
    public final void scale(double s, GVector v1) {
	
    	this.set(v1);
    	this.scale(s);
    }

    /**
     * Scales this vector by the scale factor s. 
     * @param s the scalar value 
     */
    public final void scale(double s) {
    	
        for(int i = 0; i < this.elementCount; i++) {
            
        	this.elementData[i] *= s;
        }
    }

    /** 
     * Sets the value of this vector to the scalar multiplication by 
     * s of vector v1 plus vector v2 (this = s*v1 + v2).
     * @param s the scalar value 
     * @param v1 the vector to be multiplied 
     * @param v2 the vector to be added 
     */
    public final void scaleAdd(double s, GVector v1, GVector v2)  {
        
        if(this.elementCount != v1.elementCount) {
        	
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v1's size:" + v1.elementCount);
        }
        
        if(this.elementCount != v2.elementCount) {
        	
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v2's size:" + v2.elementCount);
        }
    	
    	double[] v1data = v1.elementData;
        double[] v2data = v2.elementData;

        for(int i = 0; i < this.elementCount; i++) {
        	
            this.elementData[i] = s * v1data[i] + v2data[i];
        }
    }


    /**
     * Sets the value of this vector to sum of itself and the specified vector 
     * @param vector the second vector 
     */
    public final void add(GVector vector) {
    	
    	if(this.elementCount != vector.elementCount) {
    		
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v2's size:" + vector.elementCount);
    	}
    	
        double[] v1data = vector.elementData;

        for(int i = 0; i < this.elementCount; i++) {
            
        	this.elementData[i] += v1data[i];
        }
    }

    /**
     * Sets the value of this vector to the vector sum of vectors 
     * vector1 and vector2.
     * @param vector1 the first vector 
     * @param vector2 the second vector 
     */
    public final void add(GVector vector1, GVector vector2) {
	
    	this.set(vector1);
    	this.add(vector2);
    }

    /**
     * Sets the value of this vector to the vector difference of 
     * itself and vector (this = this - vector).
     * @param   vector - the other vector 
     */
    public final void sub(GVector vector)  {
        
        if(this.elementCount != vector.elementCount) {
        	
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != vector's size:" + vector.elementCount);
        }
    	
    	double[] v1data = vector.elementData;

        for(int i = 0; i < this.elementCount; i++) {
        	
            this.elementData[i] -= v1data[i];
        }
    }

    /**
     * Sets the value of this vector to the vector difference of 
     * vectors vector1 and vector2 (this = vector1 - vector2). 
     * @param vector1 the first vector 
     * @param vector2 the second vector 
     */
    public final void sub(GVector vector1,GVector vector2) {
	
    	this.set(vector1);
    	this.sub(vector2);
    }

    /**
     * Multiplies matrix m1 times Vector v1 and places the result 
     * into this vector (this = m1*v1).
     * @param m1 The matrix in the multiplication 
     * @param v1 The vector that is multiplied 
     */
    public final void mul(GMatrix m1, GVector v1) {
	
    	// note: this implementation is NOT alias-safe!
    	// i.e. v.mul(M,v) does not commutes right.

    	// note: no 'auto-grow' policy.

        double [] v1data = v1.elementData;
        int v1size = v1.elementCount;
        int nCol = m1.getNumCol();
        int nRow = m1.getNumRow();

        if(v1size != nCol) {
        	
            throw new IllegalArgumentException("v1.size:" + v1size + " != m1.nCol:" + nCol);
        }
        
        if(this.elementCount != nRow) {
        	
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != m1.nRow:" + nRow);
        }

        for(int i = 0; i < this.elementCount; i++) {
	    
        	double sum = 0.0D;
        	
            for(int j = 0; j < nCol; j++) {
            	
	            sum += m1.getElement(i, j) * v1data[j];
            }
            
            this.elementData[i] = sum;
        }
    }

    /**
     * Multiplies the transpose of vector v1 (ie, v1 becomes a row 
     * vector with respect to the multiplication) times matrix m1 
     * and places the result into this vector 
     * (this = transpose(v1)*m1). The result is technically a row 
     * vector, but the GVector class only knows about column vectors, 
     * and so the result is stored as a column vector. 
     * @param m1 The matrix in the multiplication 
     * @param v1 The vector that is temporarily transposed 
     */
    public final void mul(GVector v1, GMatrix m1) {
	
    	// note: this implementatin is NOT alias-safe!
    	// i.e. v.mul(M,v) does not comutes right.

    	// note: no 'auto-grow' policy.

        double [] v1data = v1.elementData;
        int v1size = v1.elementCount;
        int nCol = m1.getNumCol();
        int nRow = m1.getNumRow();

        if(v1size != nRow) {
        	
            throw new IllegalArgumentException("v1.size:" + v1size + " != m1.nRow:" + nRow);
        }
	
        if(this.elementCount != nCol) {
        	
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != m1.nCol:" + nCol);
        }

        for(int i = 0; i < this.elementCount; i++) {
	    
        	double sum = 0.0D;
        	
            for(int j = 0; j < nRow; j++) {
            	
	            sum += m1.getElement(j, i) * v1data[j];
            }
            
            this.elementData[i] = sum;
        }
    }

    /**
     * Negates the value of this vector: this = -this.
     */
    public final void negate() {
    	
        for(int i = 0; i < this.elementCount; i++) {
        	
            this.elementData[i] = -this.elementData[i];
        }
    }

    /**
     * Sets all the values in this vector to zero. 
     */
    public final void zero() {
    	
        for(int i = 0; i < this.elementCount; i++) {
            
        	this.elementData[i] = 0.0D;
        }
    }

    /**
     * Changes the size of this vector dynamically. If the size is 
     * increased no data values will be lost. If the size is 
     * decreased, only those data values whose vector positions 
     * were eliminated will be lost.
     * @param length number of desired elements in this vector 
     */
    public final void setSize(int newSize) {
        
    	if(newSize < 0) {
    		
            throw new NegativeArraySizeException("newSize:" + newSize + " < 0");
        }

    	if(this.elementCount < newSize) {
    		
    		double[] oldData = this.elementData;
    		this.elementData = new double[newSize];
    		System.arraycopy(oldData, 0, this.elementData, 0, this.elementCount);
    	}
	    
    	this.elementCount = newSize;
    }
    
    // TODO

    /**
     * Sets the value of this vector to the values found in the 
     * array parameter. The array should be at least equal in 
     * length to the number of elements in the vector. 
     * @param   vector the source array 
     */
    public final void set(double vector[]) {
	// note: only this.elementCount data is copied.(no auto-grow)
        System.arraycopy(vector, 0, elementData, 0, elementCount);
    }

    /**
     * Sets the value of this vector to the values found in 
     * vector vector. 
     * @param   vector the source vector 
     */
    public final void set(GVector vector) {
	// note: only this.elementCount data is copied.(no auto-grow)
        System.arraycopy(vector.elementData, 0, elementData, 0, elementCount);
    }

    /**
     * Sets the value of this vector to the values in tuple.
     * @param   tuple the source for the new GVector's new values 
     */
    public final void set(Tuple2f tuple)  {
        elementData[0] = (double)tuple.x;
        elementData[1] = (double)tuple.y;
    }

    /**
     * Sets the value of this vector to the values in tuple.
     * @param   tuple the source for the new GVector's new values 
     */
    public final void set(Tuple3f tuple) {
        elementData[0] = (double)tuple.x;
        elementData[1] = (double)tuple.y;
        elementData[2] = (double)tuple.z;
    }

    /**
     * Sets the value of this vector to the values in tuple.
     * @param   tuple the source for the new GVector's new values 
     */
    public final void set(Tuple3d tuple) {
        elementData[0] = tuple.x;
        elementData[1] = tuple.y;
        elementData[2] = tuple.z;
    }

    /**
     * Sets the value of this vector to the values in tuple.
     * @param   tuple the source for the new GVector's new values 
     */
    public final void set(Tuple4f tuple) {
        elementData[0] = (double)tuple.x;
        elementData[1] = (double)tuple.y;
        elementData[2] = (double)tuple.z;
        elementData[3] = (double)tuple.w;
    }

    /**
     * Sets the value of this vector to the values in tuple.
     * @param   tuple the source for the new GVector's new values 
     */
    public final void set(Tuple4d tuple) {
        elementData[0] = tuple.x;
        elementData[1] = tuple.y;
        elementData[2] = tuple.z;
        elementData[3] = tuple.w;
    }

    /**
     * Returns the number of elements in this vector. 
     * @return  number of elements in this vector 
     */
    public final int getSize() {
        return elementCount;
    }

    /**
     * Retrieves the value at the specified index value of this 
     * vector.
     * @param   index the index of the element to retrieve (zero indexed) 
     * @return  the value at the indexed element 
     */
    public final double getElement(int index) {
	try {
	    return elementData[index];
	} catch (ArrayIndexOutOfBoundsException e) {
	    throw new ArrayIndexOutOfBoundsException("index:"+index+ 
                "must be in [0, " + (elementCount-1) + "]");
	}
    }

    /**
     * Modifies the value at the specified index of this vector. 
     * @param   index the index if the element to modify (zero indexed) 
     * @param   value the new vector element value 
     */
    public final void setElement(int index,  double value) {
	try {
            elementData[index] = value;
	} catch (ArrayIndexOutOfBoundsException e) {
	    throw new ArrayIndexOutOfBoundsException("index:"+index+
		 " must be in [0, " + (elementCount-1) + "]");
	}
    }

    /**
     * Returns a string that contains the values of this GVector.
     * @return  the String representation 
     */
    @Override
    public final String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("(");
	for (int i = 0 ; i < elementCount-1 ; i++) {
	    buf.append(elementData[i]);
	    buf.append(",");
	}
	buf.append(elementData[elementCount-1]);
	buf.append(")");
	return buf.toString();
    }

    /**
     * Returns a hash number based on the data values in this
     * object.  Two different GMatrix objects with identical data values
     * (ie, returns true for equals(GMatrix) ) will return the same hash
     * number.  Two objects with different data members may return the
     * same hash value, although this is not likely.
     * @return the integer hash value
     */
    @Override
    public final int hashCode() {
	int hash = 0;
	for (int i = 0; i < elementCount; i++) {
	    long bits = Double.doubleToLongBits(elementData[i]);
	    hash ^= (int)(bits ^ (bits >> 32));
	}
	return hash;
    }

    /**
     * Returns true if all of the data members of GVector vector1 
     * are equal to the corresponding data members in this GVector.
     * @param   vector1 The vector with which the comparison is made. 
     * @return  true or false 
     */
    public final boolean equals(GVector vector1) {
	if (vector1 == null)
	    return false;
        if (elementCount != vector1.elementCount)
	    return false;
	double [] v1data = vector1.elementData;
        for (int i = 0; i < elementCount; i++) {
            if (elementData[i] != v1data[i])
		return false;
        }
        return true;
    }

    /**
      * Returns true if the Object o1 is of type GVector and all of the data
      * members of t1 are equal to the corresponding data members in this
      * GVector.
      * @param o1 the object with which the comparison is made.
      */
    @Override
    public final boolean equals(Object o1) {
	return o1 != null && (o1 instanceof GVector) && equals((GVector)o1);
    }

    /**
     * Returns true if the L-infinite distance between this vector 
     * and vector v1 is less than or equal to the epsilon parameter, 
     * otherwise returns false. The L-infinite distance is equal 
     * to MAX[abs(x1-x2), abs(y1-y2), . . . ]. <p>
     * @param   v1 The vector to be compared to this vector 
     * @param   epsilon the threshold value 
     */
    public final boolean epsilonEquals(GVector v1, double epsilon) {
        if (elementCount != v1.elementCount)
	    return false;
	double [] v1data = v1.elementData;
        for (int i = 0; i < elementCount; i++) {
            if (Math.abs(elementData[i] - v1data[i]) > epsilon)
		return false;
        }
        return true;
    }

    /**
     * Returns the dot product of this vector and vector v1.
     * @param   v1 the other vector 
     * @return  the dot product of this and v1 
     */
    public final double dot(GVector v1) {
        double [] v1data = v1.elementData;
        if (elementCount != v1.elementCount)
            throw new IllegalArgumentException("this.size:"+elementCount+" != v1.size:"+v1.elementCount);
	double sum = 0.0;
        for (int i = 0; i < elementCount; ++i)
            sum += elementData[i] * v1data[i];
        return sum;
    }

    /**
     * Solves for x in Ax = b, where x is this vector (nx1), 
     * A is mxn, b is mx1, and A = U*W*transpose(V); 
     * U,W,V must be precomputed and can be found by taking the 
     * singular value decomposition (SVD) of A using the method 
     * SVD found in the GMatrix class.
     * @param   U The U matrix produced by the GMatrix method SVD 
     * @param   W The W matrix produced by the GMatrix method SVD 
     * @param   V The V matrix produced by the GMatrix method SVD 
     * @param   b The b vector in the linear equation Ax = b 
     */
    public final void SVDBackSolve(GMatrix U, GMatrix W, GMatrix V, GVector b) {
        if (elementCount != U.getNumRow() || elementCount != U.getNumCol())
            throw new ArrayIndexOutOfBoundsException(
                "this.size:"+elementCount + " != U.nRow,nCol:" + U.getNumRow()+","+U.getNumCol());
        if (elementCount != W.getNumRow())
            throw new ArrayIndexOutOfBoundsException(
                "this.size:"+elementCount + " != W.nRow:" + W.getNumRow());
        if (b.elementCount != W.getNumCol())
            throw new ArrayIndexOutOfBoundsException(
                "b.size:"+b.elementCount + " != W.nCol:" + W.getNumCol());
        if (b.elementCount != V.getNumRow() || b.elementCount != V.getNumCol())
            throw new ArrayIndexOutOfBoundsException(
                "b.size:"+elementCount + " != V.nRow,nCol:" + V.getNumRow()+","+V.getNumCol());

	int m = U.getNumRow();  // this.elementCount
	int n = V.getNumRow();  // b.elementCount
	double [] tmp = new double [n];;

	for (int j = 0; j < n;j++) {
	    double s = 0.0;	
	    double wj = W.getElement(j, j);
	    if (wj != 0.0) {
		for (int i = 0; i < m; i++)
		    s += U.getElement(i, j)*b.elementData[i];
		s /= wj;
	    }
	    tmp[j] = s;
	}
	for (int j = 0; j < n; j++) {
	    double s = 0.0;
	    for (int jj = 0; jj < n; jj++)
		s += V.getElement(j,jj)*tmp[jj];
	    elementData[j] = s;
	}
    }

    /**
     * LU Decomposition Back Solve; this method takes the LU matrix 
     * and the permutation vector produced by the GMatrix method LUD 
     * and solves the equation (LU)*x = b by placing the solution 
     * vector x into this vector. This vector should be the same 
     * length or longer than b.
     * @param LU The matrix into which the lower and upper decompositions have been placed 
     * @param b The b vector in the equation (LU)*x = b 
     * @param permutation The row permuations that were necessary to produce the LU matrix parameter 
     */
    public final void LUDBackSolve(GMatrix LU, GVector b, GVector permutation) {
	
    	// not alias-safe with b and this!

    	// note: this is from William H. Press et.al Numerical Recipes in C.
    	// hiranabe modified 1-n indexing to 0-(n-1).
    	// I fixed some bugs in NRC, which are bad permutation handling.

        if (elementCount != b.elementCount)
            throw new ArrayIndexOutOfBoundsException(
		"this.size:"+elementCount+ " != b.size:" + b.elementCount);
        if (elementCount != LU.getNumRow())
            throw new ArrayIndexOutOfBoundsException(
                "this.size:"+elementCount + " != LU.nRow:" + LU.getNumRow());
        if (elementCount != LU.getNumCol())
            throw new ArrayIndexOutOfBoundsException(
                "this.size:"+elementCount + " != LU.nCol:" + LU.getNumCol());

	int n = elementCount;
        double [] indx = permutation.elementData;
	double [] x = elementData;  // to put answer.
	double [] bdata = b.elementData;

	/* make permutated b (b'=Pb)*/
	for (int i = 0; i < n; i++) {
	    // not alias-safe!
	    x[i] = bdata[(int)indx[i]];
	}

	/* forward substitution Ux' = b' */
	int ii = -1;
	for (int i = 0; i < n; i++) {
	    double sum = x[i];
	    if (0 <= ii) {
		for (int j = ii; j <= i - 1; j++)  
		    sum -= LU.getElement(i,j)*x[j];
	    } else if (sum != 0.0)  {
		/* found the first non-zero x */
		ii = i;
	    }
	    x[i] = sum;
	}

	/* backward substitution, solve x' */
	for (int i = n - 1; i >= 0 ; i--) {
	    double sum = x[i];
	    for (int j = i + 1; j < n; j++) 
		sum -= LU.getElement(i, j) * x[j];

	    // zero-div may occur
	    x[i] = sum / LU.getElement(i, i);
	}
    }

    /**
     * Returns the (n-space) angle in radians between this vector 
     * and the vector parameter; the return value is constrained to 
     * the range [0,PI].
     * @param v1 The other vector 
     * @return The angle in radians in the range [0,PI] 
     */
    public final double angle(GVector v1) {
       
    	return Math.acos(this.dot(v1) / this.norm() / v1.norm());
    }

    /**
     * Linearly interpolates between vectors v1 and v2 and places 
     * the result into this tuple: this = (1-alpha)*v1 + alpha*v2.
     * @param v1 the first vector 
     * @param v2 the second vector 
     * @param alpha the alpha interpolation parameter 
     */
    public final void interpolate(GVector v1, GVector v2, double alpha) {
	
    	this.set(v1);
    	this.interpolate(v2, alpha);
    }

    /**
     * Linearly interpolates between this vector and vector v1 and 
     * places the result into this tuple: 
     * this = (1-alpha)*this + alpha*v1. 
     * @param v1 the first vector 
     * @param alpha the alpha interpolation parameter 
     */
    public final void interpolate(GVector v1, double alpha) {
    	
        if(this.elementCount != v1.elementCount) {
        	
        	throw new IllegalArgumentException(String.format("this.size:%d != v1.size:%d", this.elementCount, v1.elementCount));
        }
    	
        double[] v1data = v1.elementData;
        double beta = (1.0D - alpha);
        
        for(int i = 0; i < this.elementCount; ++i) {
            
        	this.elementData[i] = beta * this.elementData[i] + alpha * v1data[i];
        }
    }
}
