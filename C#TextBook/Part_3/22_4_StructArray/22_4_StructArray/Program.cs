using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _22_4_StructArray
{
    struct BusinessCard
    {
        public string name;
        public int age;
    }

    internal class Program
    {
        static void Print(string name, int age) => Console.WriteLine($"{name}은(는) {age}살입니다.");

        static void Main(string[] args)
        {
            BusinessCard biz;
            biz.name = "백승수";
            biz.age = 17;
            Print(biz.name, biz.age);

            BusinessCard[] names = new BusinessCard[2];
            names[0].name = "이세영"; names[0].age = 102;
            names[1].name = "권경민"; names[1].age = 31;

            for (int i=0; i<2; i++)
                Print(names[i].name, names[i].age);
        }
    }
}
