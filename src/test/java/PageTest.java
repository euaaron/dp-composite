import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {
    Page newPage;
    Map<String, String> pageOptions = new HashMap<>();

    @BeforeEach
    void setUp() {
        pageOptions.put("theme", "dark");
        newPage = new Page(pageOptions);
    }

    @AfterEach
    void Clear() {
        newPage = null;
        pageOptions = null;
    }

    @Test
    void ShouldCreateAnEmptyPage() {
        assertTrue(newPage.getContentAsJson().contains("type: 'page'"));
    }

    @Test
    void ShouldCreateAPageWithoutContent() {
        assertTrue(newPage.getContentAsJson().contains("content: []"));
    }

    @Test
    void ShouldUpdatePageOptions() {
        pageOptions.put("fontFamily", "Roboto");
        newPage.getContent().setOptions(pageOptions);
        assertTrue(newPage.getContentAsJson().contains("fontFamily: 'Roboto'"));
    }

    @Test
    void ShouldAddElementsToPage() {
        newPage.getContent().addComponent(new Inline("h1", "Hello World"));
        assertTrue(newPage.getContentAsJson().contains("content: [{\ntype: 'h1',\noptions: {\nindex: '0',\n},\ncontent: 'Hello World'\n}]"));
    }

    @Test
    void ShouldAddMultipleElementsToPage() {
        newPage.getContent().addComponent(new Inline("h1", "Hello World"));
        newPage.getContent().addComponent(new Inline("strong", "This is a strong text"));
        assertTrue(newPage.getContentAsJson().contains("type: 'strong',\noptions: {\nindex: '0',\n},\ncontent: 'This is a strong text'"));
    }

    @Test
    void ShouldBeAbleToSortContentFromTheValueOfTheIndexOption() {
        Map<String, String> options = new HashMap<>();

        options.put("index", "2");
        newPage.getContent().addComponent(new Inline("h1", "Hello World", options));

        options.put("index", "1");
        newPage.getContent().addComponent(new Inline("strong", "This is a strong text", options));
        newPage.getContent().addComponent(new Inline("p", "This is a paragraph"));

        assertEquals("{\ntype: 'h1',\noptions: {\nindex: '2',\n},\ncontent: 'Hello World'\n}", newPage.getContent().components.get(2).getComponent());
    }

    @Test
    void ShouldBeAbleToAddInlineComponentsWithinABlock() {
        Block section = new Block("section");
        Map<String, String> options = new HashMap<>();

        options.put("index", "2");
        section.addComponent(new Inline("h1", "Hello World", options));
        options.put("index", "1");

        section.addComponent(new Inline("strong", "This is a strong text", options));
        section.addComponent(new Inline("p", "This is a paragraph"));

        newPage.getContent().addComponent(section);

        assertTrue(newPage.getContent().getComponent().contains("type: 'section'"));
    }

    @Test
    void ShouldBeAbleToAddMoreThanOneBlockElement() {
        Block section = new Block("section");
        Map<String, String> options = new HashMap<>();
        options.put("index", "2");
        section.addComponent(new Inline("h1", "Hello World", options));
        options.put("index", "1");
        section.addComponent(new Inline("strong", "This is a strong text", options));
        section.addComponent(new Inline("p", "This is a paragraph"));

        Block header = (Block) section.clone().setType("header");

        newPage.getContent().addComponent(header);
        newPage.getContent().addComponent(section);

        assertTrue(newPage.getContent().getComponent().contains("type: 'header'"));
    }
}