package com.course.server.dto;

import com.course.server.dto.PageDto;

//这个类用于接收courseId
public class ChapterPageDto extends PageDto {
    private String courseId;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
