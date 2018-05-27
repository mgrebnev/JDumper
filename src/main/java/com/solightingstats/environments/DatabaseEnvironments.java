package com.solightingstats.environments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseEnvironments {
    private String url;
    private String className;
    private String username;
    private String password;
}
