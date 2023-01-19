namespace Database_Control_System
{
    partial class lb_DB
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.cb_DBList = new System.Windows.Forms.ComboBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.lb_host = new System.Windows.Forms.Label();
            this.tb_host = new System.Windows.Forms.TextBox();
            this.lb_password = new System.Windows.Forms.Label();
            this.tb_password = new System.Windows.Forms.TextBox();
            this.lb_ID = new System.Windows.Forms.Label();
            this.tb_name = new System.Windows.Forms.TextBox();
            this.lb_dbname = new System.Windows.Forms.Label();
            this.tb_dbname = new System.Windows.Forms.TextBox();
            this.bt_connection = new System.Windows.Forms.Button();
            this.bt_disconnection = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // cb_DBList
            // 
            this.cb_DBList.BackColor = System.Drawing.SystemColors.Window;
            this.cb_DBList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cb_DBList.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.cb_DBList.FormattingEnabled = true;
            this.cb_DBList.Location = new System.Drawing.Point(15, 33);
            this.cb_DBList.Name = "cb_DBList";
            this.cb_DBList.Size = new System.Drawing.Size(121, 20);
            this.cb_DBList.TabIndex = 0;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.bt_disconnection);
            this.groupBox1.Controls.Add(this.bt_connection);
            this.groupBox1.Controls.Add(this.tb_dbname);
            this.groupBox1.Controls.Add(this.lb_dbname);
            this.groupBox1.Controls.Add(this.tb_name);
            this.groupBox1.Controls.Add(this.lb_ID);
            this.groupBox1.Controls.Add(this.tb_password);
            this.groupBox1.Controls.Add(this.lb_password);
            this.groupBox1.Controls.Add(this.tb_host);
            this.groupBox1.Controls.Add(this.lb_host);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.cb_DBList);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(776, 95);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("돋움", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(12, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "접속 DB";
            // 
            // lb_host
            // 
            this.lb_host.AutoSize = true;
            this.lb_host.Font = new System.Drawing.Font("돋움", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.lb_host.Location = new System.Drawing.Point(164, 17);
            this.lb_host.Name = "lb_host";
            this.lb_host.Size = new System.Drawing.Size(76, 13);
            this.lb_host.TabIndex = 3;
            this.lb_host.Text = "접속 호스트";
            // 
            // tb_host
            // 
            this.tb_host.BackColor = System.Drawing.SystemColors.Window;
            this.tb_host.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.tb_host.Location = new System.Drawing.Point(167, 32);
            this.tb_host.Name = "tb_host";
            this.tb_host.Size = new System.Drawing.Size(130, 21);
            this.tb_host.TabIndex = 2;
            // 
            // lb_password
            // 
            this.lb_password.AutoSize = true;
            this.lb_password.Font = new System.Drawing.Font("돋움", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.lb_password.Location = new System.Drawing.Point(474, 17);
            this.lb_password.Name = "lb_password";
            this.lb_password.Size = new System.Drawing.Size(59, 13);
            this.lb_password.TabIndex = 4;
            this.lb_password.Text = "비밀번호";
            // 
            // tb_password
            // 
            this.tb_password.BackColor = System.Drawing.SystemColors.Window;
            this.tb_password.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.tb_password.Location = new System.Drawing.Point(477, 32);
            this.tb_password.Name = "tb_password";
            this.tb_password.PasswordChar = '●';
            this.tb_password.Size = new System.Drawing.Size(130, 21);
            this.tb_password.TabIndex = 6;
            // 
            // lb_ID
            // 
            this.lb_ID.AutoSize = true;
            this.lb_ID.Font = new System.Drawing.Font("돋움", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.lb_ID.Location = new System.Drawing.Point(319, 17);
            this.lb_ID.Name = "lb_ID";
            this.lb_ID.Size = new System.Drawing.Size(33, 13);
            this.lb_ID.TabIndex = 6;
            this.lb_ID.Text = "이름";
            // 
            // tb_name
            // 
            this.tb_name.BackColor = System.Drawing.SystemColors.Window;
            this.tb_name.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.tb_name.Location = new System.Drawing.Point(322, 32);
            this.tb_name.Name = "tb_name";
            this.tb_name.Size = new System.Drawing.Size(130, 21);
            this.tb_name.TabIndex = 5;
            // 
            // lb_dbname
            // 
            this.lb_dbname.AutoSize = true;
            this.lb_dbname.Font = new System.Drawing.Font("돋움", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.lb_dbname.Location = new System.Drawing.Point(633, 17);
            this.lb_dbname.Name = "lb_dbname";
            this.lb_dbname.Size = new System.Drawing.Size(115, 13);
            this.lb_dbname.TabIndex = 7;
            this.lb_dbname.Text = "데이터베이스 이름";
            // 
            // tb_dbname
            // 
            this.tb_dbname.BackColor = System.Drawing.SystemColors.Window;
            this.tb_dbname.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.tb_dbname.Location = new System.Drawing.Point(636, 32);
            this.tb_dbname.Name = "tb_dbname";
            this.tb_dbname.PasswordChar = '●';
            this.tb_dbname.Size = new System.Drawing.Size(130, 21);
            this.tb_dbname.TabIndex = 8;
            // 
            // bt_connection
            // 
            this.bt_connection.Location = new System.Drawing.Point(15, 59);
            this.bt_connection.Name = "bt_connection";
            this.bt_connection.Size = new System.Drawing.Size(351, 23);
            this.bt_connection.TabIndex = 9;
            this.bt_connection.Text = "연결";
            this.bt_connection.UseVisualStyleBackColor = true;
            this.bt_connection.Click += new System.EventHandler(this.bt_connection_Click);
            // 
            // bt_disconnection
            // 
            this.bt_disconnection.Location = new System.Drawing.Point(419, 59);
            this.bt_disconnection.Name = "bt_disconnection";
            this.bt_disconnection.Size = new System.Drawing.Size(351, 23);
            this.bt_disconnection.TabIndex = 10;
            this.bt_disconnection.Text = "연결 끊기";
            this.bt_disconnection.UseVisualStyleBackColor = true;
            this.bt_disconnection.Click += new System.EventHandler(this.bt_disconnection_Click);
            // 
            // lb_DB
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.groupBox1);
            this.Name = "lb_DB";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox cb_DBList;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tb_host;
        private System.Windows.Forms.Label lb_host;
        private System.Windows.Forms.TextBox tb_password;
        private System.Windows.Forms.Label lb_password;
        private System.Windows.Forms.TextBox tb_name;
        private System.Windows.Forms.Label lb_ID;
        private System.Windows.Forms.TextBox tb_dbname;
        private System.Windows.Forms.Label lb_dbname;
        private System.Windows.Forms.Button bt_disconnection;
        private System.Windows.Forms.Button bt_connection;
    }
}

