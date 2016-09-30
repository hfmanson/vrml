import vrml.*;
import vrml.field.*;
import vrml.node.*;

public class Testjava extends Script
{
	// Declare events/fields
	public void initialize()
	{
		MFNode first = (MFNode) getField("First");
		SFNode Test = (SFNode) getField("Test");
		Node n = (Node) Test.getValue();
		MFNode set_children = (MFNode) n.getEventIn("set_children");
		set_children.setValue(first);
	}
}
