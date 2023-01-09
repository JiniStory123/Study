using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DB_connection_test
{
    internal class Program
    {
        static void Main(string[] args)
        {
            using (var conn = new NpgsqlConnection("Host=localhost;Username=postgres;Password=1234;Database=study"))
            {
                try
                {
                    conn.Open();
                    using(var cmd = new NpgsqlCommand())
                    {
                        cmd.Connection= conn;
                        cmd.CommandText = "select * from car";

                        using (var reader = cmd.ExecuteReader())
                        {
                            Console.WriteLine("table column = {0}개", reader.FieldCount);

                            while(reader.Read())
                            {
                                var data = new string[]
                                {
                                    reader["name"].ToString(),
                                    reader["number"].ToString(),
                                    reader["date"].ToString()
                                };

                                foreach(var x in data)
                                {
                                    Console.Write(x);
                                    Console.Write(" -- ");
                                }
                                Console.WriteLine();
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("============== Error ==============");
                    Console.WriteLine(ex.Message);
                }

                Console.ReadLine();
            }
        }

        void connectionDB()
        {

        }
    }
}
