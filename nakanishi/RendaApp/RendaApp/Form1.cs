using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RendaApp
{
    public partial class Form1 : Form
    {
        int count = 0;
        int duration = 30;

        public Form1()
        {
            InitializeComponent();

            label1.Text = "残り時間";
            label2.Text = "クリック回数";

            counter.Text = count.ToString();
            timer.Text = duration.ToString();

            timer1 = new System.Windows.Forms.Timer();
            timer1.Tick += new EventHandler(count_down);
            timer1.Interval = 1000;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            timer1.Start();

            if (duration > 0)
            {
                count++;
                counter.Text = count.ToString();
            }
            
        }

        private void count_down(object sender, EventArgs e)
        {

            if (duration == 0)
            {
                timer1.Stop();

            }
            else if (duration > 0)
            {
                duration--;
                timer.Text = duration.ToString();
            }
        }
    }
}
