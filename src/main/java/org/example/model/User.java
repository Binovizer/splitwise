package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * The type User
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
@ToString
public class User {
    private String id;
    private String email;
    private String name;
}
