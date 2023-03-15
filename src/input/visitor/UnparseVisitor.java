package input.visitor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import input.components.*;
import input.components.point.*;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;

//
// This file implements a Visitor (design pattern) with 
// the intent of building an unparsed, String representation
// of a geometry figure.
//
public class UnparseVisitor implements ComponentNodeVisitor
{
	@Override
	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//gets root of AST
		sb.append("Figure" + "\n" + StringUtilities.indent(level) + "{");

		//increment level for proper indentation when methods are called
		level++;

		//gets Description of AST and adds to sb
		sb.append("\n" + StringUtilities.indent(level) + "Description : " + node.getDescription());
		
		
		//calls to get Points of AST and adds to sb
		sb.append("\n" + StringUtilities.indent(level) + "Points: \n" + StringUtilities.indent(level) + "{");
		node.getPointsDatabase().accept(this, pair);
		sb.append("\n" + StringUtilities.indent(level) + "}");
	
		
		//calls to get segments of AST and adds to sb
		sb.append("\n" + StringUtilities.indent(level) + "Segments: \n" + StringUtilities.indent(level) + "{");
		node.getSegments().accept(this, pair);
		sb.append("\n" + StringUtilities.indent(level) + "}");

		
		//decrement level for proper indentation when methods are called
		level--;
		sb.append("\n" + StringUtilities.indent(level) + "}");

		return o;
	}

	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		//nested loop to get all point combinations
		for(PointNode dEdge : node.getAdjList().keySet())
		{
			// adds name to the sb, wont be printed with the nested loop
			sb.append("\n" + StringUtilities.indent(level) + dEdge.getName() + " : ");
		
			for(PointNode uEdge : node.getAdjList().get(dEdge))
			{
				//append each undirected edge for a given dEdge
				sb.append(uEdge.getName() + " ");
			}
		}

		return o;
	}

	
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		//adds toString to sb; not used since segment database uses the Adjacency list representation
		sb.append("\n" + StringUtilities.indent(level) + node.toString());
		return sb;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		
		int level = pair.getValue();
		for(PointNode point : node.getPoints())
		{
			//calls VisitPointNode to get the point
			visitPointNode(point, pair);
		}

		return o;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		
		//adds point to sb and returns sb
		sb.append("\n" + StringUtilities.indent(level) + "Point(" + node.getName() + ")(" + node.getX() + ", " + node.getY() + ")");
		return sb;
	}
}