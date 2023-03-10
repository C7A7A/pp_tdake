package ai.beans;

public class ColorBean {
    private String foregroundColor;
    private String backgroundColor;
    private String borderVisible;
    public ColorBean() {
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderVisible() {
        return borderVisible;
    }

    public void setBorderVisible(String borderVisible) {
        this.borderVisible = borderVisible;
    }
}