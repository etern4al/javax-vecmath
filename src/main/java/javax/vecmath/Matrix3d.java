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
 * A double precision floating point 3 by 3 matrix.
 * Primarily to support rotations
 * @version specification 1.1, implementation $Revision$, $Date$
 * @author Kenji hiranabe
 */
public final class Matrix3d implements Serializable {

	private static final long serialVersionUID = 2642420119797623189L;

	/**
     * The first element of the first row.
     */
    public double m00;

    /**
     * The second element of the first row.
     */
    public double m01;

    /**
     * third element of the first row.
     */
    public double m02;

    /**
     * The first element of the second row.
     */
    public double m10;

    /**
     * The second element of the second row.
     */
    public double m11;

    /**
     * The third element of the second row.
     */
    public double m12;

    /**
     * The first element of the third row.
     */
    public double m20;

    /**
     * The second element of the third row.
     */
    public double m21;

    /**
     * The third element of the third row.
     */
    public double m22;

    /**
     * Constructs and initializes a Matrix3d from the specified nine values.
     * @param m00 the [0][0] element
     * @param m01 the [0][1] element
     * @param m02 the [0][2] element
     * @param m10 the [1][0] element
     * @param m11 the [1][1] element
     * @param m12 the [1][2] element
     * @param m20 the [2][0] element
     * @param m21 the [2][1] element
     * @param m22 the [2][2] element
     */
    public Matrix3d(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
	
    	this.set(m00, m01, m02,
    			 m10, m11, m12,
    			 m20, m21, m22);
    }

    /**
     * Constructs and initializes a Matrix3d from the specified 9
     * element array.  this.m00 =v[0], this.m01=v[1], etc.
     * @param  v the array of length 9 containing in order
     */
    public Matrix3d(double v[]) {
	
    	this.set(v);
    }

    /**
     * Constructs a new matrix with the same values as the Matrix3d parameter.
     * @param m1 The source matrix.
     */
    public Matrix3d(Matrix3d m1) {
	
    	this.set(m1);
    }

    /**
     * Constructs a new matrix with the same values as the Matrix3f parameter.
     * @param m1 The source matrix.
     */
    public Matrix3d(Matrix3f m1) {
	
    	this.set(m1);
    }

    /**
     * Constructs and initializes a Matrix3d to all zeros.
     */
    public Matrix3d() {
	
    	this.setZero();
    }

    /**
     * Returns a string that contains the values of this Matrix3d.
     * @return the String representation
     */
    @Override
    public final String toString() {
	
    	String nl = System.getProperty("line.separator"); 
    	StringBuilder builder = new StringBuilder();
    	builder.append('[');
    	builder.append(nl);
    	builder.append(String.format("  [%s\t%s\t%s]", this.m00, this.m01, this.m02));
    	builder.append(nl);
    	builder.append(String.format("  [%s\t%s\t%s]", this.m10, this.m11, this.m12));
    	builder.append(nl);
    	builder.append(String.format("  [%s\t%s\t%s] ]", this.m20, this.m21, this.m22));
    	builder.append(nl);
    	
    	return builder.toString();
    }

    /**
     * Sets this Matrix3d to identity.
     */
    public final void setIdentity() {
    	
    	this.set(1.0D, 0.0D, 0.0D,
    			 0.0D, 1.0D, 0.0D,
    			 0.0D, 0.0D, 1.0D);
    }

    /**
     * Sets the scale component of the current matrix by factoring out the
     * current scale (by doing an SVD) from the rotational component and
     * multiplying by the new scale.
     * @param scale the new scale amount
     */
    public final void setScale(double scale) {
	
    	this.SVD(this);
    	this.m00 *= scale;
    	this.m11 *= scale;
    	this.m22 *= scale;
    }

    /**
     * Sets the specified element of this matrix3d to the value provided.
     * @param row  the row number to be modified (zero indexed)
     * @param column  the column number to be modified (zero indexed)
     * @param value the new value
     */
    public final void setElement(int row, int column, double value) {
	
    	if(row == 0) {
	    
    		       if(column == 0) {this.m00 = value;
    		} else if(column == 1) {this.m01 = value;
    		} else if(column == 2) {this.m02 = value;
    		} else {
		
    			throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
    		}
    		
    	} else if(row == 1) {
	    
    		       if(column == 0) {this.m10 = value;
    		} else if(column == 1) {this.m11 = value;
    		} else if(column == 2) {this.m12 = value;
    		} else {
		
    			throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
    		}
    		       
    	} else if (row == 2) {
	    
    			   if(column == 0) {this.m20 = value;
    		} else if(column == 1) {this.m21 = value;
    		} else if(column == 2) {this.m22 = value;
    		} else {
		
    			throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
    		}
    			   
    	} else {
    		
    		throw new ArrayIndexOutOfBoundsException("row must be 0 to 2 and is " + row);
    	}
    }

    /**
     * Retrieves the value at the specified row and column of this matrix.
     * @param row  the row number to be retrieved (zero indexed)
     * @param column  the column number to be retrieved (zero indexed)
     * @return the value at the indexed element
     */
    public final double getElement(int row, int column) {
    	
    	double element;
	
    	if(row == 0) {
	    
    			   if(column == 0) {element = this.m00;
    		} else if(column == 1) {element = this.m01;
	    	} else if(column == 2) {element = this.m02;
	    	} else {
		
	    		throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
	    	}
	    
    	} else if(row == 1) {

    			   if(column == 0) {element = this.m10;
    		} else if(column == 1) {element = this.m11;
    		} else if(column == 2) {element = this.m12;
    		} else {
		
    			throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
    		}

    	} else if (row == 2) {
	    
    		       if(column == 0) {element = this.m20;
    		} else if(column == 1) {element = this.m21;
    		} else if(column == 2) {element = this.m22;
    		} else {
		
    			throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
    		}
	
    	} else {
    		
    		throw new ArrayIndexOutOfBoundsException("row must be 0 to 2 and is " + row);
    	}
	
    	return element;
    }

    /**
     * Sets the specified row of this matrix3d to the three values provided.
     * @param row  the row number to be modified (zero indexed)
     * @param x the first column element
     * @param y the second column element
     * @param z the third column element
     */
    public final void setRow(int row, double x, double y, double z) {
	
    	if(row == 0) {
    		
		    this.m00 = x;
		    this.m01 = y;
		    this.m02 = z;
	    
    	} else if (row == 1) {
    		
		    this.m10 = x;
		    this.m11 = y;
		    this.m12 = z;
	    
    	} else if (row == 2) {
    		
		    this.m20 = x;
		    this.m21 = y;
		    this.m22 = z;
	    
    	} else {
	    
    		throw new ArrayIndexOutOfBoundsException("row must be 0 to 2 and is " + row);
    	}
    }

    /**
     * Sets the specified row of this matrix3d to the Vector provided.
     * @param row the row number to be modified (zero indexed)
     * @param v the replacement row
     */
    public final void setRow(int row, Vector3d v) {
    	
    	this.setRow(row, v.x, v.y, v.z);
    }

    /**
     * Sets the specified row of this matrix3d to the four values provided.
     * @param row the row number to be modified (zero indexed)
     * @param v the replacement row
     */
    public final void setRow(int row, double v[]) {

    	this.setRow(row, v[0], v[1], v[2]);
    }

    /**
     * Copies the matrix values in the specified row into the
     * array parameter.
     * @param row the matrix row
     * @param v The array into which the matrix row values will be copied
     */
    public final void getRow(int row, double v[]) {
	
    	if(row == 0) {
    		
		    v[0] = this.m00;
		    v[1] = this.m01;
		    v[2] = this.m02;
	    
		} else if(row == 1) {
			
		    v[0] = this.m10;
		    v[1] = this.m11;
		    v[2] = this.m12;
	    
		} else if(row == 2) {
			
		    v[0] = this.m20;
		    v[1] = this.m21;
		    v[2] = this.m22;
	    
		} else {
			
			throw new ArrayIndexOutOfBoundsException("row must be 0 to 2 and is " + row);
		}
    }

    /**
     * Copies the matrix values in the specified row into the
     * vector parameter.
     * @param row the matrix row
     * @param v The vector into which the matrix row values will be copied
     */
    public final void getRow(int row, Vector3d v) {
	
    	if(row == 0) {
    		
		    v.x = this.m00;
		    v.y = this.m01;
		    v.z = this.m02;
	    
    	} else if (row == 1) {
    		
		    v.x = this.m10;
		    v.y = this.m11;
		    v.z = this.m12;
	    
    	} else if (row == 2) {
    		
		    v.x = this.m20;
		    v.y = this.m21;
		    v.z = this.m22;
	    
    	} else {
	    
    		throw new ArrayIndexOutOfBoundsException("row must be 0 to 2 and is " + row);
    	}
    }

    /**
     * Sets the specified column of this matrix3d to the three values provided.
     * @param  column the column number to be modified (zero indexed)
     * @param x the first row element
     * @param y the second row element
     * @param z the third row element
     */
    public final void setColumn(int column, double x, double y, double z) {
	
    	if(column == 0) {
    		
		    this.m00 = x;
		    this.m10 = y;
		    this.m20 = z;
	    
    	}  else if(column == 1) {
    		
		    this.m01 = x;
		    this.m11 = y;
		    this.m21 = z;
	    
		} else if(column == 2) {
			
		    this.m02 = x;
		    this.m12 = y;
		    this.m22 = z;
	    
		} else {
			
	    	throw new ArrayIndexOutOfBoundsException("col must be 0 to 2 and is " + column);
		}
    }

    /**
     * Sets the specified column of this matrix3d to the vector provided.
     * @param column the column number to be modified (zero indexed)
     * @param v the replacement column
     */
    public final void setColumn(int column, Vector3d v) {

    	this.setColumn(column, v.x, v.y, v.z);
    }

    /**
     * Sets the specified column of this matrix3d to the four values provided. 
     * @param column  the column number to be modified (zero indexed) 
     * @param v       the replacement column 
     */
    public final void setColumn(int column,  double v[]) {

    	this.setColumn(column, v[0], v[1], v[2]);
    }


    /**
     * Copies the matrix values in the specified column into the vector 
     * parameter.
     * @param column the matrix column
     * @param v The vector into which the matrix row values will be copied
     */
    public final void getColumn(int column, Vector3d v) {
	
    	if(column == 0) {
    		
		    v.x = this.m00;
		    v.y = this.m10;
		    v.z = this.m20;
	    
    	} else if(column == 1) {
    		
		    v.x = this.m01;
		    v.y = this.m11;
		    v.z = this.m21;
	    
    	} else if(column == 2) {
    		
		    v.x = this.m02;
		    v.y = this.m12;
		    v.z = this.m22;
	    
    	} else {
	    
    		throw new ArrayIndexOutOfBoundsException("column must be 0 to 2 and is " + column);
    	}
    }

    /**
     * Copies the matrix values in the specified column into the array
     * parameter.
     * @param column the matrix column
     * @param v The array into which the matrix row values will be copied
     */
    public final void getColumn(int column,  double v[]) {
	
    	if(column == 0) {
	    
    		v[0] = this.m00;
    		v[1] = this.m10;
    		v[2] = this.m20;
	
    	} else if(column == 1) {
	    
    		v[0] = this.m01;
    		v[1] = this.m11;
    		v[2] = this.m21;
	
    	} else if (column == 2) {
	    
    		v[0] = this.m02;
    		v[1] = this.m12;
    		v[2] = this.m22;
	
    	} else {
    		
    		throw new ArrayIndexOutOfBoundsException("column must be 0 to 2 and is " + column);
    	}
    }

    /**
     * Performs an SVD normalization of this matrix to calculate and return the
     * uniform scale factor. This matrix is not modified.
     * @return the scale factor of this matrix
     */
    public final double getScale() {
	
    	return this.SVD(null);
    }

    /**
     * Adds a scalar to each component of this matrix.
     * @param scalar The scalar adder.
     */
    public final void add(double scalar) {
	
    	this.m00 += scalar;
    	this.m01 += scalar;
    	this.m02 += scalar;
    	this.m10 += scalar;
    	this.m11 += scalar;
    	this.m12 += scalar;
    	this.m20 += scalar;
    	this.m21 += scalar;
    	this.m22 += scalar;
    }

    /**
     * Adds a scalar to each component of the matrix m1 and places
     * the result into this. Matrix m1 is not modified.
     * @param scalar The scalar adder.
     * @parm m1 The original matrix values.
     */
    public final void add(double scalar, Matrix3d m1) {
	
    	this.set(m1);
    	this.add(scalar);
    }

    /**
     * Sets the value of this matrix to the matrix sum of matrices m1 and m2. 
     * @param m1 the first matrix 
     * @param m2 the second matrix 
     */
    public final void add(Matrix3d m1, Matrix3d m2) {
	
    	// note this is alias safe.
    	this.set(m1.m00 + m2.m00, m1.m01 + m2.m01, m1.m02 + m2.m02,
    			 m1.m10 + m2.m10, m1.m11 + m2.m11, m1.m12 + m2.m12,
    			 m1.m20 + m2.m20, m1.m21 + m2.m21, m1.m22 + m2.m22);
    }

    /**
     * Sets the value of this matrix to sum of itself and matrix m1. 
     * @param m1 the other matrix 
     */
    public final void add(Matrix3d m1) {
	
    	this.m00 += m1.m00;
    	this.m01 += m1.m01;
    	this.m02 += m1.m02;
    	this.m10 += m1.m10;
    	this.m11 += m1.m11;
    	this.m12 += m1.m12;
    	this.m20 += m1.m20;
    	this.m21 += m1.m21;
    	this.m22 += m1.m22;
    }

    /**
     * Sets the value of this matrix to the matrix difference
     * of matrices m1 and m2. 
     * @param m1 the first matrix 
     * @param m2 the second matrix 
     */
    public final void sub(Matrix3d m1, Matrix3d m2) {
	
    	// note this is alias safe.
    	this.set(m1.m00 - m2.m00, m1.m01 - m2.m01, m1.m02 - m2.m02,
    			 m1.m10 - m2.m10, m1.m11 - m2.m11, m1.m12 - m2.m12,
    			 m1.m20 - m2.m20, m1.m21 - m2.m21, m1.m22 - m2.m22);
    }

    /**
     * Sets the value of this matrix to the matrix difference of itself
     * and matrix m1 (this = this - m1). 
     * @param m1 the other matrix 
     */
    public final void sub(Matrix3d m1) {
	
    	this.m00 -= m1.m00;
    	this.m01 -= m1.m01;
    	this.m02 -= m1.m02;
    	this.m10 -= m1.m10;
    	this.m11 -= m1.m11;
    	this.m12 -= m1.m12;
    	this.m20 -= m1.m20;
    	this.m21 -= m1.m21;
    	this.m22 -= m1.m22;
    }

    /**
     * Sets the value of this matrix to its transpose. 
     */
    public final void transpose() {
	
    	double tmp = this.m01;
    	this.m01 = m10;
    	this.m10 = tmp;

    	tmp = this.m02;
    	this.m02 = this.m20;
    	this.m20 = tmp;

    	tmp = this.m12;
    	this.m12 = this.m21;
    	this.m21 = tmp;
    }

    /**
     * Sets the value of this matrix to the transpose of the argument matrix
     * @param m1 the matrix to be transposed 
     */
    public final void transpose(Matrix3d m1) {
	
    	// alias-safe
    	this.set(m1);
    	this.transpose();
    }


    /**
     * Sets the value of this matrix to the matrix conversion of the
     * (double precision) quaternion argument. 
     * @param q1 the quaternion to be converted 
     */
    public final void set(Quat4d q1) {
	
    	this.setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }

    /**
     * Sets the value of this matrix to the matrix conversion of the
     * double precision axis and angle argument. 
     * @param a1 the axis and angle to be converted 
     */
    public final void set(AxisAngle4d a1) {
	
    	this.setFromAxisAngle(a1.x, a1.y, a1.z, a1.angle);
    }

    /**
     * Sets the value of this matrix to the matrix conversion of the
     * single precision quaternion argument. 
     * @param q1 the quaternion to be converted 
     */
    public final void set(Quat4f q1)  {
	
    	this.setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }

    /**
     * Sets the value of this matrix to the matrix conversion of the
     * single precision axis and angle argument. 
     * @param a1 the axis and angle to be converted 
     */
    public final void set(AxisAngle4f a1) {
	
    	this.setFromAxisAngle(a1.x, a1.y, a1.z, a1.angle);
    }

    /**
     * Sets the value of this matrix to the value of the Matrix3d
     * argument.
     */
    public final void set(Matrix3d m1) {
    	
    	this.set(m1.m00, m1.m01, m1.m02,
    			 m1.m10, m1.m11, m1.m12,
    			 m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the value of this matrix to the double value of the Matrix3f
     * argument.
     * @param m1 the matrix3f to be converted to double
     */
    public final void set(Matrix3f m1)  {

    	this.set(m1.m00, m1.m01, m1.m02,
   			 	 m1.m10, m1.m11, m1.m12,
   			 	 m1.m20, m1.m21, m1.m22);
    }

    /**
     * Sets the values in this Matrix3d equal to the row-major array parameter
     * (ie, the first four elements of the array will be copied into the first
     * row of this matrix, etc.).
     */
    public final void set(double m[]) {
    	
    	this.set(m[0], m[1], m[2],
    			 m[3], m[4], m[5],
    			 m[6], m[7], m[8]);
    }


    /**
     * Sets the value of this matrix to the matrix inverse
     * of the passed matrix m1. 
     * @param m1 the matrix to be inverted 
     */
    public final void invert(Matrix3d m1)  {
	
    	this.set(m1);
    	this.invert();
    }

    /**
     * Sets the value of this matrix to its inverse.
     */
    public final void invert() {
	
    	double s = this.determinant();
	
    	if(s != 0.0D) {
		
    		s = 1.0D / s;
    		
			// alias-safe way.
			this.set(this.m11 * this.m22 - this.m12 * this.m21,
					 this.m02 * this.m21 - this.m01 * this.m22,
					 this.m01 * this.m12 - this.m02 * this.m11,
					 this.m12 * this.m20 - this.m10 * this.m22,
					 this.m00 * this.m22 - this.m02 * this.m20,
					 this.m02 * this.m10 - this.m00 * this.m12,
					 this.m10 * this.m21 - this.m11 * this.m20,
					 this.m01 * this.m20 - this.m00 * this.m21,
					 this.m00 * this.m11 - this.m01 * this.m10);
			this.mul(s);
    	}
    }

    /**
     * Computes the determinant of this matrix. 
     * @return the determinant of the matrix 
     */
    public final double determinant()  {
	
    	// less *,+,- calculation than expanded expression.
    	return this.m00 * (this.m11 * this.m22 - this.m21 * this.m12)
    		 - this.m01 * (this.m10 * this.m22 - this.m20 * this.m12)
    		 + this.m02 * (this.m10 * this.m21 - this.m20 * this.m11);
    }

    /**
     * Sets the value of this matrix to a scale matrix with the
     * passed scale amount. 
     * @param scale the scale factor for the matrix 
     */
    public final void set(double scale)  {
	
    	this.set(scale, 0.0D, 0.0D,
    			 0.0D, scale, 0.0D,
    			 0.0D, 0.0D, scale);
    }


    /**
     * Sets the value of this matrix to a rotation matrix about the x axis
     * by the passed angle. 
     * @param angle the angle to rotate about the X axis in radians 
     */
    public final void rotX(double angle)  {
	
    	double c = Math.cos(angle);
    	double s = Math.sin(angle);
    	
    	this.set(1.0D, 0.0D, 0.0D,
    			 0.0D, c,    -s,
    			 0.0D, s,    c);
    }

    /**
     * Sets the value of this matrix to a rotation matrix about the y axis
     * by the passed angle. 
     * @param angle the angle to rotate about the Y axis in radians 
     */
    public final void rotY(double angle)  {
	
    	double c = Math.cos(angle);
    	double s = Math.sin(angle);
	
    	this.m00 = c;
    	this.m01 = 0.0D;
    	this.m02 = s;
    	this.m10 = 0.0D;
    	this.m11 = 1.0D;
    	this.m12 = 0.0D;
    	this.m20 = -s;
    	this.m21 = 0.0D;
    	this.m22 = c;
    }

    /**
     * Sets the value of this matrix to a rotation matrix about the z axis
     * by the passed angle. 
     * @param angle the angle to rotate about the Z axis in radians 
     */
    public final void rotZ(double angle)  {
	
    	double c = Math.cos(angle);
    	double s = Math.sin(angle);
	
    	this.set(c,    -s,   0.0D,
    			 s,    c,    0.0D,
    			 0.0D, 0.0D, 1.0D);
    }

    /**
     * Multiplies each element of this matrix by a scalar.
     * @param scalar The scalar multiplier.
     */
     public final void mul(double scalar) {
	
    	 this.m00 *= scalar;
    	 this.m01 *= scalar; 
    	 this.m02 *= scalar;
    	 this.m10 *= scalar;
    	 this.m11 *= scalar; 
    	 this.m12 *= scalar;
    	 this.m20 *= scalar;
    	 this.m21 *= scalar;
    	 this.m22 *= scalar;
     }

    /**
     * Multiplies each element of matrix m1 by a scalar and places the result
     * into this. Matrix m1 is not modified.
     * @param scalar The scalar multiplier.
     * @param m1 The original matrix.
     */
     public final void mul(double scalar, Matrix3d m1) {
	 
    	 this.set(m1);
    	 this.mul(scalar);
     }

    /**
     * Sets the value of this matrix to the result of multiplying itself
     * with matrix m1. 
     * @param m1 the other matrix 
     */
    public final void mul(Matrix3d m1) {
	
    	this.mul(this, m1);
    }

    /**
     * Sets the value of this matrix to the result of multiplying
     * the two argument matrices together. 
     * @param m1 the first matrix 
     * @param m2 the second matrix 
     */
    public final void mul(Matrix3d m1, Matrix3d m2) {
	
    	// alias-safe way.
    	this.set(m1.m00 * m2.m00 + m1.m01 * m2.m10 + m1.m02 * m2.m20,
    			 m1.m00 * m2.m01 + m1.m01 * m2.m11 + m1.m02 * m2.m21,
    			 m1.m00 * m2.m02 + m1.m01 * m2.m12 + m1.m02 * m2.m22,
    			 m1.m10 * m2.m00 + m1.m11 * m2.m10 + m1.m12 * m2.m20,
    			 m1.m10 * m2.m01 + m1.m11 * m2.m11 + m1.m12 * m2.m21,
    			 m1.m10 * m2.m02 + m1.m11 * m2.m12 + m1.m12 * m2.m22,
    			 m1.m20 * m2.m00 + m1.m21 * m2.m10 + m1.m22 * m2.m20,
    			 m1.m20 * m2.m01 + m1.m21 * m2.m11 + m1.m22 * m2.m21,
    			 m1.m20 * m2.m02 + m1.m21 * m2.m12 + m1.m22 * m2.m22);
    }

    /**
     * Multiplies this matrix by matrix m1, does an SVD normalization of the
     * result, and places the result back into this matrix this =
     * SVDnorm(this*m1).
     * @param m1 the matrix on the right hand side of the multiplication
     */
    public final void mulNormalize(Matrix3d m1) {
	
    	this.mul(m1);
    	this.SVD(this);
    }


    /**
     * Multiplies matrix m1 by matrix m2, does an SVD normalization of the
     * result, and places the result into this matrix this = SVDnorm(m1*m2).
     * @param m1  the matrix on the left hand side of the multiplication
     * @param m2  the matrix on the right hand side of the multiplication
     */
    public final void mulNormalize(Matrix3d m1, Matrix3d m2) {
	
    	this.mul(m1, m2);
    	this.SVD(this);
    }

    /**
     * Multiplies the transpose of matrix m1 times the transpose of matrix m2,
     * and places the result into this.
     * @param m1 The matrix on the left hand side of the multiplication
     * @param m2 The matrix on the right hand side of the multiplication
     */
    public final void mulTransposeBoth(Matrix3d m1, Matrix3d m2) {
	
    	this.mul(m2, m1);
    	this.transpose();
    }

    /**
     * Multiplies matrix m1 times the transpose of matrix m2, and places the
     * result into this.
     * @param m1 The matrix on the left hand side of the multiplication
     * @param m2 The matrix on the right hand side of the multiplication
     */
    public final void mulTransposeRight(Matrix3d m1, Matrix3d m2) {
	
    	// alias-safe way.
    	this.set(m1.m00 * m2.m00 + m1.m01 * m2.m01 + m1.m02 * m2.m02,
    			 m1.m00 * m2.m10 + m1.m01 * m2.m11 + m1.m02 * m2.m12,
    			 m1.m00 * m2.m20 + m1.m01 * m2.m21 + m1.m02 * m2.m22,
    			 m1.m10 * m2.m00 + m1.m11 * m2.m01 + m1.m12 * m2.m02,
    			 m1.m10 * m2.m10 + m1.m11 * m2.m11 + m1.m12 * m2.m12,
    			 m1.m10 * m2.m20 + m1.m11 * m2.m21 + m1.m12 * m2.m22,
    			 m1.m20 * m2.m00 + m1.m21 * m2.m01 + m1.m22 * m2.m02,
    			 m1.m20 * m2.m10 + m1.m21 * m2.m11 + m1.m22 * m2.m12,
    			 m1.m20 * m2.m20 + m1.m21 * m2.m21 + m1.m22 * m2.m22);
    }
    
    /**
     * Multiplies the transpose of matrix m1 times matrix m2, and places the
     * result into this.
     * @param m1 The matrix on the left hand side of the multiplication
     * @param m2 The matrix on the right hand side of the multiplication
     */
    public final void mulTransposeLeft(Matrix3d m1, Matrix3d m2) {
	
    	// alias-safe way.
    	this.set(m1.m00 * m2.m00 + m1.m10 * m2.m10 + m1.m20 * m2.m20,
    			 m1.m00 * m2.m01 + m1.m10 * m2.m11 + m1.m20 * m2.m21,
    			 m1.m00 * m2.m02 + m1.m10 * m2.m12 + m1.m20 * m2.m22,
    			 m1.m01 * m2.m00 + m1.m11 * m2.m10 + m1.m21 * m2.m20,
    			 m1.m01 * m2.m01 + m1.m11 * m2.m11 + m1.m21 * m2.m21,
    			 m1.m01 * m2.m02 + m1.m11 * m2.m12 + m1.m21 * m2.m22,
    			 m1.m02 * m2.m00 + m1.m12 * m2.m10 + m1.m22 * m2.m20,
    			 m1.m02 * m2.m01 + m1.m12 * m2.m11 + m1.m22 * m2.m21,
    			 m1.m02 * m2.m02 + m1.m12 * m2.m12 + m1.m22 * m2.m22);
    }

    /**
     * Performs singular value decomposition normalization of this matrix.
     */
    public final void normalize() {
	
    	this.SVD(this);
    }

    /**
     * Perform singular value decomposition normalization of matrix m1 and
     * place the normalized values into this.
     * @param m1 Provides the matrix values to be normalized
     */
    public final void normalize(Matrix3d m1) {
	
    	this.set(m1);
    	this.SVD(this);
    }

    /**
     * Perform cross product normalization of this matrix.
     */
    public final void normalizeCP() {
	
    	double s = Math.pow(Math.abs(this.determinant()), -1.0D / 3.0D);
    	this.mul(s);
    }
      
    /**
     * Perform cross product normalization of matrix m1 and place the
     * normalized values into this.
     * @param m1 Provides the matrix values to be normalized
     */
    public final void normalizeCP(Matrix3d m1) {
	
    	this.set(m1);
    	this.normalizeCP();
    }



    /**
     * Returns true if all of the data members of Matrix3d m1 are
     * equal to the corresponding data members in this Matrix3d. 
     * @param m1 The matrix with which the comparison is made. 
     * @return true or false 
     */
    public final boolean equals(Matrix3d m1)  {
	
    	return m1 != null &&
    		   this.m00 == m1.m00 &&
    		   this.m01 == m1.m01 &&
    		   this.m02 == m1.m02 &&
    		   this.m10 == m1.m10 &&
    		   this.m11 == m1.m11 &&
    		   this.m12 == m1.m12 &&
    		   this.m20 == m1.m20 &&
    		   this.m21 == m1.m21 &&
    		   this.m22 == m1.m22;
    }

    /**
     * Returns true if the Object o1 is of type Matrix3d and all of the data
     * members of t1 are equal to the corresponding data members in this
     * Matrix3d.
     * @param o1 the object with which the comparison is made.
     */
    public final boolean equals(Object o1) {
	
    	return o1 != null && (o1 instanceof Matrix3d) && equals((Matrix3d)o1);
    }

    /**
     * Returns true if the L-infinite distance between this matrix and matrix
     * m1 is less than or equal to the epsilon parameter, otherwise returns
     * false. The L-infinite distance is equal to MAX[i=0,1,2,3 ; j=0,1,2,3 ;
     * abs(this.m(i,j) - m1.m(i,j)]
     * @param m1 The matrix to be compared to this matrix
     * @param epsilon the threshold value
     */
    public final boolean epsilonEquals(Matrix3d m1, double epsilon) {
	
    	return Math.abs(this.m00 - m1.m00) <= epsilon &&
    		   Math.abs(this.m01 - m1.m01) <= epsilon &&
    		   Math.abs(this.m02 - m1.m02) <= epsilon &&
    		   Math.abs(this.m10 - m1.m10) <= epsilon &&
    		   Math.abs(this.m11 - m1.m11) <= epsilon &&
    		   Math.abs(this.m12 - m1.m12) <= epsilon &&
    		   Math.abs(this.m20 - m1.m20) <= epsilon &&
    		   Math.abs(this.m21 - m1.m21) <= epsilon &&
    		   Math.abs(this.m22 - m1.m22) <= epsilon;
    }

    /**
     * Returns a hash number based on the data values in this
     * object.  Two different Matrix3d objects with identical data values
     * (ie, returns true for equals(Matrix3d) ) will return the same hash
     * number.  Two objects with different data members may return the
     * same hash value, although this is not likely.
     * @return the integer hash value 
     */
    @Override
    public final int hashCode() {
	
    	return this.hash(this.m00) ^
    		   this.hash(this.m01) ^
    		   this.hash(this.m02) ^
    		   this.hash(this.m10) ^
    		   this.hash(this.m11) ^
    		   this.hash(this.m12) ^
    		   this.hash(this.m20) ^
    		   this.hash(this.m21) ^
    		   this.hash(this.m22);
    }
    
    private final int hash(double val) {
    	
    	long bits = Double.doubleToLongBits(m00);
    	return (int)(bits ^ (bits >> 32));
    }

    /**
     * Sets this matrix to all zeros. 
     */
    public final void setZero() {
	
    	this.set(0.0D, 0.0D, 0.0D,
    			 0.0D, 0.0D, 0.0D,
    			 0.0D, 0.0D, 0.0D);
    }

    /**
     * Negates the value of this matrix: this = -this.
     */
    public final void negate() {
        
    	this.set(-this.m00, -this.m01, -this.m02,
    			 -this.m10, -this.m11, -this.m12,
    			 -this.m20, -this.m21, -this.m22);
    }

    /**
     * Sets the value of this matrix equal to the negation of of the Matrix3d
     * parameter.
     * @param m1 The source matrix
     */
    public final void negate(Matrix3d m1) {
	
    	this.set(m1);
    	this.negate();
    }

    /**
     * Transform the vector vec using this Matrix3d and place the
     * result back into vec.
     * @param vec the double precision vector to be transformed
     */
    public final void transform(Tuple3d t)  {
	
    	this.transform(t, t);
    }

    /**
     * Transform the vector vec using this Matrix3d and place the
     * result into vecOut.
     * @paramt the double precision vector to be transformed
     * @param result the vector into which the transformed values are placed
     */
    public final void transform(Tuple3d t, Tuple3d result) {
	
    	// alias-safe
    	result.set(this.m00 * t.x + this.m01 * t.y + this.m02 * t.z,
    			   this.m10 * t.x + this.m11 * t.y + this.m12 * t.z,
    			   this.m20 * t.x + this.m21 * t.y + this.m22 * t.z);
    }

    /**
     * Sets 9 values	
     */
    private void set(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
		
    	this.m00 = m00;
    	this.m01 = m01;
    	this.m02 = m02;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
    }

    /**
     * Performs SVD on this matrix and gets scale and rotation.
     * Rotation is placed into rot.
     * @param rot the rotation factor.
     * @return scale factor
     */
    private double SVD(Matrix3d rot) {
	
    	// this is a simple svd.
    	// Not complete but fast and reasonable.

		//
		// SVD scale factors(squared) are the 3 roots of
		// 
		//     | xI - M*MT | = 0.
		// 
		// This will be expanded as follows
		// 
		// x^3 - A x^2 + B x - C = 0
		// 
		// where A, B, C can be denoted by 3 roots x0, x1, x2.
		//
		// A = (x0+x1+x2), B = (x0x1+x1x2+x2x0), C = x0x1x2.
		//
		// An avarage of x0,x1,x2 is needed here. C^(1/3) is a cross product
		// normalization factor.
		// So here, I use A/3. Note that x should be sqrt'ed for the
		// actual factor.

    	double s = Math.sqrt((this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20 + 
    						  this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21 +
    						  this.m02 * this.m02 + this.m12 * this.m12 + this.m22 * this.m22) / 3.0D);

    	if(rot != null) {
        
    		// zero-div may occur.
	        double n = 1.0D / Math.sqrt(this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20);
	        rot.m00 = this.m00 * n;
	        rot.m10 = this.m10 * n;
	        rot.m20 = this.m20 * n;
	
	        n = 1.0D / Math.sqrt(this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21);
	        rot.m01 = this.m01 * n;
	        rot.m11 = this.m11 * n;
	        rot.m21 = this.m21 * n;
	
	        n = 1.0D / Math.sqrt(this.m02 * this.m02 + this.m12 * this.m12 + this.m22 * this.m22);
	        rot.m02 = this.m02 * n;
	        rot.m12 = this.m12 * n;
	        rot.m22 = this.m22 * n;
    	}

    	return s;
    }

    private void setFromQuat(double x, double y, double z, double w) {
	
    	double n = x * x + y * y + z * z + w * w;
    	double s = (n > 0.0D) ? (2.0D / n) : 0.0D;

    	double xs = x * s;
    	double ys = y * s;
    	double zs = z * s;
    	double wx = w * xs;
    	double wy = w * ys;
    	double wz = w * zs;
    	double xx = x * xs;
    	double xy = x * ys;
    	double xz = x * zs;
    	double yy = y * ys;
    	double yz = y * zs;
    	double zz = z * zs;

    	this.set((1.0D - (yy + zz)), (xy - wz), (xz + wy),
    			 (xy + wz), (1.0D - (xx + zz)), (yz - wx),
    			 (xz - wy), (yz + wx), (1.0D - (xx + yy)));
    }

    private void setFromAxisAngle(double x, double y, double z, double angle) {
	
    	// Taken from Rick's which is taken from Wertz. pg. 412
    	// Bug Fixed and changed into right-handed by hiranabe
    	double n = Math.sqrt(x * x + y * y + z * z);
	
    	// zero-div may occur
    	n = 1 / n;
    	x *= n;
    	y *= n;
    	z *= n;
    	
    	double c = Math.cos(angle);
    	double s = Math.sin(angle);
    	double omc = 1.0D - c;
	
    	this.m00 = c + x * x * omc;
    	this.m11 = c + y * y * omc;
    	this.m22 = c + z * z * omc;

    	double tmp1 = x * y * omc;
    	double tmp2 = z * s;
	
    	this.m01 = tmp1 - tmp2;
    	this.m10 = tmp1 + tmp2;

    	tmp1 = x * z * omc;
    	tmp2 = y * s;
    	
    	this.m02 = tmp1 + tmp2;
    	this.m20 = tmp1 - tmp2;

    	tmp1 = y * z * omc;
    	tmp2 = x * s;
	
    	this.m12 = tmp1 - tmp2;
    	this.m21 = tmp1 + tmp2;
    }
}
