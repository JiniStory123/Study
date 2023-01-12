import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

class FirmXML extends JFrame
{
    JTextField txtTitle = new JTextField(10);
    JTextField txtAuthor = new JTextField(10);
    JTextField txtAtt = new JTextField(10);
    JTextField txtPublisher = new JTextField(10);
    JTextField txtPrice = new JTextField(10);
    JButton bt1 = new JButton("다음");
    JButton bt2 = new JButton("이전");
    JButton bt3 = new JButton("수정");
    JButton bt4 = new JButton("제거");
    JButton bt5 = new JButton("추가");
    JLabel laTitle = new JLabel("제 목");
    JLabel laAuthor = new JLabel("저 자");
    JLabel laAtt = new JLabel("속 성");
    JLabel laPublisher = new JLabel("출판사");
    JLabel laPrice = new JLabel("가 격");
    Element eBook;
    Document document;
    MyActionNext next = new MyActionNext();
    MyActionPrev prev = new MyActionPrev();
    MyActionModify modify = new MyActionModify();
    MyActionRemove remove = new MyActionRemove();
    MyActionInsert insert = new MyActionInsert();

    FirmXML() throws ParserConfigurationException, SAXException, IOException
    {
        setTitle("XML JAVA GUI");
        setSize(550, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null);
        c.add(bt1);
        c.add(bt2);
        c.add(bt3);
        c.add(bt4);
        c.add(bt5);

        bt1.setSize(80, 30);
        bt1.setLocation(20, 10);
        bt2.setSize(80, 30);
        bt2.setLocation(120, 10);
        bt3.setSize(80, 30);
        bt3.setLocation(220, 10);
        bt4.setSize(80, 30);
        bt4.setLocation(320, 10);
        bt5.setSize(80, 30);
        bt5.setLocation(420, 10);

        bt1.addActionListener(next);
        bt2.addActionListener(prev);
        bt3.addActionListener(modify);
        bt4.addActionListener(remove);
        bt5.addActionListener(insert);

        c.add(laAuthor);
        laAuthor.setSize(50, 30);
        laAuthor.setLocation(50, 50);

        c.add(laAtt);
        laAtt.setSize(50, 30);
        laAtt.setLocation(50, 80);

        c.add(laTitle);
        laTitle.setSize(50, 30);
        laTitle.setLocation(50, 110);

        c.add(laPublisher);
        laPublisher.setSize(50, 30);
        laPublisher.setLocation(50, 140);

        c.add(laPrice);
        laPrice.setSize(50, 30);
        laPrice.setLocation(50, 170);

        c.add(txtTitle);
        c.add(txtAuthor);
        c.add(txtAtt);
        c.add(txtPublisher);
        c.add(txtPrice);
        txtAuthor.setSize(100, 25);
        txtAuthor.setLocation(120, 50);
        txtAtt.setSize(100, 25);
        txtAtt.setLocation(120, 80);
        txtTitle.setSize(100, 25);
        txtTitle.setLocation(120, 110);
        txtPublisher.setSize(100, 25);
        txtPublisher.setLocation(120, 140);
        txtPrice.setSize(100, 25);
        txtPrice.setLocation(120, 170);

        // DOM 파서 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        // XML 문서 파싱
        document = builder.parse("bml.xml");

        // 루트 엘리먼트 참조 얻기
        Element eRoot = document.getDocumentElement();

        // 첫번째 엘리먼트 정보 받아오기
        eBook = (Element) eRoot.getFirstChild();

        String strKind = eBook.getAttribute("kind");
        txtAtt.setText(strKind);

        Element eTitle = (Element) eBook.getFirstChild();
        String strTitle = eTitle.getTextContent();
        txtTitle.setText(strTitle);

        Element eAuthor = (Element) eTitle.getNextSibling();
        String strAuthor = eAuthor.getTextContent();
        txtAuthor.setText(strAuthor);

        Element ePublisher = (Element) eAuthor.getNextSibling();
        String strPublisher = ePublisher.getTextContent();
        txtPublisher.setText(strPublisher);

        Element ePrice = (Element) ePublisher.getNextSibling();
        String strPrice = ePrice.getTextContent();
        txtPrice.setText(strPrice);

    }

    class MyActionNext implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Element eRoot = document.getDocumentElement();
            Element eList = (Element) eRoot.getLastChild();
            if(eList == eBook)
            {
                return;
            }
            eBook=(Element) eBook.getNextSibling();

            String strKind = eBook.getAttribute("kind");
            txtAtt.setText(strKind);

            Element eTitle = (Element) eBook.getFirstChild();
            String strTitle = eTitle.getTextContent();
            txtTitle.setText(strTitle);

            Element eAuthor = (Element) eTitle.getNextSibling();
            String strAuthor = eAuthor.getTextContent();
            txtAuthor.setText(strAuthor);

            Element ePublisher = (Element) eAuthor.getNextSibling();
            String strPublisher = ePublisher.getTextContent();
            txtPublisher.setText(strPublisher);

            Element ePrice = (Element) ePublisher.getNextSibling();
            String strPrice = ePrice.getTextContent();
            txtPrice.setText(strPrice);
        }
    }

    class MyActionPrev implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Element eRoot = document.getDocumentElement();
            Element eFirst = (Element) eRoot.getFirstChild();
            if(eBook == eFirst)
            {
                return;
            }
            eBook=(Element) eBook.getPreviousSibling();
            String strKind = eBook.getAttribute("kind");
            txtAtt.setText(strKind);

            Element eTitle = (Element) eBook.getFirstChild();
            String strTitle = eTitle.getTextContent();
            txtTitle.setText(strTitle);

            Element eAuthor = (Element) eTitle.getNextSibling();
            String strAuthor = eAuthor.getTextContent();
            txtAuthor.setText(strAuthor);

            Element ePublisher = (Element) eAuthor.getNextSibling();
            String strPublisher = ePublisher.getTextContent();
            txtPublisher.setText(strPublisher);

            Element ePrice = (Element) ePublisher.getNextSibling();
            String strPrice = ePrice.getTextContent();
            txtPrice.setText(strPrice);
        }
    }

    class MyActionModify implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Element eRoot = document.getDocumentElement();

            Element eBook = (Element) eRoot.getFirstChild();
            Element eTitle = (Element) eBook.getFirstChild();
            Text tTitle = (Text) eTitle.getFirstChild();
            tTitle.setData("성공을 위한 열쇠");
            System.out.println("수정 후 제목 : " + tTitle.getData());

            // 첫번째 책 종류 수정하기
            eBook.setAttribute("kind", "소설");
            System.out.println("수정 후 종류 : " + eBook.getAttribute("kind"));
        }
    }

    class MyActionRemove implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Element eRoot = document.getDocumentElement();

            // 루트 엘리먼트 첫번째 자식 book 엘리먼트 제거
            Element eBook = (Element) eRoot.getFirstChild();
            System.out.println("제거 전 : " + eBook.getFirstChild().getTextContent());
            eRoot.removeChild(eBook);

            // 제거 후 루트 엘리먼트 첫번째 자식 엘리먼트 얻기
            eBook = (Element) eRoot.getFirstChild();
            Element eTitle = (Element) eBook.getFirstChild();
            Text tTitle = (Text) eTitle.getFirstChild();
            String strTitle = tTitle.getData();

            System.out.println("제거 후 루트 : " + strTitle);
        }
    }

    class MyActionInsert implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Element eRoot = document.getDocumentElement();
            Element eBook = document.createElement("book");

            Element eTitle = document.createElement("title");
            Text tTitle = document.createTextNode("시인과 도둑");
            eTitle.appendChild(tTitle);

            Element eAuthor = document.createElement("author");
            Text tAuthor = document.createTextNode("김도둑");
            eAuthor.appendChild(tAuthor);

            Element ePublisher = document.createElement("publisher");
            Text tPublisher = document.createTextNode("시인출판사");
            ePublisher.appendChild(tPublisher);

            Element ePrice = document.createElement("price");
            Text tPrice = document.createTextNode("9000");
            ePrice.appendChild(tPrice);

            eBook.appendChild(eTitle);
            eBook.appendChild(eAuthor);
            eBook.appendChild(ePublisher);
            eBook.appendChild(ePrice);
            eBook.setAttribute("kind", "소설");

            eRoot.appendChild(eBook);
            System.out.println("추가 성공");
        }

    }
}

public class XMLGUIEX
{
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        new FirmXML();
    }
}