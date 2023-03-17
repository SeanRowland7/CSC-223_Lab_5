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
		//A----B-----C--D-----E----------F

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
	}


	@Test
	void bow_shape_trapezoid_test()
	{
		//  A        D
		//  | \     /|
		//  |  B _ C |
		//  |  |   | |
		//  |  E - F |
		//  G /     \H

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("bow_shape_trapezoid.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("four_triangles_connected_by_one.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("heart_diagram.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("rectangle_arrows.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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


		ComponentNode node = GeometryBuilderTest.runFigureParseTest("segments_to_one_point.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
	}


	@Test
	void single_triangle_test()
	{
		ComponentNode node = GeometryBuilderTest.runFigureParseTest("single_triangle.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
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

		ComponentNode node = GeometryBuilderTest.runFigureParseTest("star_shape.json");

		assertTrue(node instanceof FigureNode);


		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor(); 

		unparser.visitFigureNode((FigureNode)node, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		System.out.println(sb.toString());
	}








}
