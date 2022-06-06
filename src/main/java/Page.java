public class Paper {
    private Component content;

    private Paper() {
        this.content = new Block("Body", "Dark");
    }

    public void setComponent(Component content) {
        this.content = content;
    }

    public String getComponent() {
        return this.content.getComponent();
    }
}
