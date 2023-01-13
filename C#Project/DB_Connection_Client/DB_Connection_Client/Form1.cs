
/*
 *  2023-01-09
 *  Postgresql 연동 데이터베이스 조회, 삭제
 *  김진
 */


using Npgsql;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;
using System.Threading;

namespace DB_Connection_Client
{
    public partial class group_insert : Form
    {
        Boolean isSuccess = false; // DB 테이블에 접속했는지
        String commandText = ""; // 쿼리문
        String rearPrimary = ""; // 이전 키값 저장할 변수
        int connMode; // 1 = book 테이블, 2 = car 테이블
        // 접속할 호스트 경로
        // 회사 제공 경로
        string connStr = "Host=192.168.201.151;Username=postgres;Password=12345678;Database=book_car";
        // 로컬
        //string connStr = "Host=localhost;Username=postgres;Password=1234;Database=study"; 
        string str_serverURL = "127.0.0.1";
        string str_serverPort = "5555";

        StreamReader streamReader;  // 데이타 읽기 위한 스트림리더
        StreamWriter streamWriter;  // 데이타 쓰기 위한 스트림라이터

        public group_insert()
        {
            InitializeComponent();

            group_insert_update.Visible = false;
            // 키보드 입력
            this.KeyPreview = true;
            this.KeyDown += new KeyEventHandler(Form_KeyDown); 

            // 컬럼값 비우기
            this.ch_primary.Text = string.Format("");
            this.ch_humen.Text = string.Format("");
            this.ch_name.Text = string.Format("");
            this.ch_date.Text = string.Format("");

            // 라벨값 비우기
            this.label_primary.Text = string.Format("");
            this.label_name.Text = string.Format("");
            this.label_Author.Text = string.Format("");
            this.label_date.Text = string.Format("");

            // 버튼 클릭 이벤트
            this.bt_book.Click += new System.EventHandler(this.bt_book_Click);
            this.bt_car.Click += new System.EventHandler(this.bt_car_Click);
            this.bt_delete.Click += new System.EventHandler(this.bt_delete_Click);
            this.bt_search_primary.Click += new System.EventHandler(this.bt_search_primary_Click);
            this.bt_insert.Click += new System.EventHandler(this.bt_insert_Click);
            this.bt_update.Click += new System.EventHandler(this.bt_update_Click);
        }

        private void writeRichTextbox(string str)  // richTextbox1 에 쓰기 함수
        {
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.AppendText(str + "\r\n"); }); // 데이타를 수신창에 표시, 반드시 invoke 사용. 충돌피함.
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.ScrollToCaret(); });  // 스크롤을 젤 밑으로.
        }

        private void connect()
        {
            str_serverURL = txt_ip.Text;
            TcpClient tcpClient1 = new TcpClient();  // TcpClient 객체 생성
            IPEndPoint ipEnd = new IPEndPoint(IPAddress.Parse(str_serverURL), int.Parse(str_serverPort));  // IP주소와 Port번호를 할당
            tcpClient1.Connect(ipEnd);  // 서버에 연결 요청
            writeRichTextbox("서버 연결됨...");

            streamReader = new StreamReader(tcpClient1.GetStream());  // 읽기 스트림 연결
            streamWriter = new StreamWriter(tcpClient1.GetStream());  // 쓰기 스트림 연결
            streamWriter.AutoFlush = true;  // 쓰기 버퍼 자동으로 뭔가 처리..

            while (tcpClient1.Connected)  // 클라이언트가 연결되어 있는 동안
            {
                string receiveData1 = streamReader.ReadLine();  // 수신 데이타를 읽어서 receiveData1 변수에 저장
                if(!receiveData1.Equals(""))
                {
                    if (connMode == 1)
                    {
                        commandText = "select * from book";
                    }
                    else if (connMode == 2)
                    {
                        commandText = "select * from car";
                    }
                    Receive_Server();
                }
                writeRichTextbox(receiveData1);  // 데이타를 수신창에 쓰기
            }
        }

        void Receive_Server()
        {
            if (list.InvokeRequired)
            {
                list.Invoke(new MethodInvoker(delegate
                {
                    list.Items.Clear();
                }));
            }
            else
            {
                list.Items.Clear();
            }

            if(txt_search.InvokeRequired)
            {
                txt_search.Invoke(new MethodInvoker(delegate
                {
                    txt_search.Text = string.Empty;
                }));
            }
            else
            {
                txt_search.Text = string.Empty;
            }
            using (var conn = new NpgsqlConnection(connStr))
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
                                if (connMode == 1)
                                {
                                    string[] row = { reader["isbn"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["author"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                                    item = new ListViewItem(row);

                                    if (list.InvokeRequired)
                                    {
                                        list.Invoke(new MethodInvoker(delegate 
                                        { 
                                            list.Items.Add(item);
                                            list.EnsureVisible(list.Items.Count - 1);
                                        }));
                                    }
                                    else
                                    {
                                        list.Items.Add(item);
                                        list.EnsureVisible(list.Items.Count - 1);
                                    }
                                }
                                else if (connMode == 2)
                                {
                                    string[] row = { reader["number"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["company"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                                    item = new ListViewItem(row);
                                    if (list.InvokeRequired)
                                    {
                                        list.Invoke(new MethodInvoker(delegate
                                        {
                                            list.Items.Add(item);
                                            list.EnsureVisible(list.Items.Count - 1);
                                        }));
                                    }
                                    else
                                    {
                                        list.Items.Add(item);
                                        list.EnsureVisible(list.Items.Count - 1);
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }
            }
        }

        private void bt_conncetion_Click(object sender, EventArgs e)
        {
            Thread thread1 = new Thread(connect);  // Thread 객채 생성, Form과는 별도 쓰레드에서 connect 함수가 실행됨.
            thread1.IsBackground = true;  // Form이 종료되면 thread1도 종료.
            thread1.Start();  // thread1 시작.
        }

        void init_Column()
        {
            // 접속 테이블에 맞춰 컬럼 텍스트 및 라벨 텍스트 초기화
            if(connMode == 1)
            {
                this.ch_primary.Text = string.Format("ISBN");
                this.ch_humen.Text = string.Format("Author");

                this.label_primary.Text = string.Format("ISBN");
                this.label_Author.Text = string.Format("Author");
            }
            else if(connMode == 2)
            {
                this.ch_primary.Text = string.Format("Number");
                this.ch_humen.Text = string.Format("Company");

                this.label_primary.Text = string.Format("Number");
                this.label_Author.Text = string.Format("Company");
            }
            this.ch_name.Text = string.Format("Name");
            this.ch_date.Text = string.Format("Date");
            this.label_name.Text = string.Format("Name");
            this.label_date.Text = string.Format("Date");
        }

        void init_txt()
        {
            // 테이블 이동시 텍스트 박스 내용 값 비우기
            txt_search.Text = string.Empty;
            txt_primary.Text = string.Empty;
            txt_name.Text = string.Empty;
            txt_infor.Text = string.Empty;
            txt_date.Text = string.Empty;
        }

        void init_Column_Size()
        {
            // 리스트뷰 내용에 따라 컬럼 사이즈 변경하기
            for (int i = 0; i < list.Columns.Count; i++)
            {
                list.Columns[i].Width = -2;
            }
        }

        void print_error(Exception e)
        {
            Console.WriteLine("============== Error ==============");
            Console.WriteLine(e.Message);
            MessageBox.Show("에러가 발생했습니다.\n\n============== Error ==============\n\n" + e.Message, "Error");
        }

        void Reading_Data(NpgsqlCommand cmd)
        {
            // DB 데이터 읽어와 리스트뷰에 표시하기
                // 일시적으로 리스트뷰 아이템 모두 제거
            if (list.InvokeRequired)
            {
                list.Invoke(new MethodInvoker(delegate { list.Items.Clear(); }));
            }
            else
            {
                list.Items.Clear();
            }
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
                        
                        if(list.InvokeRequired)
                        {
                            list.Invoke(new MethodInvoker(delegate { list.Items.Add(item); }));
                        }
                        else
                        {
                            list.Items.Add(item);
                        }
                    }
                    else if (connMode == 2)
                    {
                        string[] row = { reader["number"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["company"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                        item = new ListViewItem(row);
                        if (list.InvokeRequired)
                        {
                            list.Invoke(new MethodInvoker(delegate { list.Items.Add(item); }));
                        }
                        else
                        {
                            list.Items.Add(item);
                        }
                    }
                    init_Column();
                }
            }
        }

        void DB_Connection_Reading()
        {
            // DB에 접속하여 리스트뷰에 뿌릴 준비하기
            using(var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open();
                    using(var cmd = new NpgsqlCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        Reading_Data(cmd); // 리스트뷰에 아이템 뿌림
                        isSuccess = true; // 접속 완료 체크
                        init_Column_Size(); // 컬럼 사이즈 자동 조절
                    }
                }
                catch (Exception e) 
                {
                    print_error(e);
                }
            }
        }

        void DB_Insert(string str_primary, string str_name, string str_infor, string str_date)
        {
            using(var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open(); // 접속
                    using (var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        Reading_Data(cmd);

                        if (connMode == 1)
                        {
                            commandText = "select * from book";
                        }
                        else if (connMode == 2)
                        {
                            commandText = "select * from car";
                        }
                        DB_Connection_Reading();
                        list.EnsureVisible(list.Items.Count-1);
                        init_txt();
                    }

                }
                catch (Exception e)
                {
                    print_error(e);
                    init_txt();
                }
            }
        }

        void DB_Update(string str_primary, string str_name, string str_infor, string str_date)
        {
            using(var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open();
                    using(var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        Reading_Data(cmd);

                        if (connMode == 1)
                        {
                            commandText = "select * from book";
                        }
                        else if (connMode == 2)
                        {
                            commandText = "select * from car";
                        }
                        DB_Connection_Reading();
                        init_txt();
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                    init_txt();
                }
            }
        }

        void DB_Search(string selectString)
        {
            using(var conn = new NpgsqlConnection(connStr))
            {
                try
                {
                    conn.Open();
                    using(var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        cmd.CommandText = commandText;

                        Reading_Data(cmd);
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
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
                    using (var cmd = conn.CreateCommand())
                    {
                        cmd.Connection = conn;
                        if (connMode == 1)
                        {
                            commandText = "delete from book where isbn = '" + selectString + "';";
                        }
                        else if (connMode == 2)
                        {
                            commandText = "delete from car where number = '" + selectString + "';";
                        }
                        cmd.CommandText = commandText;
                        cmd.ExecuteNonQuery();
                        list.Items.Clear();
                        if (connMode == 1)
                        {
                            commandText = "select * from book";
                        }
                        else if (connMode == 2)
                        {
                            commandText = "select * from car";
                        }
                        DB_Connection_Reading();
                        streamWriter.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : delete를 알림");

                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }
            }
        }

        private void bt_book_Click(object sender, EventArgs e)
        {
            connMode = 1;
            init_Column();
            init_txt();
            commandText = "select * from book";
            DB_Connection_Reading();
        }

        private void bt_car_Click(object sender, EventArgs e)
        {
            connMode = 2;
            init_Column();
            init_txt();
            commandText = "select * from car";
            DB_Connection_Reading();
        }

        private void bt_insert_Click(object sender, EventArgs e)
        {
            if(isSuccess == false)
            {
                return;
            }

            String str_primary = txt_primary.Text;
            String str_name = txt_name.Text;
            String str_infor = txt_infor.Text;
            String str_date = txt_date.Text;

            /*
             * 유효성 검증
             * 1. 텍스트 박스에 빈 항목 X
             * 2. Book Table Insert 시도 시, ISBN 텍스트 박스에는 숫자만 입력되야 함
             * 3. date 텍스트 박스는 8자리의 숫자만 포함되야 함
             */
            if(txt_primary.Text.Equals("") || txt_name.Text.Equals("") || txt_infor.Text.Equals("") || txt_date.Text.Equals(""))
            {
                MessageBox.Show("빈 항목 없이 작성해주세요.");
                return;
            }
            // 유효성 검사 끝

            // 쿼리문 작성
            if (connMode == 1)
            {
                commandText = "insert into book values(" + str_primary +
                                ", '" + str_name +
                                "', '" + str_infor +
                                "', '" + str_date + "')";
            }
            else if(connMode == 2)
            {
                commandText = "insert into car values('" + str_primary +
                                "', '" + str_name +
                                "', '" + str_infor +
                                "', '" + str_date + "')";
            }
            Console.WriteLine(commandText);
            DB_Insert(str_primary, str_name, str_infor, str_date);
        }

        private void bt_update_Click(object sender, EventArgs e)
        {
            if(isSuccess == false)
            {
                return;
            }

            String str_primary = txt_primary.Text;
            String str_name = txt_name.Text;
            String str_infor = txt_infor.Text;
            String str_date = txt_date.Text;

            /*
             * 유효성 검증
             * 1. 텍스트 박스에 빈 항목 X
             * 2. Book Table Insert 시도 시, ISBN 텍스트 박스에는 숫자만 입력되야 함
             * 3. date 텍스트 박스는 8자리의 숫자만 포함되야 함
             */
            if (txt_primary.Text.Equals("") || txt_name.Text.Equals("") || txt_infor.Text.Equals("") || txt_date.Text.Equals(""))
            {
                MessageBox.Show("빈 항목 없이 작성해주세요.");
                return;
            }
            // 유효성 검사 끝

            // 쿼리문 작성
            if (connMode == 1)
            {
                commandText = "update book set isbn = " + str_primary +
                                ", name = '" + str_name +
                                "', author = '" + str_infor +
                                "', date = '" + str_date + "' where isbn = " + rearPrimary;
            }
            else if (connMode == 2)
            {
                commandText = "update car set number = '" + str_primary +
                                "', name = '" + str_name +
                                "', company = '" + str_infor +
                                "', date = '" + str_date + "' where number = '" + rearPrimary + "'";
            }
            Console.WriteLine(commandText);
            DB_Update(str_primary, str_name, str_infor, str_date);
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

        private void bt_search_primary_Click(object sender, EventArgs e)
        {
            if (isSuccess == false)
            {
                return;
            }

            string input = txt_search.Text;

            if (input.Equals(""))
            {
                if (connMode == 1)
                {
                    commandText = "select * from book";
                }
                else if (connMode == 2)
                {
                    commandText = "select * from car";
                }
            }
            else
            {
                if (connMode == 1)
                {
                    commandText = "select * from book where cast(isbn as varchar(10)) like '%" + input +
                                    "%' or lower(name) like lower('%" + input +
                                    "%') or lower(author) like lower('%" + input +
                                    "%') or cast(date as varchar(20)) like '%" + input + "%'";
                }
                else if (connMode == 2)
                {
                    commandText = "select * from car where lower(number) like lower('%" + input +
                                    "%') or lower(name) like lower('%" + input +
                                    "%') or lower(company) like lower('%" + input +
                                    "%') or cast(date as varchar(20)) like '%" + input + "%'";
                }
            }
            DB_Search(connStr);
        }

        private void Form_KeyDown(object sender, KeyEventArgs e)
        {
            switch(e.KeyData)
            {
                case Keys.Enter:
                    bt_search_primary_Click(sender, e);
                    break;
                case Keys.F1:
                    this.Size = new Size(573, 272);
                    group_search.Visible = true;
                    group_insert_update.Visible = false;
                    break;

               /*
                case Keys.F2:
                    this.Size = new Size(573, 292);
                    group_search.Visible = false;
                    group_insert_update.Visible = true;
                    break;
               */
            }
        }

        private void list_MouseClick(object sender, MouseEventArgs e)
        {
            bool selected = list.SelectedItems.Count > 0;
            if (selected)
            {
                ListViewItem item = list.SelectedItems[0];
                String str_primary = item.SubItems[0].Text;
                String str_name = item.SubItems[1].Text;
                String str_infor = item.SubItems[2].Text;
                String str_date = item.SubItems[3].Text;

                txt_primary.Text = str_primary;
                txt_name.Text = str_name;
                txt_infor.Text = str_infor;
                txt_date.Text = str_date;

                rearPrimary = str_primary;
                Console.WriteLine(rearPrimary);
            }

        }

        
    }
}
