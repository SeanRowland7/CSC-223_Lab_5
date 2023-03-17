package input.builder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.components.ComponentNode;
import input.components.FigureNode;
import input.parser.JSONParser;
import input.visitor.UnparseVisitor;

class GeometryBuilderTest
{

	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void colinear_line_segments_test()
	{
		ComponentNode node = GeometryBuilderTest.runFigureParseTest("collinear_line_segments.json");
		
		assertTrue(node instanceof FigureNode);
		
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 
		
		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		System.out.println(sb.toString());
	}
	
	
}
