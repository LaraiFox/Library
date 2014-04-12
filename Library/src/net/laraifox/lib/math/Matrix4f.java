package net.laraifox.lib.math;

public class Matrix4f {
	private float[][] data;

	public Matrix4f() {
		this.data = new float[4][4];
	}

	public Matrix4f(Matrix3f matrix) {
		this.data = new float[4][4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				data[i][j] = matrix.getDataAt(i, j);
			}
		}
		data[3][3] = 1.0f;
	}

	public Matrix4f(Matrix4f matrix) {
		this.data = matrix.data;
	}

	public Matrix4f multiply(Matrix4f matrix) {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result.setDataAt(i, j, data[i][0] * matrix.getDataAt(0, j) + data[i][1] * matrix.getDataAt(1, j) + data[i][2] * matrix.getDataAt(2, j)
						+ data[i][3] * matrix.getDataAt(3, j));
			}
		}

		return result;
	}

	public Vector4f multiply(Vector4f vector) {
		Vector4f result = new Vector4f();
		result.setX(data[0][0] * vector.getX() + data[1][0] * vector.getY() + data[2][0] * vector.getZ() + data[3][0] * vector.getW());
		result.setY(data[0][1] * vector.getX() + data[1][1] * vector.getY() + data[2][1] * vector.getZ() + data[3][1] * vector.getW());
		result.setZ(data[0][2] * vector.getX() + data[1][2] * vector.getY() + data[2][2] * vector.getZ() + data[3][2] * vector.getW());
		result.setW(data[0][3] * vector.getX() + data[1][3] * vector.getY() + data[2][3] * vector.getZ() + data[3][3] * vector.getW());

		return result;
	}

	public Matrix4f transpose() {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result.setDataAt(i, j, data[j][i]);
			}
		}

		return result;
	}

	public String toString() {
		return new String("{ [" + data[0][0] + ", " + data[0][1] + ", " + data[0][2] + ", " + data[0][3] + ", " + "]\n" + //
				"  [" + data[1][0] + ", " + data[1][1] + ", " + data[1][2] + ", " + data[1][3] + ", " + "]\n" + //
				"  [" + data[2][0] + ", " + data[2][1] + ", " + data[2][2] + ", " + data[2][3] + ", " + "]\n" + //
				"  [" + data[3][0] + ", " + data[3][1] + ", " + data[3][2] + ", " + data[3][3] + ", " + "] }");
	}

	public float[][] getData() {
		float[][] result = new float[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
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

	public static Matrix4f initializeIdentity() {
		Matrix4f result = new Matrix4f();

		result.data[0][0] = 1;
		result.data[0][1] = 0;
		result.data[0][2] = 0;
		result.data[0][3] = 0;

		result.data[1][0] = 0;
		result.data[1][1] = 1;
		result.data[1][2] = 0;
		result.data[1][3] = 0;

		result.data[2][0] = 0;
		result.data[2][1] = 0;
		result.data[2][2] = 1;
		result.data[2][3] = 0;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 0;
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeTranslation(Vector3f translation) {
		return Matrix4f.initializeTranslation(translation.getX(), translation.getY(), translation.getZ());
	}

	public static Matrix4f initializeTranslation(float x, float y, float z) {
		Matrix4f result = new Matrix4f();

		result.data[0][0] = 1;
		result.data[0][1] = 0;
		result.data[0][2] = 0;
		result.data[0][3] = x;

		result.data[1][0] = 0;
		result.data[1][1] = 1;
		result.data[1][2] = 0;
		result.data[1][3] = y;

		result.data[2][0] = 0;
		result.data[2][1] = 0;
		result.data[2][2] = 1;
		result.data[2][3] = z;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 0;
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeRotation(float x, float y, float z) {
		Matrix4f result = new Matrix4f();

		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);

		// Rotation around the Z axis
		rz.data[0][0] = (float) Math.cos(z);
		rz.data[0][1] = (float) -Math.sin(z);
		rz.data[0][2] = 0;
		rz.data[0][3] = 0;

		rz.data[1][0] = (float) Math.sin(z);
		rz.data[1][1] = (float) Math.cos(z);
		rz.data[1][2] = 0;
		rz.data[1][3] = 0;

		rz.data[2][0] = 0;
		rz.data[2][1] = 0;
		rz.data[2][2] = 1;
		rz.data[2][3] = 0;

		rz.data[3][0] = 0;
		rz.data[3][1] = 0;
		rz.data[3][2] = 0;
		rz.data[3][3] = 1;

		// Rotation around the X axis
		rx.data[0][0] = 1;
		rx.data[0][1] = 0;
		rx.data[0][2] = 0;
		rx.data[0][3] = 0;

		rx.data[1][0] = 0;
		rx.data[1][1] = (float) Math.cos(x);
		rx.data[1][2] = (float) -Math.sin(x);
		rx.data[1][3] = 0;

		rx.data[2][0] = 0;
		rx.data[2][1] = (float) Math.sin(x);
		rx.data[2][2] = (float) Math.cos(x);
		rx.data[2][3] = 0;

		rx.data[3][0] = 0;
		rx.data[3][1] = 0;
		rx.data[3][2] = 0;
		rx.data[3][3] = 1;

		// Rotation around the Y axis
		ry.data[0][0] = (float) Math.cos(y);
		ry.data[0][1] = 0;
		ry.data[0][2] = (float) -Math.sin(y);
		ry.data[0][3] = 0;

		ry.data[1][0] = 0;
		ry.data[1][1] = 1;
		ry.data[1][2] = 0;
		ry.data[1][3] = 0;

		ry.data[2][0] = (float) Math.sin(y);
		ry.data[2][1] = 0;
		ry.data[2][2] = (float) Math.cos(y);
		ry.data[2][3] = 0;

		ry.data[3][0] = 0;
		ry.data[3][1] = 0;
		ry.data[3][2] = 0;
		ry.data[3][3] = 1;

		result = rz.multiply(ry.multiply(rx));

		return result;
	}

	public static Matrix4f initializeRotation(Vector3f forward, Vector3f upward) {
		Vector3f zAxis = Vector3f.normalize(forward);
		Vector3f xAxis = Vector3f.normalize(upward).cross(zAxis);
		Vector3f yAxis = Vector3f.cross(zAxis, xAxis).normalize();

		return Matrix4f.initializeRotation(zAxis, yAxis, xAxis);
	}

	public static Matrix4f initializeRotation(Vector3f forward, Vector3f upward, Vector3f right) {
		Matrix4f result = new Matrix4f();

		result.data[0][0] = right.getX();
		result.data[0][1] = upward.getX();
		result.data[0][2] = forward.getX();
		result.data[0][3] = 0;

		result.data[1][0] = right.getY();
		result.data[1][1] = upward.getY();
		result.data[1][2] = forward.getY();
		result.data[1][3] = 0;

		result.data[2][0] = right.getZ();
		result.data[2][1] = upward.getZ();
		result.data[2][2] = forward.getZ();
		result.data[2][3] = 0;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 0;
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeViewRotation(Vector3f forward, Vector3f upward) {
		Matrix4f result = new Matrix4f();

		Vector3f zAxis = Vector3f.normalize(forward);
		Vector3f xAxis = Vector3f.normalize(upward).cross(zAxis);
		Vector3f yAxis = Vector3f.cross(zAxis, xAxis).normalize();

		result.data[0][0] = xAxis.getX();
		result.data[0][1] = xAxis.getY();
		result.data[0][2] = xAxis.getZ();
		result.data[0][3] = 0;

		result.data[1][0] = yAxis.getX();
		result.data[1][1] = yAxis.getY();
		result.data[1][2] = yAxis.getZ();
		result.data[1][3] = 0;

		result.data[2][0] = zAxis.getX();
		result.data[2][1] = zAxis.getY();
		result.data[2][2] = zAxis.getZ();
		result.data[2][3] = 0;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 0;
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeScale(float x, float y, float z) {
		Matrix4f result = new Matrix4f();

		result.data[0][0] = x;
		result.data[0][1] = 0;
		result.data[0][2] = 0;
		result.data[0][3] = 0;

		result.data[1][0] = 0;
		result.data[1][1] = y;
		result.data[1][2] = 0;
		result.data[1][3] = 0;

		result.data[2][0] = 0;
		result.data[2][1] = 0;
		result.data[2][2] = z;
		result.data[2][3] = 0;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 0;
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeProjection(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = new Matrix4f();

		float xRange = right - left;
		float yRange = top - bottom;
		float zRange = far - near;

		result.data[0][0] = 2.0f / xRange;
		result.data[0][1] = 0;
		result.data[0][2] = 0;
		result.data[0][3] = 0;

		result.data[1][0] = 0;
		result.data[1][1] = 2.0f / yRange;
		result.data[1][2] = 0;
		result.data[1][3] = 0;

		result.data[2][0] = 0;
		result.data[2][1] = 0;
		result.data[2][2] = -2.0f / zRange;
		result.data[2][3] = 0;

		result.data[3][0] = -((right + left) / xRange);
		result.data[3][1] = -((top + bottom) / yRange);
		result.data[3][2] = -((far + near) / zRange);
		result.data[3][3] = 1;

		return result;
	}

	public static Matrix4f initializeProjection(float fov, float width, float height, float zNear, float zFar) {
		Matrix4f result = new Matrix4f();

		float aspectRatio = width / height;
		float tanHalfFOV = (float) Math.tan(Math.toRadians(fov / 2.0f));
		float zRange = zNear - zFar;

		result.data[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
		result.data[0][1] = 0;
		result.data[0][2] = 0;
		result.data[0][3] = 0;

		result.data[1][0] = 0;
		result.data[1][1] = 1.0f / tanHalfFOV;
		result.data[1][2] = 0;
		result.data[1][3] = 0;

		result.data[2][0] = 0;
		result.data[2][1] = 0;
		result.data[2][2] = (-zNear - zFar) / zRange;
		result.data[2][3] = (2 * zFar * zNear) / zRange;

		result.data[3][0] = 0;
		result.data[3][1] = 0;
		result.data[3][2] = 1;
		result.data[3][3] = 0;

		return result;
	}
}
