namespace DB_Connection_Client
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
            this.list = new System.Windows.Forms.ListView();
            this.ch_primary = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_humen = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ch_date = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.bt_book = new System.Windows.Forms.Button();
            this.bt_car = new System.Windows.Forms.Button();
            this.bt_delete = new System.Windows.Forms.Button();
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
            this.list.HideSelection = false;
            this.list.Location = new System.Drawing.Point(12, 12);
            this.list.Name = "list";
            this.list.Size = new System.Drawing.Size(369, 127);
            this.list.TabIndex = 0;
            this.list.UseCompatibleStateImageBehavior = false;
            this.list.View = System.Windows.Forms.View.Details;
            // 
            // ch_primary
            // 
            this.ch_primary.Text = "ISBN";
            this.ch_primary.Width = 90;
            // 
            // ch_name
            // 
            this.ch_name.Text = "Name";
            this.ch_name.Width = 80;
            // 
            // ch_humen
            // 
            this.ch_humen.Text = "Author";
            this.ch_humen.Width = 80;
            // 
            // ch_date
            // 
            this.ch_date.Text = "Date";
            this.ch_date.Width = 120;
            // 
            // bt_book
            // 
            this.bt_book.Location = new System.Drawing.Point(12, 145);
            this.bt_book.Name = "bt_book";
            this.bt_book.Size = new System.Drawing.Size(173, 23);
            this.bt_book.TabIndex = 1;
            this.bt_book.Text = "BOOK";
            this.bt_book.UseVisualStyleBackColor = true;
            // 
            // bt_car
            // 
            this.bt_car.Location = new System.Drawing.Point(208, 145);
            this.bt_car.Name = "bt_car";
            this.bt_car.Size = new System.Drawing.Size(173, 23);
            this.bt_car.TabIndex = 2;
            this.bt_car.Text = "CAR";
            this.bt_car.UseVisualStyleBackColor = true;
            // 
            // bt_delete
            // 
            this.bt_delete.Location = new System.Drawing.Point(12, 174);
            this.bt_delete.Name = "bt_delete";
            this.bt_delete.Size = new System.Drawing.Size(369, 23);
            this.bt_delete.TabIndex = 3;
            this.bt_delete.Text = "DELETE";
            this.bt_delete.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(393, 372);
            this.Controls.Add(this.bt_delete);
            this.Controls.Add(this.bt_car);
            this.Controls.Add(this.bt_book);
            this.Controls.Add(this.list);
            this.Name = "Form1";
            this.Text = "데이터베이스";
            this.ResumeLayout(false);

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
    }
}

