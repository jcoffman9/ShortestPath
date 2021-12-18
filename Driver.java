

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Driver{

	public static void main(String args[]) {

		int greatest = -1;
		String line = "";
		String src = "";
		String dest = "";
		int weight = -1;
		
		if (args.length < 1) {
			System.out.println("Usage: java Driver <vietnamFile>");
			System.exit(1);
		}
		
		
		String vietFile = args[0];
		Graph graph = new Graph();
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(vietFile));

			while ((line = in.readLine()) != null) {
				
					String[] entry = line.split(",");
					if(entry.length == 1) {
						src = entry[0];
						graph.addVertex(src);

					} else if(entry.length == 3){
						src = entry[0];
						dest = entry[1];
						weight = Integer.parseInt(entry[2]);
						if(weight > greatest) {
							greatest = weight;
						}
						graph.addVertex(src);
						graph.addVertex(dest);
						graph.addEdge(src, dest, weight);
					}
			}

			in.close();
			
		} catch (FileNotFoundException e) {
			System.exit(2);
		} catch (IOException e) {
			System.exit(3);
		}

		
		
		
		try {
            PrintWriter out = new PrintWriter(new File("output.txt"));
            	out.println("Number of vertices: "+graph.numVertices());
                if (graph.zeroIn().size()==0) {
                	out.println("Zero Inbound: none");
                } else {
                	out.println("Zero Inbound: "+graph.zeroIn());
                }
                if(graph.selfEdge().size()==0) {
                	out.println("Self Edges: none");
                }else {
                	out.println("Self Edges: "+graph.selfEdge());
                }
                if(graph.zeroOut().size()==0) {
                	out.println("Zero Outbound: none");
                }else {
                	out.println("Zero Outbound: "+graph.zeroOut());
                }
                out.println("Heaviest weight: "+greatest);
		
            out.close();
        } catch (IOException e) {
        	System.exit(4);
        }
		
	
		
	}
}
