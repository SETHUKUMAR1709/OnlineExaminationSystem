package com.sethukumar.OnlineExaminationSystem.dto;

public class ScorePerQuestionDTO {

    private Long questionId;
    private String questionText;
    private Integer maxMarks;
    private Integer score;

    public ScorePerQuestionDTO(
            Long questionId,
            String questionText,
            Integer maxMarks,
            Integer score
    ) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.maxMarks = maxMarks;
        this.score = score;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public Integer getScore() {
        return score;
    }
}
