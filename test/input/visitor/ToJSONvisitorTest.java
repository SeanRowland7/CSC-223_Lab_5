package input.visitor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.parser.JSONParser;

class ToJSONvisitorTest {

	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void colinear_line_segments_test()
	{
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("collinear_line_segments.json");
		
		assertTrue(node instanceof FigureNode);
	
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		
		System.out.println(json.toString(5));
	}
	
}
