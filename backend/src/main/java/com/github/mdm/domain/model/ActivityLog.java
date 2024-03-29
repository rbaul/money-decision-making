package com.github.mdm.domain.model;

import com.github.rbaul.spring.boot.activity_log.objects.ActivityLogStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldNameConstants
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class ActivityLog extends AuditableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	@NotNull
	@NotEmpty
	private String action;
	
	@NotNull
	private Date time;
	
	@NotNull
	private ActivityLogStatus status;
}
