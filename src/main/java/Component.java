import java.util.HashMap;
import java.util.Map;

public abstract class Component implements Cloneable {
    private String type;
    private String content;
    private Map<String, String> options;

    public Component(String type) {
        this.type = type;
        this.content = null;
        this.options = new HashMap<String, String>();
        this.options.put("index", "0");
    }
    public Component(String type, String content){
        this.type = type;
        this.content = content;
        this.options = new HashMap<String, String>();
        this.options.put("index", "0");
    }

    public Component(String type, String content, Map<String, String> options){
        this.type = type;
        this.content = content;
        this.options = new HashMap<String, String>();
        this.options.put("index", "0");
        this.options.putAll(options);
    }

    protected String getOptionsAsJson() {
        StringBuilder newOptions = new StringBuilder("options: {");
        for (Map.Entry<String, String> entry : this.options.entrySet()) {
            newOptions.append("\n").append(entry.getKey()).append(": '").append(entry.getValue()).append("',");
        }
        newOptions.append("\n}");
        return newOptions.toString();
    }

    protected String getContentAsJson() {
        return "content: '" + this.content + "'";
    }

    protected String getTypeAsJson() {
        return "type: " + "'" + this.type + "'";
    }

    public Component setOptions(Map<String, String> options) {
        this.options = new HashMap<String, String>();
        this.options.put("index", "0");
        this.options.putAll(options);
        return this;
    }

    public Component setContent(String content) {
        this.content = content;
        return this;
    }

    public Component setType(String type) {
        this.type = type;
        return this;
    }

    public String getBody() {
        return this.content;
    }

    public Map<String, String> getOptions() {
        return this.options;
    }

    public String getType() {
        return type;
    }

    public abstract String getComponent();

    @Override
    public Component clone() {
        try {
            return (Component) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
