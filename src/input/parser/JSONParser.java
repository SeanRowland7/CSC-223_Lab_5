package input.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.builder.DefaultBuilder;
import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

/**
* The JSONParser class provides functionality for parsing JSON 
* representations of geometry figures and constructing the representations with
* String descriptions, PointNodeDatabases and SegmentaNodeDatabases
*
* <p>Bugs: None
*
* @author Sean Rowland
* @date 02/23/2023
*/

public class JSONParser
{
	protected ComponentNode  _astRoot;
	protected DefaultBuilder _builder;
	

	public JSONParser(DefaultBuilder builder)
	{
		_astRoot = null;
		_builder = builder;
		
	}

	/**
	 * Throw a ParseException with a given error message.
	 * @param message -- String message is printed when there is an error
	 */
	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	/**
	 * Parse a String containing a JSON representation of a geometry figures and 
	   construct those figures using PointNodeDatabases and SegmentNodeDatabases
	 * @param str -- String is parsed into useful data
	 * @return ComponentNode -- where the data from str is stored
	*/
	public ComponentNode parse(String str) throws ParseException
	{
		// Parsing is accomplished via the JSONTokenizer class. 
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();
		
		// Initializes variables that will store data from JSON file
		String description = "";
		PointNodeDatabase points = null;
		SegmentNodeDatabase segments = null;
		
		// Stores information from the JSON file
		try 
		{
			JSONObject figure = JSONroot.getJSONObject("Figure");
			
			description = figure.getString("Description");
			
			List<PointNode> pointsList = convertJSONToPointsList(figure.getJSONArray("Points"));
			
			points = _builder.buildPointNodeDatabase(pointsList);
			
			segments = _builder.buildSegmentNodeDatabase();
			addSegmentsToDatabase(segments, pointsList, figure.getJSONArray("Segments"));
		}
		// If the file doesn't contain a necessary component then throw an exception
		catch(JSONException e)
		{
			error("Does not contain necessary components");
		}
		
		return _builder.buildFigureNode(description, points, segments);
	}
	
	private List<PointNode> convertJSONToPointsList(JSONArray pointsAsJSONArray)
	{
		ArrayList<PointNode> pointsList = new ArrayList<PointNode>();
		
		// Populates with points taken from the given JSONArray
		for(Object point : pointsAsJSONArray)
			pointsList.add(convertJSONToPoint((JSONObject)point));
		
		return pointsList;
	}
	
	private PointNode convertJSONToPoint(JSONObject point) 
	{
		//converts JSON data to a PointNode
		return _builder.buildPointNode(point.getString("name"), point.getDouble("x"), point.getDouble("y"));
	}
	
	
	private void addSegmentsToDatabase(SegmentNodeDatabase segments, List<PointNode> pointsList, JSONArray segmentsAsJSONArray)
	{
		for(int i = 0; i < segmentsAsJSONArray.length(); i++)
		{
			//gets the key from each object in the array
			String p1Name = segmentsAsJSONArray.getJSONObject(i).keys().next();
			
			PointNode p1 = getPointFromList(p1Name, pointsList);
			
			for(Object p2AsJSON : segmentsAsJSONArray.getJSONObject(i).getJSONArray(p1Name))
			{
				PointNode p2 = getPointFromList(p2AsJSON.toString(), pointsList);
				
				_builder.addSegmentToDatabase(segments, p1, p2);
			}
		}
	}
	
	private PointNode getPointFromList(String pointName, List<PointNode> list)
	{
		for(PointNode point : list)
		{
			if(point != null && point.getName().equals(pointName))
			{
				return point;
			}
		}
		
		return null;
	}
}
