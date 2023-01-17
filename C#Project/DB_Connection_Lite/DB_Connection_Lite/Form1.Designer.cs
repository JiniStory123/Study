namespace DB_Connection_Lite
{
    partial class Form1
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
            this.rich_state = new System.Windows.Forms.RichTextBox();
            this.bt_conncetion = new System.Windows.Forms.Button();
            this.txt_ip = new System.Windows.Forms.TextBox();
            this.group_search = new System.Windows.Forms.GroupBox();
            this.txt_search = new System.Windows.Forms.TextBox();
            this.bt_delete = new System.Windows.Forms.Button();
            this.bt_search = new System.Windows.Forms.Button();
            this.bt_car = new System.Windows.Forms.Button();
            this.bt_book = new System.Windows.Forms.Button();
            this.list_table = new System.Windows.Forms.ListView();
            this.ch_primary = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_infor = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_date = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.group_search.SuspendLayout();
            this.SuspendLayout();
            // 
            // rich_state
            // 
            this.rich_state.Location = new System.Drawing.Point(12, 39);
            this.rich_state.Name = "rich_state";
            this.rich_state.Size = new System.Drawing.Size(533, 85);
            this.rich_state.TabIndex = 18;
            this.rich_state.Text = "";
            // 
            // bt_conncetion
            // 
            this.bt_conncetion.Location = new System.Drawing.Point(449, 12);
            this.bt_conncetion.Name = "bt_conncetion";
            this.bt_conncetion.Size = new System.Drawing.Size(96, 23);
            this.bt_conncetion.TabIndex = 17;
            this.bt_conncetion.Text = "connection";
            this.bt_conncetion.UseVisualStyleBackColor = true;
            this.bt_conncetion.Click += new System.EventHandler(this.bt_conncetion_Click);
            // 
            // txt_ip
            // 
            this.txt_ip.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_ip.Location = new System.Drawing.Point(12, 12);
            this.txt_ip.Name = "txt_ip";
            this.txt_ip.Size = new System.Drawing.Size(431, 21);
            this.txt_ip.TabIndex = 16;
            // 
            // group_search
            // 
            this.group_search.BackColor = System.Drawing.SystemColors.Control;
            this.group_search.Controls.Add(this.txt_search);
            this.group_search.Controls.Add(this.bt_delete);
            this.group_search.Controls.Add(this.bt_search);
            this.group_search.ForeColor = System.Drawing.SystemColors.Desktop;
            this.group_search.Location = new System.Drawing.Point(12, 298);
            this.group_search.Name = "group_search";
            this.group_search.Size = new System.Drawing.Size(533, 51);
            this.group_search.TabIndex = 15;
            this.group_search.TabStop = false;
            // 
            // txt_search
            // 
            this.txt_search.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_search.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.txt_search.Location = new System.Drawing.Point(0, 0);
            this.txt_search.Name = "txt_search";
            this.txt_search.Size = new System.Drawing.Size(431, 21);
            this.txt_search.TabIndex = 4;
            // 
            // bt_delete
            // 
            this.bt_delete.Location = new System.Drawing.Point(0, 27);
            this.bt_delete.Name = "bt_delete";
            this.bt_delete.Size = new System.Drawing.Size(533, 23);
            this.bt_delete.TabIndex = 3;
            this.bt_delete.Text = "DELETE";
            this.bt_delete.UseVisualStyleBackColor = true;
            this.bt_delete.Click += new System.EventHandler(this.bt_delete_Click);
            // 
            // bt_search
            // 
            this.bt_search.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.bt_search.Location = new System.Drawing.Point(437, 0);
            this.bt_search.Name = "bt_search";
            this.bt_search.Size = new System.Drawing.Size(96, 23);
            this.bt_search.TabIndex = 6;
            this.bt_search.Text = "SEARCH";
            this.bt_search.UseVisualStyleBackColor = true;
            this.bt_search.Click += new System.EventHandler(this.bt_search_Click);
            // 
            // bt_car
            // 
            this.bt_car.Location = new System.Drawing.Point(291, 269);
            this.bt_car.Name = "bt_car";
            this.bt_car.Size = new System.Drawing.Size(254, 23);
            this.bt_car.TabIndex = 14;
            this.bt_car.Text = "CAR";
            this.bt_car.UseVisualStyleBackColor = true;
            this.bt_car.Click += new System.EventHandler(this.bt_car_Click);
            // 
            // bt_book
            // 
            this.bt_book.Location = new System.Drawing.Point(12, 269);
            this.bt_book.Name = "bt_book";
            this.bt_book.Size = new System.Drawing.Size(254, 23);
            this.bt_book.TabIndex = 13;
            this.bt_book.Text = "BOOK";
            this.bt_book.UseVisualStyleBackColor = true;
            this.bt_book.Click += new System.EventHandler(this.bt_book_Click);
            // 
            // list_table
            // 
            this.list_table.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.list_table.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.ch_primary,
            this.ch_name,
            this.ch_infor,
            this.ch_date});
            this.list_table.FullRowSelect = true;
            this.list_table.HideSelection = false;
            this.list_table.Location = new System.Drawing.Point(12, 130);
            this.list_table.Name = "list_table";
            this.list_table.Size = new System.Drawing.Size(533, 127);
            this.list_table.TabIndex = 12;
            this.list_table.UseCompatibleStateImageBehavior = false;
            this.list_table.View = System.Windows.Forms.View.Details;
            this.list_table.Click += new System.EventHandler(this.list_table_Click);
            // 
            // ch_primary
            // 
            this.ch_primary.Text = "ISBN";
            this.ch_primary.Width = 120;
            // 
            // ch_name
            // 
            this.ch_name.Text = "Name";
            this.ch_name.Width = 200;
            // 
            // ch_infor
            // 
            this.ch_infor.Text = "Author";
            this.ch_infor.Width = 110;
            // 
            // ch_date
            // 
            this.ch_date.Text = "Date";
            this.ch_date.Width = 80;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(556, 360);
            this.Controls.Add(this.rich_state);
            this.Controls.Add(this.bt_conncetion);
            this.Controls.Add(this.txt_ip);
            this.Controls.Add(this.group_search);
            this.Controls.Add(this.bt_car);
            this.Controls.Add(this.bt_book);
            this.Controls.Add(this.list_table);
            this.Name = "Form1";
            this.Text = "데이터베이스";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.group_search.ResumeLayout(false);
            this.group_search.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RichTextBox rich_state;
        private System.Windows.Forms.Button bt_conncetion;
        private System.Windows.Forms.TextBox txt_ip;
        private System.Windows.Forms.GroupBox group_search;
        private System.Windows.Forms.TextBox txt_search;
        private System.Windows.Forms.Button bt_delete;
        private System.Windows.Forms.Button bt_search;
        private System.Windows.Forms.Button bt_car;
        private System.Windows.Forms.Button bt_book;
        private System.Windows.Forms.ListView list_table;
        private System.Windows.Forms.ColumnHeader ch_primary;
        private System.Windows.Forms.ColumnHeader ch_name;
        private System.Windows.Forms.ColumnHeader ch_infor;
        private System.Windows.Forms.ColumnHeader ch_date;
    }
}

