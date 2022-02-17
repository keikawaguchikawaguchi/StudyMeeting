Attribute VB_Name = "Module1"
Sub fizzbuzz課題()
    Dim i As Long '列数
    Dim f As Long 'フラグ
    Dim fb As String 'FizzBuzz
    
    Cells.Clear 'セルのクリア
    
    '3で割った時の余りが0ならFizz
    '5で割った時の余りが0ならBuzz
    '15で割った時の余りが0ならFizzBuzzと出力
    For i = 1 To 100
        f = 0: fb = ""
        If i Mod 3 = 0 Then: fb = "Fizz": f = 1
        If i Mod 5 = 0 Then: fb = fb & "Buzz": f = 1
        Cells(i, 1) = IIf(f = 0, i, fb)
    Next
End Sub
