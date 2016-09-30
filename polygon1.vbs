Set Args = Wscript.Arguments
Dim i, n
Dim x, y
Dim h, d
n = Args(0)
d = 8 * Atn(1) / n
h = d / 2
For i = 1 To n
    x = Cos(h) * 0.7654
    y = Sin(h) * 0.7654
    wScript.echo x, y
    h = h + d
Next
