Attribute VB_Name = "Module1"
Sub fizzbuzz�ۑ�()
    Dim i As Long '��
    Dim f As Long '�t���O
    Dim fb As String 'FizzBuzz
    
    Cells.Clear '�Z���̃N���A
    
    '3�Ŋ��������̗]�肪0�Ȃ�Fizz
    '5�Ŋ��������̗]�肪0�Ȃ�Buzz
    '15�Ŋ��������̗]�肪0�Ȃ�FizzBuzz�Əo��
    For i = 1 To 100
        f = 0: fb = ""
        If i Mod 3 = 0 Then: fb = "Fizz": f = 1
        If i Mod 5 = 0 Then: fb = fb & "Buzz": f = 1
        Cells(i, 1) = IIf(f = 0, i, fb)
    Next
End Sub
