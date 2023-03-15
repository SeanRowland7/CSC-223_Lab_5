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

		
		sb.append("Figure" + "\n" + StringUtilities.indent(level) + "{");

		//increment level for proper indentation when methods are called
		level++;

		sb.append("\n" + StringUtilities.indent(level) + "Description : " + node.getDescription());
		sb.append("\n" + StringUtilities.indent(level) + "Points: \n" + StringUtilities.indent(level) + "{");

		//method call to add points to the sb
		node.getPointsDatabase().accept(this, pair);
	

		sb.append("\n" + StringUtilities.indent(level) + "}");
		sb.append("\n" + StringUtilities.indent(level) + "Segments: \n" + StringUtilities.indent(level) + "{");

		//method call to add segments to the sb
		node.getSegments().accept(this, pair);

		sb.append("\n" + StringUtilities.indent(level) + "}");

		//decrement level for proper indentation when methods are called
		level--;

		sb.append("\n" + StringUtilities.indent(level) + "}");

		return null;
	}

	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
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

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		return null;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();
		// Sample output: 
		// Point(A)(0.0, 0.0)
		// Point(B)(1.0, 1.0)
		// Point(C)(1.0, 0.0)
		
		for(PointNode point : node.getPoints())
		{
			sb.append("\n" + StringUtilities.indent(level) + "Point(" + point.getName() + ")(" + point.getX() + ", " + point.getY() + ")");
		}

		return o;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		//TODO
		return null;
	}
}