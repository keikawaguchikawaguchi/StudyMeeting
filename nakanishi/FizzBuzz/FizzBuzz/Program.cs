using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FizzBuzz
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("入力した数字でFizzBuzzするよ");
            Console.Write("Fizz=");
            int num1 = int.Parse(Console.ReadLine());
            Console.Write("Buzz=");
            int num2 = int.Parse(Console.ReadLine());

            for (int i = 1; i <= 100; i++)
            {
                Console.WriteLine(FizzBuzz(i,num1,num2));
            }
            Console.WriteLine("\nEnterを押して終了");
            Console.ReadLine();
        }

        static string FizzBuzz(int n,int x,int y)
        {
            if (n % x == 0 && n % y == 0)
                return "FizzBuzz";
            if (n % x == 0)
                return "Fizz";
            if (n % y == 0)
                return "Buzz";
            return n.ToString();
        }
    }
}
