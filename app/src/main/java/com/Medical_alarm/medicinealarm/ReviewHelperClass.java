package com.example.medicinealarm;

public class ReviewHelperClass {
    String reviewByUser, ratingByUser;

    public ReviewHelperClass() {
    }

    public ReviewHelperClass(String reviewByUser, String ratingByUser) {
        this.reviewByUser = reviewByUser;
        this.ratingByUser = ratingByUser;
    }

    public String getReviewByUser() {
        return reviewByUser;
    }

    public void setReviewByUser(String reviewByUser) {
        this.reviewByUser = reviewByUser;
    }

    public String getRatingByUser() {
        return ratingByUser;
    }

    public void setRatingByUser(String ratingByUser) {
        this.ratingByUser = ratingByUser;
    }
}

