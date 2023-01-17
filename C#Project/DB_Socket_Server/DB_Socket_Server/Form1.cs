using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading; // 추가
using System.Net; // 추가
using System.Net.Sockets; // 추가
using System.IO; // 추가

/*
 * 서버 달라진 점
 * 
 * 1. connect() 메소드 접속 부분 try catch로 감쌈
 * 2. connect() 내부 while문 조건 바뀜
 * 3. 내부적으로 상대 프로그램이 종료되었을 때 접속을 끊는 부분 추가함
 * 4. winform 닫기 버튼을 눌렀을 때 상대 프로그램으로 메시지 전송
 */

namespace DB_Socket_Server
{
    public partial class Form1 : Form
    {
        bool isconnection = false;
        public Form1()
        {
            InitializeComponent();
        }

        private void start()
        {
            Thread thread = new Thread(connect);
            thread.IsBackground= true;
            thread.Start();
        }

        StreamReader streamReader1;  // 데이타 읽기 위한 스트림리더
        StreamWriter streamWriter1;  // 데이타 쓰기 위한 스트림라이터

        private void connect()
        {
            if(txt_ip.Text.Equals(""))
            {
                return;
            }

            string str_ip = txt_ip.Text;
            string str_port = "5555";

            try
            {
                TcpListener tcpListener = new TcpListener(IPAddress.Parse(str_ip), int.Parse(str_port));
                tcpListener.Start(); // 서버 시작
                writeRichTextbox("서버 준비...");

                while(true)
                {
                    isconnection = true;
                    TcpClient tcpClient1 = tcpListener.AcceptTcpClient();
                    writeRichTextbox("클라이언트 연결됨...");

                    streamReader1 = new StreamReader(tcpClient1.GetStream());
                    streamWriter1 = new StreamWriter(tcpClient1.GetStream());
                    streamWriter1.AutoFlush = true;

                    while (true)
                    {
                        string receiveData1 = streamReader1.ReadLine();
                        if (receiveData1 == null || receiveData1.Equals("exit"))
                        {
                            isconnection = false;
                            writeRichTextbox("클라이언트 종료됨...\n");
                            streamReader1.Close();
                            streamWriter1.Close();
                            tcpClient1.Close();
                            break;
                        }
                        writeRichTextbox(receiveData1);
                    }
                    
                }
            }
            catch (SocketException e)
            {
                Console.WriteLine(e.ToString());
            }
        }

        private void writeRichTextbox(string str) 
        {
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.AppendText(str + "\r\n"); });
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.ScrollToCaret(); }); 
        }

        private void button1_Click(object sender, EventArgs e)
        {
            streamWriter1.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : insert를 알림");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            streamWriter1.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + " : update를 알림");
        }

        private void bt_connection_Click(object sender, EventArgs e)
        {
            start();
        }

        // 닫기 버튼을 눌러 프로그램을 종료하였을 때 클라이언트로 메시지 전송
        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (isconnection == true)
            {
                streamWriter1.WriteLine("exit");
            }
        }
    }
}
