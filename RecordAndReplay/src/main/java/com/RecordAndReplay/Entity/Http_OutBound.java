package com.RecordAndReplay.Entity;

import lombok.Data;

@Data
public class Http_OutBound {
	private String abbreviation;
	private String client_ip;
	private String datetime;
	private String day_of_week;
	private String day_of_year;
	private String dst;
	private String dst_from;
	private String dst_offset;
	private String dst_until;
	private String raw_offset;
	private String timezone;
	private String unixtime;
	private String utc_datetime;
	private String utc_offset;
	private String week_number;
}
