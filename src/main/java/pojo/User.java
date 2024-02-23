package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

//	{
//	    "name" : "sahil kumar",
//	    "email" : "{{$randomEmail}}",
//	    "gender" : "male",
//	    "status" : "active"
//	}

    private String name;
    private String email;
    private String gender;
    private String status;
}
