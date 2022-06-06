import java.util.ArrayList;
import java.util.List;

public class Block extends Component {
    ArrayList<Component> components;

    public Block(String type) {
        super(type);
        this.components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        this.components.add(component);
        this.components.sort((a, b) -> Integer.compare(Integer.parseInt(a.getOptions().get("index")), Integer.parseInt(b.getOptions().get("index"))));
    }

    public String getComponent() {
        StringBuilder result = new StringBuilder("{\n" + getTypeAsJson());

        if(getOptions() != null) {
            result.append(",\n").append(getOptionsAsJson());
        }

        if(getBody() != null) {
            result.append(",\n").append(getContentAsJson());
        }

        result.append(",\n").append("content: [");
        for (Component component : components) {
            boolean isLast = components.indexOf(component) != components.size() - 1;
            result.append(component.getComponent());
            if(isLast) {
                result.append(",\n");
            }
        }
        result.append("]\n}");
        return result.toString();
    }

}
