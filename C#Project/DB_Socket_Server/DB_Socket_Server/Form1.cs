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
            TcpListener tcpListener = new TcpListener(IPAddress.Parse(str_ip), int.Parse(str_port));
            tcpListener.Start(); // 서버 시작
            writeRichTextbox("서버 준비...");

            TcpClient tcpClient1 = tcpListener.AcceptTcpClient();
            writeRichTextbox("클라이언트 연결됨...");

            streamReader1 = new StreamReader(tcpClient1.GetStream());
            streamWriter1 = new StreamWriter(tcpClient1.GetStream());
            streamWriter1.AutoFlush = true;

            while (tcpClient1.Connected)
            {
                string receiveData1 = streamReader1.ReadLine();  
                writeRichTextbox(receiveData1);
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
    }
}
