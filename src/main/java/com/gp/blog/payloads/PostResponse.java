package com.gp.blog.payloads;

import java.util.List;

import com.gp.blog.dto.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	List<PostDto>content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalElement;
	private boolean lastPage;
	
	

}
