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

namespace DB_Connection_Client
{
    public partial class Form1 : Form
    {
        Boolean isSuccess = false;
        String commandText = "";
        int connMode;
        string connStr = "Host=localhost;Username=postgres;Password=1234;Database=study";
        //NpgsqlConnection conn = new NpgsqlConnection("Host=localhost;Username=postgres;Password=1234;Database=study");

        public Form1()
        {
            InitializeComponent();
            
            this.ch_primary.Text = string.Format("");
            this.ch_humen.Text = string.Format("");
            this.ch_name.Text = string.Format("");
            this.ch_date.Text = string.Format("");

            this.bt_book.Click += new System.EventHandler(this.bt_book_Click);
            this.bt_car.Click += new System.EventHandler(this.bt_car_Click);
            this.bt_delete.Click += new System.EventHandler(this.bt_delete_Click);
        }

        void init_Column()
        {
            if(connMode == 1)
            {
                this.ch_primary.Text = string.Format("ISBN");
                this.ch_humen.Text = string.Format("Author");
            }
            else if(connMode == 2)
            {
                this.ch_primary.Text = string.Format("Number");
                this.ch_humen.Text = string.Format("Driver");
            }
            this.ch_name.Text = string.Format("Name");
            this.ch_date.Text = string.Format("Date");
        }

        void DB_Connection()
        {
            using (var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open();
                    using(var cmd = new NpgsqlCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        using(var reader = cmd.ExecuteReader())
                        {
                            ListViewItem item;
                            
                            while(reader.Read())
                            {
                                DateTime date = (DateTime)reader["date"];
                                if(connMode == 1)
                                {
                                    string[] row = { reader["isbn"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["author"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                                    item = new ListViewItem(row);
                                    list.Items.Add(item);
                                }
                                else if(connMode == 2)
                                {
                                    string[] row = { reader["number"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["driver"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                                    item = new ListViewItem(row);
                                    list.Items.Add(item);
                                }
                            }
                            isSuccess = true;
                        }
                    }
                }
                catch (Exception e) 
                {
                    Console.WriteLine("============== Error ==============");
                    Console.WriteLine(e.Message);
                }
            }
        }

        void DB_Delete(string selectString)
        {
            using (var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open();
                    using(var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        if(connMode == 1)
                        {
                            commandText = "delete from book where isbn = '" + selectString + "';";
                        }
                        else if(connMode == 2)
                        {
                            commandText = "delete from book where number = '" + selectString + "';";
                        }
                        cmd.CommandText = commandText;
                        cmd.ExecuteNonQuery();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("============== Error ==============");
                    Console.WriteLine(e.Message);
                }
            }
        }

        private void bt_book_Click(object sender, EventArgs e)
        {
            connMode = 1;
            list.Items.Clear();
            init_Column();
            commandText = "select * from book";
            DB_Connection();
        }

        private void bt_car_Click(object sender, EventArgs e)
        {
            connMode = 2;
            list.Items.Clear();
            init_Column();
            commandText = "select * from car";
            DB_Connection();
        }

        private void bt_delete_Click(object sender, EventArgs e)
        {
            if(isSuccess == false)
            {
                return;
            }

            bool selected = list.SelectedItems.Count > 0;
            if(selected == false)
            {
                return;
            }
            ListViewItem item = list.SelectedItems[0];
            String selectString = item.SubItems[0].Text;

            DB_Delete(selectString);
        }
    }
}
