using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

namespace MySQL_Json_Parsing_Test
{
    internal class Program
    {
        static void Main(string[] args)
        {
            WebRequest get_json_church_php_request = WebRequest.Create("https://as8794.cafe24.com/test/church_Json.php");
            get_json_church_php_request.Method = "GET";

            WebResponse get_json_church_php_response = get_json_church_php_request.GetResponse();
            Stream get_data = get_json_church_php_response.GetResponseStream();
            StreamReader data_reader = new StreamReader(get_data);
            string str_json = data_reader.ReadToEnd();

            // Console.WriteLine(str_json);

            data_reader.Close();
            get_data.Close();
            get_json_church_php_response.Close();

            JArray json = JArray.Parse(str_json);

            Console.WriteLine(json.ToString());

            Console.WriteLine($"{json[10]["name"]}는 {json[9]["name"]}을 너무 좋아한다.");
        }
    }
}
