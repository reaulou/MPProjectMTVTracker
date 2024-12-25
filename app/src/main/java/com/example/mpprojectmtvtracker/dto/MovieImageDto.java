package com.example.mpprojectmtvtracker.dto;

import com.google.gson.annotations.SerializedName;

public class MovieImageDto {
        @SerializedName("aspect_ratio")
        private Double aspectRatio;

        @SerializedName("height")
        private Integer height;

        @SerializedName("iso_639_1")
        private String iso6391;

        @SerializedName("file_path")
        private String filePath;

        @SerializedName("vote_average")
        private Double voteAverage;

        @SerializedName("vote_count")
        private Integer voteCount;

        @SerializedName("width")
        private Integer width;

        // Getters
        public Double getAspectRatio() {
            return aspectRatio;
        }

        public Integer getHeight() {
            return height;
        }

        public String getIso6391() {
            return iso6391;
        }

        public String getFilePath() {
            return filePath;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public Integer getWidth() {
            return width;
        }

        // Setters
        public void setAspectRatio(Double aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }
}
