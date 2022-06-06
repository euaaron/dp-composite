import java.util.Map;

public class Page {
    private Block content;

    public Page(Map<String, String> options) {
        this.content = (Block) new Block("page").setOptions(options);
    }

    public void setContent(Block content) {
        this.content = content;
    }

    public Block getContent() {
        return this.content;
    }

    public String getContentAsJson() {
        return this.content.getComponent();
    }
}
