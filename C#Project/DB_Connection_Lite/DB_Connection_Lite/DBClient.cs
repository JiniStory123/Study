
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
    public partial class DBClient : Form
    {
        // 전역변수
        bool isSuccess = false; // DB 테이블에 접속했는지
        bool isConnection = false; // 소켓 통신 접속 판단
        int connMode; // 1 = book 테이블, 2 = car 테이블

        // 접속할 호스트 경로
        // 제공 경로
        string str_DBURL = "Host=192.168.201.151;Username=postgres;Password=12345678;Database=book_car";
        // 로컬
        //string connStr = "Host=localhost;Username=postgres;Password=1234;Database=study"; 
        
        //  소켓 통신을 위한 서버 클라이언트 접속 경로
        string str_serverURL = "127.0.0.1";
        string str_serverPort = "5555";

        TcpClient client;
        StreamReader streamReader;  // 데이타 읽기 위한 스트림리더
        StreamWriter streamWriter;  // 데이타 쓰기 위한 스트림라이터

        // SQL 구문
        string str_commandText = "";
        string str_selectBook = "select * from book order by name";
        string str_selectCar = "select * from car order by name";

        public DBClient()
        {
            InitializeComponent();

            // Form keyDown
            this.KeyPreview = true;
            this.KeyDown += new KeyEventHandler(Form_KeyDown);

            // list 항목 이름 지운다
            ch_primary.Text = string.Empty;
            ch_name.Text = string.Empty;
            ch_infor.Text = string.Empty;
            ch_date.Text = string.Empty;

            // 버튼 비활성화
            bt_book.Enabled = false;
            bt_car.Enabled = false;
            bt_search.Enabled = false;
            bt_delete.Enabled = false;
            bt_disconnection.Enabled = false;
            txt_search.Enabled = false;
        }

        // =================================== 편의 ===================================

        // 에러 받아와 메시지 박스 표시
        void print_error(Exception e)
        {
            MessageBox.Show("에러가 발생했습니다.\n\n============== Error ==============\n\n" + e.Message, "Error");
        }

        // 접속 테이블 맞춰 컬럼 초기화
        private void init_Form()
        {
            if (list_table.InvokeRequired == true)
            {
                list_table.Invoke((MethodInvoker)delegate
                {
                    // list 항목 이름 변경
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
                });
            }
            else
            {
                // list 항목 이름 변경
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
            }
        }

        // 검색창 초기화
        private void init_Search()
        {
            // 검색창
            if (txt_search.InvokeRequired == true)
            {
                txt_search.Invoke((MethodInvoker)delegate
                {
                    txt_search.Text = string.Empty;
                });
            }
            else
            {
                txt_search.Text = string.Empty;
            }
        }

        // 소켓 통신 접속 성공시 폼 활성 비활성 메소드
        private void connection_successful()
        {
            // TextBox, Button 활성화, 비활성화
            // txt_ip 비활성화
            if (txt_ip.InvokeRequired == true)
            {
                txt_ip.Invoke((MethodInvoker)delegate
                {
                    txt_ip.Enabled = false;
                });
            }
            else
            {
                txt_ip.Enabled = false;
            }
            // txt_search 활성화
            if (txt_search.InvokeRequired == true)
            {
                txt_search.Invoke((MethodInvoker)delegate
                {
                    txt_search.Enabled = true;
                });
            }
            else
            {
                txt_search.Enabled = true;
            }
            // bt_connection 버튼 비활성화
            if (bt_conncetion.InvokeRequired == true)
            {
                bt_conncetion.Invoke((MethodInvoker)delegate
                {
                    bt_conncetion.Enabled = false;
                });
            }
            else
            {
                bt_conncetion.Enabled = false;
            }
            // bt_desconnection 버튼 활성화
            if (bt_disconnection.InvokeRequired == true)
            {
                bt_disconnection.Invoke((MethodInvoker)delegate
                {
                    bt_disconnection.Enabled = true;
                });
            }
            else
            {
                bt_disconnection.Enabled = true;
            }
            // bt_book 활성화
            if (bt_book.InvokeRequired == true)
            {
                bt_book.Invoke((MethodInvoker)delegate
                {
                    bt_book.Enabled = true;
                });
            }
            else
            {
                bt_book.Enabled = true;
            }
            // bt_car 활성화
            if (bt_car.InvokeRequired == true)
            {
                bt_car.Invoke((MethodInvoker)delegate
                {
                    bt_car.Enabled = true;
                });
            }
            else
            {
                bt_car.Enabled = true;
            }
            // bt_search 활성화
            if (bt_search.InvokeRequired == true)
            {
                bt_search.Invoke((MethodInvoker)delegate
                {
                    bt_search.Enabled = true;
                });
            }
            else
            {
                bt_search.Enabled = true;
            }
            // bt_delete 활성화
            if (bt_delete.InvokeRequired == true)
            {
                bt_delete.Invoke((MethodInvoker)delegate
                {
                    bt_delete.Enabled = true;
                });
            }
            else
            {
                bt_delete.Enabled = true;
            }
        }

        // 소켓 통신 연결 해제 시 폼 활성, 비활성 메소드
        private void disconnect()
        {
            // TextBox, Button 활성화 비활성화
            // txt_ip 활성화
            if (txt_ip.InvokeRequired == true)
            {
                txt_ip.Invoke((MethodInvoker)delegate
                {
                    txt_ip.Enabled = true;
                });
            }
            else
            {
                txt_ip.Enabled = true;
            }
            // txt_search 비활성화
            if (txt_search.InvokeRequired == true)
            {
                txt_search.Invoke((MethodInvoker)delegate
                {
                    txt_search.Enabled = false;
                });
            }
            else
            {
                txt_search.Enabled = false;
            }
            // bt_connection 버튼 활성화
            if (bt_conncetion.InvokeRequired == true)
            {
                bt_conncetion.Invoke((MethodInvoker)delegate
                {
                    bt_conncetion.Enabled = true;
                });
            }
            else
            {
                bt_conncetion.Enabled = true;
            }
            // bt_desconnection 버튼 비활성화
            if (bt_disconnection.InvokeRequired == true)
            {
                bt_disconnection.Invoke((MethodInvoker)delegate
                {
                    bt_disconnection.Enabled = false;
                });
            }
            else
            {
                bt_disconnection.Enabled = false;
            }
            // bt_book 비활성화
            if (bt_book.InvokeRequired == true)
            {
                bt_book.Invoke((MethodInvoker)delegate
                {
                    bt_book.Enabled = false;
                });
            }
            else
            {
                bt_book.Enabled = false;
            }
            // bt_car 비활성화
            if (bt_car.InvokeRequired == true)
            {
                bt_car.Invoke((MethodInvoker)delegate
                {
                    bt_car.Enabled = false;
                });
            }
            else
            {
                bt_car.Enabled = false;
            }
            // bt_search 비활성화
            if (bt_search.InvokeRequired == true)
            {
                bt_search.Invoke((MethodInvoker)delegate
                {
                    bt_search.Enabled = false;
                });
            }
            else
            {
                bt_search.Enabled = false;
            }
            // bt_delete 비활성화
            if (bt_delete.InvokeRequired == true)
            {
                bt_delete.Invoke((MethodInvoker)delegate
                {
                    bt_delete.Enabled = false;
                });
            }
            else
            {
                bt_delete.Enabled = false;
            }
        }

        // =================================== 소켓 통신 ===================================

        // 서버로부터 Write 읽어서 RichBox 표시
        private void writeRichBox(string input)
        {
            if(rich_state.InvokeRequired == true)
            {
                rich_state.Invoke((MethodInvoker)delegate
                {
                    rich_state.AppendText(input + "\r\n");
                    rich_state.ScrollToCaret();
                });
            }
            else
            {
                rich_state.AppendText(input + "\r\n");
                rich_state.ScrollToCaret();
            }
        }

        // 소켓 통신을 시작하는 Thread
        private void socket_start()
        {
            Thread thread = new Thread(connect);  
            thread.IsBackground = true; 
            thread.Start();
        }

        // 서버와 소켓 통신
        private void connect()
        {
            // Connection 버튼 클릭 시 txt_ip 입력 값 받아오는데
            // txt_ip 입력 값 유효성 검증
            // TextBox가 비어있으면
            if (txt_ip.Text.Equals(""))
            {
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : IP 주소는 빈값으로 둘 수 없습니다");
                return;
            }

            // IP 형식에 맞지 않으면
            IPAddress ip;
            bool valid = IPAddress.TryParse(txt_ip.Text, out ip);
            if (valid == false)
            {
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : IP 주소가 올바르지 않습니다");
                return;
            }

            // IP 유효성 검사 이상 없을 때 TextBox에서 주소 받아와 저장
            str_serverURL = txt_ip.Text;

            // bt_connection 일단 비활성화
            if (bt_conncetion.InvokeRequired == true)
            {
                bt_conncetion.Invoke((MethodInvoker)delegate
                {
                    bt_conncetion.Enabled = false;
                });
            }
            else
            {
                bt_conncetion.Enabled = false;
            }

            // 접속 시도
            try
            {
                client = new TcpClient();
                IPEndPoint ipEnd = new IPEndPoint(IPAddress.Parse(str_serverURL), int.Parse(str_serverPort));
                client.Connect(ipEnd);

                // 접속 성공
                connection_successful();
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : 접속에 성공하였습니다 " + str_serverURL + ":" + str_serverPort);
                isConnection= true;

                // 최초 table 먼저 list에 표시하기 (bt_book Click Listener 활용)
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

                streamReader = new StreamReader(client.GetStream());  // 읽기 스트림
                streamWriter = new StreamWriter(client.GetStream());  // 쓰기 스트림
                streamWriter.AutoFlush = true;  // 쓰기 버퍼

                while (true)
                {
                    // 서버가 보낸 메시지를 읽음
                    string receiveData = streamReader.ReadLine();
                    // 서버가 닫힌 것이 확인되면
                    if (receiveData == null || receiveData.Equals("exit"))
                    {
                        writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : 서버로부터 통신이 종료되었습니다");
                        streamReader.Close();
                        streamWriter.Close();
                        client.Close();
                        isConnection = false;
                        isSuccess = false;

                        // 리스트 비우기
                        if(list_table.InvokeRequired == true)
                        {
                            list_table.Invoke((MethodInvoker)delegate
                            {
                                list_table.Items.Clear();
                            });
                        }
                        else
                        {
                            list_table.Items.Clear();
                        }
                        // 폼 활성, 비활성
                        disconnect();
                        break;
                    }

                    // 그 외 메시지가 들어오면 (Insert, Update)
                    // RichBox에 표시하고 List 업데이트
                    writeRichBox(receiveData);
                    if (isSuccess)
                    {
                        // 접속table에 따라
                        if (connMode == 1)
                        {
                            str_commandText = str_selectBook;
                        }
                        else if (connMode == 2)
                        {
                            str_commandText = str_selectCar;
                        }
                        DB_Connection();
                    }
                }
            }
            catch (SocketException e)
            {
                //print_error(e);
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : "+ e.Message);
                disconnect();
            }
        }

        // =================================== DB 조작 ===================================

        // DB 접속하기
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
            if (list_table.InvokeRequired == true)
            {
                list_table.Invoke((MethodInvoker)delegate
                {
                    list_table.Items.Clear();
                });
            }
            else
            {
                list_table.Items.Clear();
            }

            using (var reader = cmd.ExecuteReader())
            {
                ListViewItem item = null;

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
                        if (list_table.InvokeRequired == true)
                        {
                            list_table.Invoke((MethodInvoker)delegate
                            {
                                list_table.Items.Add(item);
                            });
                        }
                        else
                        {
                            list_table.Items.Add(item);
                        }
                    }
                    else if (connMode == 2)
                    {
                        string[] row = { reader["number"].ToString(),
                                                      reader["name"].ToString(),
                                                      reader["company"].ToString(),
                                                      date.ToString("yyyy-MM-dd")};
                        item = new ListViewItem(row);
                        if (list_table.InvokeRequired == true)
                        {
                            list_table.Invoke((MethodInvoker)delegate
                            {
                                list_table.Items.Add(item);
                            });
                        }
                        else
                        {
                            list_table.Items.Add(item);
                        }
                        init_Form();
                    }
                    
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
                        streamWriter.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : \"" + result + "\" 검색 알림");
                    }
                }
            }
        }

        // DB 데이터 삭제하기
        private void DB_Delete(string input, string inputName)
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

                        // 리스트 싹다 지운뒤 데이터 다시 표시
                        list_table.Items.Clear();
                        if (connMode == 1)
                        {
                            str_commandText = str_selectBook;
                        }
                        else if (connMode == 2)
                        {
                            str_commandText = str_selectCar;
                        }
                        cmd.CommandText = str_commandText;
                        Reading_Data(cmd);
                        
                        // 소켓 접속 판단
                        if(isConnection == true)
                        {
                            // 접속 테이블대로 어느 테이블에서 DELETE 한 건지 보내기
                            string mode = "";
                            if (connMode == 1)
                                mode = "BOOK TABLE";
                            if (connMode == 2)
                                mode = "CAR TABLE";
                            streamWriter.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : " + mode + " \"" + inputName + "\" DELETE 알림");
                        }
                    }
                }
                catch (Exception e)
                {
                    print_error(e);
                }
            }
        }

        // =================================== 버튼 클릭 ===================================

        // Book 버튼
        private void bt_book_Click(object sender, EventArgs e)
        {
            connMode = 1;
            str_commandText = str_selectBook;
            DB_Connection();
        }

        // Car 버튼
        private void bt_car_Click(object sender, EventArgs e)
        {
            connMode = 2;
            str_commandText = str_selectCar;
            DB_Connection();
        }

        // Connection 버튼
        private void bt_conncetion_Click(object sender, EventArgs e)
        {
            socket_start();
        }

        // Disconnection 버튼
        private void bt_disconnection_Click(object sender, EventArgs e)
        {
            if (isConnection == true)
            {
                streamWriter.WriteLine("exit");
            }
            disconnect();
        }

        // Search 버튼
        private void bt_search_Click(object sender, EventArgs e)
        {
            // 테이블엔 접속했는지
            if (isSuccess == false)
            {
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : 접속한 테이블이 없습니다");
                return;
            }

            string result = txt_search.Text; // 검색창 입력값 받아오기

            // 검색창이 비워져 있으면 전체 리스트 재조회
            if (result.Equals(""))
            {
                if (connMode == 1)
                {
                    str_commandText = str_selectBook;
                }
                else if (connMode == 2)
                {
                    str_commandText = str_selectCar;
                }
            }
            else
            {
                if (connMode == 1)
                {
                    str_commandText = "select * from book where replace(cast(isbn as text),' ','') like '%" + result +
                                    "%' or replace(lower(name), ' ', '') like lower('%" + result +
                                    "%') or replace(lower(author), ' ', '') like lower('%" + result +
                                    "%') or cast(date as varchar(20)) like '%" + result + "%' order by name";
                }
                else if (connMode == 2)
                {
                    str_commandText = "select * from car where lower(number) like lower('%" + result +
                                    "%') or replace(lower(name), ' ', '') like lower('%" + result +
                                    "%') or lower(company) like lower('%" + result +
                                    "%') or cast(date as varchar(20)) like '%" + result + "%' order by name";
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
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : 접속한 테이블이 없습니다");
                return;
            }

            // 리스트 아이템 선택 했는지
            bool selected = list_table.SelectedItems.Count > 0;
            if (selected == false)
            {
                writeRichBox(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : 선택된 아이템이 없습니다");
                return;
            }

            ListViewItem item = list_table.SelectedItems[0];
            string selectString = item.SubItems[0].Text;
            string selectString_2 = item.SubItems[1].Text;

            DB_Delete(selectString, selectString_2);
            //list_table.EnsureVisible(list_table.Items.Count - 1);
        }

        /*
        // List 마우스 클릭
        private void list_table_Click(object sender, EventArgs e)
        {
            // 선택 값 
            bool selected = list_table.SelectedItems.Count > 0;
        }
        */

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

        // 폼이 종료될 때
        private void Form_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (isConnection == true)
            {
                streamWriter.WriteLine("exit");
            }
        }
    }
}
