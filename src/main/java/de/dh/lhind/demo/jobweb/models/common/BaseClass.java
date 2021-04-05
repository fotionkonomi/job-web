package de.dh.lhind.demo.jobweb.models.common;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseClass {
	
	private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Boolean deleted;
    
}
