package ch.fdsgn.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "app")
@Getter @Setter
public class MainInfoDto {

    private String env;
    private String info;
    private String name;
    private String description;
    private Map<String, String> contacts;

}
