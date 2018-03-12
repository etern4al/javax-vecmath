package javax.vecmath.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.GMatrix;
import javax.vecmath.GVector;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;

import org.junit.jupiter.api.Test;

class VecmathTest {

	private static String NL = System.getProperty("line.separator"); 
    private static float epsilon = 1.0e-5f;
    
    private static boolean checkSVD(GMatrix m, GMatrix u, GMatrix w, GMatrix v) {
	
    	boolean ok = true;
    	
    	for(int i = 0; i < m.getNumRow(); i++) {
	    
    		for(int j = 0; j < m.getNumCol(); j++) {
		
    			double sum = 0.0;
    			
    			for(int k = 0; k < m.getNumCol(); k++) {
		    
    				sum += u.getElement(i, k) * w.getElement(k, k) * v.getElement(j, k);
    			}
		
    			/* check if SVD is OK */
    			if(epsilon < Math.abs(m.getElement(i, j) - sum)) {
		    
    				System.out.println(String.format("(SVD)ij = %f != a[%d,%d] = %f", sum, i, j, m.getElement(i, j)));
    				ok = false;
    			}
    		}
    	}
	
    	if(!ok) {
	    
    		System.out.print("[W] = ");
    		System.out.println(w);
    		System.out.print("[U] = ");
    		System.out.println(u);
    		System.out.print("[V] = ");
    		System.out.println(v);
    	}
	
    	return ok;
    }
    
    private static boolean checkLUD(GMatrix m, GMatrix LU, GVector permutation) {
	
    	int n = m.getNumCol();
    	boolean ok = true;
	
    	for(int i = 0; i < n; i++) {
    		
    		for(int j = 0; j < n; j++) {
    			
    			double aij = 0.0;
    			int min = i < j ? i : j;
		
    			for(int k = 0; k <= min; k++) {
		    
    				if(i != k) {
			
    					aij += LU.getElement(i, k)*LU.getElement(k, j);
    					
    				} else {
			
    					aij += LU.getElement(k, j);
    				}
    			}
		
    			if(Math.abs(aij - m.getElement((int)permutation.getElement(i),j)) > epsilon) {
		    
    				System.out.println(String.format("a[%d,%d] = %f(LU)ij ! = %f", i, j, aij, m.getElement((int)permutation.getElement(i), j)));
    				ok = false;
    			}
    		}
    	}
    	
    	return ok;
    }
    
    private static boolean equals(double m1, double m2) {
	
    	return Math.abs(m1 - m2) < (double)epsilon;
    }
    
    private static boolean equals(Matrix3d m1, Matrix3d m2) {
	
    	return m1.epsilonEquals(m2, (double)epsilon);
    }
    
    private static boolean equals(Matrix4d m1, Matrix4d m2) {
	
    	return m1.epsilonEquals(m2, (double)epsilon);
    }
    
    private static boolean equals(Tuple4d m1, Tuple4d m2) {
	
    	return m1.epsilonEquals(m2, (double)epsilon);
    }
    
    private static boolean equals(Tuple3d m1, Tuple3d m2) {
	
    	return m1.epsilonEquals(m2, (double)epsilon);
    }
    
    private static boolean equals(GMatrix m1, GMatrix m2) {
	
    	return m1.epsilonEquals(m2, (double)epsilon);
    }
    
    private static boolean equals(GVector v1, GVector v2) {
	
    	return v1.epsilonEquals(v2, (double)epsilon);
    }
    
    private static boolean equals(Tuple3f m1, Tuple3f m2) {
	
    	return m1.epsilonEquals(m2, epsilon);
    }
    
    private static boolean equals(AxisAngle4d a1, AxisAngle4d a2) {
	
    	if(0 < a1.x * a2.x + a1.y * a2.y + a1.z * a2.z) {  // same direction
	    
    		return VecmathTest.equals(a1.y * a2.z - a1.z * a2.y, 0) &&
    			   VecmathTest.equals(a1.z * a2.x - a1.x * a2.z, 0) &&
    			   VecmathTest.equals(a1.x * a2.y - a1.y * a2.x, 0) &&
    			   VecmathTest.equals(a1.angle, a2.angle);
	
    	} else {
	    
    		return VecmathTest.equals(a1.y*a2.z - a1.z*a2.y, 0) &&
	    		   VecmathTest.equals(a1.z*a2.x - a1.x*a2.z, 0) &&
		           VecmathTest.equals(a1.x*a2.y - a1.y*a2.x, 0) &&
		   
		           (VecmathTest.equals(a1.angle, -a2.angle) || 
				    VecmathTest.equals(a1.angle + a2.angle, 2*Math.PI) || 
		            VecmathTest.equals(a1.angle + a2.angle, -2*Math.PI));
    	}
    }
    
    private static final void Mat3dQuatAxisAngle(AxisAngle4d a1) {
    	
		Matrix3d m1 = new Matrix3d();
		Matrix3d m2 = new Matrix3d();
		AxisAngle4d a2 = new AxisAngle4d();
		Quat4d q1 = new Quat4d();
		Quat4d q2 = new Quat4d();
	
		// Axis <-> Quat
		q1.set(a1);
		a2.set(q1);
		// a1.v parallels to a2.v 
		assertTrue(equals(a1, a2));
		q2 = new Quat4d();
		q2.set(a2);
		assertTrue(equals(q1, q2));
	
		// Quat <-> Mat
		q1.set(a1);
		m1.set(q1);
		q2.set(m1);
		assertTrue(equals(q1, q2));
		m2.set(q2);
		assertTrue(equals(m1, m2));
	
		// Mat <-> AxisAngle
		m1.set(a1);
		a2.set(m1);
		assertTrue(equals(a1, a2));
		m2.set(a1);
		assertTrue(equals(m1, m2));
		a1.x *= 2;
		a1.y *= 2;
		a1.z *= 2;
		m2.set(a1);
		a1.x = -a1.x;
		a1.y = -a1.y;
		a1.z = -a1.z;
		a1.angle = -a1.angle;
		m2.set(a1);
		assertTrue(equals(m1, m2));
	}
    
	private static final void Mat4dQuatAxisAngle(AxisAngle4d a1) {
		
		Matrix4d m1 = new Matrix4d();
		Matrix4d m2 = new Matrix4d();
		AxisAngle4d a2 = new AxisAngle4d();
		Quat4d q1 = new Quat4d();
		Quat4d q2 = new Quat4d();
	
		// Axis <-> Quat
		q1.set(a1);
		a2.set(q1);
		// a1.v parallels to a2.v 
		assertTrue(equals(a1, a2));
		q2 = new Quat4d();
		q2.set(a2);
		assertTrue(equals(q1, q2));
	
		// Quat <-> Mat
		q1.set(a1);
		m1.set(q1);
		q2.set(m1);
		assertTrue(equals(q1, q2));
		m2.set(q2);
		assertTrue(equals(m1, m2));
	
		// Mat <-> AxisAngle
		m1.set(a1);
		a2.set(m1);
		assertTrue(equals(a1, a2));
		m2.set(a1);
		assertTrue(equals(m1, m2));
		a1.x *= 2;
		a1.y *= 2;
		a1.z *= 2;
		m2.set(a1);
		a1.x = -a1.x;
		a1.y = -a1.y;
		a1.z = -a1.z;
		a1.angle = -a1.angle;
		m2.set(a1);
		assertTrue(equals(m1, m2));
    }
    
    @Test
    void Vector3dTest() {
	
    	Vector3d zeroVector = new Vector3d();
    	Vector3d v1 = new Vector3d(2, 3, 4);
    	Vector3d v2 = new Vector3d(2, 5, -8);

    	Vector3d v3 = new Vector3d();
    	v3.cross(v1, v2);

    	// check cross and dot.
    	assertTrue(equals(v3.dot(v1), 0));
    	assertTrue(equals(v3.dot(v2), 0));

    	// check alias-safe
    	v1.cross(v1, v2);
    	assertTrue(equals(v1, new Vector3d(-44, 24, 4)));

    	// check length
    	assertTrue(equals(v2.lengthSquared(), 93));
    	assertTrue(equals(v2.length(), Math.sqrt(93)));

    	// check normalize
    	v1.set(v2);
    	v2.normalize();
    	assertTrue(equals(v2.length(), 1));
    	v1.cross(v2, v1);
    	assertTrue(equals(v1, zeroVector));

    	// check Angle
    	v1.set(1, 2, 3);
    	v2.set(-1, -6, -3);
    	double ang = v1.angle(v2);
    	assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));

    	// check Angle (0)
    	v1.set(v2);
    	ang = v1.angle(v2);
    	assertTrue(equals(ang, 0));
    	assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));

    	// check small Angle
    	v1.set(1, 2, 3);
    	v2.set(1, 2, 3.00001);
		ang = v1.angle(v2);
		assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));

		// check large Angle
		v1.set(1, 2, 3);
		v2.set(-1, -2, -3.00001);
		ang = v1.angle(v2);
		assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
    }

    @Test
    void Vector3fTest() {
	
    	Vector3f zeroVector = new Vector3f();
    	Vector3f v1 = new Vector3f(2, 3, 4);
    	Vector3f v2 = new Vector3f(2, 5, -8);

    	Vector3f v3 = new Vector3f();
    	v3.cross(v1, v2);

    	// check cross and dot.
    	assertTrue(equals(v3.dot(v1), 0));
    	assertTrue(equals(v3.dot(v2), 0));

    	// check alias-safe
    	v1.cross(v1, v2);
    	assertTrue(equals(v1, new Vector3f(-44, 24, 4)));

    	// check length
    	assertTrue(equals(v2.lengthSquared(), 93));
    	assertTrue(equals(v2.length(), Math.sqrt(93)));

    	// check normalize
    	v1.set(v2);
    	v2.normalize();
    	assertTrue(equals(v2.length(), 1));
    	v1.cross(v2,v1);
    	assertTrue(equals(v1, zeroVector));

    	// check Angle
    	v1.set(1, 2, 3);
    	v2.set(-1, -6, -3);
    	double ang = v1.angle(v2);
    	assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));

    	// check Angle (0)
    	v1.set(v2);
    	ang = v1.angle(v2);
    	assertTrue(equals(ang, 0));
    	assertTrue(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
    }

    @Test
    void Matrix3dTest() {
	
    	Matrix3d O = new Matrix3d();
    	Matrix3d I = new Matrix3d();
    	I.setIdentity();
    	Matrix3d m1 = new Matrix3d();
    	Matrix3d m2 = new Matrix3d();
    	double [] v = {2, 1, 4, 1, -2, 3, -3, -1, 1};

    	// check get/set
    	for(int i = 0; i < 3; i++) {
    		
    		for(int j = 0; j < 3; j++) {
    			
    			double val = i * 2 * j + 3;
    			m1.setElement(i, j, val);
    			assertTrue(equals(m1.getElement(i, j), val));
    		}
    	}

    	// check mul with O, I
    	m1.set(v);
    	m2 = new Matrix3d(m1);
    	m2.mul(O);
    	assertTrue(equals(m2, O));
    	m2.mul(m1, I);
    	assertTrue(equals(m2, m1));

    	// check determinant
    	assertTrue(equals(m1.determinant(), -36));

    	// check negate, add
    	m2.negate(m1);
    	m2.add(m1);
    	assertTrue(equals(m2, O));

    	// check mul, sub
    	m2.negate(m1);
    	Matrix3d m3 = new Matrix3d(m1);
    	m3.sub(m2);
    	m3.mul(0.5D);
    	assertTrue(equals(m1, m3));
	
    	// check invert
    	m3.invert(m2);
    	m3.mul(m2);
    	assertTrue(equals(m3, I));

    	// translate
    	Point3d p1 = new Point3d(1, 2, 3);
    	
    	// rotZ
    	// rotate (1,0,0) 30degree abount z axis -> (cos 30,sin 30,0)
    	p1.set(1, 0, 0);
    	m1.rotZ(Math.PI / 6);
    	m1.transform(p1);
    	assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 6), Math.sin(Math.PI / 6), 0)));

    	// rotY
    	// rotate() (1,0,0) 60degree about y axis -> (cos 60,0,-sin 60)
    	p1.set(1,0,0);
    	m1.rotY(Math.PI / 3);
    	m1.transform(p1);
    	assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));

    	// rot around arbitary axis
    	// rotate() (1,0,0) 60degree about y axis -> (cos 60,0,-sin 60)
    	AxisAngle4d a1 = new AxisAngle4d(0, 1, 0, Math.PI / 3);
    	p1.set(1, 0, 0);
    	m1.set(a1);
    	m1.transform(p1, p1);
    	assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));

    	// use quat.
    	Quat4d q1 = new Quat4d();
    	p1.set(1, 0, 0);
		q1.set(a1);
		m2.set(q1);
		assertTrue(equals(m1, m2));
		m2.transform(p1, p1);
		assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));

		// Mat <-> Quat <-> Axis
		a1.set(1,2,-3,Math.PI / 3);
		Mat3dQuatAxisAngle(a1);

		// Mat <-> Quat <-> Axis (near PI case)
		a1.set(1,2,3,Math.PI);
		Mat3dQuatAxisAngle(a1);
		
		// Mat <-> Quat <-> Axis (near PI, X major case )
		a1.set(1, 0.1D, 0.1D, Math.PI);
		Mat3dQuatAxisAngle(a1);
	
		// Mat <-> Quat <-> Axis (near PI, Y major case )
		a1.set(0.1D, 1, 0.1D, Math.PI);
		Mat3dQuatAxisAngle(a1);
	
		// Mat <-> Quat <-> Axis (near PI, Z major case )
		a1.set(0.1D, 0.1D, 1, Math.PI);
		Mat3dQuatAxisAngle(a1);

		// isometric view 3 times 2/3 turn
		a1.set(1, 1, 1, 2 * Math.PI / 3);
		m1.set(a1);
		p1.set(1, 0, 0);
		m1.transform(p1);
	
		assertTrue(equals(p1, new Point3d(0, 1, 0)));
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(0, 0, 1)));
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(1, 0, 0)));

		// check normalize, normalizeCP
		m1.set(a1);
		assertTrue(equals(m1.determinant(), 1));
		assertTrue(equals(m1.getScale(), 1));
		m2.set(a1);
		m2.normalize();
		assertTrue(equals(m1, m2));
		m2.set(a1);
		m2.normalizeCP();
		assertTrue(equals(m1, m2));
		double scale = 3.0D;
		m2.rotZ(-Math.PI / 4);
		m2.mul(scale);
		assertTrue(equals(m2.determinant(), scale * scale * scale));
		assertTrue(equals(m2.getScale(), scale));
		m2.normalize();
		assertTrue(equals(m2.determinant(), 1));
		assertTrue(equals(m2.getScale(), 1));
		m2.rotX(Math.PI / 3);
		m2.mul(scale);
		assertTrue(equals(m2.determinant(), scale * scale * scale));
		assertTrue(equals(m2.getScale(), scale));
		m2.normalizeCP();
		assertTrue(equals(m2.determinant(), 1));
		assertTrue(equals(m2.getScale(), 1));

		// transpose and inverse
		m1.set(a1);
		m2.invert(m1);
		m1.transpose();
		assertTrue(equals(m1, m2));
    }

    @Test
    void Matrix4dTest() {
    	
		Matrix4d O = new Matrix4d();
		Matrix4d I = new Matrix4d();
		I.setIdentity();
		Matrix4d m1 = new Matrix4d();
		Matrix4d m2 = new Matrix4d();
	
		// check get/set
		for(int i = 0; i < 4; i++) {
			
		    for(int j = 0; j < 4; j++) {
		    	
		    	double val = i * 2 * j + 3;
		    	m1.setElement(i, j, val);
		    	assertTrue(equals(m1.getElement(i, j), val));
		    }
			
		}

		// check mul with O, I
		m1 = new Matrix4d(2, 1, 4, 1, -2, 3, -3, 1, -1, 1, 2, 2, 0, 8, 1, -10);
		m2 = new Matrix4d(m1);
		m2.mul(O);
		assertTrue(equals(m2, O), "O = m2 x O");
		m2.mul(m1, I);
		assertTrue(equals(m2, m1), "m2 = m1 x I");
	
		// check negate, add
		m2.negate(m1);
		m2.add(m1);
		assertTrue(equals(m2, O));
		double v[] = {5, 1, 4, 0, 2, 3, -4, -1, 2, 3, -4, -1, 1, 1, 1, 1};
		m2.set(v);
		m2.negate(m1);
		Matrix4d m3 = new Matrix4d(m1);
		m3.sub(m2);
		m3.mul(0.5);
		assertTrue(equals(m1, m3));

		// check invert
		m2 = new Matrix4d(0.5D, 1, 4, 1, -2, 3, -4, -1, 1, 9, 100, 2, -20, 2, 1, 9);
		m3.invert(m2);
		m3.mul(m2);
		assertTrue(equals(m3, I));

		// translate
		m1 = new Matrix4d(-1, 2, 0, 3, -1, 1, -3, -1, 1, 2, 1, 1, 0, 0, 0, 1);
		Point3d p1 = new Point3d(1, 2, 3);
		Vector3d v1 = new Vector3d(1, 2, 3);
		Vector4d V2 = new Vector4d(2, -1, -4, 1);
		assertTrue(m1.toString().equals("[" + NL + "  [-1.0	2.0	0.0	3.0]" + NL + "  [-1.0	1.0	-3.0	-1.0]" + NL + "  [1.0	2.0	1.0	1.0]" + NL + "  [0.0	0.0	0.0	1.0] ]"));
			
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(6, -9, 9)));
		m1.transform(V2, V2);
		assertTrue(equals(V2, new Vector4d(-1, 8, -3, 1)));

		// rotZ
		// rotate (1,0,0) 30degree about z axis -> (cos 30,sin 30,0)
		p1.set(1, 0, 0);
		m1.rotZ(Math.PI / 6);
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 6), Math.sin(Math.PI / 6), 0)));
	
		// rotY
		// rotate() (1,0,0) 60degree about y axis -> (cos 60,0,-sin 60)
		p1.set(1, 0, 0);
		m1.rotY(Math.PI / 3);
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));
		// System.out.println("10");
	
		// rot around arbitary axis
		// rotate() (1,0,0) 60degree about y axis -> (cos 60,0,-sin 60)
		AxisAngle4d a1 = new AxisAngle4d(0, 1, 0, Math.PI / 3);
		p1.set(1, 0, 0);
		m1.set(a1);
		m1.transform(p1, p1);
		assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));
	
		// use quat.
		Quat4d q1 = new Quat4d();
		p1.set(1, 0, 0);
		q1.set(a1);
		m2.set(q1);
		assertTrue(equals(m1, m2));
		m2.transform(p1, p1);
		assertTrue(equals(p1, new Point3d(Math.cos(Math.PI / 3), 0, -Math.sin(Math.PI / 3))));
	
		// Mat <-> Quat <-> Axis
		a1.set(1, 2, -3, Math.PI / 3);
		Mat4dQuatAxisAngle(a1);
	
		// Mat <-> Quat <-> Axis (near PI case)
		a1.set(1, 2, 3, Math.PI);
		Mat4dQuatAxisAngle(a1);
		
		// Mat <-> Quat <-> Axis (near PI, X major case )
		a1.set(1, 0.1D, 0.1D, Math.PI);
		Mat4dQuatAxisAngle(a1);
		
		// Mat <-> Quat <-> Axis (near PI, Y major case )
		a1.set(0.1D, 1, 0.1D, Math.PI);
		Mat4dQuatAxisAngle(a1);
		
		// Mat <-> Quat <-> Axis (near PI, Z major case )
		a1.set(.1,.1,1,Math.PI);
		Mat4dQuatAxisAngle(a1);
	
		// isometric view 3 times 2/3 turn
		a1.set(1, 1, 1, 2 * Math.PI / 3);
		m1.set(a1);
		p1.set(1, 0, 0);
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(0, 1, 0)));
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(0, 0, 1)));
		m1.transform(p1);
		assertTrue(equals(p1, new Point3d(1, 0, 0)));
	
		// check getScale
		m1.set(a1);
		assertTrue(equals(m1.determinant(), 1));
		assertTrue(equals(m1.getScale(), 1));
		m2.set(a1);
	
		// transpose and inverse
		m1.set(a1);
		m2.invert(m1);
		m1.transpose();
		assertTrue(equals(m1, m2));
		
		// rot, scale, trans
		Matrix3d n1 = new Matrix3d();
		n1.set(a1);
		Matrix3d n2 = new Matrix3d();
		v1.set(2, -1, -1);
		m1.set(n1, v1, 0.4D);
		m2.set(n1, v1, 0.4D);
		Vector3d v2 = new Vector3d();
		double s = m1.get(n2, v2);
		assertTrue(equals(n1, n2));
		assertTrue(equals(s, 0.4));
		assertTrue(equals(v1, v2));
		assertTrue(equals(m1, m2)); // not modified
    }
    
    @Test
    void GMatrixTest() {
    	
		GMatrix I44 = new GMatrix(4, 4); // Identity 4x4
		GMatrix O44 = new GMatrix(4, 4);
		O44.setZero(); // O 4x4
		GMatrix O34 = new GMatrix(3, 4);
		O34.setZero(); // O 3x4
		GMatrix m1 = new GMatrix(3, 4);
		GMatrix m2 = new GMatrix(3, 4);
		Matrix3d mm1 = new Matrix3d();
		Matrix3d mm2 = new Matrix3d();
	
		// get/setElement
		for(int i = 0; i < 3; i++) {
			
			for(int j = 0; j < 4; j++) {
				
				m1.setElement(i, j, (i + 1) * (j + 2));
				
				if(j < 3) {
					
					mm1.setElement(i, j, (i + 1) * (j + 2));
				}
			}
		}
		    
		for(int i = 0; i < 3; i++) {
		    
			for(int j = 0; j < 4; j++) {
			
				assertTrue(equals(m1.getElement(i, j), (i + 1) * (j + 2)));
		    }
		}
	
		m1.get(mm2);
		assertTrue(equals(mm1, mm2));
	
		// mul with I,O
		m2.mul(m1, I44);
		assertTrue(equals(m1, m2));
		m2.mul(m1, O44);
		assertTrue(equals(O34, m2));
	
		// LUD
		Matrix4d mm3 = new Matrix4d(1, 2, 3, 4, -2, 3, -1, 3, -1, -2, -4, 1, 1, 1, -1, -2);
		Matrix4d mm4 = new Matrix4d();
		Matrix4d mm5 = new Matrix4d();
		mm5.set(mm3);
	
		// setSize, invert
		m1.setSize(4, 4);
		m2.setSize(4, 4);
		m1.set(mm3);
		assertTrue(m1.toString().equals("[" + NL + "  [1.0	2.0	3.0	4.0]" + NL + "  [-2.0	3.0	-1.0	3.0]" + NL + "  [-1.0	-2.0	-4.0	1.0]" + NL + "  [1.0	1.0	-1.0	-2.0] ]"));
		m2.set(m1);
		m1.invert();
		mm3.invert();
		mm5.mul(mm3);
		assertTrue(equals(mm5, new Matrix4d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)));
		m1.get(mm4);
		assertTrue(equals(mm3, mm4));
		m1.mul(m2);
		assertTrue(equals(m1, I44));
	
		// LUD
		Matrix4d mm6 = new Matrix4d(1, 2, 3, 4, -2, 3, -1, 3, -1, -2, -4, 1, 1, 1, -1, -2);
		Vector4d vv1 = new Vector4d(1, -1, -1, 2);
		Vector4d vv2 = new Vector4d();
		Vector4d vv3 = new Vector4d(4, 2, 7, -3);
		mm6.transform(vv1, vv2);
		assertTrue(equals(vv2, vv3));
	
		m1.set(mm6);
		GVector x = new GVector(4);
		GVector b = new GVector(4);
		x.set(vv1); // (1,-1,-1,2)
		b.set(vv3); // (4,2,7,-3)
		GVector mx = new GVector(4);
		mx.mul(m1, x); // M*x = (4,2,7,-3)
		assertTrue(equals(mx, b));
	
		GVector p = new GVector(4);
		m1.LUD(m2, p);
		assertTrue(checkLUD(m1, m2, p));
		GVector xx = new GVector(4);
		xx.LUDBackSolve(m2, b, p);
		assertTrue(equals(xx, x));
		
		GMatrix u = new GMatrix(m1.getNumRow(), m1.getNumRow());
		GMatrix w = new GMatrix(m1.getNumRow(), m1.getNumCol());
		GMatrix v = new GMatrix(m1.getNumCol(), m1.getNumCol());
		int rank = m1.SVD(u, w, v);
		assertTrue(rank == 4);
		assertTrue(checkSVD(m1, u, w, v));
		xx.SVDBackSolve(u, w, v, b);
		assertTrue(equals(xx, x));
    }

    @Test
    void SVDTest() {
    	
        double val[] = {
        		
        	1, 2, 3, 4,
            5, 6, 7, 8,
            9, 0, 8, 7,
            6, 5, 4, 3,
            2, 1, 0, 1
        };

        int m = 5;
        int n = 4;

        GMatrix matA = new GMatrix(m, n, val);
        GMatrix matU = new GMatrix(m, m);
        GMatrix matW = new GMatrix(m, n, val);
        GMatrix matV = new GMatrix(n, n);

        GMatrix matTEMP = new GMatrix(m, n);
        matTEMP.mul(matU, matW);
        matV.transpose();
        matTEMP.mul(matV);
        
        assertTrue(equals(matTEMP, matA));
    }
}
