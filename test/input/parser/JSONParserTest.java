package input.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.builder.DefaultBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.visitor.UnparseVisitor;

class JSONParserTest {

	
	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new DefaultBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void colinear_line_segments_test()
	{
		
		ComponentNode node = JSONParserTest.runFigureParseTest("collinear_line_segments.json");
		
		assertTrue(node == null);
		//assertTrue(node instanceof FigureNode);
		
		//create string builder, create unparse visitor object, call methods from there to unparse
		StringBuilder sb = new StringBuilder();
		int level = 0;
		Object o = new Object();
		UnparseVisitor uP = new UnparseVisitor();
		
		
		
	
		
		
		//System.out.println(sb.toString());
	}
}
