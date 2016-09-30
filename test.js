var multTable = new Array
(
	new Array(10, 10,  9,  9, 10),
	new Array(10, 10, 10, 10, 10),
	new Array(10, 10, 11, 11, 10),
	new Array(10, 10, 11, 11, 10),
	new Array(10, 10, 10, 10,  9),
	new Array(10, 10, 10, 10,  9),
	new Array(10, 10, 10, 10,  9),
	new Array(10, 10, 10, 10,  9),
	new Array( 9, 10, 10, 10,  9),
	new Array( 9, 10, 10, 10,  9)
)

var fso = new ActiveXObject("Scripting.FileSystemObject")
var a = fso.CreateTextFile("testfile.txt", true)
a.WriteLine(multTable[0][2])
a.WriteLine(multTable[2][2])
a.WriteLine(multTable.length)
a.WriteLine(multTable[0].length)
