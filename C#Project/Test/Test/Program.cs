using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Schema;

namespace Test
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int year = 1;
            /*
                윤달 조건
                1. 연도를 4로 나누었을 때 떨어져야 함
                2. 근데 100으로 떨어지면 평년
                3. 근데 4로 나누어 떨어지고 100으로 나누어 떨어지나 400으로 나눠 떨어지면 윤년임
                허허 거참
            */

            while(year != 0)
            {
                year = int.Parse(Console.ReadLine());

                if (year == 0)
                    break;

                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                {
                    Console.WriteLine(year + " : 윤년");
                }
                else
                {
                    Console.WriteLine(year + " : 평년");
                }
            }
            
        }
    }
}
