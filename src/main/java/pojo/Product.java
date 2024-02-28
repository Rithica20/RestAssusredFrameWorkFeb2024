package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
//    {
//        "title" : "test product1",
//            "price" : 13.5,
//            "description" : "lorem ipsum set",
//            "image" : "https://i.pravatar.cc",
//            "category":"electronic"
//    }
    private String title;
    private float price;
    private String description;
    private String image;

    private String category;
}
