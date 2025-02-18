package chapter22;

public enum Color {

    WHITE("未被发现待被发现"),
    GRAY("被发现待完成访问"),
    BLACK("访问完成");

    private String description;

    Color(String description) {
        this.description = description;
    }

}
