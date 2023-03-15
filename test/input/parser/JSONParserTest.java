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
		StringBuilder sb = new StringBuilder();
		//create string builder, create unparse visitor object, call methods from there to unparse
		UnparseVisitor uP = new UnparseVisitor();
		
		//node.unparse(sb, 0);
		//System.out.println(sb.toString());
	}
}
