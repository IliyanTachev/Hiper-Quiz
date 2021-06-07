package view;

public class MenuItem {
    private String label;
    private MenuCommand menuCommand;

    public MenuItem(String label, MenuCommand menuCommand) {
        this.label = label;
        this.menuCommand = menuCommand;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuCommand getMenuCommand() {
        return menuCommand;
    }

    public void setMenuCommand(MenuCommand menuCommand) {
        this.menuCommand = menuCommand;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuItem{");
        sb.append("label='").append(label).append('\'');
        sb.append(", menuCommand=").append(menuCommand);
        sb.append('}');
        return sb.toString();
    }
}
