package net.laraifox.lib.math;

public class Matrix2f {
	private float[][] data;

	public Matrix2f() {
		this.data = new float[2][2];
	}

	public Matrix2f(Matrix2f matrix) {
		this.data = matrix.data;
	}

	public static Matrix2f initializeIdentityMatrix() {
		Matrix2f result = new Matrix2f();

		result.setDataAt(0, 0, 1);
		result.setDataAt(0, 1, 0);

		result.setDataAt(1, 0, 0);
		result.setDataAt(1, 1, 1);

		return result;
	}

	public static Matrix2f initializeRotationMatrix(float theta) {
		Matrix2f result = new Matrix2f();

		theta = (float) Math.toRadians(theta);

		result.setDataAt(0, 0, (float) Math.cos(theta));
		result.setDataAt(0, 1, (float) Math.sin(theta));

		result.setDataAt(1, 0, (float) -Math.sin(theta));
		result.setDataAt(1, 1, (float) Math.cos(theta));

		return result;
	}

	public Matrix2f multiply(Matrix2f matrix) {
		Matrix2f result = new Matrix2f();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 1; j++) {
				result.setDataAt(i, j, data[i][0] * matrix.getDataAt(0, j) + data[i][1] * matrix.getDataAt(1, j));
			}
		}

		return result;
	}

	public Vector2f multiply(Vector2f v) {
		Vector2f result = new Vector2f();
		result.setX(data[0][0] * v.getX() + data[0][1] * v.getY());
		result.setY(data[1][0] * v.getX() + data[1][1] * v.getY());

		return result;
	}

	public Matrix2f scale(float scalar) {
		Matrix2f result = new Matrix2f();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				result.setDataAt(i, j, data[i][j] * scalar);
			}
		}

		return result;
	}

	public Matrix2f transpose() {
		Matrix2f result = new Matrix2f();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				result.setDataAt(i, j, data[j][i]);
			}
		}

		return result;
	}

	public float[][] getData() {
		float[][] result = new float[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
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
		return new String("[ " + data[0][0] + ", " + data[0][1] + " ]\n" + //
				"[ " + data[1][0] + ", " + data[1][1] + " ]");
	}
}
