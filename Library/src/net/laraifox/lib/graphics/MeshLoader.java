package net.laraifox.lib.graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import net.laraifox.lib.graphics.Vertex3f;
import net.laraifox.lib.math.Vector3f;
import net.laraifox.lib.util.NumberUtil;
import net.laraifox.lib.util.StringUtil;

public class MeshLoader {
	public static Mesh loadMesh(String fileName) {
		String[] splitArray = fileName.split("\\.");
		String extention = splitArray[splitArray.length - 1];

		Mesh result = new Mesh();

		if (extention.endsWith("obj")) {
			result = loadOBJMesh(fileName);
		} else {
			System.err.println("ERROR: File format not supported for model @" + fileName);
			new Exception().printStackTrace();
			System.exit(1);
		}

		return result;
	}

	private static Mesh loadOBJMesh(String fileName) {
		ArrayList<Vertex3f> vertices = new ArrayList<Vertex3f>();
		ArrayList<Integer> indices = new ArrayList<Integer>();

		try {
			BufferedReader meshReader = new BufferedReader(new FileReader(fileName));
			String line = new String();

			while ((line = meshReader.readLine()) != null) {
				String[] tokens = line.split(" ");
				tokens = StringUtil.removeEmptyStrings(tokens);

				if (tokens.length == 0 || tokens[0].equals("#")) {
					continue;
				} else if (tokens[0].equals("v")) {
					vertices.add(new Vertex3f(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
				} else if (tokens[0].equals("f")) {
					indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
					
					if (tokens.length > 4) {
						indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
					}
				}
			}

			meshReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		Mesh result = new Mesh();
		Vertex3f[] vertexData = new Vertex3f[vertices.size()];
		vertices.toArray(vertexData);

		Integer[] indexData = new Integer[indices.size()];
		indices.toArray(indexData);

		result.addVertices(vertexData, NumberUtil.toIntArray(indexData));
		
		return result;
	}
}
