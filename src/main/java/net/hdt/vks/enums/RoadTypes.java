package net.hdt.vks.enums;

import net.minecraft.util.IStringSerializable;

public enum RoadTypes implements IStringSerializable {

    WHITE_MIDDLE_LINE_VERTICAL_STRAIGHT(0, "white_middle_line_vertical_straight"),
    WHITE_MIDDLE_LINE_HORIZONTAL_STRAIGHT(1, "white_middle_line_horizontal_straight"),
    WHITE_MIDDLE_LINE_CORNER_LEFT(2, "white_middle_line_left_corner"),
    WHITE_MIDDLE_LINE_CORNER_RIGHT(3, "white_middle_line_right_corner"),
    WHITE_MIDDLE_LINE_CORNER_LEFT_FLIPPED(4, "white_middle_line_left_corner_flipped"),
    WHITE_MIDDLE_LINE_CORNER_RIGHT_FLIPPED(5, "white_middle_line_right_corner_flipped"),
    YELLOW_MIDDLE_LINE_VERTICAL_STRAIGHT(6, "yellow_middle_line_vertical_straight"),
    YELLOW_MIDDLE_LINE_HORIZONTAL_STRAIGHT(7, "yellow_middle_line_horizontal_straight"),
    YELLOW_MIDDLE_LINE_CORNER_LEFT(8, "yellow_middle_line_left_corner"),
    YELLOW_MIDDLE_LINE_CORNER_RIGHT(9, "yellow_middle_line_right_corner"),
    YELLOW_MIDDLE_LINE_CORNER_LEFT_FLIPPED(10, "yellow_middle_line_left_corner_flipped"),
    YELLOW_MIDDLE_LINE_CORNER_RIGHT_FLIPPED(11, "yellow_middle_line_right_corner_flipped"),
    WHITE_OUTER_LINE_VERTICAL_STRAIGHT(12, "white_outer_line_vertical_straight"),
    WHITE_OUTER_LINE_HORIZONTAL_STRAIGHT(13, "white_outer_line_horizontal_straight"),
    WHITE_OUTER_LINE_VERTICAL_STRAIGHT_FLIPPED(14, "white_outer_line_vertical_straight_flipped"),
    WHITE_OUTER_LINE_HORIZONTAL_STRAIGHT_FLIPPED(15, "white_outer_line_horizontal_straight_flipped"),
    WHITE_OUTER_LINE_CORNER_LEFT(16, "white_outer_line_left_corner"),
    WHITE_OUTER_LINE_CORNER_RIGHT(17, "white_outer_line_right_corner"),
    WHITE_OUTER_LINE_CORNER_LEFT_FLIPPED(18, "white_outer_line_left_corner_flipped"),
    WHITE_OUTER_LINE_CORNER_RIGHT_FLIPPED(19, "white_outer_line_right_corner_flipped"),
    YELLOW_OUTER_LINE_VERTICAL_STRAIGHT(20, "yellow_outer_line_vertical_straight"),
    YELLOW_OUTER_LINE_HORIZONTAL_STRAIGHT(21, "yellow_outer_line_horizontal_straight"),
    YELLOW_OUTER_LINE_VERTICAL_STRAIGHT_FLIPPED(22, "yellow_outer_line_vertical_straight_flipped"),
    YELLOW_OUTER_LINE_HORIZONTAL_STRAIGHT_FLIPPED(23, "yellow_outer_line_horizontal_straight_flipped"),
    YELLOW_OUTER_LINE_CORNER_LEFT(24, "yellow_outer_line_left_corner"),
    YELLOW_OUTER_LINE_CORNER_RIGHT(25, "yellow_outer_line_right_corner"),
    YELLOW_OUTER_LINE_CORNER_LEFT_FLIPPED(26, "yellow_outer_line_left_corner_flipped"),
    YELLOW_OUTER_LINE_CORNER_RIGHT_FLIPPED(27, "yellow_outer_line_right_corner_flipped");

    private String name;
    private int ID;

    RoadTypes(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

}
