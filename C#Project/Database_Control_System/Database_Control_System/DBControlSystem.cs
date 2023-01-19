using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Database_Control_System
{
    public partial class lb_DB : Form
    {
        // 전역 변수
        bool isConnection = false;

        // 메인
        public lb_DB()
        {
            InitializeComponent();

            InitCombo();
            Disconnection_Button_State_Change();
        }

        // comboBox 초기화
        private void InitCombo()
        {
            string[] data = { "MySQL", "PostgreSQL" };

            cb_DBList.Items.AddRange(data);
            cb_DBList.SelectedIndex = 0;
        }

        private void Connection_Button_State_Change()
        {
            // 연결 버튼
            if(bt_connection.InvokeRequired == true)
            {
                bt_connection.Invoke((MethodInvoker)delegate
                {
                    bt_connection.Enabled = false;
                });
            }
            else
            {
                bt_connection.Enabled = false;
            }
            // 연결 해제 버튼
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
            // 데이터베이스 선택 콤보 박스
            if (cb_DBList.InvokeRequired == true)
            {
                cb_DBList.Invoke((MethodInvoker)delegate
                {
                    cb_DBList.Enabled = false;
                });
            }
            else
            {
                cb_DBList.Enabled = false;
            }
            // 호스트 텍스트 박스
            if (tb_host.InvokeRequired == true)
            {
                tb_host.Invoke((MethodInvoker)delegate
                {
                    tb_host.Enabled = false;
                });
            }
            else
            {
                tb_host.Enabled = false;
            }
            // 이름 텍스트 박스
            if (tb_name.InvokeRequired == true)
            {
                tb_name.Invoke((MethodInvoker)delegate
                {
                    tb_name.Enabled = false;
                });
            }
            else
            {
                tb_name.Enabled = false;
            }
            // 비밀번호 텍스트 박스
            if (tb_password.InvokeRequired == true)
            {
                tb_password.Invoke((MethodInvoker)delegate
                {
                    tb_password.Enabled = false;
                });
            }
            else
            {
                tb_password.Enabled = false;
            }
            // 데이터베이스 이름 텍스트 박스
            if (tb_password.InvokeRequired == true)
            {
                tb_dbname.Invoke((MethodInvoker)delegate
                {
                    tb_dbname.Enabled = false;
                });
            }
            else
            {
                tb_dbname.Enabled = false;
            }
        }

        private void Disconnection_Button_State_Change()
        {
            // 연결 버튼
            if (bt_connection.InvokeRequired == true)
            {
                bt_connection.Invoke((MethodInvoker)delegate
                {
                    bt_connection.Enabled = true;
                });
            }
            else
            {
                bt_connection.Enabled = true;
            }
            // 연결 해제 버튼
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
            // 데이터베이스 선택 콤보 박스
            if (cb_DBList.InvokeRequired == true)
            {
                cb_DBList.Invoke((MethodInvoker)delegate
                {
                    cb_DBList.Enabled = true;
                });
            }
            else
            {
                cb_DBList.Enabled = true;
            }
            // 호스트 텍스트 박스
            if (tb_host.InvokeRequired == true)
            {
                tb_host.Invoke((MethodInvoker)delegate
                {
                    tb_host.Enabled = true;
                });
            }
            else
            {
                tb_host.Enabled = true;
            }
            // 이름 텍스트 박스
            if (tb_name.InvokeRequired == true)
            {
                tb_name.Invoke((MethodInvoker)delegate
                {
                    tb_name.Enabled = true;
                });
            }
            else
            {
                tb_name.Enabled = true;
            }
            // 비밀번호 텍스트 박스
            if (tb_password.InvokeRequired == true)
            {
                tb_password.Invoke((MethodInvoker)delegate
                {
                    tb_password.Enabled = true;
                });
            }
            else
            {
                tb_password.Enabled = true;
            }
            // 데이터베이스 이름 텍스트 박스
            if (tb_password.InvokeRequired == true)
            {
                tb_dbname.Invoke((MethodInvoker)delegate
                {
                    tb_dbname.Enabled = true;
                });
            }
            else
            {
                tb_dbname.Enabled = true;
            }
        }

        private void bt_connection_Click(object sender, EventArgs e)
        {
            Connection_Button_State_Change();
        }

        private void bt_disconnection_Click(object sender, EventArgs e)
        {
            Disconnection_Button_State_Change();
        }
    }
}
