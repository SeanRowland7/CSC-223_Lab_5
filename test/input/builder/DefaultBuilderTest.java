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
		//A----B-----C--D-----E----------F


		ComponentNode node = DefaultBuilderTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node == null);
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("bow_shape_trapezoid.json");

		assertTrue(node == null);


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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node == null);	
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("four_triangles_connected_by_one.json");

		assertTrue(node == null);
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node == null);	
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("heart_diagram.json");

		assertTrue(node == null);
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("rectangle_arrows.json");

		assertTrue(node == null);
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("segments_to_one_point.json");

		assertTrue(node == null);
	}


	@Test
	void single_triangle_test()
	{
		ComponentNode node = DefaultBuilderTest.runFigureParseTest("single_triangle.json");

		assertTrue(node == null);
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

		ComponentNode node = DefaultBuilderTest.runFigureParseTest("star_shape.json");

		assertTrue(node == null);
	}
}
