package com.RecordAndReplay.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse implements Serializable{

	private String db_post;
	private String http_outbound;
}
