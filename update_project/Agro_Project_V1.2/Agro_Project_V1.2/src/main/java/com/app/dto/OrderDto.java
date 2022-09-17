package com.app.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDto {
    
	Long cartId;
	
	List<Long>orderItemsIdArray ;
	

	 
}
