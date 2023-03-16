package input.builder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.components.ComponentNode;
import input.components.FigureNode;
import input.parser.JSONParser;
import input.visitor.UnparseVisitor;

class DefaultBuilderTest 
{
	
	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new DefaultBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void colinear_line_segments_test()
	{
		ComponentNode node = DefaultBuilderTest.runFigureParseTest("collinear_line_segments.json");
		
		assertTrue(node == null);
		
		//create string builder, create unparse visitor object, call methods from there to unparse
		//UnparseVisitor uP = new UnparseVisitor();
		//StringBuilder sb = new StringBuilder(); 
		//uP.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
	}
	
	
	
}
