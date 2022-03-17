package net.xi.news.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsQuery {

    private String title;
    private Long typeId;
    private boolean recommend;


}
