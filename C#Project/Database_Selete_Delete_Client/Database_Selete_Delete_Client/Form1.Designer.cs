namespace Database_Selete_Delete_Client
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
            this.listView1 = new System.Windows.Forms.ListView();
            this.ch_name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_author = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_date = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.bt_book = new System.Windows.Forms.Button();
            this.bt_car = new System.Windows.Forms.Button();
            this.bt_delete = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // listView1
            // 
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.ch_name,
            this.ch_author,
            this.ch_date});
            this.listView1.FullRowSelect = true;
            this.listView1.HideSelection = false;
            this.listView1.Location = new System.Drawing.Point(13, 12);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(321, 109);
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            // 
            // ch_name
            // 
            this.ch_name.Text = "";
            this.ch_name.Width = 120;
            // 
            // ch_author
            // 
            this.ch_author.Text = "";
            this.ch_author.Width = 90;
            // 
            // ch_date
            // 
            this.ch_date.Text = "";
            this.ch_date.Width = 100;
            // 
            // bt_book
            // 
            this.bt_book.Location = new System.Drawing.Point(13, 127);
            this.bt_book.Name = "bt_book";
            this.bt_book.Size = new System.Drawing.Size(149, 23);
            this.bt_book.TabIndex = 1;
            this.bt_book.Text = "BOOK";
            this.bt_book.UseVisualStyleBackColor = true;
            // 
            // bt_car
            // 
            this.bt_car.Location = new System.Drawing.Point(185, 127);
            this.bt_car.Name = "bt_car";
            this.bt_car.Size = new System.Drawing.Size(149, 23);
            this.bt_car.TabIndex = 2;
            this.bt_car.Text = "CAR";
            this.bt_car.UseVisualStyleBackColor = true;
            // 
            // bt_delete
            // 
            this.bt_delete.Location = new System.Drawing.Point(13, 156);
            this.bt_delete.Name = "bt_delete";
            this.bt_delete.Size = new System.Drawing.Size(321, 23);
            this.bt_delete.TabIndex = 3;
            this.bt_delete.Text = "DELETE";
            this.bt_delete.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(346, 323);
            this.Controls.Add(this.bt_delete);
            this.Controls.Add(this.bt_car);
            this.Controls.Add(this.bt_book);
            this.Controls.Add(this.listView1);
            this.Name = "Form1";
            this.Text = "데이터베이스 테스트";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.ColumnHeader ch_name;
        private System.Windows.Forms.ColumnHeader ch_author;
        private System.Windows.Forms.ColumnHeader ch_date;
        private System.Windows.Forms.Button bt_book;
        private System.Windows.Forms.Button bt_car;
        private System.Windows.Forms.Button bt_delete;
    }
}

