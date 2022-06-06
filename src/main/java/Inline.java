import java.util.Map;

public class Inline extends Component {
    public Inline(String type, String content) {
        super(type, content);
    }

    public Inline(String type, String content, Map<String, String> options) {
        super(type, content, options);
    }
    public String getComponent() {
        return "{\n" + this.getTypeAsJson() + ",\n" + this.getOptionsAsJson() + ",\n" + this.getContentAsJson() + "\n}";
    }
}
