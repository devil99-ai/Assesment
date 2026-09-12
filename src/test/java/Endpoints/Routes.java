package Endpoints;
/*	
[ Base URL: petstore.swagger.io/v2 ]
POST	/user/createWithList	Creates list of users with given input array
GET	/user/{username}	Get user by user name
PUT	/user/{username}	Updated user
DELETE	/user/{username}	Delete user
GET	/user/login	Logs user into the system
GET	/user/logout	Logs out current logged in user session
POST	/user/createWithArray	Creates list of users with given input array
POST /user 	Create user*/

public class Routes {
public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";
	public static String post_with_array_url=base_url+"/user/createWithArray";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	

}
