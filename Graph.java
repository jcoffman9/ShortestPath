

import java.util.*;

public class Graph{

	private Set<String> vertices;
	private Hashtable<String, Hashtable<String, Integer>> edges;

	public Graph() {
		edges = new Hashtable<String, Hashtable<String, Integer>>();
		vertices = new HashSet<String>();
	}

	boolean addEdge(String src, String dest, int weight) {
		if (this.contains(src) && this.contains(dest)) {
			edges.get(src).put(dest, weight);
			return true;
		} else {
			return false;
		}
	}

	boolean removeEdge(String src, String dest) {
		if (!this.contains(src, dest)) {
			return false;
		} else {
			edges.get(src).remove(dest);
			return true;
		}

	}

	void addVertex(String name) {
		if (!this.contains(name)) {
			vertices.add(name);
			edges.put(name, new Hashtable<String, Integer>());
		}
	}

	boolean removeVertex(String name) {
		if(this.contains(name)) {
			for (String vert : vertices) {
				Set<String> edge = edges.get(vert).keySet();
				for (String key : edge) {
					if (key.equals(name)) {
						edges.get(vert).remove(key);
					}
				}
			}
			return true;
		}
		return false;

	}

	boolean contains(String name) {
		if (vertices.contains(name)) {
			return true;
		}
		return false;
	}

	boolean contains(String src, String dest) {
		if (edges.containsKey(src) && edges.get(src).containsKey(dest)) {
				return true;
		}
		return false;
	}

	int numVertices() {
		return this.vertices.size();
	}

	List<String> zeroIn() {

		List<String> zeroIn = new ArrayList<String>();
		Set<String> verts = this.edges.keySet();
		Set<String> hasInEdge = new HashSet<String>();
		Set<String> edges;
		

		for (String vert : verts) {
			edges = this.edges.get(vert).keySet();
			for (String edge : edges) {
				if (verts.contains(edge)) {
					hasInEdge.add(edge);
				}
			}
		}

		for (String vert : verts) {
			if (!hasInEdge.contains(vert)) {
				zeroIn.add(vert);
			}
		}

		return zeroIn;

	}

	List<String> selfEdge() {

		List<String> selfEdge = new ArrayList<String>();
		Set<String> verts = this.edges.keySet();

		for (String vert : verts) {
			if (contains(vert,vert)) {
				selfEdge.add(vert);
			}
		}

		return selfEdge;
	}

	List<String> zeroOut() {

		List<String> zeroOut = new ArrayList<String>();
		Set<String> edge = this.edges.keySet();

		for (String vert : edge) {
			if (this.edges.get(vert).isEmpty()) {
				zeroOut.add(vert);
			}
		}

		return zeroOut;

	}

}