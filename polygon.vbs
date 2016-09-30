Set Args = Wscript.Arguments
Dim i, n
Dim x, y
Dim h, d
n = Args(0)
h = 0
d = 8 * Atn(1) / n
For i = 1 To n
    x = Cos(h)
    y = Sin(h)
    wScript.echo x, y
    h = h + d
Next
