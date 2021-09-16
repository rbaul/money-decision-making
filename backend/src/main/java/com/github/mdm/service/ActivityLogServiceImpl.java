package com.github.mdm.service;

import com.github.mdm.domain.model.ActivityLog;
import com.github.mdm.domain.repositories.ActivityLogRepository;
import com.github.mdm.web.dtos.ActivityLogDto;
import com.github.rbaul.spring.boot.activity_log.ActivityLogReceiver;
import com.github.rbaul.spring.boot.activity_log.objects.ActivityLogObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogReceiver {
	
	private final ActivityLogRepository activityLogRepository;
	
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public void receive(ActivityLogObject activityLogObject) {
		ActivityLog activityLog = modelMapper.map(activityLogObject, ActivityLog.class);
		activityLogRepository.save(activityLog);
	}
	
	@Transactional(readOnly = true)
	public Page<ActivityLogDto> getPageable(String filter, Pageable pageable) {
		return activityLogRepository.findAll(ActivityLogRepository.filterEveryWay(filter), pageable)
				.map(activityLog -> modelMapper.map(activityLog, ActivityLogDto.class));
	}
	
	@Transactional(readOnly = true)
	public ActivityLogDto get(Long productId) {
		ActivityLog activityLog = getActivityLogById(productId);
		return modelMapper.map(activityLog, ActivityLogDto.class);
	}
	
	@Transactional
	public void delete(Long activityLogId) {
		if (!activityLogRepository.existsById(activityLogId)) {
			throw new EmptyResultDataAccessException("No found activity log with id: " + activityLogId, 1);
		}
		activityLogRepository.deleteById(activityLogId);
	}
	
	private ActivityLog getActivityLogById(Long activityLogId) {
		return activityLogRepository.findById(activityLogId)
				.orElseThrow(() -> new EmptyResultDataAccessException("No found activity log with id: " + activityLogId, 1));
	}
}
