package com.sethukumar.OnlineExaminationSystem.dto;

import com.sethukumar.OnlineExaminationSystem.models.QuestionType;

public class ScorePerQuestionDTO {

    private Long questionId;
    private String questionText;
    private Integer maxMarks;
    private Integer score;
    private QuestionType questionType;

    public ScorePerQuestionDTO(
            Long questionId,
            String questionText,
            Integer maxMarks,
            Integer score,
            QuestionType questionType) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.maxMarks = maxMarks;
        this.score = score;
        this.questionType = questionType;
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

    public QuestionType getQuestionType() {
        return questionType;
    }
}
