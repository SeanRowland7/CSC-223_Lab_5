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
		//A----B-----C--D-----E----------F
		
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("collinear_line_segments.json");
		
		assertTrue(node instanceof FigureNode);
	
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	
	@Test
	void  bow_shape_trapezoid_test() {
		//  A        D
		//  | \     /|
		//  |  B _ C |
		//  |  |   | |
		//  |  E - F |
		//  G /     \H
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("bow_shape_trapezoid.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	@Test
	void crossing_symmetric_triangle_test()
	{
		//	     A                                 
		//      / \                                
		//     B___C                               
		//    / \ / \                              
		//   /   X   \ 
		//  D_________E  
		
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("crossing_symmetric_triangle.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	
	@Test
	void four_triangles_connected_by_one_test()
	{
		//      A
		//     / \
		//    /   \
		//    B---C
		//    /\ /\
		//   /  D  \
		//   E /  \ F
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("four_triangles_connected_by_one.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	
	@Test
	void fully_connected_irregular_polygon_test()
	{
		//
		//
		//		               D(3, 7)
		//
		//
		//   E(-2,4)
		//		                       		C(6, 3)
		//
		//		       A(2,0)        B(4, 0)
		//
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("fully_connected_irregular_polygon.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	
	@Test
	void heart_diagram_test()
	{

		//    C—-B   J—-I
		//   /    \ /     \  
		// 	/	    A      \
		//  D	           H
		//  |		       |	
		//  E              G
		//   \        	  /
		//    \          /
		//     \        /
		//      \      /
		//	  	 \    /
		//	  	  \  /
		//	   	  	F   
		
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("heart_diagram.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	
	@Test
	void rectangle_arrows_test()
	{
		//  A ______B
		//  |\ \ / /|
		//  | \ C / |
		//  |  \ /  |
		//  |   \   |
		//  |  / \  |
		//  | / E \ |
		//  |/ / \ \| 
		//  D_______F
		
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("rectangle_arrows.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	@Test
	void segments_to_one_point_test()
	{
		// 
		//	  B C
		//   / / D
		//  A-/-/- E
		//   \ \ \F
		//	  H G

		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("segments_to_one_point.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	@Test
	void single_triangle_test()
	{
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("single_triangle.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
	@Test
	void star_shape_test()
	{
		//	 	 A
		//      / \
		//     /   \
		//B------------C
		//  \ /     \/
		//   / \   / \
		//   /  /\   \
		//  / /   \  \
		// //      \ \
		//D	    	  E
		
		ComponentNode node = ToJSONvisitorTest.runFigureParseTest("star_shape.json");
		
		assertTrue(node instanceof FigureNode);
		
		ToJSONvisitor toJSON = new ToJSONvisitor(); 
		
		JSONObject json = (JSONObject)toJSON.visitFigureNode((FigureNode)node, null);
		
		System.out.println(json.toString(5));
	}
	
}
