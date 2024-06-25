package com.bce.api.endpoints;

public class Routes {

	public static String base_url="http://localhost:8080";
	
	public static String emp_get_all_url=base_url+"/employee/fetch/all";
	
	public static String emp_post_all_url=base_url+"/employee";

	public static String emp_get_by_id_uri=base_url+"/employee/fetch/{id}";
	public static String emp_delete_by_id_uri=base_url+"/employee/delete/{id}";
	public static String emp_delete_all_id_uri=base_url+"/employee/delete/all";

	
}
