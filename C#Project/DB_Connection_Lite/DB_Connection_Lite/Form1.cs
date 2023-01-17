using Npgsql;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DB_Connection_Lite
{
    public partial class Form1 : Form
    {
        // 전역변수
        bool isSuccess = false; // DB 테이블에 접속했는지
        bool isConnection = false; // 소켓 통신 접속 판단
        int connMode; // 1 = book 테이블, 2 = car 테이블

        // 접속할 호스트 경로
        // 회사 제공 경로
        string str_DBURL = "Host=192.168.201.151;Username=postgres;Password=12345678;Database=book_car";
        // 로컬
        //string connStr = "Host=localhost;Username=postgres;Password=1234;Database=study"; 
        
        //  소켓 통신을 위한 서버 클라이언트 접속할 경로
        string str_serverURL = "127.0.0.1";
        string str_serverPort = "5555";

        StreamReader streamReader;  // 데이타 읽기 위한 스트림리더
        StreamWriter streamWriter;  // 데이타 쓰기 위한 스트림라이터

        // SQL 구문
        string str_commandText = "";

        public Form1()
        {
            InitializeComponent();

            this.KeyPreview = true;
            this.KeyDown += new KeyEventHandler(Form_KeyDown);
        }

        // 에러 받아와 메시지 박스 표시
        void print_error(Exception e)
        {
            MessageBox.Show("에러가 발생했습니다.\n\n============== Error ==============\n\n" + e.Message, "Error");
        }

        // 접속 테이블 맞춰 컬럼 초기화
        private void init_Form()
        {
            // list Invoke (타 thread 충돌 회피)
            list_table.Invoke(new MethodInvoker(delegate
            {
                if (connMode == 1)
                {
                    this.ch_primary.Text = string.Format("ISBN");
                    this.ch_infor.Text = string.Format("Author");
                }
                else if (connMode == 2)
                {
                    this.ch_primary.Text = string.Format("Number");
                    this.ch_infor.Text = string.Format("Company");
                }
                this.ch_name.Text = string.Format("Name");
                this.ch_date.Text = string.Format("Date");

                // 리스트뷰 내용에 따라 컬럼 사이즈 변경하기
                for (int i = 0; i < list_table.Columns.Count; i++)
                {
                    list_table.Columns[i].Width = -2;
                }
            }));

        }

        // 검색창 초기화
        private void init_Search()
        {
            // 검색창
            txt_search.Invoke(new MethodInvoker(delegate
            {
                txt_search.Text = string.Empty;
            }));
        }

        /*
         * 소켓 통신 
         */

        // 서버로부터 write 읽어서 richBox 표시
        private void writeRichBox(string input)
        {
            rich_state.Invoke(new MethodInvoker(delegate
            {
                rich_state.AppendText(input + "\r\n");
                rich_state.ScrollToCaret();
            }));
        }

        private void socket_start()
        {
            Thread thread = new Thread(connect);  
            thread.IsBackground = true; 
            thread.Start();
        }

        // 서버와 소켓 통신
        private void connect()
        {
            if (txt_ip.Text.Equals(""))
            {
                return;
            }
            str_serverURL = txt_ip.Text;
            
            try
            {
                TcpClient client = new TcpClient();
                IPEndPoint ipEnd = new IPEndPoint(IPAddress.Parse(str_serverURL), int.Parse(str_serverPort));
                client.Connect(ipEnd);
                writeRichBox("서버 연결됨...");
                isConnection= true;
                if(this.bt_book.InvokeRequired == true)
                {
                    this.bt_book.Invoke((MethodInvoker)delegate
                    {
                        this.bt_book.PerformClick();
                    });
                }
                else
                {
                    this.bt_book.PerformClick();
                }

                streamReader = new StreamReader(client.GetStream());  // 읽기 스트림 연결
                streamWriter = new StreamWriter(client.GetStream());  // 쓰기 스트림 연결
                streamWriter.AutoFlush = true;  // 쓰기 버퍼 자동으로 뭔가 처리..

                while (true)
                {
                    string receiveData = streamReader.ReadLine();
                    if (receiveData == null || receiveData.Equals("exit"))
                    {
                        writeRichBox("서버가 종료됨...\n");
                        streamReader.Close();
                        streamWriter.Close();
                        client.Close();
                        isConnection = false;
                        break;
                    }
                    writeRichBox(receiveData);
                }
            }
            catch (SocketException e)
            {
                print_error(e);
            }
        }

        /*
         * DB 조작
         */

        // DB 최초 접속하기
        void DB_Connection()
        {
            using (var conn = new NpgsqlConnection(str_DBURL))
            {
                try
                {
                    conn.Open();
                    using(var cmd = new NpgsqlCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = str_commandText;

                        Reading_Data(cmd);
                        isSuccess= true;
                        init_Search();
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }
            }
        }

        // 읽어온 DB 데이터 List에 표시하기
        private void Reading_Data(NpgsqlCommand cmd)
        {
            // 일시적으로 리스트뷰 아이템 모두 제거
            list_table.Invoke(new MethodInvoker(delegate
            {
                list_table.Items.Clear();
            }));

            using (var reader = cmd.ExecuteReader())
            {
                ListViewItem item;

                while (reader.Read())
                {
                    DateTime date = (DateTime)reader["date"];
                    if (connMode == 1)
                    {
                        string[] row = { reader["isbn"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["author"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                        item = new ListViewItem(row);
                        list_table.Items.Add(item);
                    }
                    else if (connMode == 2)
                    {
                        string[] row = { reader["number"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["company"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                        item = new ListViewItem(row);
                        list_table.Items.Add(item);
                    }
                    init_Form();
                }
            }
        }

        // DB 데이터 검색하기
        private void DB_Search(string result)
        {
            using (var conn = new NpgsqlConnection(str_DBURL))
            {
                try
                {
                    conn.Open();
                    using (var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = str_commandText;

                        Reading_Data(cmd);
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }

                // 소켓 접속 판단
                if (isConnection == true)
                {
                    if(!result.Equals(""))
                    {
                        streamWriter.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : \"" + result + "\" 검색");
                    }
                }
            }
        }

        // DB 데이터 삭제하기
        private void DB_Delete(string input)
        {
            using (var conn = new NpgsqlConnection(str_DBURL))
            {
                try
                {
                    conn.Open();
                    using (var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        if (connMode == 1)
                        {
                            str_commandText = "delete from book where isbn = '" + input + "';";
                        }
                        else if (connMode == 2)
                        {
                            str_commandText = "delete from car where number = '" + input + "';";
                        }
                        cmd.CommandText = str_commandText;
                        cmd.ExecuteNonQuery();
                        list_table.Items.Clear();
                        if (connMode == 1)
                        {
                            str_commandText = "select * from book";
                        }
                        else if (connMode == 2)
                        {
                            str_commandText = "select * from car";
                        }
                        DB_Connection();
                        
                        // 소켓 접속 판단
                        if(isConnection == true)
                        {
                            streamWriter.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : delete를 알림");
                        }
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }
            }
        }

        // 버튼 클릭 이벤트

        // Book 버튼
        private void bt_book_Click(object sender, EventArgs e)
        {
            connMode = 1;
            str_commandText = "select * from book";
            DB_Connection();
        }

        // Car 버튼
        private void bt_car_Click(object sender, EventArgs e)
        {
            connMode = 2;
            str_commandText = "select * from car";
            DB_Connection();
        }

        // Connection 버튼
        private void bt_conncetion_Click(object sender, EventArgs e)
        {
            socket_start();
        }

        // Search 버튼
        private void bt_search_Click(object sender, EventArgs e)
        {
            // 테이블엔 접속했는지
            if (isSuccess == false)
            {
                return;
            }

            string result = txt_search.Text; // 검색창 입력값 받아오기

            // 검색창이 비워져 있으면
            if (result.Equals(""))
            {
                if (connMode == 1)
                {
                    str_commandText = "select * from book";
                }
                else if (connMode == 2)
                {
                    str_commandText = "select * from car";
                }
            }
            else
            {
                if (connMode == 1)
                {
                    str_commandText = "select * from book where cast(isbn as varchar(10)) like '%" + result +
                                    "%' or lower(name) like lower('%" + result +
                                    "%') or lower(author) like lower('%" + result +
                                    "%') or cast(date as varchar(20)) like '%" + result + "%'";
                }
                else if (connMode == 2)
                {
                    str_commandText = "select * from car where lower(number) like lower('%" + result +
                                    "%') or lower(name) like lower('%" + result +
                                    "%') or lower(company) like lower('%" + result +
                                    "%') or cast(date as varchar(20)) like '%" + result + "%'";
                }
            }
            DB_Search(result);
        }

        // Delete 버튼
        private void bt_delete_Click(object sender, EventArgs e)
        {
            // 테이블 접속 했는지
            if (isSuccess == false)
            {
                return;
            }

            // 리스트 아이템 선택 했는지
            bool selected = list_table.SelectedItems.Count > 0;
            if (selected == false)
            {
                return;
            }

            ListViewItem item = list_table.SelectedItems[0];
            String selectString = item.SubItems[0].Text;

            DB_Delete(selectString);
        }

        // 키보드 입력 처리
        private void Form_KeyDown(object sender, KeyEventArgs e)
        {
            switch (e.KeyData)
            {
                case Keys.Enter:
                    bt_search_Click(sender, e);
                    break;
            }
        }

        // List 마우스 클릭 커리
        private void list_table_Click(object sender, EventArgs e)
        {
            bool selected = list_table.SelectedItems.Count > 0;
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (isConnection == true)
            {
                streamWriter.WriteLine("exit");
            }
        }
    }
}
