namespace DB_Connection_Client
{
    partial class group_insert
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
            this.list = new System.Windows.Forms.ListView();
            this.ch_primary = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_humen = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_date = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.bt_book = new System.Windows.Forms.Button();
            this.bt_car = new System.Windows.Forms.Button();
            this.bt_delete = new System.Windows.Forms.Button();
            this.txt_search = new System.Windows.Forms.TextBox();
            this.bt_search_primary = new System.Windows.Forms.Button();
            this.group_search = new System.Windows.Forms.GroupBox();
            this.group_insert_update = new System.Windows.Forms.GroupBox();
            this.label_date = new System.Windows.Forms.Label();
            this.label_Author = new System.Windows.Forms.Label();
            this.label_name = new System.Windows.Forms.Label();
            this.label_primary = new System.Windows.Forms.Label();
            this.bt_update = new System.Windows.Forms.Button();
            this.bt_insert = new System.Windows.Forms.Button();
            this.txt_date = new System.Windows.Forms.TextBox();
            this.txt_infor = new System.Windows.Forms.TextBox();
            this.txt_name = new System.Windows.Forms.TextBox();
            this.txt_primary = new System.Windows.Forms.TextBox();
            this.txt_ip = new System.Windows.Forms.TextBox();
            this.bt_conncetion = new System.Windows.Forms.Button();
            this.richTextBox1 = new System.Windows.Forms.RichTextBox();
            this.group_search.SuspendLayout();
            this.group_insert_update.SuspendLayout();
            this.SuspendLayout();
            // 
            // list
            // 
            this.list.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.list.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.ch_primary,
            this.ch_name,
            this.ch_humen,
            this.ch_date});
            this.list.FullRowSelect = true;
            this.list.HideSelection = false;
            this.list.Location = new System.Drawing.Point(12, 130);
            this.list.Name = "list";
            this.list.Size = new System.Drawing.Size(533, 127);
            this.list.TabIndex = 0;
            this.list.UseCompatibleStateImageBehavior = false;
            this.list.View = System.Windows.Forms.View.Details;
            this.list.MouseClick += new System.Windows.Forms.MouseEventHandler(this.list_MouseClick);
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
            // ch_humen
            // 
            this.ch_humen.Text = "Author";
            this.ch_humen.Width = 110;
            // 
            // ch_date
            // 
            this.ch_date.Text = "Date";
            this.ch_date.Width = 80;
            // 
            // bt_book
            // 
            this.bt_book.Location = new System.Drawing.Point(12, 269);
            this.bt_book.Name = "bt_book";
            this.bt_book.Size = new System.Drawing.Size(254, 23);
            this.bt_book.TabIndex = 1;
            this.bt_book.Text = "BOOK";
            this.bt_book.UseVisualStyleBackColor = true;
            // 
            // bt_car
            // 
            this.bt_car.Location = new System.Drawing.Point(291, 269);
            this.bt_car.Name = "bt_car";
            this.bt_car.Size = new System.Drawing.Size(254, 23);
            this.bt_car.TabIndex = 2;
            this.bt_car.Text = "CAR";
            this.bt_car.UseVisualStyleBackColor = true;
            // 
            // bt_delete
            // 
            this.bt_delete.Location = new System.Drawing.Point(0, 27);
            this.bt_delete.Name = "bt_delete";
            this.bt_delete.Size = new System.Drawing.Size(533, 23);
            this.bt_delete.TabIndex = 3;
            this.bt_delete.Text = "DELETE";
            this.bt_delete.UseVisualStyleBackColor = true;
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
            // bt_search_primary
            // 
            this.bt_search_primary.Font = new System.Drawing.Font("돋움", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.bt_search_primary.Location = new System.Drawing.Point(437, 0);
            this.bt_search_primary.Name = "bt_search_primary";
            this.bt_search_primary.Size = new System.Drawing.Size(96, 23);
            this.bt_search_primary.TabIndex = 6;
            this.bt_search_primary.Text = "SEARCH";
            this.bt_search_primary.UseVisualStyleBackColor = true;
            // 
            // group_search
            // 
            this.group_search.BackColor = System.Drawing.SystemColors.Control;
            this.group_search.Controls.Add(this.txt_search);
            this.group_search.Controls.Add(this.bt_delete);
            this.group_search.Controls.Add(this.bt_search_primary);
            this.group_search.ForeColor = System.Drawing.SystemColors.Desktop;
            this.group_search.Location = new System.Drawing.Point(12, 298);
            this.group_search.Name = "group_search";
            this.group_search.Size = new System.Drawing.Size(533, 51);
            this.group_search.TabIndex = 7;
            this.group_search.TabStop = false;
            // 
            // group_insert_update
            // 
            this.group_insert_update.BackColor = System.Drawing.SystemColors.Window;
            this.group_insert_update.Controls.Add(this.label_date);
            this.group_insert_update.Controls.Add(this.label_Author);
            this.group_insert_update.Controls.Add(this.label_name);
            this.group_insert_update.Controls.Add(this.label_primary);
            this.group_insert_update.Controls.Add(this.bt_update);
            this.group_insert_update.Controls.Add(this.bt_insert);
            this.group_insert_update.Controls.Add(this.txt_date);
            this.group_insert_update.Controls.Add(this.txt_infor);
            this.group_insert_update.Controls.Add(this.txt_name);
            this.group_insert_update.Controls.Add(this.txt_primary);
            this.group_insert_update.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.group_insert_update.Location = new System.Drawing.Point(0, 355);
            this.group_insert_update.Name = "group_insert_update";
            this.group_insert_update.Size = new System.Drawing.Size(557, 79);
            this.group_insert_update.TabIndex = 8;
            this.group_insert_update.TabStop = false;
            // 
            // label_date
            // 
            this.label_date.AutoSize = true;
            this.label_date.Font = new System.Drawing.Font("돋움", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label_date.Location = new System.Drawing.Point(426, 4);
            this.label_date.Name = "label_date";
            this.label_date.Size = new System.Drawing.Size(40, 16);
            this.label_date.TabIndex = 9;
            this.label_date.Text = "Date";
            // 
            // label_Author
            // 
            this.label_Author.AutoSize = true;
            this.label_Author.Font = new System.Drawing.Font("돋움", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label_Author.Location = new System.Drawing.Point(288, 4);
            this.label_Author.Name = "label_Author";
            this.label_Author.Size = new System.Drawing.Size(55, 16);
            this.label_Author.TabIndex = 8;
            this.label_Author.Text = "Author";
            // 
            // label_name
            // 
            this.label_name.AutoSize = true;
            this.label_name.Font = new System.Drawing.Font("돋움", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label_name.Location = new System.Drawing.Point(147, 4);
            this.label_name.Name = "label_name";
            this.label_name.Size = new System.Drawing.Size(49, 16);
            this.label_name.TabIndex = 7;
            this.label_name.Text = "Name";
            // 
            // label_primary
            // 
            this.label_primary.AutoSize = true;
            this.label_primary.Font = new System.Drawing.Font("돋움", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label_primary.Location = new System.Drawing.Point(12, 4);
            this.label_primary.Name = "label_primary";
            this.label_primary.Size = new System.Drawing.Size(41, 16);
            this.label_primary.TabIndex = 6;
            this.label_primary.Text = "ISBN";
            // 
            // bt_update
            // 
            this.bt_update.Location = new System.Drawing.Point(291, 50);
            this.bt_update.Name = "bt_update";
            this.bt_update.Size = new System.Drawing.Size(254, 23);
            this.bt_update.TabIndex = 5;
            this.bt_update.Text = "Update";
            this.bt_update.UseVisualStyleBackColor = true;
            // 
            // bt_insert
            // 
            this.bt_insert.Location = new System.Drawing.Point(12, 50);
            this.bt_insert.Name = "bt_insert";
            this.bt_insert.Size = new System.Drawing.Size(254, 23);
            this.bt_insert.TabIndex = 4;
            this.bt_insert.Text = "Insert";
            this.bt_insert.UseVisualStyleBackColor = true;
            // 
            // txt_date
            // 
            this.txt_date.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_date.Location = new System.Drawing.Point(429, 23);
            this.txt_date.Name = "txt_date";
            this.txt_date.Size = new System.Drawing.Size(116, 21);
            this.txt_date.TabIndex = 3;
            // 
            // txt_infor
            // 
            this.txt_infor.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_infor.Location = new System.Drawing.Point(291, 23);
            this.txt_infor.Name = "txt_infor";
            this.txt_infor.Size = new System.Drawing.Size(116, 21);
            this.txt_infor.TabIndex = 2;
            // 
            // txt_name
            // 
            this.txt_name.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_name.Location = new System.Drawing.Point(150, 23);
            this.txt_name.Name = "txt_name";
            this.txt_name.Size = new System.Drawing.Size(116, 21);
            this.txt_name.TabIndex = 1;
            // 
            // txt_primary
            // 
            this.txt_primary.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_primary.Location = new System.Drawing.Point(12, 23);
            this.txt_primary.Name = "txt_primary";
            this.txt_primary.Size = new System.Drawing.Size(116, 21);
            this.txt_primary.TabIndex = 0;
            // 
            // txt_ip
            // 
            this.txt_ip.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.txt_ip.Location = new System.Drawing.Point(12, 12);
            this.txt_ip.Name = "txt_ip";
            this.txt_ip.Size = new System.Drawing.Size(431, 21);
            this.txt_ip.TabIndex = 9;
            // 
            // bt_conncetion
            // 
            this.bt_conncetion.Location = new System.Drawing.Point(449, 12);
            this.bt_conncetion.Name = "bt_conncetion";
            this.bt_conncetion.Size = new System.Drawing.Size(96, 23);
            this.bt_conncetion.TabIndex = 10;
            this.bt_conncetion.Text = "connection";
            this.bt_conncetion.UseVisualStyleBackColor = true;
            this.bt_conncetion.Click += new System.EventHandler(this.bt_conncetion_Click);
            // 
            // richTextBox1
            // 
            this.richTextBox1.Location = new System.Drawing.Point(12, 39);
            this.richTextBox1.Name = "richTextBox1";
            this.richTextBox1.Size = new System.Drawing.Size(533, 85);
            this.richTextBox1.TabIndex = 11;
            this.richTextBox1.Text = "";
            // 
            // group_insert
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(557, 434);
            this.Controls.Add(this.richTextBox1);
            this.Controls.Add(this.bt_conncetion);
            this.Controls.Add(this.txt_ip);
            this.Controls.Add(this.group_search);
            this.Controls.Add(this.bt_car);
            this.Controls.Add(this.bt_book);
            this.Controls.Add(this.list);
            this.Controls.Add(this.group_insert_update);
            this.Name = "group_insert";
            this.Text = "데이터베이스";
            this.group_search.ResumeLayout(false);
            this.group_search.PerformLayout();
            this.group_insert_update.ResumeLayout(false);
            this.group_insert_update.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView list;
        private System.Windows.Forms.ColumnHeader ch_primary;
        private System.Windows.Forms.ColumnHeader ch_name;
        private System.Windows.Forms.ColumnHeader ch_humen;
        private System.Windows.Forms.ColumnHeader ch_date;
        private System.Windows.Forms.Button bt_book;
        private System.Windows.Forms.Button bt_car;
        private System.Windows.Forms.Button bt_delete;
        private System.Windows.Forms.TextBox txt_search;
        private System.Windows.Forms.Button bt_search_primary;
        private System.Windows.Forms.GroupBox group_search;
        private System.Windows.Forms.GroupBox group_insert_update;
        private System.Windows.Forms.Button bt_update;
        private System.Windows.Forms.Button bt_insert;
        private System.Windows.Forms.TextBox txt_date;
        private System.Windows.Forms.TextBox txt_infor;
        private System.Windows.Forms.TextBox txt_name;
        private System.Windows.Forms.TextBox txt_primary;
        private System.Windows.Forms.Label label_date;
        private System.Windows.Forms.Label label_Author;
        private System.Windows.Forms.Label label_name;
        private System.Windows.Forms.Label label_primary;
        private System.Windows.Forms.TextBox txt_ip;
        private System.Windows.Forms.Button bt_conncetion;
        private System.Windows.Forms.RichTextBox richTextBox1;
    }
}

