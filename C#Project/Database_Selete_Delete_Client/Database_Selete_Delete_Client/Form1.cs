using Npgsql;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;

namespace Database_Selete_Delete_Client
{
    public partial class Form1 : Form
    {
        Boolean isSuccess = false;
        String commandText = "";
        int connMode;

        public Form1()
        {
            InitializeComponent();
            this.bt_book.Click += new System.EventHandler(this.bt_book_Click);
            this.bt_car.Click += new System.EventHandler(this.bt_car_Click);
            this.bt_delete.Click += new System.EventHandler(this.bt_delete_Click);
        }

        void set_Column()
        {
            this.ch_name.Text = string.Format("Name");
            this.ch_date.Text = string.Format("Date");
        }

        void DB_connection()
        {
            using (var conn = new NpgsqlConnection("Host=localhost;Username=postgres;Password=1234;Database=study"))
            {
                try
                {
                    conn.Open();
                    using (var cmd = new NpgsqlCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        using (var reader = cmd.ExecuteReader())
                        {
                            ListViewItem item;

                            while (reader.Read())
                            {
                                DateTime date = (DateTime)reader["date"];
                                if(connMode == 0)
                                {
                                    string[] row = { reader["name"].ToString(), reader["author"].ToString(), date.ToString("yyyy-MM-dd") };
                                    item = new ListViewItem(row);
                                    listView1.Items.Add(item);
                                }
                                else
                                {
                                    string[] row = { reader["name"].ToString(), reader["number"].ToString(), date.ToString("yyyy-MM-dd") };
                                    item = new ListViewItem(row);
                                    listView1.Items.Add(item);
                                }
                            }
                            isSuccess = true;
                        }
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("============== Error ==============");
                    Console.WriteLine(ex.Message);
                }
            }
        }

        private void bt_book_Click(object sender, EventArgs e)
        {
            listView1.Items.Clear();
            set_Column();
            connMode = 0;
            commandText = "select * from book";
            DB_connection();
            this.ch_author.Text = string.Format("Author");
        }

        private void bt_car_Click(object sender, EventArgs e)
        {
            listView1.Items.Clear();
            set_Column();
            connMode = 1;
            commandText = "select * from car";
            DB_connection();
            this.ch_author.Text = string.Format("Number");
        }

        private void bt_delete_Click(object sender, EventArgs e)
        {
            if(isSuccess == true)
            {
                bool selected = listView1.SelectedItems.Count > 0;
                if(selected == false)
                {
                    return;
                }
                ListViewItem item = listView1.SelectedItems[0];
                String selectString = item.SubItems[1].Text;
                //Console.WriteLine(item.SubItems[1].Text);

                using (var conn = new NpgsqlConnection("Host=localhost;Username=postgres;Password=1234;Database=study"))
                {
                    try
                    {
                        conn.Open();
                        using (var cmd = new NpgsqlCommand())
                        {
                            cmd.Connection = conn;
                            if(connMode == 1)
                            {

                            }
                            String text = "DELETE FROM car WHERE number = '" + selectString + "';";
                            Console.WriteLine(text);
                            cmd.CommandText = text;
                            cmd.ExecuteNonQuery();
                        }
                    }
                    catch (Exception ee)
                    {

                    }
                }
            }
            else
            {
                Console.WriteLine("No Connection");
                return;
            }
        }
    }
}
