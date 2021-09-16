package com.github.mdm.domain.repositories;

import com.github.mdm.domain.model.ActivityLog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long>, JpaSpecificationExecutor<ActivityLog> {
	static Specification<ActivityLog> filterEveryWay(String filterString) {
		if (ObjectUtils.isEmpty(filterString)) {
			return null;
		}
		return (root, query, builder) -> {
			String pattern = "%" + filterString + "%";
			return builder
					.or(builder.like(builder.lower(root.get(ActivityLog.Fields.action)), pattern),
							builder.like(builder.lower(root.get(ActivityLog.Fields.username)), pattern));
		};
	}
	
}
