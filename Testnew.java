// AddRemoveTest.java

// Simple applet illustrating use of add/removeChildren fields.

import java.awt.*;
import java.applet.*;
import vrml.field.*;
import vrml.external.*;
import vrml.external.field.*;
import vrml.external.exception.*;

public class Test extends Applet
{
	boolean error = false;
	static int yCoord[][] =
	{
//        0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25		
		{ 9, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  8,  8,  8,  8,  9,  9,  9,  9,  9,  8,  7,  6,  6 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9, 10, 10, 10, 10,  9,  8,  7,  7,  7 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10, 11, 11, 11,  9, 10, 10, 10, 10, 10, 10, 10,  9,  8,  8,  8,  8 },
		{ 9, 10, 10, 10, 10, 10,  9,  9, 10, 10, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9, 10, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9,  9,  8,  8,  8,  8,  8,  8,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10 },
		{ 8,  9, 10, 10, 10,  9,  9,  9,  9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  9,  9,  9,  9,  9,  9,  9 },
		{ 8,  9, 10, 10, 10,  9,  9,  8,  8,  8,  8,  8,  8,  8,  7,  7,  8,  8,  8,  9,  9,  9,  9,  9,  9,  9 },
		{ 9,  9, 10, 10, 10, 10,  9,  8,  8,  8,  8,  8,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 10, 10,  9,  9,  9,  9,  9,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 11, 10, 10,  9,  9,  9,  9,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 11, 10, 10, 10, 10,  9,  9,  8,  8,  7,  7,  8,  8,  8,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  9,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10, 10,  9,  8,  8,  9,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  8 },
		{ 9, 10, 10, 10, 10, 10,  8,  8,  8,  8,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9, 10, 10,  9,  9,  9,  8 },
		{ 9, 10, 10, 10, 10, 10,  8,  8,  8,  8,  9,  9,  8,  7,  7,  7,  7,  8,  9,  9, 10, 10,  9,  9,  8,  7 },
		{ 9, 10, 10, 10, 10, 10,  9,  8,  8,  8,  9,  9,  8,  7,  7,  7,  7,  8,  9,  9,  9,  9,  9,  9,  8,  7 }
	};
	
	// Browser we're using
	Browser browser;
	// Root of the scene graph (to which we add our nodes)
	Node script;
	
	// EventIns of the script node
	EventInSFString printstring;
	
	protected void SetupGrid()
	{
		int row, col;
		int i, j, k;
		int p1, p2, p3, p4;
		int c1, c2, c3, c4;

		int RowSize = yCoord.length;
		int ColSize = yCoord[0].length;
		int color = 1;
		SFVec3f vertex;
		float[][] point;
		int[] coordIndex;
		int[] colorIndex;
		Node Grond;
		Node coord;
		Grond = browser.getNode("Grond");
		coord = browser.getNode("coord");
		EventInMFVec3f set_point = (EventInMFVec3f) coord.getEventIn("set_point");
		EventInMFInt32 set_coordIndex = (EventInMFInt32) Grond.getEventIn("set_coordIndex");
		EventInMFInt32 set_colorIndex = (EventInMFInt32) Grond.getEventIn("set_colorIndex");
		
		i = RowSize * ColSize;
		point = new float[i][3];
		coordIndex = new int[10000];
		colorIndex = new int[10000];
		for (row = RowSize - 1; row >= 0; row--)
		{
			for (col = ColSize - 1; col >= 0; col--)
			{
				--i;
				point[i][0] = col;
				point[i][1] = yCoord[row][col];
				point[i][2] = row;
			}
		}
		j = 0;
		k = 0;
		for (row = 0; row < RowSize - 1; row++)
		{
			for (col = 0; col < ColSize - 1; col++)
			{
				p1 = yCoord[row][col];
				p2 = yCoord[row][col + 1];
				p3 = yCoord[row + 1][col + 1];
				p4 = yCoord[row + 1][col];
				c1 = row * ColSize + col;
				c2 = c1 + 1;
				c3 = c2 + ColSize;
				c4 = c1 + ColSize;
			
				if (p1 != p2 && p1 != p3 && p1 != p4)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = -1;
					colorIndex[k++] = 4;
					coordIndex[j++] = c1;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else if (p2 != p1 && p2 != p3 && p2 != p4)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else if (p3 != p1 && p3 != p2 && p3 != p4)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = -1;
					colorIndex[k++] = 4;
					coordIndex[j++] = c1;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else if (p4 != p1 && p4 != p2 && p4 != p3)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;
					coordIndex[j++] = -1;
					if (p1 == p2 && p1 == p3 && p1 == p4)
					{
						if (row % 2 == 0)
						{
							if (col % 2 == 0)
								colorIndex[k++] = 1;
							else
								colorIndex[k++] = 2;
						}
						else
						{
							if (col % 2 == 0)
								colorIndex[k++] = 2;
							else
								colorIndex[k++] = 1;
						}
					}
					else
						colorIndex[k++] = 4;
				}
			}
		}
		set_point.setValue(point);
		coordIndex. = j;
		colorIndex.length = k;
		set_coordIndex.setValue(coordIndex);
		set_colorIndex.setValue(colorIndex);
	}

	public void init()
	{
		System.out.println(yCoord[0][0]);
		// Note Browser.print() is only available in Cosmo Player 2.x
		Browser.print("AddRemoveTest.init()...");
		
		// Browser.getBrowser() doesn't belong here!  (See below)
		// But we do build the applet's UI right now:
		
		// Create some UI widgets
		add(new Button("absorb"));
		add(new Button("tree"));
		add(new Button("boulder"));
		add(new Button("robot"));
		add(new Button("transform"));
	}
	
	public void start()
	{
		System.out.println("AddRemoveTest.start()...");
		Browser.print("AddRemoveTest.start()...");
		
		// NOTE: It's important do to all VRML EAI setup here in the
		//   start() method, and *not* in init()!  The avoids the problem
		//   of stale Browser and Node pointers when the user leaves and
		//   then revisits the web page containing this applet.
		
		// Get a handle to the VRML browser:
		//
		//   NOTE: Use "Browser.getBrowser()" here, instead of the old-style
		//   LiveConnect "JSObject" methods (which are Netscape-specific).
		//
		//   Also note that in more complicated EAI applets, you should
		//   check for getBrowser() returning null!  (This can happen
		//   on some platforms if the applet starts running while your
		//   browser is still downloading files; in this case you should
		//   sleep a few seconds and retry the getBrowser() call.  See the
		//   Cosmo Player 2.0 FAQ at http://cosmo.sgi.com/support/player
		//   for an example of how to do this.)
		//
		//    browser = (Browser)BrowserFactory.getBrowser(this);
		
		browser = Browser.getBrowser(this);
		
		System.out.println("Got the browser: " + browser);
		Browser.print("Got the browser: " + browser);
		SetupGrid();
		
		// Now we've got the handle to the VRML Browser.
		// Look up some Nodes and EventIns/EventOuts:
		try
		{
			// Get script node of the scene, and its EventIns
			script = browser.getNode("SentinelScript");
			printstring = (EventInSFString) script.getEventIn("newcommand");
		}
		catch (InvalidNodeException e)
		{
			error = true;
		}
		catch (InvalidEventInException e)
		{
			error = true;
		}
		catch (InvalidVrmlException e)
		{
			error = true;
		}
	}
	
	public boolean action(Event event, Object what)
	{
		if (event.target instanceof Button)
		{
			Button b = (Button) event.target;
			System.out.println(b.getLabel());
			printstring.setValue(b.getLabel());
		}
		return true;
	}
}
