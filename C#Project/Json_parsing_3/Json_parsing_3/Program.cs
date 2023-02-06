using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

namespace Json_parsing_3
{
    internal class Program
    {
        static void Main(string[] args)
        {

            string json = File.ReadAllText(@"D:\GitDirectory\Study\C#Project\Json_parsing_3\student.json");

            JObject jObject = JObject.Parse(json);
            // 0 : 김진, 1 : 김수환, 2 : 이승연, 3 : 전현진, 4 : 최정욱, 5 : 김찬교, 6 : 최인영, 7 : 황이루리, 8 : 김용호, 9 : 유승엽

            Console.WriteLine(jObject.ToString());

            Root root = JsonConvert.DeserializeObject<Root>(json);

            //Console.WriteLine(root.student[0].address);
            //Console.WriteLine(root.update.ToString("yyyy년 MM월 dd일"));

            //foreach (Student students in root.student)
            //    Console.WriteLine($"{students.name}, {students.age}, {students.address}");

            Console.WriteLine($"{root.student[7].address}에 사는 {root.student[7].name}는 아주 행복한 군생활을 하고 있다.");
            Console.WriteLine($"{root.student[7].name}는 {root.student[9].name}을 너무 좋아한다.");
        }
    }

    public class Root
    {
        public DateTime update;
        public List<Student> student;
    }
    public class Student
    {
        public string name;
        public int age;
        public string address;
    }
}
