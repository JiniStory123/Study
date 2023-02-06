using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JSON_Parsing
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // JSON Data
            string json = "" +
                "{ " +
                "  'squadName': 'Super hero squad', " +
                "  'homeTown': 'Metro City', " +
                "  'active': true, " +
                "  'members': [ " +
                "               { 'name': 'Molecule Man', " +
                "                 'age': 29, " +
                "                 'powers': [ " +
                "                             'Radiation resistance', " +
                "                             'Turning tiny', " +
                "                           ] " +
                "               }, " +
                "               { " +
                "                 'name': 'Madame Uppercut', " +
                "                 'age': 39, " +
                "                 'powers': [ " +
                "                             'Million tonne punch', " +
                "                           ] " +
                "               }, " +
                "               { " +
                "                 'name': 'Eternal Flame', " +
                "                 'age': 1000000, " +
                "                 'powers': [ " +
                "                             'Immortality', " +
                "                             'Heat Immunity', " +
                "                           ] " +
                "               } " +
                "             ] " +
                "}";


            /////////////////////////////////////////////////
            // Native Object 사용 방법
            /////////////////////////////////////////////////

            // Native Object 생성
            JObject jObject = JObject.Parse(json);

            // Json Data 전체 출력
            Console.WriteLine(jObject.ToString());

            // JSON 데이터 중 'Radiation resistance' 데이터까지 접근하는 방법
            Console.WriteLine(jObject["members"][0]["powers"][0]);

            // JSON 데이터 하위 객체인 members 객체의 name 값을 반복적으로 접근하는 방법
            JToken jToken = jObject["members"];
            foreach (JToken members in jToken)
                Console.WriteLine(members["name"]);


            /////////////////////////////////////////////////
            // 역직렬화(Deserialize) 방법
            /////////////////////////////////////////////////

            // 역직렬화 수행 후 미리 선언한 클래스에 저장
            Root rootObject = JsonConvert.DeserializeObject<Root>(json);

            // JSON 데이터 중 'Radiation resistance' 데이터까지 접근하는 방법
            Console.WriteLine(rootObject.members[0].powers[0]);

            // JSON 데이터 하위 객체인 members 객체의 name 값을 반복적으로 접근하는 방법
            foreach (Members members in rootObject.members)
                Console.WriteLine(members.name);


            /////////////////////////////////////////////////
            // 직렬화(Serialize) 방법
            /////////////////////////////////////////////////

            // 직렬화 수행 후 string 변수에 저장
            string serializeResult = JsonConvert.SerializeObject(rootObject);

            // Json Data 전체 출력
            Console.WriteLine(serializeResult);
        }

        // 역직렬화를 위한 클래스 선언
        public class Root
        {
            public string squadName;
            public string homeTown;
            public string active;
            public List<Members> members;
        }
        public class Members
        {
            public string name;
            public string age;
            public List<string> powers;
        }
    }
}
