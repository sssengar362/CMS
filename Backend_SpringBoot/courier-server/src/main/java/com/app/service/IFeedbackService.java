package com.app.service;

import java.util.List;

import com.app.dto.FeedbackDTO;
import com.app.entities.FeedbackEntity;

public interface IFeedbackService {
	List<FeedbackDTO> getAllFeedbacks();
	//FeedbackEntity insertFeedbackDetails(String message, long id);
	FeedbackEntity insertFeedbackDetails(long customerId, String message);
}
