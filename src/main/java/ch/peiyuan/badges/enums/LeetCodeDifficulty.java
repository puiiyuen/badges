package ch.peiyuan.badges.enums;

public enum LeetCodeDifficulty {
    ALL(0,"all", "blue"),
    EASY(1, "easy", "brightgreen"),
    MEDIUM(2, "medium", "orange"),
    HARD(3, "hard", "red");

    private final int order;
    private final String difficulty;
    private final String color;

    LeetCodeDifficulty(int order, String difficulty, String color) {
        this.order = order;
        this.difficulty = difficulty;
        this.color = color;
    }

    public int getOrder() {
        return order;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getColor() {
        return color;
    }
}
