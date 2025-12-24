package com.sethukumar.OnlineExaminationSystem.dto;

public class ExamResultDTO {

    private Long studentId;
    private String studentName;
    private Integer totalScore;

    public ExamResultDTO(
            Long studentId,
            String studentName,
            Integer totalScore
    ) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.totalScore = totalScore;
    }

    public Long getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Integer getTotalScore() { return totalScore; }
}
