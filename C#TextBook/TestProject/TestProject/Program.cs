using System;
using System.Numerics;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace TestProject
{
    internal class Program
    {
        static void Main(string[] args)
        {
            StringBuilder str = new StringBuilder();
            int n = int.Parse(Console.ReadLine());
            List<int> list = new List<int>();

            for (int i=0; i<n; i++)
                list.Add(int.Parse(Console.ReadLine()));

            list.Sort();

            for (int i = 0; i < n; i++)
                str.AppendLine(list[i] + "");
            Console.WriteLine(str.ToString());
        }
    }
}
