package input.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import input.builder.DefaultBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;

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

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
}
