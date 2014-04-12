package net.laraifox.lib.math;

import net.laraifox.lib.math.Vector3f;

public class Matrix3f {
	private float[][] data;

	public Matrix3f() {
		this.data = new float[3][3];
	}

	public Matrix3f(Matrix3f matrix) {
		this.data = matrix.data;
	}

	public static Matrix3f initializeIdentityMatrix() {
		Matrix3f result = new Matrix3f();

		result.setDataAt(0, 0, 1);
		result.setDataAt(0, 1, 0);
		result.setDataAt(0, 2, 0);

		result.setDataAt(1, 0, 0);
		result.setDataAt(1, 1, 1);
		result.setDataAt(1, 2, 0);

		result.setDataAt(2, 0, 0);
		result.setDataAt(2, 1, 0);
		result.setDataAt(2, 2, 1);

		return result;
	}

	public static Matrix3f initializeRotationMatrix(float rx, float ry, float rz) {
		Matrix3f rxMatrix = new Matrix3f();
		Matrix3f ryMatrix = new Matrix3f();
		Matrix3f rzMatrix = new Matrix3f();

		rx = (float) Math.toRadians(rx);
		ry = (float) Math.toRadians(ry);
		rz = (float) Math.toRadians(rz);

		rxMatrix.setDataAt(0, 0, 1);
		rxMatrix.setDataAt(0, 1, 0);
		rxMatrix.setDataAt(0, 2, 0);
		rxMatrix.setDataAt(1, 0, 0);
		rxMatrix.setDataAt(1, 1, (float) Math.cos(rx));
		rxMatrix.setDataAt(1, 2, (float) Math.sin(rx));
		rxMatrix.setDataAt(2, 0, 0);
		rxMatrix.setDataAt(2, 1, (float) -Math.sin(rx));
		rxMatrix.setDataAt(2, 2, (float) Math.cos(rx));

		ryMatrix.setDataAt(0, 0, (float) Math.cos(ry));
		ryMatrix.setDataAt(0, 1, 0);
		ryMatrix.setDataAt(0, 2, (float) -Math.sin(ry));
		ryMatrix.setDataAt(1, 0, 0);
		ryMatrix.setDataAt(1, 1, 1);
		ryMatrix.setDataAt(1, 2, 0);
		ryMatrix.setDataAt(2, 0, (float) Math.sin(ry));
		ryMatrix.setDataAt(2, 1, 0);
		ryMatrix.setDataAt(2, 2, (float) Math.cos(ry));

		rzMatrix.setDataAt(0, 0, (float) Math.cos(rz));
		rzMatrix.setDataAt(0, 1, (float) Math.sin(rz));
		rzMatrix.setDataAt(0, 2, 0);
		rzMatrix.setDataAt(1, 0, (float) -Math.sin(rz));
		rzMatrix.setDataAt(1, 1, (float) Math.cos(rz));
		rzMatrix.setDataAt(1, 2, 0);
		rzMatrix.setDataAt(2, 0, 0);
		rzMatrix.setDataAt(2, 1, 0);
		rzMatrix.setDataAt(2, 2, 1);

		return rzMatrix.multiply(ryMatrix.multiply(rxMatrix));
	}

	public static Matrix3f initializeRotationMatrix(Vector3f axis, float theta) {
		Matrix3f result = new Matrix3f();

		float s = (float) Math.sin(theta);
		float c = (float) Math.cos(theta);
		float C = 1.0f - c;

		float x = axis.getX();
		float y = axis.getY();
		float z = axis.getZ();

		result.setDataAt(0, 0, x * x * C + c);
		result.setDataAt(0, 1, x * y * C - z * s);
		result.setDataAt(0, 2, x * z * C + y * s);

		result.setDataAt(1, 0, y * x * C + z * s);
		result.setDataAt(1, 1, y * y * C + c);
		result.setDataAt(1, 2, y * z * C - x * s);

		result.setDataAt(2, 0, z * x * C - y * s);
		result.setDataAt(2, 1, z * y * C + x * s);
		result.setDataAt(2, 2, z * z * C + c);

		return result;
	}

	public Matrix3f multiply(Matrix3f matrix) {
		Matrix3f result = new Matrix3f();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result.setDataAt(i, j, data[i][0] * matrix.getDataAt(0, j) + data[i][1] * matrix.getDataAt(1, j) + data[i][2] * matrix.getDataAt(2, j));
			}
		}

		return result;
	}

	public Vector3f multiply(Vector3f v) {
		Vector3f result = new Vector3f();
		result.setX(data[0][0] * v.getX() + data[0][1] * v.getY() + data[0][2] * v.getZ());
		result.setY(data[1][0] * v.getX() + data[1][1] * v.getY() + data[1][2] * v.getZ());
		result.setZ(data[2][0] * v.getX() + data[2][1] * v.getY() + data[2][2] * v.getZ());

		return result;
	}

	public Matrix3f scale(float scalar) {
		Matrix3f result = new Matrix3f();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result.setDataAt(i, j, data[i][j] * scalar);
			}
		}

		return result;
	}
	
	public Matrix3f transpose() {
		Matrix3f result = new Matrix3f();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result.setDataAt(i, j, data[j][i]);
			}
		}
		
		return result;
	}

	public float[][] getData() {
		float[][] result = new float[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = data[i][j];
			}
		}

		return result;
	}

	public float getDataAt(int x, int y) {
		return data[x][y];
	}

	public void setData(float[][] data) {
		this.data = data;
	}

	public void setDataAt(int x, int y, float value) {
		this.data[x][y] = value;
	}

	public String toString() {
		return new String("[ " + data[0][0] + ", " + data[0][1] + ", " + data[0][2] + " ]\n" + //
				"[ " + data[1][0] + ", " + data[1][1] + ", " + data[1][2] + " ]\n" + //
				"[ " + data[2][0] + ", " + data[2][1] + ", " + data[2][2] + " ]");
	}
}
