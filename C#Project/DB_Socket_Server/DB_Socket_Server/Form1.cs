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

namespace DB_Socket_Server
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            start();
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
            string str_ip = "127.0.0.1";
            string str_port = "5555";
            TcpListener tcpListener = new TcpListener(IPAddress.Parse(str_ip), int.Parse(str_port));
            tcpListener.Start(); // 서버 시작
            writeRichTextbox("서버 준비...");

            TcpClient tcpClient1 = tcpListener.AcceptTcpClient();
            writeRichTextbox("클라이언트 연결됨...");

            streamReader1 = new StreamReader(tcpClient1.GetStream());  // 읽기 스트림 연결
            streamWriter1 = new StreamWriter(tcpClient1.GetStream());  // 쓰기 스트림 연결
            streamWriter1.AutoFlush = true;  // 쓰기 버퍼 자동으로 뭔가 처리..

            while (tcpClient1.Connected)  // 클라이언트가 연결되어 있는 동안
            {
                string receiveData1 = streamReader1.ReadLine();  // 수신 데이타를 읽어서 receiveData1 변수에 저장
                writeRichTextbox(receiveData1); // 데이타를 수신창에 쓰기                  
            }
        }

        private void writeRichTextbox(string str)  // richTextbox1 에 쓰기 함수
        {
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.AppendText(str + "\r\n"); }); // 데이타를 수신창에 표시, 반드시 invoke 사용. 충돌피함.
            richTextBox1.Invoke((MethodInvoker)delegate { richTextBox1.ScrollToCaret(); });  // 스크롤을 젤 밑으로.
        }

        private void button1_Click(object sender, EventArgs e)
        {
            streamWriter1.WriteLine("서버에서 보냄");
        }
    }
}
