package net.laraifox.lib.graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import net.laraifox.lib.math.Matrix4f;
import net.laraifox.lib.math.Vector3f;
import net.laraifox.lib.util.BufferUtil;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

public class Shader {
	private int program;
	private HashMap<String, Integer> uniforms;

	public Shader() {
		program = GL20.glCreateProgram();
		uniforms = new HashMap<String, Integer>();

		if (program == 0) {
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}
	}

	public void bind() {
		GL20.glUseProgram(program);
	}

	// public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
	//
	// }

	public void addUniform(String uniform) {
		int uniformLocation = GL20.glGetUniformLocation(program, uniform);

		if (uniformLocation == 0xFFFFFFFF) {
			System.err.println("Error: Could not find uniform: " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}

		uniforms.put(uniform, uniformLocation);
	}

	public void addVertexShaderFromFile(String text) {
		addProgram(loadShader(text), GL20.GL_VERTEX_SHADER);
	}

	public void addGeometryShaderFromFile(String text) {
		addProgram(loadShader(text), GL32.GL_GEOMETRY_SHADER);
	}

	public void addFragmentShaderFromFile(String text) {
		addProgram(loadShader(text), GL20.GL_FRAGMENT_SHADER);
	}

	public void addVertexShader(String text) {
		addProgram(text, GL20.GL_VERTEX_SHADER);
	}

	public void addGeometryShader(String text) {
		addProgram(text, GL32.GL_GEOMETRY_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL20.GL_FRAGMENT_SHADER);
	}

	public void setAttribLocation(String attribute, int location) {
		GL20.glBindAttribLocation(program, location, attribute);
	}

	public void compileShader() {
		GL20.glLinkProgram(program);

		if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == 0) {
			System.err.println(GL20.glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}

		GL20.glValidateProgram(program);

		if (GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) == 0) {
			System.err.println(GL20.glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
	}

	private void addProgram(String text, int type) {
		int shader = GL20.glCreateShader(type);

		if (shader == 0) {
			System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
			System.exit(1);
		}

		GL20.glShaderSource(shader, text);
		GL20.glCompileShader(shader);

		if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == 0) {
			System.err.println(GL20.glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		GL20.glAttachShader(program, shader);
	}

	private static String loadShader(String fileName) {
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;

		try {
			shaderReader = new BufferedReader(new FileReader(fileName));
			String line;

			while ((line = shaderReader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}

			shaderReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return shaderSource.toString();
	}

	public void setUniformi(String uniformName, int value) {
		GL20.glUniform1i(uniforms.get(uniformName), value);
	}

	public void setUniformf(String uniformName, float value) {
		GL20.glUniform1f(uniforms.get(uniformName), value);
	}

	public void setUniform(String uniformName, Vector3f value) {
		GL20.glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
	}

	public void setUniform(String uniformName, Matrix4f value) {
		GL20.glUniformMatrix4(uniforms.get(uniformName), true, BufferUtil.createFlippedFloatBuffer(value));
	}
}
