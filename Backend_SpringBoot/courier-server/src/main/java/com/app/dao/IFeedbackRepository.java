package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.FeedbackEntity;

public interface IFeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
	//get all feedbacks 
		//Done with inherited methods
	//add a feedback
		//Done with inherited methods
}
