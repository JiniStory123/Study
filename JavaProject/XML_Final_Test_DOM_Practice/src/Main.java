import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class DOM
{
    DOM() throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("bml.xml");

        Element eRoot = document.getDocumentElement();

        Element eBook = (Element) eRoot.getFirstChild();

        Element eTitle = (Element) eBook.getFirstChild();
        String strTitle = eTitle.getTextContent();
        String strAttKind = eBook.getAttribute("kind");
        System.out.println("책 제목 : " + strTitle);
        System.out.println("책 속성 : " + strAttKind);

        // 다음 요소로 이동하기
        eBook = (Element) eBook.getNextSibling();

        eTitle = (Element) eBook.getFirstChild();
        strTitle = eTitle.getTextContent();
        strAttKind = eBook.getAttribute("kind");
        System.out.println("책 제목 : " + strTitle);
        System.out.println("책 속성 : " + strAttKind);

        // 첫번째 책 요소 수정하기
        eBook = (Element) eRoot.getFirstChild();

        eTitle = (Element) eBook.getFirstChild();
        Text txtTitle = (Text) eTitle.getFirstChild();
        txtTitle.setData("성공을 위한 열쇠");
        System.out.println("수정 후 제목 : " + txtTitle.getData());

        // 제거
        eBook = (Element) eRoot.getFirstChild();
        System.out.println("제거 한 책 제목 : " + eBook.getFirstChild().getTextContent());

        eRoot.removeChild(eBook);
        // 제거 후 첫번째 자식 요소 얻기
        eBook = (Element) eRoot.getFirstChild();
        eTitle = (Element) eBook.getFirstChild();
        strTitle = eTitle.getTextContent();
        strAttKind = eBook.getAttribute("kind");
        System.out.println("책 제목 : " + strTitle);
        System.out.println("책 속성 : " + strAttKind);

        // 책 추가
        eBook = document.createElement("book");
        eTitle = document.createElement("title");
        txtTitle = document.createTextNode("시인과 도둑");
        eTitle.appendChild(txtTitle);

        eBook.appendChild(eTitle);
        eBook.setAttribute("kind", "소설");
        eRoot.appendChild(eBook);

        eBook = (Element) eRoot.getLastChild();
        eTitle = (Element) eBook.getFirstChild();
        System.out.println("추가 한 책 제목 : " + eTitle.getTextContent());

        // 한꺼번에 출력
        NodeList nodeTitle = eRoot.getElementsByTagName("title");
        for(int i=0; i<nodeTitle.getLength(); i++)
        {
            eTitle = (Element) nodeTitle.item(i);
            System.out.println("책 제목 : " + eTitle.getTextContent());
        }
    }
}
public class Main
{
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException
    {
        new DOM();
    }
}