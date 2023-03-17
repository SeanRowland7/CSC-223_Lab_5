package input.visitor;

import java.util.AbstractMap;

import org.json.JSONArray;
import org.json.JSONObject;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;

public class ToJSONvisitor implements ComponentNodeVisitor {

	public Object visitFigureNode(FigureNode node, Object o)
	{
	
		//gets Description of AST and adds to sb
		JSONObject figure = new JSONObject();
		figure.put("Description" , node.getDescription());
		
		
		//calls to get Points of AST and adds to sb
		//JSONObject points = new JSONObject();
		figure.put("Points", node.getPointsDatabase().accept(this, null));
		
		
		//calls to get segments of AST and adds to sb
		//JSONObject segments = new JSONObject();
		figure.append("Segments", node.getSegments().accept(this, null));
		

		JSONObject jsonOb = new JSONObject();
		jsonOb.put("figure", figure);
		

		return o;
	}


	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
		JSONObject adjList = new JSONObject();
		//nested loop to get all point combinations
		for(PointNode dEdge : node.getAdjList().keySet())
		{
			// adds name to the sb, wont be printed with the nested loop
			adjList.put(dEdge.getName(), getDestinationList(node,o,dEdge));
					
		}

		return o;
	}
	
	public Object getDestinationList(SegmentNodeDatabase node, Object o, PointNode dEdge)
	{
		JSONArray destList = new JSONArray();
		for(PointNode uEdge : node.getAdjList().get(dEdge))
		{
			//append each undirected edge for a given dEdge
			destList.put(uEdge.getName());
		}
	
		return o;
	}
	

	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		//adds toString to sb; not used since segment database uses the Adjacency list representation
		sb.append("\n" + StringUtilities.indent(level) + node.toString());
		return o;
	}


	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		JSONArray jsonPoints = new JSONArray();
		for(PointNode point : node.getPoints())
		{
			//calls VisitPointNode to get the point
			jsonPoints.put(visitPointNode(point, visitPointNode(point,null)));
		}

		return o;
	}


	public Object visitPointNode(PointNode node, Object o)
	{
		JSONObject point = new JSONObject();
		point.put("name", node.getName());
		point.put("x", node.getX());
		point.put("y", node.getY());
		

		return o;
	}
	
	//NOT DONE
	public String toString(int i) {
		String s = "";
		return s;
	}
}

