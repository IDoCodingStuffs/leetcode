package depthFirstSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
 */
public class RemoveInvalidParentheses
{
	public List<String> removeInvalidParentheses( String s ) 
	{
		List<String> result = new ArrayList<>();
		if ( s == null || s.length() == 0 )
		{
			return result;
		}

		Set<String> isVisited = new HashSet<>();
		int numInvalid = calcNumInvalid( s );
		Queue<String> bfsQueue = new LinkedList<>();
		bfsQueue.add( s );
		while ( !bfsQueue.isEmpty() )
		{
			int levelSize = bfsQueue.size();
			for ( int i = 0; i < levelSize; i++ )
			{
				String qHead = bfsQueue.poll();
				for ( int j = 0; j < qHead.length(); j++ )
				{
					if ( qHead.charAt( j ) == '(' || qHead.charAt( j ) == ')' )
					{
						String newString = qHead.substring( 0, j ) + qHead.substring( j + 1 ); // mark
						if ( !isVisited.contains( newString ) )
						{						
							isVisited.add( newString );
							int newInvalid = calcNumInvalid( newString );
							if ( newInvalid == numInvalid - 1 )
							{
								if ( newInvalid > 0 )
								{
									bfsQueue.add( newString );
								}
								else
								{
									result.add( newString );
								}
							}
						}
					}
				}
			}
			numInvalid--;
		}
		return result;
	}
	
	private int calcNumInvalid( String s )
	{
		Stack<Character> stack = new Stack<>();
		for ( char ch : s.toCharArray() )
		{
			if ( !stack.isEmpty() && stack.peek() == '(' && ch == ')' )
			{
				stack.pop();
			}
			else
			{
				stack.push( ch );
			}
		}
		return stack.size();
	}
}
