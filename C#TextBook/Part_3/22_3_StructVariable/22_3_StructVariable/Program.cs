using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _22_3_StructVariable
{
    internal class Program
    {
        struct Product
        {
            public int Id;
            public string Title;
            public decimal Price;
        }

        static void Main(string[] args)
        {
            Product product;

            product.Id = 1;
            product.Title = "좋은 책";
            product.Price = 10000M;

            string message = $"번호 : {product.Id}\n상품명 : {product.Title}\n가격 : {product.Price}";
            Console.WriteLine(message);
        }
    }
}
